package com.lgUCamp.catchMe.Challenge;

import java.util.ArrayList;

public interface ChallengeService {
    ArrayList<Challenge> selectAll();
    Challenge selectOne(int cNoticeNo);
}
