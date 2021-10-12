package tea;

import java.util.Scanner;

public class Tea {
    public static final int NB_TEA_MAX = 4;
    public static final int NB_PLAYER_MAX = 5;

    private Tea() {}

    /**
     * 13012 "Identifying tea"
     * @throws Exception if invalid input for the correct tea number
     */
    public static void runAlgo() throws Exception {
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        assertAnswerLimit(answer);
        int correctAnswer = 0;
        for(int i = 0; i < NB_PLAYER_MAX; i++) {
            if(sc.nextInt() == answer) correctAnswer++;
        }
        System.out.println(correctAnswer);
    }

    private static void assertAnswerLimit(int answer) throws Exception {
        if(!(answer > 0 && answer <= NB_TEA_MAX)) throw new Exception("Invalid tea number !");
    }
}
