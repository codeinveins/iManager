package com.supra.imanager.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supra.imanager.bean.LeaveInput;
import com.supra.imanager.bean.LeaveSummary;
import com.supra.imanager.bean.Response;
import com.supra.imanager.bean.TrackLeave;
import com.supra.imanager.dto.RestToken;
import com.supra.imanager.repository.RestTokenRepository;
import com.supra.imanager.service.LeaveService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "Apis conatining leave related operations", description = " ", produces = "application/json", tags = {
"Leave Management" })
public class LeaveController {

	@Autowired
	private LeaveService leaveService;

	@Autowired
	private RestTokenRepository restTokenRepository;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, false));
	}
	
	@GetMapping(value = "/v1/leaveBalance")
	public ResponseEntity<Response> leaveBalance(HttpServletRequest request, HttpSession session) {
		LeaveSummary leaveSummary = null;
		Response restResponse = new Response();
		try {
			String userId = getUsernameFromToken(request);
			leaveSummary = leaveService.leaveBalance(userId);
			restResponse.setResponseCode(HttpStatus.OK.value());
			restResponse.setResponseMessage("Success");
			restResponse.setResponseData(leaveSummary);
			return ResponseEntity.ok().body(restResponse);

		} catch (Exception e) {
			e.printStackTrace();
			restResponse.setResponseCode(HttpStatus.NOT_FOUND.value());
			restResponse.setResponseMessage(e.getMessage());
			restResponse.setResponseData(leaveSummary);
			return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(value = "/v1/agree")
	public ResponseEntity<Response> aggree(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "status", required = true) String status) {

		int result=0;
		try {

			String userId = getUsernameFromToken(request);
			result = leaveService.changeUserLeaveBalanceUpdationFlag(userId, status);
			Response restResponse = new Response();

			if(result >= 1) {
				restResponse.setResponseCode(HttpStatus.OK.value());
				restResponse.setResponseMessage(status.equals("N") ? "Success. HR Will be in contact to you" : null);
				restResponse.setResponseData(null); 
				return ResponseEntity.ok().body(restResponse);
			}
			else {
				restResponse.setResponseCode(HttpStatus.NOT_MODIFIED.value());
				restResponse.setResponseMessage("Error in updation. Contact Administrator");
				restResponse.setResponseData(null); 
				return ResponseEntity.ok().body(restResponse);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Response restResponse = new Response();
			restResponse.setResponseCode(HttpStatus.NOT_FOUND.value());
			restResponse.setResponseMessage(e.getMessage());
			restResponse.setResponseData(null);
			return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/v1/submit")
	public ResponseEntity<Response> submit(HttpServletRequest request, HttpSession session, 
			@RequestBody LeaveInput leaveInput) {

		String userId = getUsernameFromToken(request);
		boolean resultOfLeaveBalanceUpdate = false;
		try {
			resultOfLeaveBalanceUpdate = leaveService.createNewLeaveRequest(leaveInput, userId);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Response restResponse = new Response();
		restResponse.setResponseCode(HttpStatus.OK.value());
		restResponse.setResponseMessage(resultOfLeaveBalanceUpdate ? "Leave applied successfully." : "Some error occurred whie applying leaves.");
		restResponse.setResponseData(null);
		return ResponseEntity.ok().body(restResponse);
	}

	
	
	@GetMapping(value = "/v1/trackLeave")
	public ResponseEntity trackLeave(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "whome", required = true) String whome) {
		Response restResponse = new Response();
		restResponse.setResponseCode(HttpStatus.OK.value());
		restResponse.setResponseMessage("Success");
		restResponse.setResponseData(new TrackLeave());
		return ResponseEntity.ok().body(restResponse);

	}

	@PostMapping(value = "/v1/approveOrReject")
	public ResponseEntity acceptOrReject(HttpServletRequest request, HttpSession session,
			@RequestParam(value="reqNumber", required=true)String reqNumber,
			@RequestParam(value="approveFlag", required=true)String approveFlag,
			@RequestParam(value="remark", required=true)String remark)
 {
		
		JSONArray jsonarr = new JSONArray();
		int statusString;
		//System.out.println(reqNumber+approveFlag+reqStatus+remark);
		try{
			statusString = leaveService.updateLMSRemarkAndStatus(reqNumber,approveFlag,"Pending",remark);
			jsonarr.put(statusString);
		}catch(Exception e){
			e.printStackTrace();
		}
		//return jsonarr.toString();
		
		/*
		LeaveApprovedOrRejected leaveApprovedOrRejected1 = new LeaveApprovedOrRejected();

		leaveApprovedOrRejected1.getApproveFlag();
		leaveApprovedOrRejected1.getRemarks();
		leaveApprovedOrRejected1.getRequestNumber();
		
		*/
		
		Response restResponse = new Response();
		restResponse.setResponseCode(HttpStatus.OK.value());
		restResponse.setResponseMessage("Success");
		restResponse.setResponseData(null);
		return ResponseEntity.ok().body(restResponse);
	}

	private String getUsernameFromToken(HttpServletRequest request) {
		String accessToken = request.getHeader("token");
		RestToken restToken = restTokenRepository.findByToken(accessToken);
		return restToken.getUserName();
	}
}


/*
 * @GetMapping(value="/v1/approveLeaveOthers") public ResponseEntity
 * approveLeave(HttpServletRequest request, HttpSession session,
 * 
 * @RequestParam(value = "email", required = true) String usermail) { Response
 * restResponse = new Response();
 * restResponse.setResponseCode(HttpStatus.OK.value());
 * restResponse.setResponseMessage("Success"); restResponse.setResponseData(new
 * TrackLeave()); return ResponseEntity.ok().body(restResponse); }
 */

/*
 * @PostMapping(value="/v1/addRemark") public ResponseEntity
 * addRemark(HttpServletRequest request, HttpSession session,
 * 
 * @RequestParam(value = "usermail", required = true) String usermail,
 * 
 * @RequestParam(value = "remark", required = true) String remark) { Response
 * restResponse = new Response();
 * restResponse.setResponseCode(HttpStatus.OK.value());
 * restResponse.setResponseMessage("Success. Remark has been Saved.");
 * restResponse.setResponseData(null); return
 * ResponseEntity.ok().body(restResponse); }
 */