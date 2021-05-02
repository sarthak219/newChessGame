package main.model;

import main.model.pieces.Piece;

public class Square {
    private final int row;      //row number of the square
    private final int column;   //column number of the square
    private final String name;  //name of the square
    private Piece piece;        //piece currently present on the square


    //REQUIRES: row and column must be between [0, 7]
    //EFFECTS: creates a square and names it using the given row and column
    public Square(int row, int column) {
        this.row = row;
        this.column = column;
        this.name = (char) (column + 'a') + Integer.toString(7 - row + 1);
        this.piece = null;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getName() {
        return name;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Square square = (Square) o;

        if (row != square.row) return false;
        return column == square.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }
}
