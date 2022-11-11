package com.lgUCamp.catchMe.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
@Table(name="tbl_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;
    @Column(length = 30, nullable = false, unique = true, name="user_id")
    private String userId;
    @Column(length = 30, nullable = false, unique = true, name="user_nickname")
    private String userNickname;
    @Column(length = 30, nullable = false, unique = true, name="user_phone")
    private String userPhone;
    @Column(name="user_pass")
    private String userPass;

}
