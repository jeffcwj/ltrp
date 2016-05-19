package lt.ltrp.event.property.garage;

import lt.ltrp.event.property.PlayerEnterPropertyEvent;
import lt.ltrp.object.Garage;
import lt.ltrp.object.LtrpPlayer;
import lt.ltrp.object.LtrpVehicle;

/**
 * @author Bebras
 *         2015.11.29.
 */
public class PlayerEnterGarageEvent extends PlayerEnterPropertyEvent {

    private LtrpVehicle vehicle;

    public PlayerEnterGarageEvent(Garage garage, LtrpPlayer player, LtrpVehicle vehicle) {
        super(garage, player);
        this.vehicle = vehicle;
    }

    @Override
    public Garage getProperty() {
        return (Garage)super.getProperty();
    }

    public LtrpVehicle getVehicle() {
        return vehicle;
    }

}
