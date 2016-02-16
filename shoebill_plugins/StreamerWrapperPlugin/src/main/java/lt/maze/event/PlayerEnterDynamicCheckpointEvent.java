package lt.maze.event;

import lt.maze.object.DynamicCheckpoint;
import net.gtaun.shoebill.event.player.PlayerEvent;
import net.gtaun.shoebill.object.Player;

/**
 * @author Bebras
 *         2016.02.16.
 */
public class PlayerEnterDynamicCheckpointEvent extends PlayerEvent {

    private DynamicCheckpoint cp;

    public PlayerEnterDynamicCheckpointEvent(Player player, DynamicCheckpoint cp) {
        super(player);
        this.cp = cp;
    }

    public DynamicCheckpoint getCheckpoint() {
        return cp;
    }
}
