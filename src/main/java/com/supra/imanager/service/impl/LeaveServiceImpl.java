package com.supra.imanager.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supra.imanager.bean.AppliedLeaveDetails;
import com.supra.imanager.bean.LeaveInfo;
import com.supra.imanager.bean.LeaveInput;
import com.supra.imanager.bean.LeaveSummary;
import com.supra.imanager.dto.SupraLeaveRequest;
import com.supra.imanager.dto.SupraLeaveRequestId;
import com.supra.imanager.dto.SupraLeaveRequestMapping;
import com.supra.imanager.dto.SupraLeaveRequestMappingId;
import com.supra.imanager.dto.SupraLeaveUserBalance;
import com.supra.imanager.repository.SupraLeaveRequestMappingRepository;
import com.supra.imanager.repository.SupraLeaveRequestRepository;
import com.supra.imanager.repository.SupraLeaveTypeRepository;
import com.supra.imanager.repository.SupraLeaveUserBalanceRepository;
import com.supra.imanager.service.LeaveService;
import com.supra.imanager.utilities.ApplicationConstants;
import com.supra.imanager.utilities.ApplicationUtilities;


@Service
public class LeaveServiceImpl implements LeaveService{

	@Autowired
	private SupraLeaveRequestRepository supraLeaveRequestRepository;

	@Autowired
	private SupraLeaveTypeRepository supraLeaveTypeRepository;

	@Autowired
	private SupraLeaveUserBalanceRepository supraLeaveUserBalanceRepository;
	
