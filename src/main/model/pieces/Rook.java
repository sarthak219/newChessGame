package main.model.pieces;

import main.model.Board;
import main.model.Square;

import java.util.ArrayList;

/**
 * represents the Rook piece of a chess game
 */
public class Rook extends Piece {
    public Rook(PieceColour colour, Board board) {
        super(colour, board);
        this.name = Name.ROOK;
    }

    @Override
    public void assignImage(PieceColour colour) {
        this.image = colour == PieceColour.WHITE ? "./images/wR.png" : "./images/bR.png";
    }

    @Override
    public void calculateValidSquares() {
        this.validSquares = new ArrayList<>();
        checkSquaresAbove();
        checkSquaresBelow();
        checkSquaresToTheLeft();
        checkSquaresToTheRight();
    }

    private void checkSquaresAbove() {
        int i = this.assignedSquare.getRow() - 1;
        int j = this.assignedSquare.getColumn();
        Square square;
        while (i >= 0) {
            square = board.getSquareAt(i, j);
            if (square.getPiece() != null) {
                if (isOppositeColourAndNotKing(square, this.colour)) {
                    this.validSquares.add(square);
                }
                return;
            }
            this.validSquares.add(square);
            i--;
        }
    }

    private void checkSquaresBelow() {
        int i = 1 + this.assignedSquare.getRow();
        int j = this.assignedSquare.getColumn();
        Square square;
        while (i <= 7) {
            square = board.getSquareAt(i, j);
            if (square.getPiece() != null) {
                if (isOppositeColourAndNotKing(square, this.colour)) {
                    this.validSquares.add(square);
                }
                return;
            }
            this.validSquares.add(square);
            i++;
        }
    }

    private void checkSquaresToTheLeft() {
        int i = this.assignedSquare.getRow();
        int j = this.assignedSquare.getColumn() - 1;
        Square square;
        while (j >= 0) {
            square = board.getSquareAt(i, j);
            if (square.getPiece() != null) {
                if (isOppositeColourAndNotKing(square, this.colour)) {
                    this.validSquares.add(square);
                }
                return;
            }
            this.validSquares.add(square);
            j--;
        }

    }

    private void checkSquaresToTheRight() {
        int i = this.assignedSquare.getRow();
        int j = this.assignedSquare.getColumn() + 1;
//        if (j <= 7) {
        Square square;
        while (j <= 7) {
            square = board.getSquareAt(i, j);
            if (square.getPiece() != null) {
                if (isOppositeColourAndNotKing(square, this.colour)) {
                    this.validSquares.add(square);
                }
                return;
            }
            this.validSquares.add(square);
            j++;
        }
//        }
    }
}
