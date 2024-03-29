package lt.ltrp.house.event;

import lt.ltrp.player.object.LtrpPlayer;
import lt.ltrp.house.object.House;


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
