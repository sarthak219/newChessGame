package main.model.pieces;

import main.model.Board;
import main.model.Square;

import java.util.ArrayList;

/**
 * represents the King piece of a chess game
 */
public class King extends Piece {

    public King(PieceColour colour, Board board) {
        super(colour, board);
        this.name = Name.KING;
    }

    @Override
    public void assignImage(PieceColour colour) {
        this.image = colour == PieceColour.WHITE ? "./images/wK.png" : "./images/bK.png";
    }

    @Override
    public void calculateValidSquares() {
        this.validSquares = new ArrayList<>();
        int i = this.assignedSquare.getRow();
        int start;
        int end;
        if (i == 0) {
            start = 0;
            end = 1;
        } else if (i == 7) {
            start = 6;
            end = 7;
        } else {
            start = i - 1;
            end = i + 1;
        }
        for (int k = start; k <= end; ++k) {
            checkRow(k);
        }
    }

    private void checkRow(int i) {
        int j = this.assignedSquare.getColumn();
        int start;
        int end;
        Square square;
        if (j == 0) {
            start = 0;
            end = 1;
        } else if (j == 7) {
            start = 6;
            end = 7;
        } else {
            start = j - 1;
            end = j + 1;
        }
        for (int k = start; k <= end; ++k) {
            square = board.getSquareAt(i, k);
            if (!checkPieceValidity(square)) this.validSquares.add(square);
        }
    }
}
