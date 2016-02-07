package lt.ltrp.dmv.event;

import lt.ltrp.dmv.BoatingTest;
import lt.ltrp.dmv.Dmv;
import lt.ltrp.player.LtrpPlayer;

/**
 * @author Bebras
 *         2015.12.27.
 */
public class PlayerBoatingTestEnd extends PlayerDmvTestEndEvent {

    public PlayerBoatingTestEnd(LtrpPlayer player, Dmv dmv, BoatingTest test) {
        super(player, dmv, test);
    }

    public BoatingTest getTest() {
        return (BoatingTest)super.getTest();
    }
}