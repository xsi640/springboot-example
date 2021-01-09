package com.suyang.entity;

import lombok.Getter;

/**
 * description: RoleType <br>
 * date: 2021/1/2 2:01 下午 <br>
 * author: suyang <br>
 * version: 1.0 <br>
 */
@Getter
public enum RoleType {
    USER("USER", "用户"),
    TEMP_USER("TEMP_USER", "临时用户"),
    MANAGER("MANAGER", "管理者"),
    ADMIN("ADMIN", "Admin");
    private final String name;
    private final String description;

    RoleType(java.lang.String name, java.lang.String description) {
        this.name = name;
        this.description = description;
    }
}
