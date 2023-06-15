public class Main {
    public static void main(String[] args) {
        Card cTeste = new Card("Teste", Color.BLUE, null);

        System.out.println(cTeste.getName());
        System.out.println(cTeste.getColor());

        Card[] allCards = Card.createCards();

        for (int i = 0; i < 8; i++) {
            System.out.println(allCards[i].getName());
        }
    }
}
