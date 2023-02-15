package perceptrontraining;

import java.util.Random;
import javax.swing.JFrame;

public class AdalineTraining {

    public static void train() throws InterruptedException {

        //Training data
        double[][] traningSet = new double[][]{
            {1, 1, 4, -1},
            {1, 2, 9, 1},
            {1, 5, 6, 1},
            {1, 4, 5, 1},
            {1, 6, 0.7, -1},
            {1, 1, 1.5, -1}};

        //Graph is a JFrame
        graph graph = new graph("Adaline Training");
        graph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        graph.setSize(1000, 1000);
        graph.setResizable(false);
        //Calculate integer graph cordinates
        int[][] coords = new int[traningSet.length][3];
        for (int i = 0; i < traningSet.length; i++) {
            coords[i][0] = (int) Math.round(traningSet[i][1] * 30);
            coords[i][1] = (int) Math.round(traningSet[i][2] * 30);
            coords[i][2] = (int) traningSet[i][3];
        }
        graph.setCords(coords);
        //Displays the graph
        graph.setVisible(true);

        Random rand = new Random();

        //Initially set all weights to random (-100 to 100)
        double[] weights = new double[]{
            rand.nextDouble(),
            rand.nextDouble(),
            rand.nextDouble()};
        
        double learningParameter = 0.01;

        int iterations = 500;

        for (int i = 0; i < iterations; i++) {
            boolean stable = true;
            //Loop trough every data point
            for (double[] sample : traningSet) {

                double output = weights[0]
                        + weights[1] * sample[1]
                        + weights[2] * sample[2];

                double C = sample[3];
                
                //new weight = old weight + learningParameter * (C - output) * sample;
                weights[0] = weights[0] + learningParameter * (C - output) * sample[0];
                weights[1] = weights[1] + learningParameter * (C - output) * sample[1];
                weights[2] = weights[2] + learningParameter * (C - output) * sample[2];

                //If the output is incorrect
                if ((output >= 0 && C == -1) || (output <= 0 && C == 1)) {
                    stable = false;
                }

            }
            String status = String.format("Epoch: %d\t W0: %2.4f \t W1: %2.4f\t W2: %2.4f", i + 1, weights[0], weights[1], weights[2]);
            if (stable) {
                status += " Stable";
            }
            System.out.println(status);
            graph.setStatus(status);

            double gradient = -weights[1] / weights[2];
            double intersect = (-weights[0] / weights[2]) * 30;

            graph.setLine(0, intersect, 1000, gradient * 1000 + intersect);
            Thread.sleep(10);
        }
    }

}
