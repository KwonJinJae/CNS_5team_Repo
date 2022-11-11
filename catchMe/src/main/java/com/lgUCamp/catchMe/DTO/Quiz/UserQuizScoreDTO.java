package com.lgUCamp.catchMe.DTO.Quiz;

import com.lgUCamp.catchMe.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserQuizScoreDTO {

    private int quizScoreNum;
    private int quizScore;
    private java.sql.Date quizScoreTime;
    private int userNo;
    private int quizCateNo;

    private UserDTO user;
    private QuizCateDTO quizCate;

}
