public class Main {
    public static void main(String[] args) {
        Card[] allCards = Card.createCards();
        System.out.println("Testando método para gerar todas as cartas: ");
        for (int i = 0; i < 5; i++) {
            System.out.println(allCards[i].getName() + " - " + allCards[i].getColor());
        }

        System.out.println("--------------------------------------------------------------------");

//        Piece p1 = new Piece(Color.RED, false);
//        Piece p2 = new Piece(Color.RED, false);
//        Piece p3 = new Piece(Color.BLUE, false);
//
//        Spot s1 = new Spot(p1, new Position(0,0), Color.RED);
//        Spot s2 = new Spot(p2, new Position(1,0), Color.RED);
//        Spot s3 = new Spot(p3, new Position(2,2), Color.RED);
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

        GameImpl gi = new GameImpl("Guilherme", "Jahchan", cards);
        gi.printBoard();
        System.out.println(gi.getTableCard().getName());

        gi.makeMove(cards[0], new Position(2,1), new Position(4, 1));
        gi.printBoard();
    }
}
