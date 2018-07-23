import java.util.Random;

public class PlayingCardDeck {
    PlayingCard playingCard = new PlayingCard(0, 13);
    Random random = new Random();
    PlayingCard[] playingCards = new PlayingCard[52];


    public PlayingCardDeck() {
        for (int i = 0; i < playingCards.length; i++){
            if (i < 13){
                new PlayingCard(0, i+1);
            }
            else if (i >= 13 && i < 26) {
                new PlayingCard(1, i+1);
            }
            else if (i >= 26 && i < 39) {
                new PlayingCard(3, i+1);
            }
            else {
                new PlayingCard(4, i+1);
            }
        }
        shuffleCards(playingCards);
        System.out.println(playingCards);
    }

    public static void shuffleCards(PlayingCard[] p) {
        Random rnd = new Random();
        for (int i = p.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            PlayingCard a = p[index];
            p[index] = p[i];
            p[i] = a;
        }
    }

}
