package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * represents the JPanel on which the game is displayed
 */
public class GameScreen extends JPanel implements ActionListener {
    public int width;
    public int height;
    private ChessBoard chessBoard = new ChessBoard();
    private JButton newGameButton;

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
        initialiseScreen();
        add(chessBoard);
        setupNewGameButton();
        addThemeRadioButtons();
        setVisible(true);
    }

    //EFFECTS: initialises the GameScreen
    public void initialiseScreen() {
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(19, 19, 19));
        setBounds(0, 0, width, height);
    }

    //MODIFIES: this.newGameButton
    //EFFECTS: sets up the newGameButton
    public void setupNewGameButton() {
        newGameButton = new JButton("New Game");
        initialiseButton();
        newGameButton.addActionListener(e -> newGame());
        add(newGameButton);
    }

    //EFFECTS: initialises the given button to match the theme of the game
    private void initialiseButton() {
        newGameButton.setFont(new Font("helvetica", Font.PLAIN, 16));
        newGameButton.setPreferredSize(new Dimension(width / 5, 40));
        newGameButton.setBorder(null);
        newGameButton.setOpaque(true);
        newGameButton.setBackground(new Color(217, 217, 217));
        newGameButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    //MODIFIES: this
    //EFFECTS: resets the this.board to a new ChessBoard, removes all the elements from the GameScreen, re-adds them
    // to GameScreen and then refreshes the frame
    public void newGame() {
        removeAll();
        chessBoard = new ChessBoard();
        add(chessBoard);
        add(newGameButton);
        addThemeRadioButtons();
        repaint();
        validate();
    }

    //EFFECTS: adds radio buttons to with different themes to choose from
    public void addThemeRadioButtons() {
        ButtonGroup theme = new ButtonGroup();
        JPanel themePanel = new JPanel();
        themePanel.setOpaque(false);
        JLabel themeLabel = new JLabel("    Choose theme: ");
        themeLabel.setForeground(Color.WHITE);

        themePanel.add(themeLabel);
        for (BoardTheme boardTheme : BoardTheme.values()) {
            JRadioButton themeOption = new JRadioButton(boardTheme.name().toLowerCase(Locale.ROOT));
            if (String.valueOf(boardTheme).equalsIgnoreCase("green")) {
                themeOption.setSelected(true);
                themeOption.setText(themeOption.getText());
            }
            theme.add(themeOption);
            themeOption.addActionListener(this);
            themeOption.setForeground(Color.WHITE);
            themePanel.add(themeOption);
        }
        add(themePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButton tempButton = (JRadioButton) e.getSource();
        for (BoardTheme boardTheme : BoardTheme.values()) {
            if (tempButton.getText().equalsIgnoreCase(boardTheme.name())) {
                chessBoard.setBoardTheme(boardTheme);
                chessBoard.refresh();
            }
        }
    }
}