	@Autowired
	private SupraLeaveRequestMappingRepository supraLeaveRequestMappingRepository;
	
	
	
	
	@Override
	public LeaveSummary leaveBalance(String username) throws Exception {
		LeaveSummary leaveSummary = null;
		try {
			//User u =  userRepository.findByPrimaryemail(email);

			if(null == username) {
				throw new Exception("User not found");
			}
			else {
				String requestNum = supraLeaveRequestRepository.getRequestNumber(String.valueOf(username));	

				if(null == requestNum || "".equalsIgnoreCase(requestNum)) {
					requestNum = "LEAVE"+ username +"-0001";
				}
				else if(requestNum.contains("LEAVE") || requestNum.contains("leave")){
					Integer num = new Integer(requestNum.substring(requestNum.lastIndexOf("-") + 1));
					num += 1; // num = num+1
					DecimalFormat dcfm = new DecimalFormat("0000");
					requestNum = "LEAVE" +username+"-"+ dcfm.format(num);
				}

				leaveSummary = new LeaveSummary();
				List<Object[]> listBalance =  supraLeaveTypeRepository.leaveBalance(String.valueOf(username));
				String flag = null; 
				if(null != listBalance && listBalance.size() >= 0) {
					Iterator<Object[]> iterator = listBalance.iterator();
					List<LeaveInfo> leaveInfos = new ArrayList<>();
					while (iterator.hasNext()) {
						Object[] obj = (Object[]) iterator.next();
						LeaveInfo leaveInfo = 
						new LeaveInfo(String.valueOf(obj[0]),String.valueOf(obj[1]),Float.valueOf(String.valueOf(obj[2])));
						flag = String.valueOf(obj[3]);
						leaveInfos.add(leaveInfo);
					}
					leaveSummary.setFlag(flag);
					leaveSummary.setBalanceLeaves(leaveInfos);
					leaveSummary.setRequestNumber(requestNum);

				} else {

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return leaveSummary;
	}


	@Override
	public int changeUserLeaveBalanceUpdationFlag(String userId, String flag) {
		return supraLeaveUserBalanceRepository.changeUserLeaveBalanceUpdationFlag(userId, flag);
	}


	@Override
	public int updateLMSRemarkAndStatus(String reqNumber, String approveFlag, String pendingstatus, String remark) {
		// TODO Auto-generated method stub

		return supraLeaveRequestRepository.updateLMSRemarkAndStatus( reqNumber,approveFlag,  pendingstatus,  remark);
	}


	public boolean createNewLeaveRequest(LeaveInput leaveInput, String username) throws ParseException {
		
		Date currentDate = ApplicationUtilities.getCurrentDate(null);
		double totalDays = 0;
		List<SupraLeaveRequestMapping> leaveRequestMappings = new ArrayList<>();
		
		Iterator<AppliedLeaveDetails> appliedLeaveIterator = leaveInput.getAppliedLeaves().iterator();
		Map<Long, Float> LEAVE_CODE_COUNT = new HashMap<>();
		while (appliedLeaveIterator.hasNext()) {
			AppliedLeaveDetails appliedLeaveDetails = (AppliedLeaveDetails) appliedLeaveIterator.next();
			
			totalDays += ApplicationUtilities.getTotalDays(appliedLeaveDetails.getStartDate()
					, appliedLeaveDetails.getEndDate(), appliedLeaveDetails.getFullOrHalfDay());
			
			//Prepare supra leave request mapping objects and add into a list
			SupraLeaveRequestMappingId leaveRequestMappingId = new SupraLeaveRequestMappingId(leaveInput.getRequestNumber(),
					ApplicationConstants.LEAVE_CODE_MAP.get(appliedLeaveDetails.getLeaveType()).longValue(), username, 
					ApplicationUtilities.getCurrentDate(appliedLeaveDetails.getStartDate()), 
					ApplicationUtilities.getCurrentDate(appliedLeaveDetails.getEndDate()), 
					username, currentDate, appliedLeaveDetails.getPurpose(), 
					ApplicationUtilities.getTotalDays(appliedLeaveDetails.getStartDate(), appliedLeaveDetails.getEndDate(), appliedLeaveDetails.getFullOrHalfDay()),
					appliedLeaveDetails.getFullOrHalfDay());
			SupraLeaveRequestMapping leaveRequestMapping = new SupraLeaveRequestMapping(leaveRequestMappingId);
			leaveRequestMappings.add(leaveRequestMapping);
			
			LEAVE_CODE_COUNT.put(ApplicationConstants.LEAVE_CODE_MAP.get(appliedLeaveDetails.getLeaveType()).longValue(), appliedLeaveDetails.getDays());
		}
		
		SupraLeaveRequestId leaveRequestId = new SupraLeaveRequestId(leaveInput.getRequestNumber(), String.valueOf(totalDays),
				username, currentDate, username, "NA", "Not Applicable", "Approval Pending", username, 
				ApplicationUtilities.dateFormat.format(currentDate), ApplicationUtilities.getCurrentYear());
		SupraLeaveRequest leaveRequest = new SupraLeaveRequest(leaveRequestId);

		
		
		//saving logic starts here
		boolean savingLeaveRequest = false;
		try {
			//saving supra_leave_request
			leaveRequest = supraLeaveRequestRepository.save(leaveRequest);
			savingLeaveRequest = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		boolean resultOfLeaveBalanceupdate = false;
		if(savingLeaveRequest) {
			//if supra_leave_request is saved
			boolean saveMappings = false;
			try {
				//saving supra_leave_request_mapping
				supraLeaveRequestMappingRepository.save(leaveRequestMappings);
				saveMappings = true;
			} catch (Exception e) {
				e.printStackTrace();
				//if error occurred, delete row in supra_leave_request
				supraLeaveRequestRepository.delete(leaveRequest);
			}
			if(saveMappings) {
				//if supra_leave_request_mapping is saved, update leave balance of user from LEAVE_CODE_COUNT
				List<SupraLeaveUserBalance> leaveUserBalances = supraLeaveUserBalanceRepository.findByIdUsername(username);
				Iterator<SupraLeaveUserBalance> iterator = leaveUserBalances.iterator();
				try {
					while (iterator.hasNext()) {
						SupraLeaveUserBalance supraLeaveUserBalance = (SupraLeaveUserBalance) iterator.next();
						if(null != supraLeaveUserBalance) {
							if(null != LEAVE_CODE_COUNT.get(supraLeaveUserBalance.getId().getLeavecode())) {
								float balanceLeaves = Float.valueOf(supraLeaveUserBalance.getId().getLeavebalance()) 
										- LEAVE_CODE_COUNT.get(supraLeaveUserBalance.getId().getLeavecode());
								supraLeaveUserBalanceRepository.updateLeaveBalance(String.valueOf(balanceLeaves), username, supraLeaveUserBalance.getId().getLeavecode());
							}
						}
					}
					resultOfLeaveBalanceupdate = true;
				}
				catch (Exception e) {
					e.printStackTrace();
					//if any error occurred, delete rows created in supra_leave_request and supra_leave_request_mapping
					supraLeaveRequestRepository.delete(leaveRequest);
					supraLeaveRequestMappingRepository.delete(leaveRequestMappings);
				}
			}
		}
		return resultOfLeaveBalanceupdate;
	}


}
