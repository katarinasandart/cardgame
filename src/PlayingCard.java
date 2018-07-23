import java.io.Serializable;

public class PlayingCard implements Serializable {
    public static final int klover = 0;
    public static final int ruter = 1;
    public static final int hjarter = 2;
    public static final int spader = 3;
    private static final String[] f = {"Klöver", "Ruter", "Hjärter", "Spader"};
    private static final String[] n = {"ess", "två", "tre", "fyra", "fem", "sex", "sju", "åtta", "nio",
            "knekt", "dam", "kung"};
    private int nummer;
    private int farg;

    public PlayingCard(int farg, int nummer) {
        if (farg >= 0 && farg <= 3 || nummer >= 0 && nummer <= 13) {
            this.farg = farg;
            this.nummer = nummer;
        } else
            throw new IllegalArgumentException("Felaktigt");
    }

    public int getFarg() {
        return farg;
    }


    public int getNummer() {
        return nummer;
    }

    @Override
    public String toString() {
        return f[farg] + " " + n[nummer - 1];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;
        else {
            PlayingCard playingCard = (PlayingCard) obj;
            return playingCard.farg == farg && playingCard.nummer == nummer;
        }
    }

}
