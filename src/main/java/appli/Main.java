package appli;

import cake.Cake;
import dice.Dice;
import queue.Queue;
import tea.Tea;

public class Main {

    public static void main(String[] args) throws Exception {
        Queue.runAlgo();
        Cake.runAlgo();
        Tea.runAlgo();
        Dice.runAlgo();
    }
}
