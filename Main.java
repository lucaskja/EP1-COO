public class Main {
    public static void main(String[] args) {
        Card[] allCards = Card.createCards();
        System.out.println("Testando método para gerar todas as cartas: ");
        for (int i = 0; i < 5; i++) {
            System.out.println(allCards[i].getName() + " - " + allCards[i].getColor());
        }

        System.out.println("--------------------------------------------------------------------");

        Piece p1 = new Piece(Color.RED, false);
        Piece p2 = new Piece(Color.RED, false);
        Piece p3 = new Piece(Color.BLUE, false);

        Spot s1 = new Spot(p1, new Position(0,0), Color.RED);
        Spot s2 = new Spot(p2, new Position(1,0), Color.RED);
        Spot s3 = new Spot(p3, new Position(2,2), Color.RED);


        System.out.println(p1);
        System.out.println(s1.getPiece());

        s1.occupySpot(p3);
        s1.occupySpot(p2);
        s1.occupySpot(p1); //Programa vai gerar uma runTime Exception. Caso queira rodar o main, comentar ou apagar essa linha.

        System.out.println(p3);
        System.out.println(s1.getPiece());
    }
}
