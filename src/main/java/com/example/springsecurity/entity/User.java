package com.example.springsecurity.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
@DynamicUpdate(true)
public class User {

	@Id
	private String username;
	private String email;
	private String password;
	@Column(name="isActive",nullable = false, columnDefinition="bit default 1")
	private boolean isActive;
	
//	@Column(name = "roles", nullable = false, updatable = false, insertable = false, columnDefinition = "varchar(128) DEFAULT 'ROLE_USER'")
//	private String roles;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
		joinColumns = {@JoinColumn(name="user_id", referencedColumnName="username")},
		inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")})
	private List<Role> roles;

}
