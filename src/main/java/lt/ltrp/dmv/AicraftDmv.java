package lt.ltrp.dmv;

import lt.ltrp.player.LtrpPlayer;
import lt.ltrp.vehicle.LtrpVehicle;
import net.gtaun.util.event.EventManager;

/**
 * @author Bebras
 *         2015.12.27.
 */
public class AicraftDmv extends CheckpointDmvImpl {


    public AicraftDmv(int id) {
        super(id, null, null, null);
    }



    @Override
    public AbstractCheckpointTest startCheckpointTest(LtrpPlayer player, LtrpVehicle vehicle, EventManager eventManager) {
        return FlyingTest.create(player, vehicle, this, eventManager);
    }

    @Override
    public int getCheckpointTestPrice() {
        return FlyingTest.PRICE;
    }

}