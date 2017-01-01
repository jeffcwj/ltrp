package lt.ltrp.vehicle.event;

import lt.ltrp.aircraft.FlyingTest;
import lt.ltrp.object.Dmv;
import lt.ltrp.object.LtrpPlayer;

/**
 * @author Bebras
 *         2015.12.27.
 */
public class PlayerFlyingTestEnd extends PlayerDmvTestEndEvent {

    public PlayerFlyingTestEnd(LtrpPlayer player, Dmv dmv, FlyingTest test) {
        super(player, dmv, test);
    }

    @Override
    public FlyingTest getTest() {
        return (FlyingTest)super.getTest();
    }


}
