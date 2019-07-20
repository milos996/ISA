package com.example.ISAums.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.example.ISAums.model.enumeration.Role;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.example.ISAums.util.ValidationConstraints.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Where(clause = "is_deleted='false'")
public class User extends BaseEntity implements UserDetails {


	@Column(name = "first_name")
	@NotBlank
	@Size(max = FIRST_NAME_SIZE)
	private String firstName;

	@Column(name = "last_name")
	@NotBlank
	@Size(max = LAST_NAME_SIZE)
	private String lastName;

	@Column(name = "role")
	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "email")
	@NotBlank
	@Size(max = EMAIL_SIZE)
	private String email;

	@Column(name = "password")
	@NotBlank
	@Size(max = PASSWORD_HASH_SIZE)
	private String password;

	@Column(name = "phone_number")
	@NotBlank
	@Size(max = PHONE_NUMBER_SIZE)
	private String phoneNumber;

	@Column(name = "city")
	@NotBlank
	@Size(max = CITY_SIZE)
	private String city;

	@Column(name = "state")
	@NotBlank
	@Size(max = STATE_SIZE)
	private String state;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(this.getRole().toString()));
	}

	private Role getRole() {
		return role;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
