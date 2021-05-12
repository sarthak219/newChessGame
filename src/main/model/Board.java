package main.model;

import main.model.pieces.*;
import main.sound.SoundManager;

/**
 * represents a chess board with 64 squares
 */
public class Board {
    private final Square[][] board = new Square[8][8];
    private King whiteKing;
    private Rook whiteARook;
    private Rook whiteHRook;
    private King blackKing;
    private Rook blackARook;
    private Rook blackHRook;

    public Board() {
        initialiseBoard();
        placePieces();
        showBoard();
    }

    // EFFECTS: makes a chess board with 64 squares
    public void initialiseBoard() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                this.board[i][j] = new Square(i, j);
            }
        }
    }

    //EFFECTS: displays the board by showing names of all the 64 squares
    public void showBoard() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (this.board[i][j].getPiece() != null) {
                    System.out.printf("%-10s", this.board[i][j].getPiece().getName());
                } else {
                    System.out.printf("%-10s", "---");
                }
            }
            for (int j = 0; j < 8; ++j) {
                System.out.printf("%-10s", "(" + i + ", " + j + ")");
            }
            System.out.println();
        }

        System.out.println("---------------------------------------------------------------------------");
    }

    //MODIFIES: this
    //EFFECTS: places the pieces on the chess board
    public void placePieces() {
        setupBlackPieces();
        setupWhitePieces();
    }

    private void setupBlackPieces() {
        board[0][0].setPiece(new Rook(PieceColour.BLACK, this));
        blackARook = (Rook) board[0][0].getPiece();
        board[0][1].setPiece(new Knight(PieceColour.BLACK, this));
        board[0][2].setPiece(new Bishop(PieceColour.BLACK, this));
        board[0][3].setPiece(new Queen(PieceColour.BLACK, this));
        board[0][4].setPiece(new King(PieceColour.BLACK, this));
        blackKing = (King) board[0][4].getPiece();
        board[0][5].setPiece(new Bishop(PieceColour.BLACK, this));
        board[0][6].setPiece(new Knight(PieceColour.BLACK, this));
        board[0][7].setPiece(new Rook(PieceColour.BLACK, this));
        blackHRook = (Rook) board[0][7].getPiece();
        setupAssignedSquareForPieces(0);
        setupBlackPawns();
    }

    private void setupAssignedSquareForPieces(int i) {
        for (int j = 0; j < 8; ++j) {
            board[i][j].getPiece().setAssignedSquare(board[i][j]);
        }
    }

    private void setupBlackPawns() {
        for (int i = 0; i < 8; ++i) {
            board[1][i].setPiece(new Pawn(PieceColour.BLACK, this));
            board[1][i].getPiece().setAssignedSquare(board[1][i]);
        }
    }

    private void setupWhitePieces() {
        board[7][0].setPiece(new Rook(PieceColour.WHITE, this));
        whiteARook = (Rook) board[7][0].getPiece();
        board[7][1].setPiece(new Knight(PieceColour.WHITE, this));
        board[7][2].setPiece(new Bishop(PieceColour.WHITE, this));
        board[7][3].setPiece(new Queen(PieceColour.WHITE, this));
        board[7][4].setPiece(new King(PieceColour.WHITE, this));
        whiteKing = (King) board[7][4].getPiece();
        board[7][5].setPiece(new Bishop(PieceColour.WHITE, this));
        board[7][6].setPiece(new Knight(PieceColour.WHITE, this));
        board[7][7].setPiece(new Rook(PieceColour.WHITE, this));
        whiteHRook = (Rook) board[7][7].getPiece();
        setupAssignedSquareForPieces(7);
        setupWhitePawns();
    }

    private void setupWhitePawns() {
        for (int i = 0; i < 8; ++i) {
            board[6][i].setPiece(new Pawn(PieceColour.WHITE, this));
            board[6][i].getPiece().setAssignedSquare(board[6][i]);
        }
    }

    //EFFECTS: returns the square at the given coordinates from the chess board
    public Square getSquareAt(int row, int col) {
        return board[row][col];
    }

    //REQUIRES: i, j, x and y column must be between [0, 7]
    //MODIFIES: this
    //EFFECTS: replaces the piece present at the square at x, y with the piece present at the square at  i, j
    //         sets the piece present at i, j to null
    public boolean movePiece(int i, int j, int x, int y) {
        SoundManager soundManager = new SoundManager();
        if ((i != x || j != y) && board[i][j].getPiece() != null) {
            Piece piece = this.board[i][j].getPiece();
            checkShortCastleAvailability(piece, i);
            piece.calculateValidSquares();
            if (piece.canGoTo(board[x][y])) {
                if (checkIfCastling(piece, i, j, x, y)) {
                    return true;
                }

                boolean captured = board[x][y].getPiece() != null;
                this.board[x][y].setPiece(piece);
                this.board[x][y].getPiece().setAssignedSquare(board[x][y]);
                this.board[i][j].setPiece(null);
                checkRookAndKingMovements(piece);
                playSound(soundManager, captured);
                return true;
            }
            soundManager.play("./sounds/error.wav");
        }
        return false;
    }

    public boolean isShortCastling(int i, int j, int x, int y) {
        return (i == x) && (y - j == 2);
    }

    public boolean checkIfCastling(Piece piece, int i, int j, int x, int y) {
        if (piece.getName() == Name.KING) {
            if (isShortCastling(i, j, x, y)) {
                if (((King) (piece)).canShortCastle()) {
                    shortCastle(piece.getColour(), i);
                    return true;
                } else return false;
            }
        }
        return false;
    }

    private void playSound(SoundManager soundManager, boolean captured) {
        if (captured) {
            soundManager.play("./sounds/check.wav");
        } else {
            soundManager.play("./sounds/pieceMoving.wav");
        }
    }

    private void checkRookAndKingMovements(Piece piece) {
        if (piece.getName() == Name.ROOK && !((Rook) piece).hasMoved()) {
            if (piece == whiteARook || piece == whiteHRook) {
                whiteKing.setCanShortCastle(false);
            } else if (piece == blackARook || piece == blackHRook) {
                blackKing.setCanShortCastle(false);
            }
            ((Rook) piece).setHasMoved(true);
        } else if (piece.getName() == Name.KING) {
            assert piece instanceof King;
            ((King) piece).setHasMoved(true);
            ((King) piece).setCanShortCastle(false);
        }
    }

    private void shortCastle(PieceColour colour, int row) {
        if (colour == PieceColour.WHITE) {
            if (!whiteKing.hasMoved() && !whiteHRook.hasMoved() && emptyRightSide(row)) {
                board[row][6].setPiece(board[row][4].getPiece());
                board[row][6].getPiece().setAssignedSquare(board[row][6]);
                board[row][4].setPiece(null);
                board[row][5].setPiece(board[row][7].getPiece());
                board[row][5].getPiece().setAssignedSquare(board[row][5]);
                board[row][7].setPiece(null);
                whiteKing.setHasMoved(true);
                whiteHRook.setHasMoved(true);
                whiteKing.setCanShortCastle(false);
            }
        } else if (colour == PieceColour.BLACK) {
            if (!blackKing.hasMoved() && !blackHRook.hasMoved() && emptyRightSide(row)) {
                board[row][6].setPiece(board[row][4].getPiece());
                board[row][6].getPiece().setAssignedSquare(board[row][6]);
                board[row][4].setPiece(null);
                board[row][5].setPiece(board[row][7].getPiece());
                board[row][5].getPiece().setAssignedSquare(board[row][5]);
                board[row][7].setPiece(null);
                blackKing.setHasMoved(true);
                blackHRook.setHasMoved(true);
                blackKing.setCanShortCastle(false);
            }
        }
    }

    public void checkShortCastleAvailability(Piece piece, int row) {
        if (piece.getColour() == PieceColour.WHITE) {
            if (!whiteKing.hasMoved() && !whiteHRook.hasMoved() && emptyRightSide(row)) {
                whiteKing.setCanShortCastle(true);
            }
        } else if (piece.getColour() == PieceColour.BLACK) {
            if (!blackKing.hasMoved() && !blackHRook.hasMoved() && emptyRightSide(row)) {
                blackKing.setCanShortCastle(true);
            }
        }
    }

    public boolean emptyRightSide(int row) {
        return getSquareAt(row, 5).getPiece() == null && getSquareAt(row, 6).getPiece() == null;
    }
}

