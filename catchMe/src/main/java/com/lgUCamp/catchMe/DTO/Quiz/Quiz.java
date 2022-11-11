package com.lgUCamp.catchMe.DTO.Quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quiz {

    private int quizNo;
    private String quizContent;
    private String quizReason;
    private char quizAnswer;
    private int adminNo;
    private int quizCateNo;

}
