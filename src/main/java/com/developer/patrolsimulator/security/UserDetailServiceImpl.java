package com.developer.patrolsimulator.security;

import com.developer.patrolsimulator.db.entities.UserEntity;
import com.developer.patrolsimulator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository
                .findOneByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("the user with username " + username + " don't exist"));

        return new UserDetailImpl(userEntity);
    }
}
