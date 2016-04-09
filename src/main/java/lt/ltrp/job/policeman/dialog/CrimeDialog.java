package lt.ltrp.job.policeman.dialog;

import lt.ltrp.player.data.PlayerCrime;
import lt.ltrp.player.object.LtrpPlayer;
import net.gtaun.shoebill.common.dialog.AbstractDialog;
import net.gtaun.shoebill.common.dialog.MsgboxDialog;
import net.gtaun.util.event.EventManager;

/**
 * @author Bebras
 *         2015.12.30.
 */
public class CrimeDialog {


    public static MsgboxDialog create(LtrpPlayer player, EventManager eventManager, PlayerCrime crime, AbstractDialog parent) {
        return MsgboxDialog.create(player, eventManager)
                .caption(() -> String.format(crime.getPlayerName() + " nusikaltimas"))
                .parentDialog(parent)
                .message((d) -> String.format("Data: %s\nPa�eid�jas: %s\n\n%s", crime.getDate(), crime.getPlayerName(), crime.getCrime().replaceAll("(.{100})", "$1\n")))
                .buttonOk("U�daryti")
                .buttonCancel("Atgal")
                .onClickCancel(d -> d.getParentDialog().show())

                .build();
    }

}
