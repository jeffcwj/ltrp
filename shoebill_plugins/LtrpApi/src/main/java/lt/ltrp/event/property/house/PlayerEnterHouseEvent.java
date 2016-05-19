package lt.ltrp.event.property.house;

import lt.ltrp.event.property.PlayerEnterPropertyEvent;
import lt.ltrp.object.House;
import lt.ltrp.object.LtrpPlayer;


/**
 * @author Bebras
 *         2015.11.29.
 */
public class PlayerEnterHouseEvent extends PlayerEnterPropertyEvent {


    public PlayerEnterHouseEvent(House house, LtrpPlayer player) {
        super(house, player);
    }

    @Override
    public House getProperty() {
        return (House)super.getProperty();
    }
}