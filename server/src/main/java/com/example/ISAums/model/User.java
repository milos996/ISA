package com.example.ISAums.model;

import com.example.ISAums.model.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;

import static com.example.ISAums.util.ValidationConstraints.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Where(clause = "is_deleted='false'")
public class User extends BaseEntity implements UserDetails{

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

	@NotNull
	@Column(name = "is_enabled")
	private Boolean isEnabled;

	@Nullable
	@OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
	private HotelAdmin hotelAdmin;

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
	public boolean isEnabled() { return isEnabled; }



}
