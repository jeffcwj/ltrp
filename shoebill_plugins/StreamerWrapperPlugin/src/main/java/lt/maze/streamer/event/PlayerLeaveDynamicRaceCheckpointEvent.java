package lt.maze.streamer.event;

import lt.maze.streamer.object.DynamicRaceCheckpoint;
import net.gtaun.shoebill.object.Player;

/**
 * @author Bebras
 *         2016.02.16.
 */
public class PlayerLeaveDynamicRaceCheckpointEvent extends AbstractPlayerCheckpointEvent {

    public PlayerLeaveDynamicRaceCheckpointEvent(Player player, DynamicRaceCheckpoint cp) {
        super(player, cp);
    }

    @Override
    public DynamicRaceCheckpoint getCheckpoint() {
        return (DynamicRaceCheckpoint)super.getCheckpoint();
    }
}
