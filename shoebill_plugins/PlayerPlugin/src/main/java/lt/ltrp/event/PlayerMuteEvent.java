package lt.ltrp.event;

import lt.ltrp.event.player.PlayerEvent;
import lt.ltrp.object.LtrpPlayer;

/**
 * @author Bebras
 *         2016.06.06.
 */
public class PlayerMuteEvent extends PlayerEvent {


    public PlayerMuteEvent(LtrpPlayer player) {
        super(player);
    }
}
