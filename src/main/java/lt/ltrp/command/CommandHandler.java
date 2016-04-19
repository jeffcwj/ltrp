package lt.ltrp.command;

import lt.ltrp.object.LtrpPlayer;
import net.gtaun.shoebill.object.Player;

/**
 * @author Bebras
 *         2015.11.13.
 */
@FunctionalInterface
public interface CommandHandler {
    boolean handle(LtrpPlayer player, Object[] params);
}
