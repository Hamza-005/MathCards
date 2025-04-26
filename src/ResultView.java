import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// This class represents the result view of the application
public class ResultView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // Constructor of the ResultView class
    public ResultView(int cn, int ca, int[] timeList) {
        // Set the default close operation
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Calculate the percentage of correct answers
        double percent = (double) ca / cn * 100;
        String percentStr = String.format("%.2f", percent);
        // Format the elapsed time
        String elapsedTimeStr = String.format("%02d:%02d", timeList[0], timeList[1]);

        // Calculate the average time per card
        double averageSeconds = (double) (timeList[0] * 60 + timeList[1]) / cn;
        timeList[0] = (int) averageSeconds / 60;
        timeList[1] = (int) averageSeconds % 60;
        // Format the time per card
        String timePerCardStr = String.format("%02d:%02d", timeList[0], timeList[1]);

        // Set the title and default close operation
        setTitle("Results");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 516, 367);
        contentPane = new JPanel();
        contentPane.setBackground(Color.decode("#99b4d1"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Set the content pane and layout
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(4, 4, 0, 0));

        // Create and add the labels to the content pane
        JLabel label1 = new JLabel("Tried Math Cards:");
        label1.setFont(new Font("Tahoma", Font.BOLD, 18));
        contentPane.add(label1);

        JLabel cardNum = new JLabel(cn + " card");
        cardNum.setFont(new Font("Tahoma", Font.BOLD, 18));
        contentPane.add(cardNum);

        JLabel label2 = new JLabel("Correct Answers:");
        label2.setFont(new Font("Tahoma", Font.BOLD, 18));
        contentPane.add(label2);

        JLabel correctAnswers = new JLabel(ca + " (" + percentStr + "%)");
        correctAnswers.setFont(new Font("Tahoma", Font.BOLD, 18));
        contentPane.add(correctAnswers);

        JLabel label3 = new JLabel("Elapsed Time:");
        label3.setFont(new Font("Tahoma", Font.BOLD, 18));
        contentPane.add(label3);

        JLabel elapsedTime = new JLabel(elapsedTimeStr);
        elapsedTime.setFont(new Font("Tahoma", Font.BOLD, 18));
        contentPane.add(elapsedTime);

        JLabel label4 = new JLabel("Time per Card:");
        label4.setFont(new Font("Tahoma", Font.BOLD, 18));
        contentPane.add(label4);

        JLabel tpc = new JLabel(timePerCardStr);
        tpc.setFont(new Font("Tahoma", Font.BOLD, 18));
        contentPane.add(tpc);
    }
}