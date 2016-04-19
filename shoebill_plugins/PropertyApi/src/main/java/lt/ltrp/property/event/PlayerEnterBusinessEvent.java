package lt.ltrp.property.event;

import lt.ltrp.player.object.LtrpPlayer;
import lt.ltrp.property.object.Business;

/**
 * @author Bebras
 *         2015.11.29.
 */
public class PlayerEnterBusinessEvent extends PlayerBusinessEvent {

    public PlayerEnterBusinessEvent(LtrpPlayer player, Business property) {
        super(player, property);
    }
}