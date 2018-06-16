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
								+ "vs.module, sum(vs.statuscount), vs.status "
						+ "from VIEW_SUPRA_USER_STATUS vs "
						+ "where "
							+ "vs.username in (select u.username from user u where u.reportingmanager=:uname"
							+ " and u.username !=:uname) group by vs.module,"
							+ "vs.status union select 'reimbursement',count(distinct(s.requestNumber)),'ApprovedByYou' "
							+ "from supra_rms_action_history s,supra_rms_action_history h "
							+ " where s.requestnumber=h.requestnumber and s.actionby=:uname and s.username != :uname "
							+ "and s.assignedto != h.username union "
							+ "select 'reimbursement',count(distinct(s.requestNumber)),'RejectedByYou' "
							+ "from supra_rms_action_history s,supra_rms_action_history h  "
							+ "where s.requestnumber=h.requestnumber and s.actionby=:uname and s.username != :uname "
							+ "and s.assignedto = h.username union"
							+ " select 'reimbursement' AS `module`,count(1) AS `statuscount`,'Pending' AS `status`"
							+ " from supra_rms_approver_list l where l.currentassignflag='Y' and (l.userorgroup =:uname "
							+ "or EXISTS (SELECT um.groupid FROM supra_accessgroup_user_mapping um "
							+ "WHERE um.username=:uname and um.groupid=l.userorgroup))";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> fetchOtherUsersItemCount(String username) {
		Query query = entityManager.createNativeQuery(TM179);
		query.setParameter("uname", username);
		List<Object[]> data =  (List<Object[]>) query.getResultList();
		return data;
	}

}
