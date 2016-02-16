package lt.maze.event;

import lt.maze.object.DynamicArea;
import net.gtaun.shoebill.event.player.PlayerEvent;
import net.gtaun.shoebill.object.Player;

/**
 * @author Bebras
 *         2016.02.16.
 */
public class PlayerEnterDynamicAreaEvent extends PlayerEvent {

    private DynamicArea area;

    public PlayerEnterDynamicAreaEvent(Player player, DynamicArea area) {
        super(player);
        this.area = area;
    }

    public DynamicArea getArea() {
        return area;
    }
}
