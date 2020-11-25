package com.backend.challenge.repository.model;

import com.backend.challenge.resources.MessageContentResourceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Builder @Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message {
    @Id
    @GenericGenerator(name="message_id" , strategy="increment")
    @GeneratedValue(generator="message_id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;
    @ManyToOne(fetch = FetchType.LAZY)
    private User recipient;
    private Date timestamp;
    @Enumerated(EnumType.STRING)
    private MessageContentResourceType type;
}
