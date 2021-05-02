package main.model.pieces;

import main.model.Board;
import main.model.Square;

import java.util.ArrayList;

/**
 * represents the Pawn piece of a chess game
 */
public class Pawn extends Piece {
    public Pawn(PieceColour colour, Board board) {
        super(colour, board);
        this.name = Name.PAWN;
    }

    @Override
    public void assignImage(PieceColour colour) {
        this.image = colour == PieceColour.WHITE ? "./images/wp.png" : "./images/bp.png";
    }

    @Override
    public void calculateValidSquares() {
        validSquares = new ArrayList<>();
        int i = this.assignedSquare.getRow();
        int j = this.assignedSquare.getColumn();
        if (this.colour == PieceColour.WHITE) {
            forwardForWhitePawn(i, j);
            diagonalForWhitePawn(i, j);
        } else {
            forwardForBlackPawn(i, j);
            diagonalForBlackPawn(i, j);
        }
    }

    private void forwardForWhitePawn(int i, int j) {
        Square square;
        if (i > 0) {
            square = board.getSquareAt(i - 1, j);
            if (square.getPiece() == null) {
                validSquares.add(square);
            } else {
                return;
            }
            if (this.assignedSquare.getRow() == 6) {
                square = board.getSquareAt(i - 2, j);
                if (square.getPiece() == null) {
                    validSquares.add(square);
                }
            }
        }
    }

    private void diagonalForWhitePawn(int i, int j) {
        Square square;
        if (i > 0) {
            if (j > 0 && j < 7) {
                square = board.getSquareAt(i - 1, j - 1);
                if (square.getPiece() != null && isBlackButNotKing(square)) {
                    validSquares.add(square);
                }
                square = board.getSquareAt(i - 1, j + 1);
            } else {
                if (j == 0) {
                    square = board.getSquareAt(i - 1, j + 1);
                } else {
                    square = board.getSquareAt(i - 1, j - 1);
                }
            }
            if (square.getPiece() != null && isBlackButNotKing(square)) {
                validSquares.add(square);
            }
        }
    }

    private void forwardForBlackPawn(int i, int j) {
        Square square;
        if (i < 7) {
            square = board.getSquareAt(i + 1, j);
            if (square.getPiece() == null) {
                validSquares.add(square);
            } else {
                return;
            }
            if (this.assignedSquare.getRow() == 1) {
                square = board.getSquareAt(i + 2, j);
                if (square.getPiece() == null) {
                    validSquares.add(square);
                }
            }
        }
    }

    private void diagonalForBlackPawn(int i, int j) {
        Square square;
        if (i < 7) {
            if (j > 0 && j < 7) {
                square = board.getSquareAt(i + 1, j - 1);
                if (square.getPiece() != null && isWhiteButNotKing(square)) {
                    validSquares.add(square);
                }
                square = board.getSquareAt(i + 1, j + 1);
            } else {
                if (j == 0) {
                    square = board.getSquareAt(i + 1, j + 1);
                } else {
                    square = board.getSquareAt(i + 1, j - 1);
                }
            }
            if (square.getPiece() != null && isWhiteButNotKing(square)) {
                validSquares.add(square);
            }
        }
    }

    private boolean isBlackButNotKing(Square square) {
        return (square.getPiece().getColour() == PieceColour.BLACK && square.getPiece().getName() != Name.KING);
    }

    private boolean isWhiteButNotKing(Square square) {
        return (square.getPiece().getColour() == PieceColour.WHITE && square.getPiece().getName() != Name.KING);
    }
}
