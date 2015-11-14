package lt.ltrp.player;

import lt.ltrp.LtrpGamemode;
import lt.ltrp.Util.AdminLog;
import lt.ltrp.command.CommandParam;
import lt.ltrp.data.Color;
import net.gtaun.shoebill.common.command.BeforeCheck;
import net.gtaun.shoebill.common.command.Command;
import net.gtaun.shoebill.common.command.CommandHelp;
import net.gtaun.shoebill.data.Location;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.Vehicle;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bebras
 *         2015.11.13.
 */
public class AdminCommands {

    private static final Map<String, Integer> adminLevels = new HashMap<>();
    private static final Map<String, Location> teleportLocations = new HashMap<>();

    static {
        adminLevels.put("ahelp", 1);
        adminLevels.put("getoldcar", 1);
        adminLevels.put("rc", 1);
        adminLevels.put("gotoloc", 1);

        adminLevels.put("makefactionmanager", 4);

        teleportLocations.put("pc", new Location(2292.1936f, 26.7535f, 25.9974f, 0, 0));
        teleportLocations.put("ls", new Location(1540.1237f, -1675.2844f, 13.5500f, 0, 0));
        teleportLocations.put("mg", new Location(1313.8589f, 314.4103f, 19.4098f, 0, 0));
        teleportLocations.put("bb", new Location(230.9343f, -146.9140f, 1.4297f, 0, 0));
        teleportLocations.put("dl", new Location(641.5609f, -559.9846f, 16.0626f, 0, 0));
        teleportLocations.put("fc", new Location(-183.3534f, 1034.6022f, 19.7422f, 0, 0));
        teleportLocations.put("lb", new Location(-837.1216f, 1537.0032f, 22.5471f, 0, 0));
    }


    @BeforeCheck
    public boolean beforeCheck(Player p, String cmd, String params) {
        LtrpPlayer player = LtrpPlayer.get(p);
        System.out.println("AdminCommandst :: beforeCheck. Player: "+  p.getName() + " cmd: " +cmd + " params:" + params + " required admin level:" +
                adminLevels.get(cmd) + " player admin levle:" + player.getAdminLevel());
        if(!adminLevels.containsKey(cmd))
            return false;
        if(player.getAdminLevel() < adminLevels.get(cmd)) {
            if(player.getAdminLevel() > 0) {
                player.sendErrorMessage("J�s� neturite teis�s naudoti �ios komandos. Komandai " + cmd + " reikalingas " + adminLevels.get(cmd) + " lygis.");
            } else {
                player.sendErrorMessage("J�s neturite teis�s naudoti �ios komandos.");
            }
            return false;
        }
        
        return true;
    }



    @CommandHelp("Parodo vis� administratoriaus komand� s�ra��")
    @Command(name = "ahelp")
    public boolean ahelp(Player player) {
        LtrpPlayer p = LtrpPlayer.get(player);
        p.sendMessage(Color.LIGHTRED,  "|____________________________ADMINISTRATORIAUS SKYRIUS____________________________|");
        p.sendMessage(Color.WHITE, "[AdmLvl 1] /kick /ban /warn /jail /noooc /adminduty /gethere /check /afrisk /fon ");
        p.sendMessage(Color.WHITE, "[AdmLvl 1] /freeze /slap /spec /specoff /setint /setvw /intvw /masked /aheal /spawn ");
        p.sendMessage(Color.WHITE, "[AdmLvl 1] /mark /lockacc /rc  /setskin  /aproperty /apkills /fon ");
        p.sendMessage(Color.WHITE, "[AdmLvl 1] PERSIK�LIMAS: /gotoloc /goto /gotomark /gotobiz /gotohouse /gotogarage /gotopos");
        p.sendMessage(Color.WHITE, "[AdmLvl 1] TR. PRIEMON�S: /getoldcar /rtc /rfc /rjc /rc");
        if(p.getAdminLevel() >= 2)
            p.sendMessage(Color.WHITE, "[AdmLvl 2] /dtc /gotocar /mute/rac ");
        if(p.getAdminLevel() >= 3)
            p.sendMessage(Color.WHITE, "[AdmLvl 3] /sethp /setarmour /forcelogout /hideadmins /serverguns /checkgun /kickall ");
        if(p.getAdminLevel() >= 4)
        {
            p.sendMessage(Color.WHITE, "[AdmLvl 4] /auninvite /givemoney /giveweapon /amenu /intmenu");
            p.sendMessage(Color.WHITE, "[AdmLvl 4] /makeleader /setstat /setstatcar /gotohouse /gotobiz");
            p.sendMessage(Color.WHITE, "[AdmLvl 4] /makeadmin /makemoderator /cartax /housetax /biztax");
            p.sendMessage(Color.WHITE, "[AdmLvl 4] /makefactinomanager  /giveitem ");
        }
        return true;
    }

