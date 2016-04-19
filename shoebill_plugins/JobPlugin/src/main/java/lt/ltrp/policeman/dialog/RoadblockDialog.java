package lt.ltrp.policeman.dialog;

import lt.ltrp.policeman.Roadblock;
import lt.ltrp.object.LtrpPlayer;
import net.gtaun.shoebill.common.dialog.ListDialog;
import net.gtaun.util.event.EventManager;

/**
 * @author Bebras
 *         2015.12.30.
 */
public class RoadblockDialog {


    public static ListDialog create(LtrpPlayer player, EventManager eventManager, Roadblock roadblock) {
        return ListDialog.create(player, eventManager)
                .caption("U�tvaro valdymas")
                .item("Pa�alinti", dialog -> roadblock.destroy())
                .build();
    }


}