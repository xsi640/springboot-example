package com.suyang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * description: UserRole <br>
 * date: 2021/1/2 11:05 上午 <br>
 * author: suyang <br>
 * version: 1.0 <br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Role role;

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }
}
