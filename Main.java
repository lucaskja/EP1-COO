import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Card[] allCards = Card.createCards();
//        System.out.println("Testando método para gerar todas as cartas: ");
//        for (int i = 0; i < 5; i++) {
//            System.out.println(allCards[i].getName() + " - " + allCards[i].getColor());
//        }
//
//        System.out.println("--------------------------------------------------------------------");

//        Piece p1 = new Piece(Color.RED, false);
//        Piece p2 = new Piece(Color.RED, false);
//        Piece p3 = new Piece(Color.BLUE, false);
//
//        Spot s1 = new Spot(p1, new Position(0,0), Color.RED);
//        Spot s2 = new Spot(p2, new Position(1,0), Color.RED);
//        Spot s3 = new Spot(p3, new Position(2,2), Color.RED);

        Card[] cards = Card.createCards();

        GameImpl gi = new GameImpl("Guilherme", "Jahchan", cards);
        Scanner scanner = new Scanner(System.in);

        while(gi.getIsRunning()){
            gi.printBoard();
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Vez do player: " + gi.getCurrentPlayerTurn().getName() + " (" + gi.getCurrentPlayerTurn().getPieceColor() + ")");
            System.out.println("--------------------------------------------------------------------");
            gi.getCurrentPlayerTurn().printCards();
            System.out.println("Carta da Mesa: " + gi.getTableCard().getName());
            System.out.println("--------------------------------------------------------------------");
            System.out.print("Digite o Index da carta utilizada, a linha e coluna para onde você deseja ir e a posição da peça atual separado por espaços respectivamente: ");
            String line = scanner.nextLine();
            System.out.println("--------------------------------------------------------------------");
            String[] numbers = line.split(" ");
            if (numbers.length != 5) throw new RuntimeException("Entrada inválida. Coloque exatamente 5 números inteiros separados por espaços.");
            int[] entrada = new int[5];
            for (int i = 0; i < numbers.length; i++) {
                try {
                    entrada[i] = Integer.parseInt(numbers[i]);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Entrada inválida. Coloque apenas números inteiros válidos.");
                }
            }
            try {
                gi.makeMove(gi.getCurrentPlayerTurn().getCards()[entrada[0]], new Position(entrada[1],entrada[2]), new Position(entrada[3], entrada[4]));
            } catch (Exception e) {
                System.out.println("Erro na Entrada");
                System.out.println("--------------------------------------------------------------------");
            }
        }
        gi.printBoard();
        System.out.println("Obrigado por jogar!");
    }
}
