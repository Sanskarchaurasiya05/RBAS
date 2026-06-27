package com.sanskar.practice.config;

import com.sanskar.practice.model.Employee;
import com.sanskar.practice.repository.EmpRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final EmpRepository repo;

    public CustomUserDetailsService(EmpRepository repo) {
        this.repo = repo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee emp = repo.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // It is best practice to prefix roles with "ROLE_" for Spring Security
        String role = emp.getRole().startsWith("ROLE_") ? emp.getRole() : "ROLE_" + emp.getRole();
        return new User(
                emp.getName(),      // username
                emp.getPassword(),  // encrypted password from DB
                List.of(new SimpleGrantedAuthority(role)) // role
        );
    }
}
