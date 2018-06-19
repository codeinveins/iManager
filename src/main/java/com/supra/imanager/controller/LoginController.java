	package com.supra.imanager.controller;

import java.sql.SQLException;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.supra.imanager.bean.Dashboard;
import com.supra.imanager.bean.DashboardAnnouncement;
import com.supra.imanager.bean.DashboardPanel;
import com.supra.imanager.bean.DashboardQuickLink;
import com.supra.imanager.bean.LoginInput;
import com.supra.imanager.bean.LoginResult;
import com.supra.imanager.bean.Menu;
import com.supra.imanager.bean.MenuDataFromDB;
import com.supra.imanager.bean.MenuModules;
import com.supra.imanager.bean.Response;
import com.supra.imanager.dto.RestPanelStatusName;
import com.supra.imanager.dto.RestToken;
import com.supra.imanager.repository.RestTokenRepository;
import com.supra.imanager.service.DashboardService;
import com.supra.imanager.service.LoginService;
import com.supra.imanager.service.MenuService;
import com.supra.imanager.service.UserService;
import com.supra.imanager.utilities.ApplicationConstants;

import io.swagger.annotations.Api;

@RestController
//@Api(value="Apis containing user related operations", description=" ", produces="application/json", tags= {"User Management"})
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserService userService;

	@Autowired
	private DashboardService dashboardService;
	
	@Autowired
	private RestTokenRepository restTokenRepository;
	
	@Autowired 
	private MenuService menuService;

	@Value("${app.domainUrl}")
	private String domainUrl;

	@Value("${server.contextPath}")
	private String contextPath;
	
	
	
	/**
	 * This api is used to perform login on mobile side
	 * @param request
	 * @param response
	 * @param session
	 * @param loginInput
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/v1/login")
	public ResponseEntity doLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestBody LoginInput loginInput) {
		Response restResponse = new Response();
		LoginResult loginResult;
		try {
			loginResult = loginService.isValidUser(loginInput, request.getRemoteAddr());
			loginService.prepareLoginReponse(loginResult, loginInput, restResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok().body(restResponse);
	}


	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/v1/dashboard/{username}")
	public ResponseEntity viewDashboard(HttpServletRequest request, 
			HttpServletResponse response, HttpSession session,
			@PathVariable("username") String username) throws SQLException {

		Response restResponse = new Response();

		// get user allowed panels
		List<RestPanelStatusName> statusNamesBean = dashboardService.getUserAllowedPanelsWithStatus(username);
	    DashboardQuickLink quickLinks=dashboardService.getQuickLink(username);
		DashboardAnnouncement announcements=dashboardService.getAnnouncements(username);

		// fill values according to self and others
		JSONArray jsonOwn = dashboardService.fetchUserOwnItemCount(username);
		JSONArray jsonOthers = dashboardService.fetchOtherUsersItemCount(username);

		dashboardService.getPanelData(jsonOwn, statusNamesBean, ApplicationConstants.PANEL_CODE_SELF);
		dashboardService.getPanelData(jsonOthers, statusNamesBean, ApplicationConstants.PANEL_CODE_OTHERS);

		List<DashboardPanel> panelList = dashboardService.preparePanelResponse(statusNamesBean);
		
		Dashboard restDashboard = new Dashboard();
		restDashboard.setPanellList(panelList);
		restDashboard.setQuickLinkBean(quickLinks);
		restDashboard.setAnnouncementBean(announcements);
		
		restResponse.setResponseCode(HttpStatus.OK.value());
		restResponse.setResponseMessage("Success");
		restResponse.setResponseData(restDashboard);

		return ResponseEntity.ok().body(restResponse);

	}
	
	@GetMapping(value = "/v1/menu/{username}")
	@ResponseBody
	public Response navigation(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("username") String username) throws SQLException {

		List<MenuDataFromDB> menuDataFromDbList = menuService.fetchModuleFunctionMapping(username, "1", "8");

		List<MenuModules> menuModules = menuService.prepareMenuResponse(menuDataFromDbList);
		
		Response restResponse = new Response();
		restResponse.setResponseCode(HttpStatus.OK.value());
		restResponse.setResponseMessage("Success");
		restResponse.setResponseData(new Menu(menuModules));

		return restResponse;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/v1/forgetPasswordActivationLink")
	public ResponseEntity forgetPassword(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "usermail", required = true) String usermail) {
		Response mailResponse = new Response();
		String activationLinkSaved=null;
		boolean status = false;
		try {
			InternetAddress emailAddr = new InternetAddress(usermail);
			emailAddr.validate();
			status = loginService.isUserExists(usermail);
			if(status) {
			activationLinkSaved = userService.saveActivationLink(usermail);
			if (activationLinkSaved.equals("Sent")) {
				mailResponse=userService.prepareMailSentResponse(activationLinkSaved, mailResponse, usermail);
			}
			else {
				mailResponse = userService.prepareMailSentResponse(activationLinkSaved, mailResponse, usermail);
			}
		}
			else {
				mailResponse.setResponseCode(HttpStatus.UNAUTHORIZED.value());
				mailResponse.setResponseMessage("Email Not Sent Successfully.");
				mailResponse.setResponseData(null);
			
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			return ResponseEntity.ok().body(mailResponse);
	}
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/v1/logout")
	public ResponseEntity logout(HttpServletRequest request, HttpSession session) {
		String token = request.getHeader("token");
		RestToken restToken = restTokenRepository.findByToken(token);

		Response restResponse = new Response();
		restResponse.setResponseData(null);
		
		if(restToken != null) {
			restTokenRepository.delete(restToken);
			restResponse.setResponseCode(HttpStatus.OK.value());
			restResponse.setResponseMessage("You are successfully logged out.");
			return ResponseEntity.ok().body(restResponse);
		}
		else {
			restResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
			restResponse.setResponseMessage("Invalid Token");
			return ResponseEntity.badRequest().body(restResponse);
		}
	}
}
