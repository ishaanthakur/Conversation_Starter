package com.backend.challenge.repository.model;

import java.util.*;

import com.backend.challenge.resources.MessageContentSourceType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder @Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MessageVideo {
    @Id
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn @MapsId
    private Message message;
    private String url;
    @Enumerated(EnumType.STRING)
    private MessageContentSourceType source;
}
