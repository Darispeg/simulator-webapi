package com.developer.patrolsimulator.service;

import com.developer.patrolsimulator.db.entities.UserEntity;
import com.developer.patrolsimulator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service(value = "UserService")
public class UserServiceImpl extends GenericServiceImpl<UserEntity, Long> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public JpaRepository<UserEntity, Long> getDao() {
        return userRepository;
    }

    @Override
    public UserEntity getByUserKey(UUID userKey) {
        Optional<UserEntity> obj = userRepository.findByUserKey(userKey);
        if (obj.isPresent())
            return obj.get();
        return null;
    }

    @Override
    public UserEntity getByUsernameAndPassword(String username, String password) {
        Optional<UserEntity> obj = userRepository.findByUsernameAndPassword(username, password);
        if (obj.isPresent())
            return obj.get();
        return null;
    }

    @Override
    public List<UserEntity> getAllByOrderByLastNameAsc() {
        List<UserEntity> returnList = new ArrayList<>();
        userRepository.getAllByOrderByLastNameAsc().forEach(obj -> returnList.add(obj.get()));
        return returnList;
    }
}