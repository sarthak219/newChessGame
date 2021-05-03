package main.model;

import main.model.pieces.*;

/**
 * represents a chess board with 64 squares
 */
public class Board {
    private final Square[][] board = new Square[8][8];

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

    //EFFECTS: places the pieces on the chess board
    public void placePieces() {
        setupBlackPieces();
        setupWhitePieces();
    }

    private void setupBlackPieces() {
        board[0][0].setPiece(new Rook(PieceColour.BLACK, this));
        board[0][1].setPiece(new Knight(PieceColour.BLACK, this));
        board[0][2].setPiece(new Bishop(PieceColour.BLACK, this));
        board[0][3].setPiece(new Queen(PieceColour.BLACK, this));
        board[0][4].setPiece(new King(PieceColour.BLACK, this));
        board[0][5].setPiece(new Bishop(PieceColour.BLACK, this));
        board[0][6].setPiece(new Knight(PieceColour.BLACK, this));
        board[0][7].setPiece(new Rook(PieceColour.BLACK, this));
        setupAssignedSquareForPiece(0);
        setupBlackPawns();
    }

    private void setupAssignedSquareForPiece(int i) {
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
        board[7][1].setPiece(new Knight(PieceColour.WHITE, this));
        board[7][2].setPiece(new Bishop(PieceColour.WHITE, this));
        board[7][3].setPiece(new Queen(PieceColour.WHITE, this));
        board[7][4].setPiece(new King(PieceColour.WHITE, this));
        board[7][5].setPiece(new Bishop(PieceColour.WHITE, this));
        board[7][6].setPiece(new Knight(PieceColour.WHITE, this));
        board[7][7].setPiece(new Rook(PieceColour.WHITE, this));
        setupAssignedSquareForPiece(7);
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
        if ((i != x || j != y) && board[i][j].getPiece() != null) {
            Piece piece = this.board[i][j].getPiece();
            piece.calculateValidSquares();
            if (piece.canGoTo(board[x][y])) {
                this.board[x][y].setPiece(piece);
                this.board[x][y].getPiece().setAssignedSquare(board[x][y]);
                this.board[i][j].setPiece(null);
                return true;
            }
        }
        return false;
    }
}

