import model.Level;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Random;

public class MazePanel extends JPanel implements KeyListener {
    Level level ;
    int rows ;
    int cols ;
    private final int cellSize = 30;
    private final Player player;
    List<int[]> path;

    private int goalRow ;
    private int goalCol;
    private final MazeGenerator generator;

    private final Image goalImage;
    private GameState gameState = GameState.NOT_STARTED;

    public MazePanel(RightSidePanel rightSidePanel) {

        level=new Level(1);
        rows=level.getRows();
        cols=level.getCols();
        goalCol=cols-1;
        goalRow=rows-1;
        generator = new MazeGenerator(rows, cols);
        generator.generateMaze();

        player = new Player(0, 0);
        setPreferredSize(new Dimension(cols*cellSize + 40, rows*cellSize + 40));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        goalImage = new ImageIcon("goal.png").getImage();
    }
    public void generateNewMaze() {
        path = null;

        generator.generateMaze();
        player.resetPosition(0, 0);
        generateRandomGoal();
        openExitWall();
        repaint();
        requestFocusInWindow();
    }
    public void solve() {
        path = null;
        MazeSolver solver = new MazeSolver(rows, cols,
                generator.wallsTop,
                generator.wallsBottom,
                generator.wallsLeft,
                generator.wallsRight);
       path= solver.solve(0,0,goalRow,goalCol);
        player.resetPosition(0, 0);

        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int wallThickness = 6;
        g2.setStroke(new BasicStroke(wallThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(Color.DARK_GRAY);
        int offset = 20;

        for (int r=0;r<rows;r++)
            for (int c=0;c<cols;c++){
                int x = c*cellSize + offset;
                int y = r*cellSize + offset;
                if (generator.wallsTop[r][c]) g2.drawLine(x,y,x+cellSize,y);
                if (generator.wallsRight[r][c]) g2.drawLine(x+cellSize,y,x+cellSize,y+cellSize);
                if (generator.wallsBottom[r][c]) g2.drawLine(x,y+cellSize,x+cellSize,y+cellSize);
                if (generator.wallsLeft[r][c]) g2.drawLine(x,y,x,y+cellSize);
            }
        if (path != null) {
            g2.setColor(Color.BLUE);
            for (int i = 0; i < path.size() - 1; i++) {
                int[] cell1 = path.get(i);
                int[] cell2 = path.get(i + 1);
                int x1 = offset + cell1[1] * cellSize + cellSize / 2;
                int y1 = offset + cell1[0] * cellSize + cellSize / 2;
                int x2 = offset + cell2[1] * cellSize + cellSize / 2;
                int y2 = offset + cell2[0] * cellSize + cellSize / 2;
                g2.drawLine(x1, y1, x2, y2);
            }
        }


        drawPlayer(g2, offset);
        drawGoal(g2, offset);
    }

    private void drawPlayer(Graphics2D g2, int offset) {
        int margin = 6;
        int size = cellSize - 2 * margin;

        int x = player.getCol() * cellSize + offset + margin;
        int y = player.getRow() * cellSize + offset + margin;

        // Border أبيض
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawOval(x - 1, y - 1, size + 2, size + 2);

        // Player نفسه
        g2.setColor(Color.RED);
        g2.fillOval(x, y, size, size);
    }


    private void drawGoal(Graphics2D g2, int offset){
        int x = goalCol*cellSize + offset;
        int y = goalRow*cellSize + offset;
        g2.drawImage(goalImage,x,y,cellSize,cellSize,this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameState != GameState.PLAYING) return;

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) player.moveUp(generator.wallsTop);
        if (key == KeyEvent.VK_DOWN) player.moveDown(generator.wallsBottom, rows);
        if (key == KeyEvent.VK_LEFT) player.moveLeft(generator.wallsLeft);
        if (key == KeyEvent.VK_RIGHT) player.moveRight(generator.wallsRight, cols);
        if (player.getRow() == goalRow && player.getCol() == goalCol) {
            boolean again = CustomMessage.showDialog(
                    this,
                    "🎉 You Win!",
                    "You reached the exit!",
                    "trophy.png",
                    "Play Again",
                    null,
                    new Color(255, 223, 186)
            );
            if (again) {
                generateNewMaze();
                rightSidePanel.resetGame();
                rightSidePanel.startGame();
            }
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    private void generateRandomGoal() {
        Random rand1 = new Random();

        Random rand = new Random();

            goalRow = rows - 1;
            goalCol = rand.nextInt(cols);


    }
    private void openExitWall() {
        generator.wallsBottom[goalRow][goalCol] = false;
    }


    public void setGameState(GameState gameState) {
       this.gameState=gameState;
    }
}
