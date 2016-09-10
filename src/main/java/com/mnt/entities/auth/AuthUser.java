package com.mnt.entities.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name="authusers")
public class AuthUser implements UserDetails {
	
	private static final long serialVersionUID = 2097496846486828450L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String name;
	private String email;
	
	private List<Role> roles = new ArrayList<Role>();
	
	@Transient
	private List<PermissionMatrix> permissions = new ArrayList<PermissionMatrix>();
	
	@Transient
	public List<PermissionMatrix> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionMatrix> permissions) {
		this.permissions = permissions;
	}

	@SuppressWarnings("unused")
	private boolean enabled = true;
    
    public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Transient
    @Transactional
    public Collection<GrantedAuthority> getAuthorities() {
    	List<GrantedAuthority> roles =  new ArrayList<GrantedAuthority>();
		roles.addAll(getRoles());
		return roles;
    }

    @Transient
    public boolean isAccountNonExpired() {
    	return true;
    }

    @Transient
    public boolean isAccountNonLocked() {
    	return true;
    }

    @Transient
    public boolean isCredentialsNonExpired() {
    	return true;
    }


    /* non UserDetails methods */
    @Id
    @Column(name="auth_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getId() { return id; }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() { return username; }
    
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() { return password; }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="email_id")
    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="name")
    public String getName() { return name; }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="userrole",
	       joinColumns=@JoinColumn(name="user_id"),
	       inverseJoinColumns=@JoinColumn(name="role_id"))
    public List<Role> getRoles() { return roles; }

    public void setRoles(List<Role> roles) {
    	this.roles = roles;
    }
    
    
    // ============================= Commented below as it is WIP for ACL =========================//
    
    /*
    public List<Group> groups = new ArrayList<Group>();
    
    public List<PermissionMatrix> permissionMatrix = new ArrayList<PermissionMatrix>();
	
    @Transient
    public Map<String, Integer> privResourceMap;
    
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    public List<Group> getGroups() { return groups; }
    
    public void setGroups(List<Group> groups) {
    	this.groups = groups;
    }
    
    @OneToMany
    @JoinTable(
	       joinColumns=@JoinColumn(name="user_id"),
	       inverseJoinColumns=@JoinColumn(name="permisionmatrix_id"))
    public List<PermissionMatrix> getPermissionMatrix() { return permissionMatrix; }
    
    public void setPermissionMatrix(List<PermissionMatrix> permissionMatrix) {
    	this.permissionMatrix = permissionMatrix;
    }
   
    */
    //@Transient
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
    
    public AuthUser(){}
    
    public AuthUser(String username, String name, String password, List<Role> role, boolean enabled) {
    	this.username = username;
		this.name = name;
		this.password = password;
		this.roles = role;
		this.enabled = enabled;
		
	}

	
}
