package com.suyang.service.impl;

import com.suyang.controller.request.UserRegisterRequest;
import com.suyang.controller.request.UserUpdateRequest;
import com.suyang.entity.Role;
import com.suyang.entity.RoleType;
import com.suyang.entity.User;
import com.suyang.entity.UserRole;
import com.suyang.repository.RoleRepository;
import com.suyang.repository.UserRepository;
import com.suyang.repository.UserRoleRepository;
import com.suyang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

/**
 * description: UserServiceImpl <br>
 * date: 2021/1/2 1:37 下午 <br>
 * author: suyang <br>
 * version: 1.0 <br>
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public User save(UserRegisterRequest user) {
        ensureUserNameNotExist(user.getUserName());
        User u = user.toUser();
        u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(u);
        Role role = roleRepository.findByName(RoleType.USER.name()).orElseThrow(() -> new IllegalArgumentException("not found user role"));
        Role role0 = roleRepository.findByName(RoleType.MANAGER.name()).orElseThrow(() -> new IllegalArgumentException("not found manager role"));
        userRoleRepository.save(new UserRole(u, role));
        userRoleRepository.save(new UserRole(u, role0));
        return u;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName(username).orElseThrow(() -> new IllegalArgumentException("not found user."));
    }

    @Override
    public User update(UserUpdateRequest user) {
        User u = findByUsername(user.getUserName());
        if (!StringUtils.isEmpty(user.getPassword())) {
            u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(u);
    }

    @Override
    public void delete(String username) {
        userRepository.deleteByUserName(username);
    }

    @Override
    public boolean check(String inputPassword, String password) {
        return this.bCryptPasswordEncoder.matches(inputPassword, password);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    private void ensureUserNameNotExist(String userName) {
        boolean exist = userRepository.findByUserName(userName).isPresent();
        if (exist) {
            throw new IllegalArgumentException("username 已存在");
        }
    }
}
