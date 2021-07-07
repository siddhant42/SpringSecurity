package com.example.springsecurity.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
@DynamicUpdate(true)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private String password;
	private Boolean isActive;
	
//	@Column(name = "roles", nullable = false, updatable = false, insertable = false, columnDefinition = "varchar(128) DEFAULT 'ROLE_USER'")
//	private String roles;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
		joinColumns = {@JoinColumn(name="user_id", referencedColumnName="ID")},
		inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="ID")})
	private List<Role> roles;

}
