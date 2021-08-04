package com.nashtech.rootkies.model;

import com.nashtech.rootkies.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(	name = "users",
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "staffcode"),
			@UniqueConstraint(columnNames = "username")
		},
		indexes ={
			@Index(name = "user_role_idx" , columnList = "roleid"),
				@Index(name = "user_username_idx" , columnList = "username"),
				@Index(name = "user_location_idx" , columnList = "locationid")
		})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@Column(name = "staffcode")
	private String staffCode;

	@NotBlank
	@Column(name = "username")
	private String username;

	@NotBlank
	@Column(name = "password")
	private String password;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "dateofbirth")
	private LocalDateTime dateOfBirth;

	@Column(name = "joineddate")
	private LocalDateTime joinedDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;

	@ManyToOne
	@JoinColumn(name = "locationid")
	private Location location;

	@ManyToOne
	@JoinColumn(name = "roleid", nullable = false)
	private Role role;

	@Column(name = "firstlogin")
	private Boolean firstLogin;

	@Column(name = "isdeleted")
	private Boolean isDeleted;

	@OneToMany(mappedBy = "assignedTo")
	private Collection<Assignment> assignmentAssignedTo;

	@OneToMany(mappedBy = "assignedBy")
	private Collection<Assignment> assignmentAssignedBy;

	/*@OneToMany(mappedBy = "staffCode")
	private Collection<Assignment> assignmentstaffCode;*/

	@OneToMany(mappedBy = "requestedBy")
	private Collection<Request> requestRequestedBy;

	@OneToMany(mappedBy = "acceptedBy")
	private Collection<Request> requestAcceptedBy;
}
