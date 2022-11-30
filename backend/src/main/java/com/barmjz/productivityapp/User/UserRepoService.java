package com.barmjz.productivityapp.User;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRepoService implements UserDetailsService {

    private final UserRepo userRepo;

    public void saveUser(User user){
        userRepo.save(user);
    }

    public Iterable<User> findAll(){return userRepo.findAll();}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo
                .getUsersByEmail(email)
                .map(SecurityUser::new)
                .orElseThrow(()->new UsernameNotFoundException("Username not found" + email));
    }
}