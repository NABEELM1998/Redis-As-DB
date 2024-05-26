package com.nabeel.redisDB.model;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Student")
public class Student implements Serializable {

    private static final Long serialVersionUID = 42L;

    private Integer id;
    private String name;
    private Integer fees;
}
