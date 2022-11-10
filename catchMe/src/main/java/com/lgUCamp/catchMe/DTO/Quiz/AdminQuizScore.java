package com.lgUCamp.catchMe.DTO.Quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminQuizScore {

    private int adminNo;
    private String adminJob;
    private String adminId;
    private String adminPass;

    private List<QuizScore> quizScoreList;

}
