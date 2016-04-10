package lt.ltrp.property.event;

import lt.ltrp.player.LtrpPlayer;
import lt.ltrp.property.Business;

/**
 * @author Bebras
 *         2015.11.29.
 */
public class PlayerEnterBusinessEvent extends PlayerBusinessEvent {

    public PlayerEnterBusinessEvent(LtrpPlayer player, Business property) {
        super(player, property);
    }
}