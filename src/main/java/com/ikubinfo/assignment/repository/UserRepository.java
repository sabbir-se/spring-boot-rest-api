package com.ikubinfo.assignment.repository;

import com.ikubinfo.assignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sabbir on 9/28/21.
 */

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long userID);
    User findUserByPhone(String phone);
}
