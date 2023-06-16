import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class GameImpl implements Game{
    private String p1, p2;
    private Card[] cards, cardsP1, cardsP2;
    private Card tableCard;
    private final Spot[][] board;

    public GameImpl(){
        this.board = new Spot[5][5];
    }

    public GameImpl(String p1, String p2){
        this.p1 = p1;
        this.p2 = p2;
        this.board = new Spot[5][5];
    }

    public GameImpl(String p1, String p2, Card[] cards){
        this.p1 = p1;
        this.p2 = p2;
        this.cards = this.selectCards(cards);
        this.cardsP1 = this.selectCardsForPlayer1(this.cards);
        this.cardsP1 = this.selectCardsForPlayer2(this.cards);
        this.tableCard = this.selectCardsForTable(this.cards);
        this.board = new Spot[5][5];
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

    public Player getRedPlayer() {}

    public Player getBluePlayer() {}

    public void makeMove(Card card, Position cardMove, Position currentPos) throws IncorrectTurnOrderException, IllegalMovementException, InvalidCardException, InvalidPieceException {}

    public boolean checkVictory(Color color) {}

    public void printBoard() {}

    public Card[] selectCards(Card[] cards) {
        List<Card> cardList = Arrays.asList(cards);
        Collections.shuffle(cardList);

        return cardList.subList(0, 5).toArray(new Card[0]);
    }

    public Card[] selectCardsForPlayer1(Card[] cards) {
        Card[] newDeck = new Card[2];
        for (int j = 0; j < 2; j++){
            newDeck[j] = cards[j];
        }
        return newDeck;
    }

    public Card[] selectCardsForPlayer2(Card[] cards) {
        Card[] newDeck = new Card[2];
        for (int j = 2; j < 4; j++){
            newDeck[j] = cards[j];
        }
        return newDeck;
    }

    public Card selectCardsForTable(Card[] cards) {
        Card tableCard;
        tableCard = cards[4];
        return tableCard;
    }
}