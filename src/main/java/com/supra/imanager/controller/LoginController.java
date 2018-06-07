package com.supra.imanager.controller;

import java.sql.SQLException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.supra.imanager.bean.Dashboard;
import com.supra.imanager.bean.DashboardAnnouncement;
import com.supra.imanager.bean.DashboardPanel;
import com.supra.imanager.bean.DashboardQuickLink;
import com.supra.imanager.bean.LoginInput;
import com.supra.imanager.bean.LoginResult;
import com.supra.imanager.bean.Response;
import com.supra.imanager.dto.RestPanelStatusName;
import com.supra.imanager.service.DashboardService;
import com.supra.imanager.service.LoginService;
import com.supra.imanager.utilities.ApplicationConstants;

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

/*	@Autowired
	ForgetPasswordService forgetPasswordService;
*/
	@Autowired
	DashboardService dashboardService;
	
/*	@Autowired 
	MenuService menuService;
*/
	@Value("${app.domainUrl}")
	private String domainUrl;

	@Value("${server.contextPath}")
	private String contextPath;
	
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
//		List<DashboardQuickLink> dashboardQuickLinks=dashboardService.getQuickLink(username);
//		List<DashboardAnnouncement> dashboardAnnouncements=dashboardService.getAnnouncements(username);

		// fill values according to self and others
		JSONArray jsonOwn = dashboardService.fetchUserOwnItemCount(username);
		JSONArray jsonOthers = dashboardService.fetchOtherUsersItemCount(username);

		dashboardService.getPanelData(jsonOwn, statusNamesBean, ApplicationConstants.PANEL_CODE_SELF);
		dashboardService.getPanelData(jsonOthers, statusNamesBean, ApplicationConstants.PANEL_CODE_OTHERS);

		List<DashboardPanel> panelList = dashboardService.preparePanelResponse(statusNamesBean);
		
		Dashboard restDashboard = new Dashboard();
		restDashboard.setPanellList(panelList);
//		restDashboard.setQuickLinkBean(dashboardQuickLinks);
//		restDashboard.setAnnouncementBean(dashboardAnnouncements);
		
		restResponse.setResponseCode(HttpStatus.OK.value());
		restResponse.setResponseMessage("Success");
		restResponse.setResponseData(restDashboard);

		return ResponseEntity.ok().body(restResponse);

	}

