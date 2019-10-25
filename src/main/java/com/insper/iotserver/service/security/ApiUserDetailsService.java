package com.insper.iotserver.service.security;

import com.insper.iotserver.model.User;
import com.insper.iotserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOp = userRepository.findByEmail(email);
        User user = userOp.orElseThrow(() -> new UsernameNotFoundException("message.user_not_found"));
        return user;
    }
}
