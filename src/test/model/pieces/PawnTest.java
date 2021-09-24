package test.model.pieces;

import main.model.Board;
import main.model.pieces.Name;
import main.model.pieces.Pawn;
import main.model.pieces.Piece;
import main.model.pieces.PieceColour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {
    Piece pawn;
    Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
        pawn = new Pawn(PieceColour.WHITE, board);
    }

    @Test
    public void testConstructor() {
        assertNull(pawn.getAssignedSquare());
        assertEquals(PieceColour.WHITE, pawn.getColour());
        assertEquals(Name.PAWN, pawn.getName());
        assertEquals("./images/wp.png", pawn.getImage());
        assertEquals(board, pawn.getBoard());
        assertEquals(0, pawn.getValidSquares().size());
    }

    @Test
    public void testCalculateValidSquaresOnInitialRow() {
        Random random = new Random();
        int randCol = random.nextInt(8);
        board.addPieceAtPosition(pawn, 6, randCol);
        pawn.calculateValidSquares();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (j == randCol && (i == 5 || i == 4)) {
                    assertTrue(pawn.getValidSquares().contains(board.getSquareAt(i, j)));
                } else {
                    assertFalse(pawn.getValidSquares().contains(board.getSquareAt(i, j)));
                }
            }
        }

        board = new Board();
        pawn = new Pawn(PieceColour.BLACK, board);
        board.addPieceAtPosition(pawn, 1, randCol);
        pawn.calculateValidSquares();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (j == randCol && (i == 2 || i == 3)) {
                    assertTrue(pawn.getValidSquares().contains(board.getSquareAt(i, j)));
                } else {
                    assertFalse(pawn.getValidSquares().contains(board.getSquareAt(i, j)));
                }
            }
        }
    }
}
