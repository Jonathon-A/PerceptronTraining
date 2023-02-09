package perceptrontraining;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;

public class graph extends JFrame {

    public graph(String title) {
        super(title);
        setLayout(new FlowLayout());
    }
    private double lineX1 = 0, lineX2 = 0, lineY1 = 0, lineY2 = 0;

    public void setLine(double x1, double y1, double x2, double y2) {
        lineX1 = x1;
        lineY1 = y1;
        lineX2 = x2;
        lineY2 = y2;
    }

    private ArrayList<int[]> points = new ArrayList<>();

    public void setCords(int[][] coords) {
        points.clear();
        for (int[] cord : coords) {
            points.add(cord);
        }
    }
    
    String status = "";

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        g.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
        g.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
        g.drawString("0", this.getWidth() / 2 - 10, this.getHeight() / 2 - 5);

        g.drawString(status, this.getWidth() / 2 + 10, this.getHeight() - 15);
        //Draws coords
        for (int[] point : points) {
            if (point[2] == 1) {
                g.setColor(Color.blue);
            } else {
                g.setColor(Color.red);
            }
            g.fillOval(this.getWidth() / 2 + point[0] - 5, this.getHeight() / 2 - point[1] - 5, 10, 10);
        }
        //Draws line
        g.setColor(Color.green);
        g.drawLine((int) (this.getWidth() / 2 + lineX1), (int) (this.getHeight() / 2 - lineY1), (int) (this.getWidth() / 2 + lineX2), (int) (this.getHeight() / 2 - lineY2));
    }
}
