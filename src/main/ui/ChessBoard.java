package main.ui;


import main.model.Board;
import main.model.Square;
import main.model.pieces.Name;
import main.model.pieces.Piece;
import main.sound.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * represent the chessBoard
 */
public class ChessBoard extends JPanel {
    public static final int WIDTH = 680;
    public static final int HEIGHT = 680;
    public Color lightColour;
    public Color darkColour;
    private JLabel background;
    private final Board board;
    private Square selectedSquare;
    private boolean pieceIsSelected;
    private Color lightHighlightColour;
    private Color darkHighlightColour;
    private BoardTheme boardTheme = BoardTheme.GREEN;
    private final SoundManager soundManager;
    private int xCor;
    private int yCor;

    public ChessBoard() {
        this.board = new Board();
        pieceIsSelected = false;
        soundManager = new SoundManager();
        initialiseGraphics();
        renderSquares();
        mouseEvents();
        mouseMotionEvents();
        setVisible(true);
    }

    public void setBoardTheme(BoardTheme boardTheme) {
        this.boardTheme = boardTheme;
    }

    // EFFECTS: initializes the window with WIDTH and HEIGHT and a bg colour
    public void initialiseGraphics() {
        setSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(255, 255, 255));
        setLayout(new GridLayout(8, 8));
    }

    //EFFECTS: renders the squares present in Board on the chessBoard
    public void renderSquares() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                JComponent square = new JPanel();
                setupSquare(i, j, square);
                add(square);
                if (board.getSquareAt(i, j).getPiece() != null) {
                    setPieceImage(i, j);
                    square.add(background);
                }
            }
        }
    }

    //REQUIRES: i and j must be within [0,7]
    //MODIFIES: square
    //EFFECTS: sets up the given square of the ChessBoard
    private void setupSquare(int i, int j, JComponent square) {
        int width = WIDTH / 8;
        int height = HEIGHT / 8;
        square.setPreferredSize(new Dimension(width, height));
        square.setBorder(null);
        square.setOpaque(true);
        setupSquareBgColour(i, j, square);
    }

    //REQUIRES: i and j must be within [0,7]
    //MODIFIES: this
    //EFFECTS: sets up the background image of the piece at the given position in this.board
    private void setPieceImage(int i, int j) {
        ImageIcon icon = new ImageIcon(board.getSquareAt(i, j).getPiece().getImage());
        Image img = icon.getImage();
        Image tempImage = img.getScaledInstance(WIDTH / 9, HEIGHT / 9, Image.SCALE_SMOOTH);
        icon = new ImageIcon(tempImage);
        background = new JLabel("", icon, JLabel.CENTER);
    }

    //REQUIRES: i and j must be within [0,7], square != null
    //MODIFIES: square
    //EFFECTS: applies lightColour as the bg colour to the given square if i+j is even, else applies the darkColour
    private void setupSquareBgColour(int i, int j, JComponent square) {
        applyTheme();
        if ((i + j) % 2 == 0) {
            square.setBackground(lightColour);
            if (pieceIsSelected && selectedSquare.getPiece().canGoTo(board.getSquareAt(i, j))) {
                square.setBackground(lightHighlightColour);
            }
        } else {
            square.setBackground(darkColour);
            if (pieceIsSelected && selectedSquare.getPiece().canGoTo(board.getSquareAt(i, j))) {
                square.setBackground(darkHighlightColour);
            }
        }
        if (selectedSquare != null && isThatSquare(i, j, selectedSquare) && selectedSquare.getPiece() != null) {
            square.setBackground(Color.RED);
        }
    }

    private boolean isThatSquare(int i, int j, Square square) {
        return square.getRow() == i && square.getColumn() == j;
    }


    //MODIFIES: this
    //EFFECTS: sets the value for lightColour and darkColour according to the current value of boardTheme
    private void applyTheme() {
        if (this.boardTheme == BoardTheme.GREEN) {
            lightColour = new Color(209, 255, 184, 255);
            darkColour = new Color(74, 146, 45);
            lightHighlightColour = new Color(228, 255, 0, 190);
            darkHighlightColour = new Color(238, 213, 8, 232);
        } else if (this.boardTheme == BoardTheme.CLASSIC) {
            lightColour = new Color(255, 255, 255, 255);
            darkColour = new Color(31, 31, 31);
        } else if (this.boardTheme == BoardTheme.PURPLE) {
            lightColour = new Color(248, 198, 255);
//            darkColour = new Color(123, 53, 128);
            darkColour = new Color(135, 69, 139);
        } else if (this.boardTheme == BoardTheme.LIGHT_GREY) {
            lightColour = new Color(255, 255, 255, 255);
            darkColour = new Color(180, 180, 180);
        } else if (this.boardTheme == BoardTheme.BROWN) {
            lightColour = new Color(224, 203, 175, 255);
            darkColour = new Color(147, 108, 43);
        } else if (this.boardTheme == BoardTheme.BLUE) {
            lightColour = new Color(189, 189, 255, 255);
            darkColour = new Color(80, 89, 193, 255);
        }
    }

    //EFFECTS: returns the square from board that encloses the given x and y coordinates
    private Square getSquareAt(int x, int y) {
        int j = getJ(x);
        int i = getI(y);
        return board.getSquareAt(i, j);
    }

    //EFFECTS: returns the i value for the given y coordinate
    private int getI(int y) {
        return y / (HEIGHT / 8);
    }

    //EFFECTS: returns the j value for the given x coordinate
    private int getJ(int x) {
        return x / (WIDTH / 8);
    }

    public void mouseEvents() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                xCor = getJ(x);
                yCor = getI(y);
                selectedSquare = getSquareAt(x, y);
                Piece piece = selectedSquare.getPiece();
                if (piece != null) {
                    if (piece.getName() == Name.KING) {
                        board.checkShortCastleAvailability(piece, yCor);
                    }
                    selectedSquare.getPiece().calculateValidSquares();
                    soundManager.play("./sounds/tick.wav");
                }
                pieceIsSelected = selectedSquare.getPiece() != null;
                refresh();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                selectedSquare = getSquareAt(x, y);
                pieceIsSelected = selectedSquare.getPiece() != null;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (xCor != getJ(x) || yCor != getI(y)) {
                    if (board.movePiece(selectedSquare.getRow(), selectedSquare.getColumn(), getI(y), getJ(x))) {
//                        board.showBoard();
                        selectedSquare = null;
                        pieceIsSelected = false;
                        refresh();
                    }
                }
            }
        });
    }

    public void mouseMotionEvents() {
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }

    //EFFECTS: refreshes the ChessBoard by removing all elements and adding them again
    public void refresh() {
        removeAll();
        renderSquares();
        repaint();
        validate();
    }
}