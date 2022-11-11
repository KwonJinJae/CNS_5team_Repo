package com.lgUCamp.catchMe.Service;

import com.lgUCamp.catchMe.Controller.CarbonInfoPaging.SelectCriteria;

import com.lgUCamp.catchMe.DTO.Quiz.Quiz;
import com.lgUCamp.catchMe.DTO.Quiz.UserQuizScoreDTO;
import com.lgUCamp.catchMe.Entity.QuizEntity;
import com.lgUCamp.catchMe.Mapper.QuizMapper;
import com.lgUCamp.catchMe.Repository.QuizRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService{

    @Autowired
    QuizMapper quizMapper;

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserQuizScoreDTO userQuizScoreOne() {
        return quizMapper.userQuizScoreCateOne();
    }

    @Override
    public UserQuizScoreDTO userQuizScoreTwo() {
        return quizMapper.userQuizScoreCateTwo();
    }

    @Override
    public UserQuizScoreDTO userQuizScoreThree() {
        return quizMapper.userQuizScoreCateThree();
    }

    @Override
    public UserQuizScoreDTO userQuizScoreFour() {
        return quizMapper.userQuizScoreCateFour();
    }

    /* 퀴즈 조회 */
    @Override
    public ArrayList<Quiz> selectQuizList(SelectCriteria selectCriteria) {

        return quizMapper.selectQuizList(selectCriteria);
    }

    @Override
    public int quizCateListCount(int quizCateNo) {
        return quizMapper.quizCateListCount(quizCateNo);
    }


    @Override
    public Map<String, Object> selectQuizSolveList(int searchCondition, Pageable pageable) {

        Page<QuizEntity> paging = null;

        List<Quiz> quizDTOList = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();

        if(1 == searchCondition){

            paging = quizRepository.findByQuizCateNo(1, pageable);

        } else if (2 == searchCondition) {

            System.out.println("페이징 객체 " + pageable);

            paging = quizRepository.findByQuizCateNo(2, pageable);


        } else if(3 == searchCondition){

            paging = quizRepository.findByQuizCateNo(3, pageable);

        } else if(4 == searchCondition){

           paging = quizRepository.findByQuizCateNo(4, pageable);
        }

        List<QuizEntity> quizList = paging.getContent();


        quizDTOList = quizList.stream().map(name -> modelMapper.map(name, Quiz.class)).collect(Collectors.toList());

        int currentPage = paging.getNumber();
        int maxPage = paging.getTotalPages();
        int startPage = (int) (currentPage / 5) * 5;
        int endPage = (int) (currentPage / 5) * 5 + 5;

        while (endPage > maxPage) {
            endPage -= 1;
        }

        map.put("quizList", quizDTOList);
        map.put("maxPage", maxPage);
        map.put("startPage", startPage);
        map.put("endPage", endPage);
        map.put("currentPage", currentPage);
        map.put("searchCondition", searchCondition);

        System.out.println("설마 여기까지..");

        return map;
    }


    /* 카테고리별 퀴즈 랭킹 1위 회원 조회 */
//    @Override
//    public ArrayList<UserQuizScore> userQuizScore() {
//
//        ArrayList<UserQuizScore> userQuizScores = new ArrayList<>();
//        userQuizScores.add(quizMapper.userQuizScoreCateOne());
//        userQuizScores.add(quizMapper.userQuizScoreCateTwo());
//        userQuizScores.add(quizMapper.userQuizScoreCateThree());
//        userQuizScores.add(quizMapper.userQuizScoreCateFour());
//
//        return ;
//    }


}
