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

@RestController
/*@Api(value = "Apis conatining leave related operations", description = " ", produces = "application/json", tags = {
"Leave Management" })
*/
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

	
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/v1/trackLeave")
	public ResponseEntity trackLeave(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "whom", required = true) String whom) {
		
		
		String userId = getUsernameFromToken(request);
		
		TrackLeave trackLeaves  = leaveService.trackLeave(userId,whom);
		
		if(null !=trackLeaves) {
			Response restResponse = new Response();
			restResponse.setResponseCode(HttpStatus.OK.value());
			restResponse.setResponseMessage("Success");
			restResponse.setResponseData(trackLeaves);
			return ResponseEntity.ok().body(restResponse);
	
		}
		else {
			Response restResponse = new Response();
			restResponse.setResponseCode(HttpStatus.NOT_FOUND.value());
			restResponse.setResponseData(null);
			return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
	
		}
		
	}

	
	@PostMapping(value = "/v1/approveOrReject")
	public ResponseEntity acceptOrReject(HttpServletRequest request, HttpSession session,
			@RequestParam(value="requsetNumberdata", required=true)String requsetNumberdata,
			@RequestParam(value="approveFlag", required=true)String approveFlag,
			@RequestParam(value="remark", required=true)String remark)
 {
		
		String userId = getUsernameFromToken(request);
		
		JSONArray jsonarr = new JSONArray();
		int statusString = 0;
		try{
			statusString = leaveService.updateLMSRemarkAndStatus(requsetNumberdata,approveFlag,"Pending",remark);
			jsonarr.put(statusString);
		}catch(Exception e){
			e.printStackTrace();
		}
	
		if(statusString>=0) {
		Response restResponse = new Response();
		restResponse.setResponseCode(HttpStatus.OK.value());
		restResponse.setResponseMessage("Success");
		restResponse.setResponseData(null);
		return ResponseEntity.ok().body(restResponse);
		}
		else {
			Response restResponse = new Response();
			restResponse.setResponseCode(HttpStatus.NOT_FOUND.value());
			restResponse.setResponseMessage("No Success");
			restResponse.setResponseData(null);
			return ResponseEntity.ok().body(restResponse);
		}
	}

	
	
	
	
	
	private String getUsernameFromToken(HttpServletRequest request) {
		String accessToken = request.getHeader("token");
		RestToken restToken = restTokenRepository.findByToken(accessToken);
		return restToken.getUserName();
	}
}

