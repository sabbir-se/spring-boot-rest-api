package com.ikubinfo.assignment.repository;

import com.ikubinfo.assignment.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sabbir on 9/29/21.
 */

@Transactional
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

    UserLogin findUserLoginByUsername(String userName);
    UserLogin findUserLoginByUsernameAndPassword(String userName, String password);
    UserLogin findUserLoginByAccessTokenAndUsername(String accessToken, String userName);
}
