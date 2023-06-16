import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Classe que contém informações das cartas
 */
public class Card {
    private final String name;
    private final Color color;
    private final Position[] position;

    /**
     * Construtor que define os principais atributos de uma cara
     * @param name Nome da carta
     * @param color Cor da carta
     * @param positions Todas as posições relativas de movimento
     */
    public Card(String name, Color color, Position[] positions) {
        this.name = name;
        this.color = color;
        this.position = positions;
    }

    /**
     * Método que devolve o nome da carta
     * @return String que contém o nome da carta
     */
    public String getName() {
        return this.name;
    }

    /**
     * Método que devolve a cor da carta
     * @return Enum Color que contém a cor da carta
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Método que devolve todas as possíveis posições relativas de movimento.
     * A posição atual da peça é o ponto de origem (0,0). Uma carta possui as possíveis posições de movimento em relação ao ponto de origem.
     * @return Um array de Position contendo todas as possíveis posições de movimento em relação ao ponto de origem
     */
    public Position[] getPositions() {
        return this.position;
    }

    /**
     * Método que cria todas as cartas do jogo, embaralha-as e devolve as 5 que serão utilizadas na partida.
     * @return Vetor de cartas com todas as cartas do jogo
     */
    public static Card[] createCards() {
        Position[] tigerPos = {
            new Position(1,0),
            new Position(-2,0),
        };
        Card tiger =  new Card("tiger", Color.BLUE, tigerPos);

        Position[] frogPos = {
            new Position(0,-2),
            new Position(-1,-1),
            new Position(1,1),
        };
        Card frog =  new Card("frog", Color.RED, frogPos);

        Position[] crabPos = {
            new Position(-1,0),
            new Position(0,-2),
            new Position(0,2),
        };
        Card crab =  new Card("crab", Color.BLUE, crabPos);

        Position[] goosePos = {
            new Position(-1,-1),
            new Position(0,-1),
            new Position(0,1),
            new Position(1,1)
        };
        Card goose =  new Card("goose", Color.BLUE, goosePos);

        Position[] dragonPos = {
            new Position(-1,-2),
            new Position(1,-1),
            new Position(1,1),
            new Position(-1,2),
        };
        Card dragon =  new Card("dragon", Color.RED, dragonPos);

        Position[] rabbitPos = {
                new Position(1,-1),
                new Position(-1,1),
                new Position(0,2),
        };
        Card rabbit =  new Card("rabbit", Color.BLUE, rabbitPos);

        Position[] elephantPos = {
                new Position(0,-1),
                new Position(-1,-1),
                new Position(0,1),
                new Position(-1,1),
        };
        Card elephant =  new Card("elephant", Color.RED, elephantPos);

        Position[] roosterPos = {
                new Position(0,-1),
                new Position(1,-1),
                new Position(1,1),
                new Position(-1,1),
        };
        Card rooster =  new Card("rooster", Color.RED, roosterPos);

        Card[] cards = { tiger, frog, crab, goose, dragon, rabbit, elephant, rooster };

        List<Card> cardList = Arrays.asList(cards);
        Collections.shuffle(cardList);

        return cardList.subList(0, 5).toArray(new Card[0]);
    }
}
