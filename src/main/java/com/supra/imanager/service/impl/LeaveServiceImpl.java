package com.supra.imanager.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supra.imanager.bean.LeaveInfo;
import com.supra.imanager.bean.LeaveSummary;
import com.supra.imanager.dto.User;
import com.supra.imanager.repository.SupraLeaveRequestRepository;
import com.supra.imanager.repository.SupraLeaveTypeRepository;
import com.supra.imanager.repository.SupraLeaveUserBalanceRepository;
import com.supra.imanager.repository.UserRepository;
import com.supra.imanager.service.LeaveService;


@Service
public class LeaveServiceImpl implements LeaveService{

	@Autowired
	private SupraLeaveRequestRepository supraLeaveRequestRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SupraLeaveTypeRepository supraLeaveTypeRepository;

	@Autowired
	private SupraLeaveUserBalanceRepository supraLeaveUserBalanceRepository;
	
	
	@Override
	public LeaveSummary leaveBalance(String email) throws Exception {
		LeaveSummary leaveSummary = null;
		try {
			User u =  userRepository.findByPrimaryemail(email);

			if(null == u) {
				throw new Exception("User not found");
			}
			else {
				String requestNum = supraLeaveRequestRepository.getRequestNumber(String.valueOf(u.getUsername()));	
/*
				if(null != reqNumber || reqNumber.size()>0) {
					Object[] a = reqNumber.get(0);
					 = (String) a[0];
				}*/

				if(null == requestNum || "".equalsIgnoreCase(requestNum)) {
					requestNum = "LEAVE"+ u.getUsername() +"-0001";
				}
				else if(requestNum.contains("LEAVE") || requestNum.contains("leave")){
					Integer num = new Integer(requestNum.substring(requestNum.lastIndexOf("-") + 1));
					num += 1; // num = num+1
					DecimalFormat dcfm = new DecimalFormat("0000");
					requestNum = "LEAVE" +u.getUsername()+"-"+ dcfm.format(num);
				}

				leaveSummary = new LeaveSummary();
				List<Object[]> listBalance =  supraLeaveTypeRepository.leaveBalance(String.valueOf(u.getUsername()));
				String flag = null; 
				if(null != listBalance || listBalance.size() >= 0) {
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
	public int changeUserLeaveBalanceUpdationFlag(int uname, String flag) {
		return supraLeaveUserBalanceRepository.changeUserLeaveBalanceUpdationFlag(uname, flag);
	}







}
