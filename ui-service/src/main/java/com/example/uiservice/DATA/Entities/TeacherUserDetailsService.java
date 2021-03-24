package com.example.uiservice.DATA.Entities;

import com.example.uiservice.DATA.Repositories.Implimentations.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherUserDetailsService implements UserDetailsService {
    @Autowired
    private TeacherRepository repository;

    public TeacherUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Teacher user = null;
        try {
            user = repository.findByEmail(email);
        } catch (HttpClientErrorException ex) {
            System.out.println("User not found");
            throw new UsernameNotFoundException(
                    "No user found with username: " + email);
        }

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        System.out.println("Email : " + email + "\n Pass : " + user.getPassword());
        return new org.springframework.security.core.userdetails.User
                (email,
                        user.getPassword(), true, true,
                        true, true,
                        getAuthorities(user.getRoles()));
    }

    private static ArrayList<GrantedAuthority> getAuthorities(ArrayList<String> roles) {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
            System.out.println("Role : " + role);
        }
        return authorities;
    }

    public TeacherRepository getRepository() {
        return repository;
    }

    public void setRepository(TeacherRepository repository) {
        this.repository = repository;
    }
}
