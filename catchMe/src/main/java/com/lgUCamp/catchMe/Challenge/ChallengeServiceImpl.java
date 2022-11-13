package com.lgUCamp.catchMe.Challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    ChallengeMapper challengeMapper;

    @Override
    public ArrayList<Challenge> selectNoticeAll() {
//        System.out.println("확인확인1"+challengeMapper.selectAll());

        return challengeMapper.selectNoticeAll();
    }

    @Override
    public ArrayList<ChallengeCertify> selectCertifyAll() {
        return challengeMapper.selectCertifyAll();
    }

    @Override
    public ArrayList<Challenge> selectNoticeThree() {
       return challengeMapper.selectNoticeThree();
    }

    @Override
    public ChallengeCertify selectCertifyOne(int cProofNo) {
        return challengeMapper.selectCertifyOne(cProofNo);
    }

    @Override
    public void insertNotice(Challenge challenge) {
        challengeMapper.insertNotice(challenge);
    }

    @Override
    public void insertCertify(ChallengeCertify challengeCertify) {
        challengeMapper.insertCertify(challengeCertify);

    }

    @Override
    public void deleteNotice(int cNoticeNo) {
        challengeMapper.deleteNotice(cNoticeNo);

    }

    @Override
    public void deleteCertify(int cProofNo) {
        challengeMapper.deleteCertify(cProofNo);

    }

    @Override
    public void updateCertify(ChallengeCertify challengeCertify) {
        challengeMapper.updateCertify(challengeCertify);
    }


}