/*
	@RequestMapping(value = "/v1/Menu/{username}", method = RequestMethod.GET)
	@ResponseBody
	public Response navigation(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("username") String username) throws SQLException {

		List<MenuDataFromDB> menuDbData = menuService.fetchModuleFunctionMapping(username, "1", "8");

		List<MenuModules> menuModules = new ArrayList<>();
		Iterator<MenuDataFromDB> iterator = menuDbData.iterator();
		while (iterator.hasNext()) {
			MenuDataFromDB queryData = (MenuDataFromDB) iterator.next();

			boolean alreadyExists = false;
			MenuModules oldObject = null;
			for (MenuModules menuModule : menuModules) {
				if (menuModule.getModuleId() == queryData.getModuleId()) {
					alreadyExists = true;
					oldObject = menuModule;
					break;
				}
			}

			if (alreadyExists) {
				List<MenuFunctions> oldFunctionsList = oldObject.getFunctionBeans();
				MenuFunctions newRestfunctionBean = new MenuFunctions();
				newRestfunctionBean.setFunctionId(queryData.getFunctionId());
				newRestfunctionBean.setFunctionName(queryData.getFunctionName());
				newRestfunctionBean.setFunctionUrl(protocol + "://" + domain + "/" + contextRoot
						+ "/resources/images/function/" + queryData.getFunctionId() + ".png");
				oldFunctionsList.add(newRestfunctionBean);
			} else {
				MenuModules moduleFunctionMapping = new MenuModules();
				moduleFunctionMapping.setModuleId(queryData.getModuleId());
				moduleFunctionMapping.setModuleName(String.valueOf(queryData.getModuleName()));
				moduleFunctionMapping.setModuleUrl(protocol + "://" + domain + "/" + contextRoot
						+ "/resources/images/module/" + queryData.getModuleId() + ".png");
				List<MenuFunctions> functionBeans = new ArrayList<MenuFunctions>();
				MenuFunctions restFunBean = new MenuFunctions();
				restFunBean.setFunctionId(queryData.getFunctionId());
				restFunBean.setFunctionName(queryData.getFunctionName());
				restFunBean.setFunctionUrl(protocol + "://" + domain + "/" + contextRoot + "/resources/images/function/"
						+ queryData.getFunctionId() + ".png");
				functionBeans.add(restFunBean);
				moduleFunctionMapping.setFunctionBeans(functionBeans);
				menuModules.add(moduleFunctionMapping);
			}
		}

		Response restResponse = new Response();
		restResponse.setResponseCode(HttpStatus.OK.value());
		restResponse.setResponseMessage("Success");
		restResponse.setResponseData(new Menu(menuModules));

		return restResponse;
	}
	
	
	

	
	
	
	
	//// Forget paassword mail 1////
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/v1/forgetPasswordActivationLink")
	public ResponseEntity generateActivationLink2(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "usermail", required = true) String usermail) {
		Response mailResponse = new Response();
		boolean status = false;
		try {
			InternetAddress emailAddr = new InternetAddress(usermail);
			emailAddr.validate();
			status = forgetPasswordService.checkUserExist(usermail);
			if (status) {
				String activationLinkSaved = forgetPasswordService.saveActivationLink(usermail);

				forgetPasswordService.prepareMailSentResponse(activationLinkSaved, mailResponse, usermail);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(status);

	
		
		return ResponseEntity.ok().body(mailResponse);
	}

	
*/	
	
	
		/*
	 * //////////////////////////////Menu
	 * API////////////////////////////////////////////////
	 * 
	 * @GetMapping(value = "/v1/restMenu/{username}") public ResponseEntity
	 * viewNavigation(HttpServletRequest request, HttpServletResponse response
	 * , @PathVariable("username") String username) throws SQLException {
	 * List<MenuDataFromDB> menuDbData =
	 * loginService.fetchModuleFunctionMapping(username,"1","8"); List<MenuModules>
	 * menuModules = new ArrayList<>(); Iterator<MenuDataFromDB> iterator =
	 * menuDbData.iterator(); while (iterator.hasNext()) { MenuDataFromDB queryData
	 * = (MenuDataFromDB) iterator.next(); boolean alreadyExists = false;
	 * MenuModules oldObject = null; for (MenuModules menuModule : menuModules) {
	 * if(menuModule.getModuleId() == queryData.getModuleId()) { alreadyExists =
	 * true; oldObject = menuModule; break; } } if(alreadyExists) {
	 * List<MenuFunctions> oldFunctionsList = oldObject.getFunctionBeans();
	 * MenuFunctions newRestfunctionBean = new MenuFunctions();
	 * newRestfunctionBean.setFunctionId(queryData.getFunctionId());
	 * newRestfunctionBean.setFunctionName(queryData.getFunctionName());
	 * oldFunctionsList.add(newRestfunctionBean); } else { MenuModules
	 * moduleFunctionMapping = new MenuModules();
	 * moduleFunctionMapping.setModuleId(queryData.getModuleId());
	 * moduleFunctionMapping.setModuleName(String.valueOf(queryData.getModuleName())
	 * ); List<MenuFunctions> functionBeans = new ArrayList<MenuFunctions>();
	 * MenuFunctions restFunBean = new MenuFunctions();
	 * restFunBean.setFunctionId(queryData.getFunctionId());
	 * restFunBean.setFunctionName(queryData.getFunctionName());
	 * functionBeans.add(restFunBean);
	 * moduleFunctionMapping.setFunctionBeans(functionBeans);
	 * menuModules.add(moduleFunctionMapping); } } Response restResponse = new
	 * Response(); restResponse.setResponseCode(HttpStatus.OK.value());
	 * restResponse.setResponseMessage("Success"); restResponse.setResponseData(new
	 * Menu(menuModules)); return restResponse; }
	 * 
	 * 
	 * 
	 * ////////////////////////////Dashboard
	 * API///////////////////////////////////////////////////
	 * 
	 * @GetMapping(value = "/v1/restDashboard/{username}") public ResponseEntity
	 * viewDashboard(HttpServletRequest request, HttpServletResponse response,
	 * HttpSession session, @PathVariable("username") String username) throws
	 * SQLException { //get user allowed panels List<DashboardDataFromDB>
	 * statusNamesBean = loginService.getUserAllowedPanelsWithStatus(username);
	 * //get quickLink List<DashboardQuickLink> listQuickLink=new ArrayList<>();
	 * List<DashboardQuickLink> dashboardquicklink =
	 * loginService.getQuickLinks(username); Iterator iquicklink=
	 * dashboardquicklink.iterator(); while (iquicklink.hasNext()) {
	 * DashboardQuickLink dashboardQuickLink2 =
	 * (DashboardQuickLink)iquicklink.next(); DashboardQuickLink link= new
	 * DashboardQuickLink();
	 * link.setQuickLinkId(dashboardQuickLink2.getQuickLinkId());
	 * link.setQuickLinkName(dashboardQuickLink2.getQuickLinkName());
	 * listQuickLink.add(link); } //get Announcement List<DashboardAnnouncement>
	 * announcements=loginService.getAnnouncementUserSpecific(username); //fill
	 * values according to self and others JSONArray jsonOwn =
	 * loginService.fetchUserOwnItemCount(username); JSONArray jsonOthers =
	 * loginService.fetchOtherUsersItemCount(username); getPanelData(jsonOwn,
	 * statusNamesBean, DashboardDataFromDB.PANEL_CODE_SELF);
	 * getPanelData(jsonOthers, statusNamesBean,
	 * DashboardDataFromDB.PANEL_CODE_OTHERS); List<DashboardPanel>
	 * panelList=preparePanelResponse(statusNamesBean); Dashboard restDashboard =
	 * new Dashboard(); restDashboard.setPanellList(panelList);
	 * restDashboard.setQuickLinkBean(dashboardquicklink);
	 * restDashboard.setAnnouncementBean(announcements); Response restResponse = new
	 * Response(); restResponse.setResponseCode(HttpStatus.OK.value());
	 * restResponse.setResponseMessage("Success");
	 * restResponse.setResponseData(restDashboard); return restResponse; }
	 * 
	 * 
	 * ////////////////////////////////Methods//////////////////////////////////////
	 * /
	 * 
	 * private void getPanelData(JSONArray jsonarr, List<DashboardDataFromDB>
	 * dashboardDataList, String panelCode) { for (int i = 0; i < jsonarr.length();
	 * i++) { try { JSONObject jsonObject = jsonarr.getJSONObject(i);
	 * 
	 * String jsonObjectmoduleName = jsonObject.getString("modulename"); int
	 * jsonObjectcount = Integer.parseInt(jsonObject.getString("count")); String
	 * jsonObjectstatusName = jsonObject.getString("statusname");
	 * 
	 * Iterator<DashboardDataFromDB> dashboardDataIterator =
	 * dashboardDataList.iterator(); while (dashboardDataIterator.hasNext()) {
	 * DashboardDataFromDB statusBean = (DashboardDataFromDB)
	 * dashboardDataIterator.next(); if
	 * (statusBean.getModuleName().equalsIgnoreCase(jsonObjectmoduleName) &&
	 * (statusBean.getPanelCode().equalsIgnoreCase(panelCode))) { if
	 * (statusBean.getStatusName().equalsIgnoreCase(jsonObjectstatusName)) {
	 * statusBean.setStatusCount(jsonObjectcount); } } }
	 * 
	 * } catch (JSONException e) { e.printStackTrace(); } } } private
	 * List<DashboardPanel> preparePanelResponse(List<DashboardDataFromDB>
	 * dashboardDataList) {
	 * 
	 * List<DashboardPanel> panelList = new ArrayList<>();
	 * 
	 * Iterator<DashboardDataFromDB> dashboardDataIterator =
	 * dashboardDataList.iterator(); while (dashboardDataIterator.hasNext()) {
	 * DashboardDataFromDB paneldata = (DashboardDataFromDB)
	 * dashboardDataIterator.next();
	 * 
	 * DashboardPanel oldObject = null; for (int i = 0; i < panelList.size(); i++) {
	 * if(panelList.get(i).getPanelId().equals(paneldata.getPanelId())) { oldObject
	 * = panelList.get(i); break; } }
	 * 
	 * if(null == oldObject) { DashboardPanel panel= new DashboardPanel();
	 * panel.setPanelId(paneldata.getPanelId());
	 * panel.setPanelName(paneldata.getPanelName());
	 * 
	 * List<DashboardStatus> statusList = new ArrayList<>(); DashboardStatus status=
	 * new DashboardStatus(); status.setStatusName(paneldata.getStatusName());
	 * status.setStatusCount(paneldata.getStatusCount()); statusList.add(status);
	 * 
	 * panel.setStatusList(statusList); panelList.add(panel); } else {
	 * List<DashboardStatus> dashboardStatusList = oldObject.getStatusList();
	 * DashboardStatus dashboardStatus= new DashboardStatus();
	 * dashboardStatus.setStatusName(paneldata.getStatusName());
	 * dashboardStatus.setStatusCount(paneldata.getStatusCount());
	 * dashboardStatusList.add(dashboardStatus); } } return panelList; }
	 * 
	 * private void setDataInSession(HttpServletRequest request, HttpSession
	 * session, LoginResult loginBean) { session = request.getSession();
	 * session.setAttribute("loggedInUserFullName", loginBean.getFirstname() + " " +
	 * loginBean.getLastname()); session.setAttribute("loggedInUser",
	 * loginBean.getEmpId()); session.setAttribute("loggedInUserCode",
	 * loginBean.getUserCode()); session.setAttribute("loggedinuserrole",
	 * loginBean.getRole()); session.setAttribute("loggedinusergroup",
	 * loginBean.getUserGroup());
	 * session.setAttribute("reportingManager",loginBean.getManagerName());
	 * session.setAttribute("reportingManagerUserName", loginBean.getManagerId());
	 * session.setAttribute("hrManagerId",loginBean.getHrmId());
	 * session.setAttribute("hrManager",loginBean.getHrmName());
	 * session.setAttribute("loggedInUserLocation", loginBean.getBaseLocation());
	 * session.setAttribute("loggedInUserAccUnit", loginBean.getAccountUnit());
	 * session.setAttribute("loggedInUserDepartment", loginBean.getDepartment());
	 * session.setAttribute("loggedInUserDesignation", loginBean.getDesignation());
	 * session.setAttribute("loggedInUserPolicyGroup", loginBean.getPolicyGroup());
	 * session.setAttribute("loggedInUserProxy", loginBean.getLoggedInUserProxy());
	 * session.setAttribute("loggedInUserFullNameProxy",
	 * loginBean.getLoggedInUserProxy()); session.setAttribute("globalProxyCount",
	 * "");
	 * 
	 * }
	 */
}
