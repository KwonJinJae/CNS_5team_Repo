package com.lgUCamp.catchMe.Challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ChallengeImpl implements ChallengeService {

    @Autowired
    ChallengeMapper challengeMapper;

    @Override
    public ArrayList<Challenge> selectAll() {
        System.out.println("확인확인1"+challengeMapper.selectAll());
        return challengeMapper.selectAll();
    }

    @Override
    public Challenge selectOne(int cNoticeNo) {
        System.out.println("확인확인2"+challengeMapper.selectOne(1));
        return challengeMapper.selectOne(cNoticeNo);
    }
}
