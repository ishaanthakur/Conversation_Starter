package com.backend.challenge.repository.model;

import lombok.*;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Builder @Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
    @Id
    private String token;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
