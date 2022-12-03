package com.barmjz.productivityapp.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    public String saveUser(User user, PasswordEncoder encoder){
        boolean userExists = userRepo.getUserByEmail(user.getEmail()).isPresent();
        if (userExists)
            throw new IllegalStateException("email already exists");

        String encodedPass = encoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        userRepo.save(user);
        // TODO: Send Confirmation Token

        return "User was added successfully";
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo
                .getUserByEmail(email)
                .map(SecurityUser::new)
                .orElseThrow(()->new UsernameNotFoundException("Username not found:" + email));
    }
}