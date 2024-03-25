package com.capstone.feelcheck.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY로 하면 프로젝트에서 연결된 DB의 넘버링 전략을 따라감. (우리 MySQL꺼 설정 따라감)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Emotion emotion;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false, length = 100)
    private String comment;

    @CreationTimestamp
    private Timestamp createDate;
}

