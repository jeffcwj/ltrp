package lt.ltrp.event.player;

import lt.ltrp.object.ItemPhone;
import lt.ltrp.object.LtrpPlayer;
import net.gtaun.shoebill.event.player.PlayerEvent;

/**
 * @author Bebras
 *         2016.03.23.
 */
public class PlayerAnswerPhoneEvent extends PlayerEvent {


    // THE ANSWERERs phone
    private ItemPhone phone;


    public PlayerAnswerPhoneEvent(LtrpPlayer player, ItemPhone phone) {
        super(player);
        this.phone = phone;
    }

    @Override
    public LtrpPlayer getPlayer() {
        return (LtrpPlayer)super.getPlayer();
    }

    public ItemPhone getPhone() {
        return phone;
    }
}