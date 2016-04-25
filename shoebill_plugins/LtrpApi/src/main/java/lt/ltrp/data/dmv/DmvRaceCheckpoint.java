package lt.ltrp.data.dmv;

import net.gtaun.shoebill.constant.RaceCheckpointType;
import net.gtaun.shoebill.data.Radius;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.RaceCheckpoint;

import java.lang.Override;import java.util.function.Consumer;

/**
 * @author Bebras
 *         2016.02.13.
 */
public class DmvRaceCheckpoint extends DmvCheckpoint {

    private DmvRaceCheckpoint next;
    private RaceCheckpointType type;

    public DmvRaceCheckpoint(int id, Radius radius, RaceCheckpointType type, DmvRaceCheckpoint next) {
        super(id, radius);
        this.next = next;
        this.type =  type;
    }


    @Override
    public RaceCheckpoint create(Consumer<Player> enterConsumer) {
        if(this.checkpoint == null) {
            this.checkpoint = RaceCheckpoint.create(getRadius(),
                    RaceCheckpointType.AIR,
                    next.getCheckpoint() == null ? getCheckpoint() : next.getCheckpoint(),
                    enterConsumer,
                    null);
        }
        return getCheckpoint();
    }

    @Override
    protected RaceCheckpoint getCheckpoint() {
        return (RaceCheckpoint)super.getCheckpoint();
    }


}