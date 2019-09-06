package com.example.demo.security;
import com.example.demo.model.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() ->
                new UsernameNotFoundException("Bad credentials"));
        return UserPrincipal.create(user);
    }

    // this method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id)  {
        User user = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User not found with id: " + id));
        return UserPrincipal.create(user);
    }

}
