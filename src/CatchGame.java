
import java.awt.Color;

/**
 * This class manages the interactions between the different pieces of the game:
 * the Board, the Daleks, and the Doctor. It determines when the game is over
 * and whether the Doctor won or lost.
 */
public class CatchGame {

    /**
     * Instance variables go up here Make sure to create a Board, 3 Daleks, and
     * a Doctor
     */
    private Board b = new Board(12, 12);
    boolean dead = false;
    //the doctor
    public Doctor eccelson = new Doctor((int) (Math.random() * 12), (int) (Math.random() * 12));
    //daleks
    public Dalek dalek1 = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
    public Dalek dalek2 = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
    public Dalek dalek3 = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));

    /**
     * The constructor for the game. Use it to initialize your game variables.
     * (create people, set positions, etc.)
     */
    public CatchGame() {
        //dalek pegs
        b.putPeg(Color.BLACK, dalek1.getCol(), dalek1.getRow());
        b.putPeg(Color.BLACK, dalek2.getCol(), dalek2.getRow());
        b.putPeg(Color.BLACK, dalek3.getCol(), dalek3.getRow());
//doctor pegs
        b.putPeg(Color.GREEN, eccelson.getCol(), eccelson.getRow());
    }

    /**
     * The playGame method begins and controls a game: deals with when the user
     * selects a square, when the Daleks move, when the game is won/lost.
     */
    public void playGame() {

        while (true) {

            if (dalekCrash(dalek2, dalek3) == 1) {
                b.putPeg(Color.RED, dalek2.getCol(), dalek2.getRow());
                b.putPeg(Color.RED, dalek3.getCol(), dalek3.getRow());
                dalek2.crash();
                dalek3.crash();

            }
            if (dalekCrash(dalek1, dalek3) == 1) {
                b.putPeg(Color.RED, dalek1.getCol(), dalek1.getRow());
                b.putPeg(Color.RED, dalek3.getCol(), dalek3.getRow());
                dalek1.crash();
                dalek3.crash();

            }
            if (dalekCrash(dalek1, dalek2) == 1) {
                b.putPeg(Color.RED, dalek1.getCol(), dalek1.getRow());
                b.putPeg(Color.RED, dalek2.getCol(), dalek2.getRow());
                dalek1.crash();
                dalek2.crash();

            }

            //doctor moves
            Coordinate g = b.getClick();
            b.removePeg(eccelson.getCol(), eccelson.getRow());
            eccelson.move(g.getCol(), g.getRow());
            b.putPeg(Color.GREEN, eccelson.getCol(), eccelson.getRow());

            //if a dalek has crashed then it doesent move
            if (!dalek1.hasCrashed()) {

                b.removePeg(dalek1.getCol(), dalek1.getRow());
                dalek1.advanceTowards(eccelson);
                b.putPeg(Color.BLACK, dalek1.getCol(), dalek1.getRow());
            }
            if (!dalek2.hasCrashed()) {

                b.removePeg(dalek2.getCol(), dalek2.getRow());
                dalek2.advanceTowards(eccelson);
                b.putPeg(Color.BLACK, dalek2.getCol(), dalek2.getRow());
            }
            if (!dalek3.hasCrashed()) {

                b.removePeg(dalek3.getCol(), dalek3.getRow());
                dalek3.advanceTowards(eccelson);
                b.putPeg(Color.BLACK, dalek3.getCol(), dalek3.getRow());
            }
            //if all the daleks are dead then the game ends 
            if (dalek1.hasCrashed() && dalek2.hasCrashed() && dalek3.hasCrashed()) {
                b.displayMessage("You win");
                break;
            }

            //remove dalek pegs when they move
//checks if the doctor is dead
            if (dalek1.getRow() == eccelson.getRow() && dalek1.getCol() == eccelson.getCol()) {
                if (dalek2.getRow() == eccelson.getRow() && dalek2.getCol() == eccelson.getCol()) {
                    if (dalek3.getRow() == eccelson.getRow() && dalek3.getCol() == eccelson.getCol()) {
                        dead = true;
                    }
                }
            }
            if (dead) {
                
        b.removePeg(eccelson.getCol(), eccelson.getRow());
        b.putPeg(Color.yellow, eccelson.getCol(), eccelson.getRow());
        b.displayMessage("The doctor has been captured");
            }
        }


    }

    /**
     *
     * @param dalek
     * @param dalek2
     * @return 0 or 1 based on if they crash or not
     */
    public int dalekCrash(Dalek dalek, Dalek dalek2) {
        if (dalek.getRow() == dalek2.getRow() && dalek.getCol() == dalek2.getCol()) {
            return 1;
        } else {
            return 0;
        }
    }

}
