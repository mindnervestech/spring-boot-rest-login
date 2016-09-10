package com.mnt.entities.auth;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@Entity
@Table(name="permissiongroup")
public class Group {
	
	public int id;
    public String name;

    public List<PermissionMatrix> permissionMatrix = new ArrayList<PermissionMatrix>();
    
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="group_id")
    public List<PermissionMatrix> getPermissionMatrix() { return permissionMatrix; }
    
    public void setPermissionMatrix(List<PermissionMatrix> permissionMatrix) {
    	this.permissionMatrix = permissionMatrix;
    } 
    
    @Id
    @Column(name="group_id")
    public int getId() { return id; }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() { return name; }
    
    public void setName(String name) {
        this.name = name;
    }

}
