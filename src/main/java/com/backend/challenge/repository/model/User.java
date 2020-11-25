package com.backend.challenge.repository.model;

import java.util.*;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Builder @Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GenericGenerator(name="user_id" , strategy="increment")
    @GeneratedValue(generator="user_id")
    private Integer id;
    @Column
    private String username;
    @Column
    private String password;
}
