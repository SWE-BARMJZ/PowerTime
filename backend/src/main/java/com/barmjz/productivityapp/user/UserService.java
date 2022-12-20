package com.barmjz.productivityapp.user;

import com.barmjz.productivityapp.user.registration.token.ConfirmationToken;
import com.barmjz.productivityapp.user.registration.token.ConfirmationTokenRepository;
import com.barmjz.productivityapp.user.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final ConfirmationTokenService confirmationTokenService;

    public String saveUser(User user, PasswordEncoder encoder){
        boolean userExists = userRepo.getUserByEmail(user.getEmail()).isPresent();
        if (userExists)
            throw new IllegalStateException("email already exists");

        String encodedPass = encoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        userRepo.save(user);
        // TODO: Send Confirmation Token
//        String token = UUID.randomUUID().toString();
//        ConfirmationToken confirmationToken = new ConfirmationToken(
//                user,
//                token,
//                LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(15)
//        );
//        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // TODO: SEND EMAIL

        return "token";
    }

    public User getUserByEmail(String email) {
        return userRepo.getUserByEmail(email).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo
                .getUserByEmail(email)
                .map(SecurityUser::new)
                .orElseThrow(()->new UsernameNotFoundException("Username not found:" + email));
    }

    public int verifyUser(String email) {
        return userRepo.verifyUser(email);
    }
}