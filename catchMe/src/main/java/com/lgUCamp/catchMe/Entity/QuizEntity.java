package com.lgUCamp.catchMe.Entity;


import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@ToString
@Getter
@Entity(name = "TBL_QUIZ")
public class QuizEntity {

    @Id
    private int quizNo;

    @Column(name = "quiz_content")
    private String quizContent;

    @Column(name = "quiz_reason")
    private String quizReason;

    @Column(name = "quiz_answer")
    private char quizAnswer;

    @Column(name = "admin_no")
    private int adminNo;

    @Column(name = "quiz_cate_no")
    private int quizCateNo;
}
