package com.piotrkafel.stripe;

public class ShopOpeningTimeProblem {

    // I assume here that the input is well formatted. Remember to ask interviewer about it and if needed add input validation.
    public static int computePenalty(String log, int closingTime) {
        String[] logArray = log.split(" ");
        int penalty = 0;

        for(int i=0;i < logArray.length;i++) {
            if(i < closingTime) {
                if(logArray[i].equals("N")) penalty++;
            } else {
                if(logArray[i].equals("Y")) penalty++;
            }
        }

        return penalty;
    }

    public static int findBestClosingTime(String log) {
        int smallestPenalty = Integer.MAX_VALUE;
        int bestClosingTime = 0;

        String[] logArray = log.split(" ");

        for (int i = 0; i <= logArray.length; i++) {
            int penalty = computePenalty(log, i);
            if(penalty < smallestPenalty) {
                smallestPenalty = penalty;
                bestClosingTime = i;
            }
        }

        return bestClosingTime;
    }
}
