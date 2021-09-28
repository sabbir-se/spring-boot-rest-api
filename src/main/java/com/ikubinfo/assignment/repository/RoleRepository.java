package com.ikubinfo.assignment.repository;

import com.ikubinfo.assignment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sabbir on 9/28/21.
 */

@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findRolesByUserId(Long userID);
}
