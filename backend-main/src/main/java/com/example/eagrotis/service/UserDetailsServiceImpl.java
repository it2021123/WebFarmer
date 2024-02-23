package com.example.eagrotis.service;

import com.example.eagrotis.entity.Role;
import com.example.eagrotis.entity.User;
import com.example.eagrotis.repository.RoleRepository;
import com.example.eagrotis.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Long saveUser(User user){
        String passwd = user.getPassword();
        String encodedPasswd = passwordEncoder.encode(passwd);
        user.setPassword(encodedPasswd);

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ERROR: Role not Found") );
        user.setRole(role);

        user = userRepository.save(user);
        return user.getId();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
    /*public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = (userRepository.findByUsername(username));

        if(opt.isEmpty()){
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }else{
            User user = opt.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    AuthorityUtils.createAuthorityList(user.getRole().toString())
            );
        }
    }*/


}