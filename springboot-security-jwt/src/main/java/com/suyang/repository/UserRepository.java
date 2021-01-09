package com.suyang.repository;

import com.suyang.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * description: UserReposiotry <br>
 * date: 2021/1/2 1:47 下午 <br>
 * author: suyang <br>
 * version: 1.0 <br>
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void deleteByUserName(String userName);

    boolean existsByUserName(String username);
}
