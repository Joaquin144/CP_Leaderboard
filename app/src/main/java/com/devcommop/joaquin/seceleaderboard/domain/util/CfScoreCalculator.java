package com.devcommop.joaquin.seceleaderboard.domain.util;

public class CfScoreCalculator {

    /**
     *
     * @param rank stands for the rank of candidate in contest.
     *             If the candidate has not given the contest then rank must be provided as -1
     * @return [score] The score of the candidate as per decided by SECE society
     */
    public static int getScore(int rank) {
        if(rank == 0)  return 0;
        if(rank <= 1000) return 10;
        if(rank <= 2000) return 9;
        if(rank <= 3000) return 8;
        if(rank <= 5000) return 7;
        if(rank <= 7500) return 6;
        if(rank <= 10000) return 5;
        else return 3;
    }

}