    @Command
    @CommandHelp("Atkelia nurodyt� transporto priemon� � j�s� pozicij�")
    public boolean getoldcar(Player player, @CommandParam("Transporto priemon�s ID")Vehicle vehicle) {
        LtrpPlayer p = LtrpPlayer.get(player);
        if(vehicle == null)
            p.sendMessage(Color.LIGHTRED, "Transporto priemon�s su tokiu ID n�ra.");
        else {
            vehicle.setLocation(p.getLocation());
        }
        return true;
    }

    @Command
    @CommandHelp("Nukelia jus � vien� i� galim� vietovi�, pvz.: ls, bb")
    public boolean gotoloc(Player player, @CommandParam("Vietov�. Pvz: ls, bb")String pos) {
        LtrpPlayer p = LtrpPlayer.get(player);
        if(pos == null || !teleportLocations.keySet().contains(pos)) {
            p.sendErrorMessage("Vietov� " + pos + "  neegzistuoja.");
            String msg = "Galimos vietov�s: ";
            for(String s : teleportLocations.keySet()) {
                msg += s + " ";
            }
            p.sendMessage(Color.WHITE, msg);
        } else {
            if(p.getVehicle() != null) {
                p.getVehicle().setLocation(teleportLocations.get(pos));
            } else {
                p.setLocation(teleportLocations.get(pos));
            }
            p.sendMessage(Color.CHOCOLATE, "Persik�l�te � " + pos);
        }
        return true;
    }

    @Command
    @CommandHelp("Atstato transporto priemon� � atsiradimo viet�")
    public boolean rc(Player player, @CommandParam("Transporto priemon�s ID")Vehicle veh) {
        LtrpPlayer p = LtrpPlayer.get(player);
        if(veh == null) {
            p.sendMessage(Color.LIGHTRED, "Transporto priemon�s su tokiu ID n�ra.");
        }
        return true;
    }


    @Command
    @CommandHelp("Paskiria nurodyt� �aid�j� frakcij� pri�i�r�toju")
    public boolean makefactionmanager(Player player, @CommandParam("�aid�jo ID/Dalis vardo")LtrpPlayer p2) {
        LtrpPlayer p = LtrpPlayer.get(player);
        if(p2 == null) {
            p.sendMessage(Color.LIGHTRED, "Tokio �aid�jo n�ra.");
        } else {
            LtrpGamemode.getDao().getPlayerDao().setFactionManager(p2);
            p2.setFactionManager(true);

            LtrpPlayer.sendAdminMessage("Administratorius " + p.getName() + " suteik� �aid�jui " + p2.getName() + " frakcij� pri�i�r�tojo rang�.");

            p2.sendMessage(Color.NEWS, "Administratorius " + p.getName() + " paskyr� jus frakcij� pri�i�r�toju.");

            AdminLog.log(p, "Paskyr� �aid�j� " + p2.getName() + " frakcij� pri�ir�toju.");
        }
        return true;
    }



}
