package com.example.enigmashop.security.services;

import com.example.enigmashop.entities.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomerDetailsImpl implements UserDetails {

    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date dateOfBirth;
    private String address;
    private String phone;
    private String email;
    @JsonIgnore
    private String password;
    private Integer status;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomerDetailsImpl(String id, String firstName, String lastName, String gender, Date dateOfBirth, String address, String phone, String email, String password, Integer status, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.status = status;
        this.authorities = authorities;
    }

    public static CustomerDetailsImpl build(Customer customer){
        List<GrantedAuthority> authorities = customer.getRoleSet().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                .collect(Collectors.toList());
        return new CustomerDetailsImpl(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getGender(),
                customer.getDateOfBirth(),
                customer.getAddress(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getStatus(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public Integer getStatus() {
        return status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDetailsImpl that = (CustomerDetailsImpl) o;
        return id.equals(that.id) && firstName.equals(that.firstName) && lastName.equals(that.lastName) && gender.equals(that.gender) && dateOfBirth.equals(that.dateOfBirth) && address.equals(that.address) && phone.equals(that.phone) && email.equals(that.email) && password.equals(that.password) && status.equals(that.status) && authorities.equals(that.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, dateOfBirth, address, phone, email, password, status, authorities);
    }
}
