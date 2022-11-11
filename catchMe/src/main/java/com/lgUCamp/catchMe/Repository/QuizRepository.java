package com.lgUCamp.catchMe.Repository;

import com.lgUCamp.catchMe.DTO.Quiz.Quiz;
import com.lgUCamp.catchMe.Entity.QuizEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, Long> {


    Page<QuizEntity> findByQuizCateNo(int searchCondition, Pageable pageable);

}
