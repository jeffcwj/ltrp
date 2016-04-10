package lt.ltrp.property.event;

import lt.ltrp.player.LtrpPlayer;
import lt.ltrp.property.House;

/**
 * @author Bebras
 *         2015.11.29.
 */
public class PlayerHouseEvent extends PlayerPropertyEvent {

    public PlayerHouseEvent(LtrpPlayer player, House property) {
        super(player, property);
    }

    public House getProperty() {
        return (House)super.getProperty();
    }
}