package com.example.weatherootd.domain;

import lombok.Data;

@Data
public class Like {
    private Long id;
    private Member member;
    private Ootd ootd;
}
