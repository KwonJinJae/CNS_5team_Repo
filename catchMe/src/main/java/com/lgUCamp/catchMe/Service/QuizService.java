package com.lgUCamp.catchMe.Service;

import com.lgUCamp.catchMe.Controller.CarbonInfoPaging.SelectCriteria;
import com.lgUCamp.catchMe.DTO.Quiz.Quiz;
import com.lgUCamp.catchMe.DTO.Quiz.UserQuizScoreDTO;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.Map;

public interface QuizService {

//    ArrayList<UserQuizScore> userQuizScore();

    UserQuizScoreDTO userQuizScoreOne();
    UserQuizScoreDTO userQuizScoreTwo();
    UserQuizScoreDTO userQuizScoreThree();
    UserQuizScoreDTO userQuizScoreFour();

    /* 퀴즈 조회 */
    ArrayList<Quiz> selectQuizList(SelectCriteria selectCriteria);

    int quizCateListCount(int quizCateNo);

    Map<String, Object> selectQuizSolveList(int searchCondition, Pageable pageable);
}
