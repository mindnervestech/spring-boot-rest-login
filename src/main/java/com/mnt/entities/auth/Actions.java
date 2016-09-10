package com.mnt.entities.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="actionable")
public class Actions {

	
	@Id
	@Column(name="action")
	private String action;
	
	@Column(name="action_name")
	private String actionName;
	
	public Actions(){}
	
	public Actions(String actionUrl) {
		this.action = action;
	}



	public String getActionUrl() {
		return action;
	}

	public void setActionUrl(String actionUrl) {
		this.action = actionUrl;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
	

	
	
}
