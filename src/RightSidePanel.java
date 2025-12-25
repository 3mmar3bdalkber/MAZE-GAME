import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RightSidePanel extends JPanel {

    private Timer gameTimer;
    private int seconds = 0;
    private JLabel timeValue;
    private JLabel scoreValue;
     private JLabel levelValue;
    private JPanel levelBox;

    private int score = 1000;

    private GameOverListener gameOverListener;

    public void setGameOverListener(GameOverListener listener) {
        this.gameOverListener = listener;
    }


    public RightSidePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(220, 600));
        setBackground(new Color(245, 245, 245));

        scoreValue = new JLabel("0");
        timeValue = new JLabel("00:00");
        levelValue = new JLabel("Easy");
        add(Box.createVerticalStrut(20));
        add(createInfoBox("SCORE", scoreValue, new Color (233, 54, 90)));   // Red
        add(Box.createVerticalStrut(15));

        add(createInfoBox("TIME", timeValue, new Color(14,128,132)));      // Blue
        add(Box.createVerticalStrut(15));

        levelBox = createInfoBox("LEVEL", levelValue, new Color(0, 180, 100));
        add(levelBox);

        add(Box.createVerticalStrut(25));
        add(createHowToPlay());


        initTimer();

    }



    private JPanel createInfoBox(String title, JLabel value, Color color) {
        JPanel box = new JPanel(new GridLayout(2, 1));
        box.setPreferredSize(new Dimension(200, 90));
        box.setBackground(color);
        box.setBorder(new LineBorder(new Color(180, 180, 180), 2, true));

        JLabel t = new JLabel(title, SwingConstants.CENTER);
        t.setFont(new Font("Arial", Font.BOLD, 14));

        value.setHorizontalAlignment(SwingConstants.CENTER);
        value.setFont(new Font("Arial", Font.BOLD, 18));
        t.setForeground(Color.WHITE);
        value.setForeground(Color.WHITE);
        box.add(t);
        box.add(value);
        return box;
    }
    private JPanel createHowToPlay() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        p.setBorder(new LineBorder(new Color(180, 180, 180), 2, true));

        p.add(new JLabel("How to Play"));
        p.add(new JLabel("→ Use arrow keys to move"));
        p.add(new JLabel("→ Reach the green flag"));
        p.add(new JLabel("→ Watch DFS solve the maze"));
        p.add(new JLabel("→ Faster time = higher score"));

        return p;
    }

    private void initTimer() {

      gameTimer=new Timer(1000, e -> {
          seconds++;
          int min=seconds/60;
          int sec=seconds%60;
          String time=String.format("%02d:%02d", min, sec);
          timeValue.setText(time);
          if(score>0){
              score-=10;
              setScore(score);

          }else {
              stopGame();
              if (gameOverListener != null) {
                  gameOverListener.onGameOver();
              }
          }


      }

      );
    }
    public void setLevelText(String level) {
        levelValue.setText(level);
    }
    public void setLevelColor(Color color) {
        levelBox.setBackground(color);
    }


    public void setScore(int score) {
        scoreValue.setText(String.valueOf(score));


    }
    public int getScore() {
      return score;
    }

    public void startGame() {
        gameTimer.start();
    }

    public void stopGame() {
        gameTimer.stop();
//        timeValue.setText("00:00");
//        setScore(0);
    }

    public void resetGame() {
        seconds = 0;
        score = 1000;
        timeValue.setText("00:00");
        setScore(score);
        gameTimer.restart();
    }


}


