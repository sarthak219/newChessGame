package main.model.pieces;

import main.model.Board;
import main.model.Square;

import java.util.ArrayList;

/**
 * represents the King piece of a chess game
 */
public class King extends Piece {
    private boolean hasMoved;
    private boolean canShortCastle;
    private boolean canLongCastle;

    public King(PieceColour colour, Board board) {
        super(colour, board);
        this.name = Name.KING;
        this.hasMoved = false;
        this.canShortCastle = false;
        this.canLongCastle = false;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setCanShortCastle(boolean canShortCastle) {
        this.canShortCastle = canShortCastle;
    }

    public boolean canShortCastle() {
        return canShortCastle;
    }

    public boolean canLongCastle() {
        return canLongCastle;
    }

    public void setCanLongCastle(boolean canLongCastle) {
        this.canLongCastle = canLongCastle;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public void assignImage(PieceColour colour) {
        this.image = colour == PieceColour.WHITE ? "./images/wK.png" : "./images/bK.png";
    }

    @Override
    public void calculateValidSquares() {
        this.validSquares = new ArrayList<>();
        int i = this.assignedSquare.getRow();
        int j = this.assignedSquare.getColumn();
        int start;
        int end;
        start = i - 1;
        end = i + 1;
        for (int k = start; k <= end; ++k) {
            checkRow(k);
        }
        if (canShortCastle) {
            if (j + 2 < 7)                                                              //remove later
                this.validSquares.add(board.getSquareAt(i, j + 2));
        }

        if (canLongCastle) {
            if (j - 2 > 0)                                                              //remove later
                this.validSquares.add(board.getSquareAt(i, j - 2));
        }
    }

    private void checkRow(int i) {
        int j = this.assignedSquare.getColumn();
        int start;
        int end;
        Square square;
        start = j - 1;
        end = j + 1;
        for (int k = start; k <= end; ++k) {
            if (i >= 0 && i <= 7 && k >= 0 && k <= 7) {
                square = board.getSquareAt(i, k);
                if (!checkPieceValidity(square)) this.validSquares.add(square);
            }
        }
    }
}