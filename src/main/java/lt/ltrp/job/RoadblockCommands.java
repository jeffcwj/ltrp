package lt.ltrp.job;

import lt.ltrp.LtrpGamemode;
import lt.ltrp.command.Commands;
import lt.ltrp.dialog.RoadblockDialog;
import lt.ltrp.dialog.RoadblockListDialog;
import lt.ltrp.job.medic.MedicManager;
import lt.ltrp.job.policeman.PolicemanManager;
import lt.ltrp.job.policeman.Roadblock;
import lt.ltrp.job.policeman.modelpreview.RoadblockModelPreview;
import lt.ltrp.player.LtrpPlayer;
import net.gtaun.shoebill.common.command.BeforeCheck;
import net.gtaun.shoebill.common.command.Command;
import net.gtaun.shoebill.common.command.CommandHelp;
import net.gtaun.shoebill.data.AngledLocation;

import java.util.Optional;

/**
 * @author Bebras
 *         2015.12.30.
 */
public class RoadblockCommands extends Commands {

    @BeforeCheck
    public boolean beforeCheck(LtrpPlayer player, String cmd, String params) {
        if(player.isAdmin() || player.getJob().getId() == PolicemanManager.JOB_ID || player.getJob().getId() == MedicManager.JOB_ID) {
            return true;
        } else {
            return false;
        }
    }

    @Command
    @CommandHelp("Pastato u�tvar�, galimi veiksmai create, destroy, edit, manage")
    public boolean roadblock(LtrpPlayer player, String param) {
        if(player.getVehicle() != null) {
            player.sendErrorMessage("U�tvaros pastatyti negalite b�dami transporto priemon�je.");
        } else {
            if(param.equalsIgnoreCase("create")) {
                RoadblockModelPreview.get().show(player, (preview, model) -> {
                    AngledLocation location = player.getLocation();
                    location.setX(location.getX() + (float)(3f * Math.sin(-Math.toRadians(location.getAngle()))));
                    location.setY(location.getY() + (float) (3f * Math.cos(-Math.toRadians(location.getAngle()))));
                    Roadblock roadblock = new Roadblock(model, location);
                    roadblock.getObject().edit(player);
                });
            } else if(param.equalsIgnoreCase("destroy")) {
                AngledLocation location = player.getLocation();
                Optional<Roadblock> roadblock = Roadblock.get().stream().filter(rd -> rd.getLocation().distance(location) < 2f).min((o1, o2) -> Float.compare(o1.getLocation().distance(location), o2.getLocation().distance(location)));
                if(!roadblock.isPresent()) {
                    player.sendErrorMessage("Prie j�s� n�ra jokios kelio u�tvaros!");
                } else {
                    roadblock.get().destroy();
                    player.sendMessage("U�tvaras pa�alintas.");
                }
            } else if(param.equalsIgnoreCase("edit")) {
                AngledLocation location = player.getLocation();
                Optional<Roadblock> roadblock = Roadblock.get().stream().filter(rd -> rd.getLocation().distance(location) < 2f).min((o1, o2) -> Float.compare(o1.getLocation().distance(location), o2.getLocation().distance(location)));
                if(!roadblock.isPresent()) {
                    player.sendErrorMessage("Prie j�s� n�ra jokios kelio u�tvaros!");
                } else {
                    roadblock.get().getObject().edit(player);
                }
            } else if(param.equalsIgnoreCase("manage")) {
                if(player.isAdmin()) {
                    new RoadblockListDialog(player, LtrpGamemode.get().getEventManager()).show();
                }
            }
            return true;
        }
        return false;
    }

}