import model.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class navPanel extends JPanel {

    public navPanel(MazePanel mazePanel, RightSidePanel rightSidePanel) {

        setLayout(new BorderLayout());
        setBackground(AppColors.HEADER_COLOR);
        setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        leftPanel.setOpaque(false);

        ImageIcon icon = new ImageIcon(
                new ImageIcon("solution.png")
                        .getImage()
                        .getScaledInstance(30, 30, Image.SCALE_SMOOTH)
        );

        JLabel title = new JLabel("Maze Adventure");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        leftPanel.add(new JLabel(icon));
        leftPanel.add(title);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
        rightPanel.setOpaque(false);
        ImageIcon volumeOn = resizeIcon(
                new ImageIcon(getClass().getResource("/icons/volume_on.png")),
                24
        );

        ImageIcon volumeOff = resizeIcon(
                new ImageIcon(getClass().getResource("/icons/volume_off.png")),
                24
        );



        JButton startBtn = new JButton("Start Game");
        JButton newGameBtn = new JButton("New Game");
        JButton solveBtn = new JButton("Solve");
        JButton exitBtn = new JButton("Exit");
        JButton muteBtn = new JButton(volumeOn);
        muteBtn.setBorderPainted(false);
        muteBtn.setFocusPainted(false);
        muteBtn.setContentAreaFilled(false);
        muteBtn.setOpaque(false);
        muteBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        styleButton(startBtn, new Color(11, 198, 61));
        styleButton(newGameBtn, new Color(120, 120, 120));
        styleButton(solveBtn, new Color(14,128,132));
        styleButton(exitBtn, Color.RED);

        solveBtn.setEnabled(false);
        newGameBtn.setEnabled(false);

        JMenuBar levelMenuBar = new JMenuBar();
        levelMenuBar.setBorder(null);

        JMenu levelMenu = new JMenu("Choose Level");
        levelMenu.setFont(new Font("Arial", Font.BOLD, 14));
        levelMenu.setOpaque(true);
        levelMenu.setForeground(Color.WHITE);
        levelMenu.setBackground(new Color(218, 132, 19));


        JMenuItem easyItem = new JMenuItem("Easy");
        JMenuItem mediumItem = new JMenuItem("Medium");
        JMenuItem hardItem = new JMenuItem("Hard");

        styleMenuItem(easyItem, new Color(0, 180, 100));
        styleMenuItem(mediumItem, new Color(218, 132, 19));
        styleMenuItem(hardItem, new Color(200, 60, 60));

        levelMenu.addActionListener(e -> {
            SoundManager.play(Sound.CLICK);

        });

        easyItem.addActionListener(e -> {
            SoundManager.play(Sound.SELECT);

            mazePanel.setLevel(new Level(1, "Easy",new Color(0, 180, 100)));
        });
        mediumItem.addActionListener(e ->{
            SoundManager.play(Sound.SELECT);

                    mazePanel.setLevel(new Level(2, "Medium",new Color(218, 132, 19)));
                });
        hardItem.addActionListener(e -> {
            SoundManager.play(Sound.SELECT);

            mazePanel.setLevel(new Level(3, "Hard", new Color(200, 60, 60)));
        });

        levelMenu.add(easyItem);
        levelMenu.add(mediumItem);
        levelMenu.add(hardItem);
        levelMenuBar.add(levelMenu);
        rightPanel.add(muteBtn);

        rightPanel.add(startBtn);
        rightPanel.add(newGameBtn);
        rightPanel.add(levelMenuBar);
        rightPanel.add(solveBtn);
        rightPanel.add(exitBtn);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        muteBtn.addActionListener(e -> {
            SoundManager.toggleMute();

            if (SoundManager.isMuted()) {
                muteBtn.setIcon(volumeOff);
            } else {
                muteBtn.setIcon(volumeOn);
                SoundManager.play(Sound.CLICK); // feedback
            }
            mazePanel.requestFocusInWindow();

        });

        startBtn.addActionListener(e -> {
            SoundManager.play(Sound.CLICK);

            mazePanel.setGameState(GameState.PLAYING);
            SoundManager.play(Sound.START);

            rightSidePanel.startGame();

            solveBtn.setEnabled(true);
            newGameBtn.setEnabled(true);

            startBtn.setEnabled(false);
            mazePanel.requestFocusInWindow();
        });

        newGameBtn.addActionListener(e -> {
            SoundManager.play(Sound.CLICK);

            boolean confirm = CustomMessage.showDialog(
                    this,
                    "Confirm",
                    "Start a new game?",
                    null,
                    "Yes",
                    "No",
                    Color.WHITE
            );

            if (confirm) {
                SoundManager.play(Sound.START);

                mazePanel.setGameState(GameState.PLAYING);
                mazePanel.generateNewMaze();
                rightSidePanel.resetGame();
                rightSidePanel.startGame();
                mazePanel.requestFocusInWindow();
            }else{
                SoundManager.play(Sound.CLICK);

            }
        });

        solveBtn.addActionListener(e -> {
            SoundManager.play(Sound.CLICK);

            CustomMessage.showDialog(
                    this,
                    "Info",
                    "DFS is used to solve the maze",
                    null,
                    "OK",
                    null,
                    Color.WHITE
            );

            mazePanel.setGameState(GameState.SOLVING);
            rightSidePanel.stopGame();
            mazePanel.solve();
        });


        exitBtn.addActionListener(e -> {
            SoundManager.play(Sound.CLICK);

            boolean confirm = CustomMessage.showDialog(
                    this,
                    "Confirm",
                    "Are you sure?",
                    null,
                    "Yes",
                    "No",
                    Color.WHITE
            );

            if (confirm) {
                SoundManager.play(Sound.CLICK);

                System.exit(0);
            }else {
                SoundManager.play(Sound.CLICK);

            }
        });
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void styleMenuItem(JMenuItem item, Color bg) {
        item.setFont(new Font("Arial", Font.BOLD, 13));
        item.setOpaque(true);
        item.setBackground(bg);
        item.setForeground(Color.WHITE);
        item.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    private ImageIcon resizeIcon(ImageIcon icon, int size) {
        Image img = icon.getImage().getScaledInstance(
                size, size, Image.SCALE_SMOOTH
        );
        return new ImageIcon(img);
    }

}
