package com.lgUCamp.catchMe.Challenge;

import java.util.ArrayList;

public interface ChallengeService {
    ArrayList<Challenge> selectNoticeAll();

    ArrayList<ChallengeCertify> selectCertifyAll();

    ArrayList<Challenge> selectNoticeThree();
    void insertNotice(Challenge challenge);
    void insertCertify(ChallengeCertify challengeCertify);

    void deleteNotice(int cNoticeNo);
    void deleteCertify(int cProofNo);
}
