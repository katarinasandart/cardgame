import java.util.Random;

public class PlayingCardDeck {
    private PlayingCard[] Deck1 = new PlayingCard[52];
    private PlayingCard[] Deck2 = new PlayingCard[52];

    public PlayingCard[] getDeck2() {
        return Deck2;
    }

    public void setDeck2(PlayingCard[] Deck2) {
        this.Deck2 = Deck2;
    }


    public PlayingCard[] getDeck1() {
        return Deck1;
    }

    public void setDeck1(PlayingCard[] Deck1) {
        this.Deck1 = Deck1;
    }


    public PlayingCard getOneCard2(int i) {
        System.out.println("you drew the " + Deck2[i].getName() + " of " + Deck2[i].getSuit());
        return Deck1[2];
    }


    public PlayingCard getOneCard(int i) {
        System.out.println("you drew the " + Deck1[i].getName() + " of " + Deck1[i].getSuit());
        return Deck1[i];
    }

    public void setDeck(PlayingCard playingCard, int position) {
        Deck1[position] = playingCard;
    }


    public void shuffleDeck(PlayingCard[] Deck) {
        for (int i = 0; i < 52; i++) {
            Random rand = new Random();
            int position = rand.nextInt((51 - 0) + 1);
            Deck2[i] = Deck1[position];
        }
    }


    public void createDeck() {
        int B;
        int value;
        String color;
        int deckPosition = 0;
        for (B = 1; B <= 4; B++) {
            if (B == 1) {
                color = "Spades";
            } else if (B == 2) {
                color = "Hearts";
            } else if (B == 3) {
                color = "Clubs";
            } else {
                color = "Diamonds";
            }
            for (int i = 1; i <= 13; i++) {
                value = i;
                PlayingCard playingCard = new PlayingCard(value, color);
                Deck1[deckPosition] = playingCard;
                if (Deck1[deckPosition].getValue() == 11) {
                    Deck1[deckPosition].setName("Jack");
                    Deck1[deckPosition].setValue(10);
                } else if (Deck1[deckPosition].getValue() == 12) {
                    Deck1[deckPosition].setName("Queen");
                    Deck1[deckPosition].setValue(10);
                } else if (Deck1[deckPosition].getValue() == 13) {
                    Deck1[deckPosition].setName("King");
                    Deck1[deckPosition].setValue(10);
                } else if (Deck1[deckPosition].getValue() == 1) {
                    Deck1[deckPosition].setName("Ace");
                    Deck1[deckPosition].setValue(11);

                } else {
                    Deck1[deckPosition].setName(Integer.toString(Deck1[deckPosition].getValue()));
                }
                deckPosition++;
            }
        }
    }
}
