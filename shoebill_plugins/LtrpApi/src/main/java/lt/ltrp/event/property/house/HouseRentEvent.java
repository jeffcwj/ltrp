package lt.ltrp.event.property.house;

import lt.ltrp.object.House;
import lt.ltrp.object.LtrpPlayer;

/**
 * @author Bebras
 *         2016.04.20.
 */
public class HouseRentEvent extends HouseEvent {
    public HouseRentEvent(House house, LtrpPlayer player) {
        super(house, player);
    }
}