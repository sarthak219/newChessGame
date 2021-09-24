package test.model;

import main.exceptions.InvalidCoordinatesException;
import main.model.Board;
import main.model.Square;
import main.model.pieces.King;
import main.model.pieces.Piece;
import main.model.pieces.PieceColour;
import main.model.pieces.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SquareTest {
    Square square;

    @BeforeEach
    public void setup() {
        try {
            square = new Square(0, 0);
        } catch (InvalidCoordinatesException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidConstructor() {
        try {
            square = new Square(0, 7);
            assertEquals(0, square.getRow());
            assertEquals(7, square.getColumn());
            square = new Square(7, 7);
            assertEquals(7, square.getRow());
            assertEquals(7, square.getColumn());
            square = new Square(7, 0);
            assertEquals(7, square.getRow());
            assertEquals(0, square.getColumn());
        } catch (InvalidCoordinatesException e) {
            fail("should not have thrown exception");
        }
    }

    @Test
    public void testNegativeRow() {
        try {
            square = new Square(-1, 7);
            fail("should have thrown exception");
        } catch (InvalidCoordinatesException e) {
            // nothing needed
        }
    }

    @Test
    public void testNegativeColumn() {
        try {
            square = new Square(0, -1);
            fail("should have thrown exception");
        } catch (InvalidCoordinatesException e) {
            // nothing needed
        }
    }

    @Test
    public void testGreaterRow() {
        try {
            square = new Square(8, 0);
            fail("should have thrown exception");
        } catch (InvalidCoordinatesException e) {
            // nothing needed
        }
    }

    @Test
    public void testGreaterColumn() {
        try {
            square = new Square(0, 10);
            fail("should have thrown exception");
        } catch (InvalidCoordinatesException e) {
            // nothing needed
        }
    }

    @Test
    public void testName() {
        assertEquals("a8", square.getName());
        try {
            square = new Square(0, 7);
            assertEquals("h8", square.getName());
            square = new Square(7, 7);
            assertEquals("h1", square.getName());
            square = new Square(7, 0);
            assertEquals("a1", square.getName());
            square = new Square(4, 6);
            assertEquals("g4", square.getName());
        } catch (InvalidCoordinatesException e) {
            fail("should not have thrown exception");
        }
    }

    @Test
    public void testPiece() {
        assertNull(square.getPiece());
        Piece rook = new Rook(PieceColour.WHITE, new Board());
        square.setPiece(rook);
        assertEquals(rook, square.getPiece());
        Piece king = new King(PieceColour.WHITE, new Board());
        square.setPiece(king);
        assertEquals(king, square.getPiece());
    }
}
