package com.supra.imanager.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supra.imanager.repository.ViewSupraUserStatusRepositoryCustom;

public class ViewSupraUserStatusRepositoryImpl implements ViewSupraUserStatusRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	private String TM179="select "
								+ "module, sum(statuscount), status "
						+ "from VIEW_SUPRA_USER_STATUS "
						+ "where "
							+ "username in (select username from user where reportingmanager=':username'"
							+ " and username !=':username') group by module,"
							+ "status union select 'reimbursement',count(distinct(s.requestNumber)),'ApprovedByYou' "
							+ "from supra_rms_action_history s,supra_rms_action_history h "
							+ " where s.requestnumber=h.requestnumber and s.actionby=':username' and s.username != ':username' "
							+ "and s.assignedto != h.username union "
							+ "select 'reimbursement',count(distinct(s.requestNumber)),'RejectedByYou' "
							+ "from supra_rms_action_history s,supra_rms_action_history h  "
							+ "where s.requestnumber=h.requestnumber and s.actionby=':username' and s.username != ':username' "
							+ "and s.assignedto = h.username union"
							+ " select 'reimbursement' AS `module`,count(1) AS `statuscount`,'Pending' AS `status`"
							+ " from supra_rms_approver_list l where l.currentassignflag='Y' and (l.userorgroup =':username' "
							+ "or EXISTS (SELECT groupid FROM supra_accessgroup_user_mapping "
							+ "WHERE username=':username' and groupid=l.userorgroup))";
	
	@Override
	public List<Object[]> fetchOtherUsersItemCount(String username) {
		Query query = entityManager.createNativeQuery(TM179);
		query.setParameter("username", username);
		List<Object[]> data =  (List<Object[]>) query.getResultList();
		return data;
	}

}
