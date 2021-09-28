package com.ikubinfo.assignment.service;

import com.ikubinfo.assignment.dto.CustomException;
import com.ikubinfo.assignment.model.Role;
import com.ikubinfo.assignment.model.User;
import com.ikubinfo.assignment.repository.RoleRepository;
import com.ikubinfo.assignment.repository.UserRepository;
import com.ikubinfo.assignment.util.Constant;
import com.ikubinfo.assignment.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabbir on 9/28/21.
 */

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public String createUser(User user) throws CustomException {
        logger.info("Crate User :: Start");
        validateUser(user);

        User existUser = userRepository.findUserByPhone(user.getPhone());
        if (null != existUser) {
            logger.info("User already exists.");
            return Utils.messageResponse(Constant.USER_EXIST);
        }
        userRepository.save(user);
        logger.info("User saved successfully!");
        if (!Utils.isNullOrEmpty(user.getRole())) {
            saveUserRole(user);
        }
        logger.info("Crate User :: End");
        return Utils.messageResponse(Constant.CREATE_MESSAGE);
    }

    private void validateUser(User user) throws CustomException {
        if (Utils.isNullOrEmpty(user.getFirstName())) {
            logger.info("First name must not be null.");
            throw new CustomException(Constant.BAD_REQUEST, Constant.USER_FNAME_NOT_FOUND);
        }
        if (Utils.isNullOrEmpty(user.getPhone())) {
            logger.info("Phone number must not be null.");
            throw new CustomException(Constant.BAD_REQUEST, Constant.USER_PHONE_NOT_FOUND);
        }
    }

    private void saveUserRole(User user) {
        logger.info("User role saving....");
        for (String roleName : user.getRole()) {
            Role role = new Role();
            role.setName(roleName);
            role.setUser(user);
            roleRepository.save(role);
        }
        logger.info("User role saved successfully.");
    }

    public String updateUser(User user) throws CustomException {
        logger.info("Update User:: Start");
        User findUser = userRepository.findUserById(user.getId());
        if (null == findUser) {
            logger.info("User not found.");
            throw new CustomException(Constant.NOT_FOUND_REQUEST, Constant.USER_NOT_FOUND);
        }

        if (!findUser.getPhone().equals(user.getPhone())) {
            findUser = userRepository.findUserByPhone(user.getPhone());
            if (null != findUser) {
                return Utils.messageResponse(Constant.USER_EXIST);
            }
        }
        userRepository.save(user);
        logger.info("User updated successfully!");
        if (!Utils.isNullOrEmpty(user.getRole())) {
            List<Role> roleList = roleRepository.findRolesByUserId(user.getId());
            if (!Utils.isNullOrEmpty(roleList)) {
                logger.info("Delete exist all roles!");
                roleRepository.deleteInBatch(roleList);
            }
            saveUserRole(user);
        }
        logger.info("Update User:: End");
        return Utils.messageResponse(Constant.UPDATE_MESSAGE);
    }

    public String deleteUser(Long userID) throws CustomException {
        logger.info("Delete User :: Start");
        User user = userRepository.findUserById(userID);
        if (null == user) {
            logger.info("User not found.");
            throw new CustomException(Constant.NOT_FOUND_REQUEST, Constant.USER_NOT_FOUND);
        }
        List<Role> roleList = roleRepository.findRolesByUserId(userID);
        if (!Utils.isNullOrEmpty(roleList)) {
            logger.info("Delete exist all roles!");
            roleRepository.deleteInBatch(roleList);
        }
        userRepository.delete(user);
        logger.info("User deleted successfully!");
        logger.info("Delete User :: End");
        return Utils.messageResponse(Constant.DELETE_MESSAGE);
    }

    public User getUserByID(Long userID) throws CustomException {
        logger.info("Get User :: Start");
        User user = userRepository.findUserById(userID);
        if (null == user) {
            logger.info("User not found.");
            throw new CustomException(Constant.NOT_FOUND_REQUEST, Constant.USER_NOT_FOUND);
        }
        setUserRole(user);
        logger.info("Get User :: End");
        return user;
    }

    private void setUserRole(User user) {
        logger.info("Get user roles...");
        List<Role> roleList = roleRepository.findRolesByUserId(user.getId());
        if (!Utils.isNullOrEmpty(roleList)) {
            List<String> roles = new ArrayList<>();
            for (Role role : roleList) {
                roles.add(role.getName());
            }
            user.setRole(roles);
        }
        logger.info("Get user roles done.");
    }

    public List<User> getAllUser() throws CustomException {
        logger.info("Get All User :: Start");
        List<User> userList = userRepository.findAll();
        if (Utils.isNullOrEmpty(userList)) {
            logger.info("User not found.");
            throw new CustomException(Constant.NOT_FOUND_REQUEST, Constant.USER_NOT_FOUND);
        }
        for (User user : userList) {
            setUserRole(user);
        }
        logger.info("Get All User :: End");
        return userList;
    }
}
