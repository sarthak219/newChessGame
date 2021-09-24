# Chess Game

This is a simple multiplayer game of chess. All pieces make valid moves such as:

- <b>King</b>: King can move one square in any direction, as long as that square is not attacked by an enemy piece. 
               Also, king is able to make a special move, known as <b>castling</b>.
- <b>Queen</b>: Queen is the most powerful piece of the game and is able to move any number of squares in all 
                directions, i.e. horizontally, vertically and diagonally without jumping over any other piece.
- <b>Rooks</b>: Rooks can move any number of squares horizontally or vertically without jumping over other pieces.
- <b>Bishops</b>: Bishops can move diagonally any number of squares without jumping over other pieces.
- <b>Knights</b>: Knights can move in "L" shape, i.e., either 2 squares horizontally and one square vertically, or 
                  2 squares vertically and 1 square horizontally. They are the only pieces in the game which are able 
                  to jump over other pieces.
- <b>Pawns</b>: Each pawn can move one square to the front in every turn and 2 squares to the front on its first move. 
  They can also move one square diagonally to capture another piece.
  
##Features:

- Players can move all pieces according to the rules above.
- Players can click on any of their piece to see all the squares it can go to.
- To move a piece, players can drag and drop it on any square that the piece is capable of going to. If the piece is 
  not capable of going to that square, the piece won't be moved, and there will be a buzzing sound.
- Players can capture any enemy piece as long as the piece being captured is not the enemy king.
- Players can perform castling(both, long castle and short castle) with their king as long as the king hasn't moved, 
  the rook on the castling side hasn't moved and there is no piece in between the king, and the rook on the castling side.
- Players can choose between 6 amazing colour themes to personalize the game for themselves.
- Players can start a new game at any point.
  

###Upcoming Features:
- The players would be able to move in turns.
- Players would be able to check the enemy king.
- When in check, either the king can move out of the check, a piece can block the check, or the piece giving the check 
  can be captured.
- The king would not be able to castle through a check or when in check.
- Both the kings would have at least one square between them.
- Pawns would be able to promote to other pieces on reaching the other end of the board.
- A piece would not be able to move if pinned by another piece.
- Users will be able to create accounts and save their games.
- The players will be able to save games and go to each position in the game to analyse.

###Feature Wishlist:
- Adding animations when moving pieces.
- Adding an engine to recommend best moves when analysing games.

###Bugs:
- Players can move out of turns.
- The "check" functionality is missing from the game, so all related features are missing as well such as King can move to an 
  otherwise illegal square, all pieces can be moved even when the king is in check, king can castle even when in check,
  etc.
- Pawns don't promote to other pieces.
