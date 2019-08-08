package com.suyang.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@NodeEntity
public class Student {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private int age;
    private Date birthday;
}
