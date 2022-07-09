package com.example.enigmashop.controllers;


import com.example.enigmashop.constant.ERole;
import com.example.enigmashop.constant.ResponseMessage;
import com.example.enigmashop.entities.Customer;
import com.example.enigmashop.entities.Role;
import com.example.enigmashop.repositories.RoleRepository;
import com.example.enigmashop.security.jwt.JwtUtils;
import com.example.enigmashop.security.payload.request.LoginRequest;
import com.example.enigmashop.security.payload.request.SignupRequest;
import com.example.enigmashop.security.payload.response.JwtResponse;
import com.example.enigmashop.security.payload.response.MessageResponse;
import com.example.enigmashop.security.services.CustomerDetailsImpl;
import com.example.enigmashop.services.impl.CustomerServiceImpl;
import com.example.enigmashop.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/enigmashop/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    RoleRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<Response<JwtResponse>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

        Response<JwtResponse> response = new Response<>();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getUserPassword())
        );
        System.out.println("authentication " + authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        System.out.println("Generate Token " + jwt);
        CustomerDetailsImpl customerDetails = (CustomerDetailsImpl) authentication.getPrincipal();
        System.out.println("Name " + customerDetails);
        List<String> roles = customerDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        String message = ResponseMessage.SUCCESS_LOGIN;
        response.setMessage(message);
        response.setData(new JwtResponse(
                jwt,
                customerDetails.getId(),
                customerDetails.getFirstName(),
                customerDetails.getLastName(),
                customerDetails.getGender(),
                customerDetails.getDateOfBirth(),
                customerDetails.getAddress(),
                customerDetails.getPhone(),
                customerDetails.getEmail(),
                customerDetails.getStatus(),
                roles));
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){
        if(customerService.userNameExist(signupRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken"));
        }

        Customer customer = new Customer(
                signupRequest.getFirstName(),
                signupRequest.getLastName(),
                signupRequest.getGender(),
                signupRequest.getDateOfBirth(),
                signupRequest.getAddress(),
                signupRequest.getPhone(),
                signupRequest.getEmail(),
                signupRequest.getStatus(),
                passwordEncoder.encode(signupRequest.getPassword())
        );
        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        System.out.println(customer);

        if(strRoles == null){
            Role userRole = repository.findByRole(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role){
                    case "admin":
                        Role adminRole = repository.findByRole(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = repository.findByRole(ERole.ROLE_USER)
                                .orElseThrow(() ->  new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);
                }
            });
        }
        System.out.println("roles " + roles);
        customer.setRoleSet(roles);
        customerService.saveCustomer(customer);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }



}

