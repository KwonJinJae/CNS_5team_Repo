package com.lgUCamp.catchMe.DTO.Quiz;

import com.lgUCamp.catchMe.DTO.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizCateAdmin {

    private int quizNo;
    private String quizContent;
    private String quizReason;
    private char quizAnswer;
    private int adminNo;
    private int quizCateNo;

    private Admin admin;
    private QuizCate quizCate;

}
