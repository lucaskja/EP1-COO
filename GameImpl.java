import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

class GameImpl implements Game{
    private final Player p1, p2;
    private final Card[] cards;
    private Card tableCard;
    private final Spot[][] board = new Spot[5][5];
    private Player currentPlayerTurn;
    private boolean isRunning;

    public GameImpl(){
        this("Player 1", "Player 2", Card.createCards());
    }

    public GameImpl(String p1, String p2){
        this(p1, p2, Card.createCards());
    }

    public GameImpl(String p1, String p2, Card[] cards){
        this.isRunning = true;
        this.cards = this.selectCards(cards);

        this.p1 = new Player(p1, Color.RED, selectCardsForPlayer1(this.cards));
        this.p2 = new Player(p2, Color.BLUE, selectCardsForPlayer2(this.cards));

        this.tableCard = this.selectCardsForTable(this.cards);
        if (this.tableCard.getColor() == Color.RED) this.currentPlayerTurn = this.p1;
        else this.currentPlayerTurn = this.p2;
        this.initialPiecesState();
    }

    public Color getSpotColor(Position position) {
        return board[position.getRow()][position.getCol()].getColor();
    }

    public Piece getPiece(Position position) {
        return board[position.getRow()][position.getCol()].getPiece();
    }

    public Card getTableCard() {
        return this.tableCard;
    }

    public Player getRedPlayer() { return this.p1; }

    public Player getBluePlayer() { return this.p2; }

    public void makeMove(Card card, Position cardMove, Position currentPos) throws IncorrectTurnOrderException, IllegalMovementException, InvalidCardException, InvalidPieceException {
        if (this.board[currentPos.getRow()][currentPos.getCol()].getPiece() == null)
            throw new InvalidPieceException("Não existe peça nessa posição(" + currentPos.getRow() + "," +  currentPos.getCol() + ")");

        if (this.board[currentPos.getRow()][currentPos.getCol()].getPiece().getColor() != this.currentPlayerTurn.getPieceColor())
            throw new IncorrectTurnOrderException(
                "Você tentou mover uma peça "
                + this.board[currentPos.getRow()][currentPos.getCol()].getPiece().getColor()
                + " no turno do jogador de cor "
                + this.currentPlayerTurn.getPieceColor()
            );

        if (!(this.currentPlayerTurn.getCards()[0].getName().equals(card.getName()) || this.currentPlayerTurn.getCards()[1].getName().equals(card.getName())))
            throw new InvalidCardException("Jogador não possui a carta escolhida (" + card.getName() + ")");

        if (cardMove.getRow() < 0 && cardMove.getRow() > 5 && cardMove.getCol() < 0 && cardMove.getCol() < 0)
            throw new IllegalMovementException("Seu movimento (" + cardMove.getRow() + ", " + cardMove.getCol() + " está para fora do tabuleiro");

        for (int i = 0; i < card.getNumberOfPositions(); i++) {
            if (currentPos.getRow() + card.getPositions()[i].getRow() == cardMove.getRow() && currentPos.getCol() + card.getPositions()[i].getCol() == cardMove.getCol()){
                if(this.board[cardMove.getRow()][cardMove.getCol()].getPiece().getColor() == this.board[currentPos.getRow()][currentPos.getCol()].getPiece().getColor())
                    throw new IllegalMovementException("Seu movimento (" + cardMove.getRow() + ", " + cardMove.getCol() + ")" + " é inválido pois você está tentando eliminar sua própria peça");
                this.board[cardMove.getRow()][cardMove.getCol()].setPiece(this.board[currentPos.getRow()][currentPos.getCol()].getPiece());
                this.board[currentPos.getRow()][currentPos.getCol()].setPiece(null);

                checkVictory(this.currentPlayerTurn.getPieceColor());
                this.currentPlayerTurn.swapCard(card, this.tableCard);
                this.tableCard = card;
                changeCurrentPlayerTurn();
                return;
            }
        }
        throw new IllegalMovementException("Seu movimento (" + cardMove.getRow() + ", " + cardMove.getCol() + ")" + " não é suportado pela carta");
    }

    public boolean checkVictory(Color color) {
        if (Objects.requireNonNull(color) == Color.RED) {
            if (this.board[0][2].getPiece() != null && this.board[0][2].getPiece().isMaster() && this.board[0][2].getPiece().getColor() == Color.RED){
                System.out.println("O Player " + this.currentPlayerTurn.getName() + " (" + this.currentPlayerTurn.getPieceColor() + ") é o vencedor");
                this.isRunning = false;
                return true;
            }
        } else {
            if (this.board[0][2].getPiece() != null && this.board[4][2].getPiece().isMaster() && this.board[4][2].getPiece().getColor() == Color.BLUE){
                System.out.println("O Player " + this.currentPlayerTurn.getName() + " (" + this.currentPlayerTurn.getPieceColor() + ") é o vencedor");
                this.isRunning = false;
                return true;
            }
        }
        if(isMasterStillInTheBoard(color)){
            System.out.println("O Player " + this.currentPlayerTurn.getName() + " (" + this.currentPlayerTurn.getPieceColor() + ") é o vencedor");
            this.isRunning = false;
        }
        return isMasterStillInTheBoard(color);
    }

    private boolean isMasterStillInTheBoard(Color color) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.board[i][j].getPiece() != null && this.board[i][j].getPiece().isMaster() && this.board[i][j].getPiece().getColor() == invertColor(color))
                    return false;
            }
        }
        return true;
    }

    private Color invertColor(Color color){
        if(color == Color.RED) return Color.BLUE;
        return Color.RED;
    }

    public void printBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].getPiece() != null){
                    if (board[i][j].getPiece().isMaster()){
                        if(board[i][j].getPiece().getColor() == Color.BLUE) System.out.print("BLU M | ");
                        else System.out.print(board[i][j].getPiece().getColor() + " M | ");
                    } else if(board[i][j].getPiece().getColor() == Color.BLUE) System.out.print("BLU A | ");
                    else System.out.print(board[i][j].getPiece().getColor() + " A | ");
                } else System.out.print("00000 | ");
            }
            System.out.println();
        }
    }

    public Card[] selectCards(Card[] cards) {
        List<Card> cardList = Arrays.asList(cards);
        Collections.shuffle(cardList);

        return cardList.subList(0, 5).toArray(new Card[0]);
    }

    public Card[] selectCardsForPlayer1(Card[] cards) {
        return new Card[]{ cards[0], cards[2] };
    }

    public Card[] selectCardsForPlayer2(Card[] cards) {
        return new Card[]{ cards[1], cards[3] };
    }

    public Card selectCardsForTable(Card[] cards) {
        return cards[4];
    }

    public void initialPiecesState(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.board[i][j] = new Spot(null, new Position(i,j));
            }
        }

        for(int i = 0; i < 5; i++){
            if(i == 2) {
                this.board[0][i] = new Spot(new Piece(Color.BLUE, true), new Position(0, i), Color.BLUE);
                this.board[4][i] = new Spot(new Piece(Color.RED, true), new Position(4, i), Color.RED);
            } else {
                this.board[4][i].setPiece(new Piece(Color.RED, false));
                this.board[0][i].setPiece(new Piece(Color.BLUE, false));
            }
        }
    }

    public void changeCurrentPlayerTurn() {
        if (this.currentPlayerTurn.equals(this.p1)) this.currentPlayerTurn = this.p2;
        else this.currentPlayerTurn = this.p1;
    }

    public boolean getIsRunning() {
        return this.isRunning;
    }

    public Player getCurrentPlayerTurn() {
        return this.currentPlayerTurn;
    }
}