package com.sanskar.practice.controller;

import com.sanskar.practice.config.JwtUtil;
import com.sanskar.practice.model.Employee;

import com.sanskar.practice.model.Login;
import com.sanskar.practice.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee")
public class Controller {

    @Autowired
    private EmpService empService;
    @Autowired
     private JwtUtil jwtUtil;
    @Autowired
     private AuthenticationManager authenticationManager;

//    public Controller(EmpService empService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
//        this.empService = empService;
//        this.jwtUtil = jwtUtil;
//        this.authenticationManager = authenticationManager;
//    }

    @GetMapping
    public String getRequest(){
    return "hello Sanskar";
}

@PostMapping()
    public Employee postRequest(@RequestBody Employee e){
       return empService.createEmployee(e);
}

@PostMapping("/login")
    public String login(@RequestBody Login req){
//    you are calling AuthenticationManager, but internally Spring's ProviderManager delegates the work to an AuthenticationProvider.
//    and one of the provider is DaoAuthenticationProvider
//    DaoAuthenticationProvider loads user
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getName(),req.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetails user = (UserDetails) authentication.getPrincipal();
    String role = user.getAuthorities().iterator().next().toString();

    return jwtUtil.generateToken(user.getUsername(), role);

}

}
