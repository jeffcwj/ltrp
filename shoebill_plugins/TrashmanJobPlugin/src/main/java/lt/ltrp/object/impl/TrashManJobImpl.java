package lt.ltrp.object.impl;

import lt.ltrp.constant.JobProperty;
import lt.ltrp.data.TrashMissions;
import lt.ltrp.object.TrashManJob;import net.gtaun.shoebill.data.Location;
import net.gtaun.util.event.EventManager;

/**
 * @author Bebras
 *         2016.03.01.
 */
public class TrashManJobImpl extends AbstractContractJob implements TrashManJob {


    @JobProperty("trashmaster_capacity")
    public int trashMasterCapacity;

    @JobProperty("trash_route_bonus")
    public int trashRouteBonus;

    @JobProperty("trash_pickup_bonus")
    public int trashPickupBonus;

    @JobProperty("trash_drop_off")
    public Location trashDropoff;

    private TrashMissions trashMissions;

    public TrashManJobImpl(int id, EventManager eventManager) {
        super(id, eventManager);
    }

    public int getTrashMasterCapacity() {
        return trashMasterCapacity;
    }

    public int getTrashRouteBonus() {
        return trashRouteBonus;
    }

    @Override
    public int getTrashPickupBonus() {
        return trashPickupBonus;
    }

    @Override
    public TrashMissions getMissions() {
        return trashMissions;
    }

    @Override
    public void setMissions(TrashMissions trashMissions) {
        this.trashMissions = trashMissions;
    }

    @Override
    public Location getDropOffLocation() {
        return trashDropoff;
    }
}
