package com.capstone.feelcheck.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // User클래스를 객체로 인지하여 아래에 있는 속성들을 자동으로 MySQL 테이블로 생성해준다.
public class User {
    @Id //PrimaryKey
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY로 하면 프로젝트에서 연결된 DB의 넘버링 전략을 따라감. (우리 MySQL꺼 설정 따라감)
    private Long id; // Mysql에선 auto_increment가 될 것임

    @Column(nullable = false, length = 10)
    private String nickname; // 유저의 닉네임
    
    @CreationTimestamp // 시간이 자동으로 입력된다.
    private Timestamp createDate;
}