package com.example.weatherootd.domain;

import lombok.Data;

@Data
public class Member {
    private String id;
    private String password;
    private String name;
    private Integer age;
    private int sex;
    private Style style;
}
