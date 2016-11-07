package lt.ltrp.player.fine.data

import lt.ltrp.`object`.LtrpPlayer
import lt.ltrp.data.PlayerOffer
import net.gtaun.util.event.EventManager

/**
 * Created by Bebras on 2016-10-28.
 */
class PlayerFineOffer(player: LtrpPlayer, offeredBy: LtrpPlayer, eventManager: EventManager, val reason: String, val amount: Int):
        PlayerOffer(player, offeredBy, eventManager, 120, PlayerFineOffer::class.java) {
}