package com.example.vodafone.entities.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="permission_matrix")
public class PermissionMatrix {

	 public int id;
	
	 @Id
	 @Column(name="permissionmatrix_id")
	 public int getId() { return id; }
	 
	/* private AuthUser user;*/
	 
	 /*private Group group;*/
	 
	 private Role role;
	 
	 private Actions action;
	 @Column(name="accesslevel")
	 private int accessLevel;

	/* @ManyToOne
	 @JoinColumn(name="user_id")
	public AuthUser getUser() {
		return user;
	}*/

	/*public void setUser(AuthUser user) {
		this.user = user;
	}*/

	/*@ManyToOne
	@JoinColumn(name="group_id")
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}*/

	@ManyToOne
	@JoinColumn(name="role_id")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="action")
	public Actions getAction() {
		return action;
	}

	public void setAction(Actions action) {
		this.action = action;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public void setId(int id) {
		this.id = id;
	}
	 
	@Override
	public String toString() {
		return action.getActionName() + ":" + accessLevel;
	}
	 
}
