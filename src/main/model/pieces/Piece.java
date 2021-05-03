package main.model.pieces;

import main.model.Board;
import main.model.Square;

import java.util.ArrayList;
import java.util.List;

/**
 * represents a piece of chess with a name, assigned square, colour, image icon and a list of squares it can move to
 */
public abstract class Piece {
    protected Name name;
    protected String image;
    protected Square assignedSquare;
    protected List<Square> validSquares;
    protected PieceColour colour;
    protected Board board;

    //EFFECTS: makes a new piece with the given colour without an assigned square or a
    // valid square it can go to, assigns an icon to the piece
    public Piece(PieceColour colour, Board board) {
        this.assignedSquare = null;
        this.validSquares = new ArrayList<>();
        this.colour = colour;
        this.board = board;
        assignImage(colour);
    }

    public String getImage() {
        return image;
    }

    public void setAssignedSquare(Square assignedSquare) {
        this.assignedSquare = assignedSquare;
    }

    public PieceColour getColour() {
        return colour;
    }

    public Name getName() {
        return name;
    }

    //EFFECTS: assigns the bg image to the piece
    public abstract void assignImage(PieceColour colour);

    //EFFECTS: return true if the given piece is in this.validSquares
    public boolean canGoTo(Square square) {
        return this.validSquares.contains(square);
    }

    //MODIFIES: this
    //EFFECTS: adds all teh squares the piece can go to to this.validSquares
    public abstract void calculateValidSquares();

    //REQUIRES: square != null;
    //EFFECTS: returns true if the piece in the given square is not a KING and its colour is opposite to the
    // given colour, false otherwise
    public boolean isOppositeColourAndNotKing(Square square, PieceColour colour) {
        return (square.getPiece().getColour() != colour && square.getPiece().getName() != Name.KING);
    }

    //MODIFIES: this
    //EFFECTS: checks all the squares above this piece, adds a square to this.validSquares
    // if the square doesn't have a piece or has a piece of the opposite colour except king
    public void checkSquaresAbove() {
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

    //MODIFIES: this
    //EFFECTS: checks all the squares below this piece, adds a square to this.validSquares
    // if the square doesn't have a piece or has a piece of the opposite colour except king
    public void checkSquaresBelow() {
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

    //MODIFIES: this
    //EFFECTS: checks all the squares to the left of this piece, adds a square to this.validSquares
    // if the square doesn't have a piece or has a piece of the opposite colour except king
    public void checkSquaresToTheLeft() {
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

    //MODIFIES: this
    //EFFECTS: checks all the squares to the right of this piece, adds a square to this.validSquares
    // if the square doesn't have a piece or has a piece of the opposite colour except king
    public void checkSquaresToTheRight() {
        int i = this.assignedSquare.getRow();
        int j = this.assignedSquare.getColumn() + 1;
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
    }

    //MODIFIES: this
    //EFFECTS: checks all the squares in the top right diagonal of this piece, adds a square to this.validSquares
    // if the square doesn't have a piece or has a piece of the opposite colour except king
    public void checkTopRightDiagonal() {
        int i = this.assignedSquare.getRow() - 1;
        int j = this.assignedSquare.getColumn() + 1;
        Square square;
        while (i >= 0 && j <= 7) {
            square = board.getSquareAt(i, j);
            if (square.getPiece() != null) {
                if (isOppositeColourAndNotKing(square, this.colour)) {
                    this.validSquares.add(square);
                }
                return;
            }
            this.validSquares.add(square);
            i--;
            j++;
        }
    }

    //MODIFIES: this
    //EFFECTS: checks all the squares in the top left diagonal of this piece, adds a square to this.validSquares
    // if the square doesn't have a piece or has a piece of the opposite colour except king
    public void checkTopLeftDiagonal() {
        int i = this.assignedSquare.getRow() - 1;
        int j = this.assignedSquare.getColumn() - 1;
        Square square;
        while (i >= 0 && j >= 0) {
            square = board.getSquareAt(i, j);
            if (square.getPiece() != null) {
                if (isOppositeColourAndNotKing(square, this.colour)) {
                    this.validSquares.add(square);
                }
                return;
            }
            this.validSquares.add(square);
            i--;
            j--;
        }
    }

    //MODIFIES: this
    //EFFECTS: checks all the squares in the bottom right diagonal of this piece, adds a square to this.validSquares
    // if the square doesn't have a piece or has a piece of the opposite colour except king
    public void checkBottomRightDiagonal() {
        int i = this.assignedSquare.getRow() + 1;
        int j = this.assignedSquare.getColumn() + 1;
        Square square;
        while (i <= 7 && j <= 7) {
            square = board.getSquareAt(i, j);
            if (square.getPiece() != null) {
                if (isOppositeColourAndNotKing(square, this.colour)) {
                    this.validSquares.add(square);
                }
                return;
            }
            this.validSquares.add(square);
            i++;
            j++;
        }
    }

    //MODIFIES: this
    //EFFECTS: checks all the squares in the bottom left diagonal of this piece, adds a square to this.validSquares
    // if the square doesn't have a piece or has a piece of the opposite colour except king
    public void checkBottomLeftDiagonal() {
        int i = this.assignedSquare.getRow() + 1;
        int j = this.assignedSquare.getColumn() - 1;
        Square square;
        while (i <= 7 && j >= 0) {
            square = board.getSquareAt(i, j);
            if (square.getPiece() != null) {
                if (isOppositeColourAndNotKing(square, this.colour)) {
                    this.validSquares.add(square);
                }
                return;
            }
            this.validSquares.add(square);
            i++;
            j--;
        }
    }
}