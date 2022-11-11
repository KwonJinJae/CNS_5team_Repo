package com.lgUCamp.catchMe.DTO.Quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizScoreDTO {

    private int quizScoreNum;
    private int quizScore;
    private java.sql.Date quizScoreTime;
    private int userNo;
    private int quizCateNo;
}
