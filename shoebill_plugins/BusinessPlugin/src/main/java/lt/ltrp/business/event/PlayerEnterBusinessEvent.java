package lt.ltrp.business.event;

import lt.ltrp.object.Business;
import lt.ltrp.player.object.LtrpPlayer;

/**
 * @author Bebras
 *         2015.11.29.
 */
public class PlayerEnterBusinessEvent extends PlayerEnterPropertyEvent {

    public PlayerEnterBusinessEvent(Business property, LtrpPlayer player) {
        super(property, player);
    }

    @Override
    public Business getProperty() {
        return (Business)super.getProperty();
    }
}
