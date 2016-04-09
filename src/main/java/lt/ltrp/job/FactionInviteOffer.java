package lt.ltrp.job;

import lt.ltrp.player.data.PlayerOffer;
import lt.ltrp.player.object.LtrpPlayer;
import net.gtaun.util.event.EventManager;

/**
 * @author Bebras
 *         2016.03.04.
 */
public class FactionInviteOffer extends PlayerOffer {


    public FactionInviteOffer(LtrpPlayer player, LtrpPlayer offeredBy, EventManager eventManager) {
        super(player, offeredBy, eventManager, 120, FactionInviteOffer.class);
    }
}
