import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class PlayingCardGame {
    private PlayingCard[] Deck = new PlayingCard[52];
    private ArrayList<PlayingCard> playerHand = new ArrayList<>();
    private ArrayList<PlayingCard> dealerHand = new ArrayList<>();
    private int pValue;
    private int dValue;
    private int cardCounter;
    private Key key;
    private Terminal terminal;
    private Screen screen;
    private ScreenWriter writer;
    private boolean play;


    public int getCardCounter() {
        return cardCounter;
    }

    public void setCardCounter(int cardCounter) {
        this.cardCounter = cardCounter;
    }

    public int getpValue() {
        return pValue;
    }

    public int getdValue() {
        return dValue;
    }

    public ArrayList<PlayingCard> getPlayerHand() {
        return playerHand;
    }

    public ArrayList<PlayingCard> getDealerHand() {
        return dealerHand;
    }

    public void dealOneCardPlayer(int i) {
        playerHand.add(Deck[i]);
    }

    public void dealOneCardDealer(int i) {
        dealerHand.add(Deck[i]);
    }

    public void setDealerHand(ArrayList<PlayingCard> dealerHand) {
        this.dealerHand = dealerHand;
    }

    public void setDeck(PlayingCard[] Deck) {
        this.Deck = Deck;
    }

    public void drawScreen() {
        terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF-8"));
        screen = new Screen(terminal);
        screen.startScreen();
        writer = new ScreenWriter(screen);
        screen.setCursorPosition(null);
        drawStart();
    }

    public void keyDetector() throws Exception {
        drawScreen();
        PlayingCardDeck playingCardDeck = new PlayingCardDeck();
        playingCardDeck.createDeck();
        playingCardDeck.shuffleDeck(playingCardDeck.getDeck1());
        setDeck(playingCardDeck.getDeck2());
        while (true) {
            screen.clear();
            do {
                Thread.sleep(5);
                key = screen.readInput();
            }
            while (key == null);
            switch (key.getKind()) {
                case Enter:
                    playerHand.clear();
                    dealerHand.clear();
                    pValue = 0;
                    dValue = 0;
                    cardCounter = 0;
                    dealOneCardPlayer(getCardCounter());
                    setCardCounter(getCardCounter() + 1);
                    dealOneCardDealer(getCardCounter());
                    setCardCounter(getCardCounter() + 1);
                    gameScreen();
                    play = true;
                    break;
                case ArrowUp:
                    dealOneCardPlayer(getCardCounter());
                    setCardCounter(getCardCounter() + 1);
                    dealOneCardDealer(getCardCounter());
                    setCardCounter(getCardCounter() + 1);
                    gameScreen();
                    break;
                case ArrowDown:
                    stopGame();
                    gameScreen();
                    break;
                case Escape:
                    System.exit(0);
            }
            screen.refresh();
            if (play == false) {
                endScreen();
            }
        }
    }

    public void drawStart() {
        writer.setBackgroundColor(Terminal.Color.BLACK);
        writer.drawString(14, 11,
                ".------..------..------..------..------..------..------..------..------.\n");
        writer.drawString(14, 12,
                "|B.--. ||L.--. ||A.--. ||C.--. ||K.--. ||J.--. ||A.--. ||C.--. ||K.--. |\n");
        writer.drawString(14, 13,
                "| :(): || :/\\: || (\\/) || :/\\: || :/\\: || :(): || (\\/) || :/\\: || :/\\: |\n");
        writer.drawString(14, 14,
                "| ()() || (__) || :\\/: || :\\/: || :\\/: || ()() || :\\/: || :\\/: || :\\/: |\n");
        writer.drawString(14, 15,
                "| '--'B|| '--'L|| '--'A|| '--'C|| '--'K|| '--'J|| '--'A|| '--'C|| '--'K|\n");
        writer.drawString(14, 16,
                "`------'`------'`------'`------'`------'`------'`------'`------'`------'\n");
        writer.drawString(40, 21, "Press Enter to Start!");
        screen.refresh();
    }

    public void endScreen() {
        screen.clear();
        if (pValue < dValue && dValue > 21) {
            writer.drawString(20, 9, "YOU WIN!!!!");
        }
        else if (pValue > dValue && pValue > 21) {
            writer.drawString(20, 9, "Dealer Wins!");
        }
        else if (dValue > 21 && pValue > 21) {
            writer.drawString(20, 9, "You Both Lose.");
        }
        else if (pValue == 21 || dValue == 21) {
            writer.drawString(20, 9, "BLACKJACK!");
        }
        else if (pValue < 21 && dValue <21 && pValue > dValue) {
            writer.drawString(20, 9, "YOU WIN!!!!");
        }
        else if (pValue < 21 && dValue < 21 && pValue < dValue) {
            writer.drawString(20, 9, "You lose ...");
        }
        writer.drawString(20, 10, "Final Hands:");
        writer.drawString(20, 13, "Dealer Hand Value = " + dValue);
        writer.drawString(20, 11, "DEALER HAND");
        for (int i = 0; i < dealerHand.size(); i++) {
            writer.drawString(20, 12, "Latest card: " + dealerHand.get(i).getName() + " of " + dealerHand.get(i).getSuit());
            dValue += dealerHand.get(i).getValue();
        }
        writer.drawString(20, 17, "Player Hand Value = " + pValue);
        writer.drawString(20, 15, "PLAYER HAND");
        for (int i = 0; i < playerHand.size(); i++) {
            writer.drawString(20, 16, "Latest card: " + playerHand.get(i).getName() + " of " + playerHand.get(i).getSuit());
            pValue += playerHand.get(i).getValue();
        }
        writer.drawString(20, 20, "Do you want to Play again? Press Enter!");
        screen.refresh();
    }

    public void gameScreen() {
        screen.clear();
        pValue = 0;
        dValue = 0;
        writer.drawString(20, 11, "DEALER HAND");
        for (int i = 0; i < dealerHand.size(); i++) {
            writer.drawString(20, 12, "Latest card: " + dealerHand.get(i).getName() + " of " + dealerHand.get(i).getSuit() + "                        ");
            dValue += dealerHand.get(i).getValue();
        }
        writer.drawString(20, 13, "Dealer Hand Value = " + dValue);
        writer.drawString(20, 14, "---------------------------------------------");
        writer.drawString(20, 15, "PLAYER HAND");
        for (int i = 0; i < playerHand.size(); i++) {
            writer.drawString(20, 16, "Latest card: " + playerHand.get(i).getName() + " of " + playerHand.get(i).getSuit() + "                          ");
            pValue += playerHand.get(i).getValue();
        }
        writer.drawString(20, 17, "Player Hand Value = " + pValue);
        writer.drawString(20, 20, "Do you want to add another card, press Up.");
        writer.drawString(20, 21, "Do you want to stop, press Down.");
        check(pValue, dValue);
        screen.refresh();
    }

    public void check(int pValue, int dValue) {
        if (pValue < dValue && dValue > 21) {
            play = false;
        }
        else if (pValue > dValue && pValue > 21) {
            play = false;
        }
        else if (dValue > 21 && pValue > 21) {
            play = false;
        }
        else if (pValue == 21 || dValue == 21) {
            play = false;
        }
        else if (play == true){
            play = true;
        }
    }

    public void stopGame() {
        play = false;
    }
}

