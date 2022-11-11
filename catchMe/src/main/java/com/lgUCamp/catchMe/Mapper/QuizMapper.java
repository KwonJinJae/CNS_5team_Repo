package com.lgUCamp.catchMe.Mapper;

import com.lgUCamp.catchMe.Controller.CarbonInfoPaging.SelectCriteria;
import com.lgUCamp.catchMe.DTO.Quiz.Quiz;
import com.lgUCamp.catchMe.DTO.Quiz.UserQuizScoreDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface QuizMapper {

    UserQuizScoreDTO userQuizScoreCateOne();

    UserQuizScoreDTO userQuizScoreCateTwo();

    UserQuizScoreDTO userQuizScoreCateThree();

    UserQuizScoreDTO userQuizScoreCateFour();

    /* 퀴즈 조회 */
    ArrayList<Quiz> selectQuizList(SelectCriteria selectCriteria);

    Integer quizCateListCount(int quizCateNo);
}
