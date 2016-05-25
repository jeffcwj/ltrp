package lt.ltrp.command;

import lt.ltrp.*;
import lt.ltrp.data.Color;
import lt.ltrp.data.JailData;
import lt.ltrp.data.PlayerJobData;
import lt.ltrp.data.PlayerReport;
import lt.ltrp.dialog.FactionListDialog;
import lt.ltrp.dialog.PlayerReportListDialog;
import lt.ltrp.dialog.ServerStatsMsgBoxDialog;
import lt.ltrp.event.PlayerSetFactionLeaderEvent;
import lt.ltrp.event.RemoveFactionLeaderEvent;
import lt.ltrp.event.player.PlayerToggleAdminDutyEvent;
import lt.ltrp.object.*;
import lt.ltrp.util.AdminLog;
import lt.maze.shoebilleventlogger.ShoebillEventLoggerPlugin;
import net.gtaun.shoebill.Shoebill;
import net.gtaun.shoebill.common.command.BeforeCheck;
import net.gtaun.shoebill.common.command.Command;
import net.gtaun.shoebill.common.command.CommandHelp;
import net.gtaun.shoebill.common.command.CommandParameter;
import net.gtaun.shoebill.common.dialog.InputDialog;
import net.gtaun.shoebill.common.dialog.ListDialog;
import net.gtaun.shoebill.common.dialog.MsgboxDialog;
import net.gtaun.shoebill.constant.SpecialAction;
import net.gtaun.shoebill.data.Location;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.Vehicle;
import net.gtaun.shoebill.object.VehicleDamage;
import net.gtaun.util.event.EventManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        adminLevels.put("rjc", 1);
        adminLevels.put("adminduty", 1);
        adminLevels.put("aduty", 1);
        adminLevels.put("kick", 1);
        adminLevels.put("ban", 1);
        adminLevels.put("warn", 1);
        adminLevels.put("jail", 1);
        adminLevels.put("are", 1);
        adminLevels.put("dre", 1);
        adminLevels.put("reports", 1);

        adminLevels.put("rfc", 2);
        adminLevels.put("dtc", 2);
        adminLevels.put("gotopos", 2);
        adminLevels.put("gotocar", 2);
        adminLevels.put("aheal", 2);
        adminLevels.put("ipban", 2);

        adminLevels.put("checkgun", 3);

        adminLevels.put("makefactionmanager", 4);
        adminLevels.put("makeleader", 4);
        adminLevels.put("removeleader", 4);
        adminLevels.put("auninvite", 4);
        adminLevels.put("bizitems", 4);
        adminLevels.put("abiz", 4);
        adminLevels.put("ahou", 4);
        adminLevels.put("agarage", 4);
        adminLevels.put("serverstats", 4);

        adminLevels.put("giveitem", 6);
        adminLevels.put("fly", 6);
        adminLevels.put("vehicledmg", 6);
        adminLevels.put("eventlog", 6);

        teleportLocations.put("pc", new Location(2292.1936f, 26.7535f, 25.9974f, 0, 0));
        teleportLocations.put("ls", new Location(1540.1237f, -1675.2844f, 13.5500f, 0, 0));
        teleportLocations.put("mg", new Location(1313.8589f, 314.4103f, 19.4098f, 0, 0));
        teleportLocations.put("bb", new Location(230.9343f, -146.9140f, 1.4297f, 0, 0));
        teleportLocations.put("dl", new Location(641.5609f, -559.9846f, 16.0626f, 0, 0));
        teleportLocations.put("fc", new Location(-183.3534f, 1034.6022f, 19.7422f, 0, 0));
        teleportLocations.put("lb", new Location(-837.1216f, 1537.0032f, 22.5471f, 0, 0));
    }

    private AdminController controller;
    private EventManager eventManager;

    public AdminCommands(AdminController controller, EventManager eventManager) {
        this.controller = controller;
        this.eventManager = eventManager;
    }


    @BeforeCheck
    public boolean beforeCheck(Player p, String cmd, String params) {
        cmd = cmd.toLowerCase();
        LtrpPlayer player = LtrpPlayer.get(p);
        if(player != null) {
            System.out.println("AdminCommandst :: beforeCheck. Player: "+  p.getName() + " cmd: " +cmd + " params:" + params + " required admin level:" +
                    adminLevels.get(cmd) + " player admin levle:" + player.getAdminLevel());
        } else System.out.println("that cant even happen");
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
        p.sendMessage(Color.WHITE, "[AdmLvl 1] /mark /rc  /setskin  /aproperty /apkills /fon ");
        p.sendMessage(Color.WHITE, "[AdmLvl 1] PERSIK�LIMAS: /gotoloc /goto /gotomark /gotobiz /gotohouse /gotogarage /gotopos");
        p.sendMessage(Color.WHITE, "[AdmLvl 1] TR. PRIEMON�S: /getoldcar /rtc /rfc /rjc /rc /are /dre /reports");
        if(p.getAdminLevel() >= 2)
            p.sendMessage(Color.WHITE, "[AdmLvl 2] /dtc /gotocar /mute/rac /ipban");
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
    public boolean togq(Player p) {
        p.sendMessage("Komanda pa�alinta. Naudokite /settings");
        return true;
    }

    @Command
    public boolean aDuty(Player p) {
        return adminDuty(p);
    }

    @Command
    @CommandHelp("Priima �aid�jo pateikt� report�")
    public boolean aRe(Player p, @CommandParameter(name = "�aid�jo ID/Dalis vardo")LtrpPlayer target) {
        LtrpPlayer player = LtrpPlayer.get(p);
        AdminPlugin adminPlugin = AdminPlugin.get(AdminPlugin.class);
        PlayerReport report;
        if(target == null)
            return false;
        else if(!target.isOnline())
            player.sendErrorMessage("�aid�jas atsijung�.");
        else if((report = adminPlugin.getPlayerReport(target)) == null)
            player.sendErrorMessage("�is �aid�jas n�ra pateik�s raporto.");
        else {
            report.setAnswered(true);
            target.sendMessage(Color.GREEN, "D�mesio, Administratorius pavirtino J�s� prane�im� (/report) ir tuojaus susisieks su Jumis. B�kite kantr�s.");
            LtrpPlayer.sendAdminMessage(String.format("Administratorius (%s) patvirtino prane�im� (/report) i� (%s) ", player.getName(), target.getName()));
            AdminLog.incrementReportAccepted(player);
            AdminLog.log(player, "Patvirtino /report i� veik�jo " + target.getUUID());
        }
        return true;
    }

    @Command
    @CommandHelp("Atmeta �aid�jo pateikt� raport�")
    public boolean aDre(Player p, @CommandParameter(name = "�aid�jo ID/Dalis vardo")LtrpPlayer target,
                        @CommandParameter(name = "Raporto atmetimo prie�astis")String reason) {
        LtrpPlayer player = LtrpPlayer.get(p);
        AdminPlugin adminPlugin = AdminPlugin.get(AdminPlugin.class);
        PlayerReport report;
        if(target == null || reason == null)
            return false;
        else if(!target.isOnline())
            player.sendErrorMessage("�aid�jas atsijung�.");
        else if((report = adminPlugin.getPlayerReport(target)) == null)
            player.sendErrorMessage("�is �aid�jas n�ra pateik�s raporto.");
        else {
            report.setAnswered(true);
            target.sendMessage(Color.GREEN, String.format("D�mesio, Administratorius %s atmet� J�s� prane�im� (/report) nes: %s ", player.getName(), reason));
            LtrpPlayer.sendAdminMessage(String.format("Administratorius (%s) atmet� prane�im� (/report) i� (%s)", player.getName(), target.getName()));
            AdminLog.incrementRepportRejected(player);
            AdminLog.log(player, "Atmet� /report i� veik�jo " + target.getUUID());
        }
        return true;
    }

    @Command
    @CommandHelp("Leid�ia per�i�r�ti paskutinius " + AdminPlugin.REPORT_CACHE_SIZE + " raportus")
    public boolean reports(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        AdminPlugin adminPlugin = AdminPlugin.get(AdminPlugin.class);
        new PlayerReportListDialog(player, eventManager, adminPlugin.getReports()).show();
        return true;
    }

    @Command
    @CommandHelp("Pradeda/u�baigia administracijos bud�jim�")
    public boolean adminDuty(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        if(controller.getAdminsOnDuty().contains(player)) {
            eventManager.dispatchEvent(new PlayerToggleAdminDutyEvent(player, true));
        } else {
            eventManager.dispatchEvent(new PlayerToggleAdminDutyEvent(player, false));
        }
        return true;
    }

    @Command
    @CommandHelp("Pasodina �aid�j� � OOC kal�jim� nurodytam laikui(minut�mis)")
    public boolean jail(Player p, @CommandParameter(name = "�aid�jo ID/Dalis vardo")LtrpPlayer target,
                        @CommandParameter(name = "Bausm�s trukm�(minut�mis)")int minutes,
                        @CommandParameter(name = "Kal�jimo prie�astis")String reason) {
        LtrpPlayer player = LtrpPlayer.get(p);
        PenaltyPlugin penaltyPlugin = PenaltyPlugin.get(PenaltyPlugin.class);
        if(target == null || reason == null)
            return false;
        else {
            LtrpPlayer.sendGlobalMessage(Color.LIGHTRED, String.format("Administratorius %s pasodino � kal�jim�%s, %d minut�ms.",
                    player.getName(), target.getName(), minutes));
            LtrpPlayer.sendGlobalMessage(Color.LIGHTRED, String.format("Nurodyt� prie�astis: %s ", reason));
            penaltyPlugin.jail(target, JailData.JailType.OutOfCharacter, minutes, player);
            AdminLog.log(player, "Pasodino " + target.getUUID() + " � OOC kal�jim� " + minutes + " minut�ms. Prie�astis:" + reason);

        }
        return true;
    }

    @Command
    @CommandHelp("�sp�ja �aid�j�(Max " + PenaltyPlugin.MAX_WARNS + " �sp�jimai")
    public boolean warn(Player p, @CommandParameter(name = "�aid�jo ID/Dalis vardo")LtrpPlayer target,
                        @CommandParameter(name = "�sp�jimo prie�astis")String reason) {
        LtrpPlayer player = LtrpPlayer.get(p);
        PenaltyPlugin penaltyPlugin = PenaltyPlugin.get(PenaltyPlugin.class);
        if(target == null || reason == null)
            return false;
        else if(target.getAdminLevel() > player.getAdminLevel())
            player.sendErrorMessage("Negalite �sp�ti auk�tesnio lygio administratoriaus.");
        else {
            LtrpPlayer.sendGlobalMessage(Color.LIGHTRED, String.format("Administratorius %s �sp�jo �aid�ja %s, prie�astis: %s",
                    player.getName(), target.getName(), reason));
            penaltyPlugin.warn(target, reason, player);
            AdminLog.log(player, "�sp�jo �adi�j� " + target.getUUID() +". Prie�astis: " + reason);

        }
        return true;
    }

    @Command
    @CommandHelp("U�blokuoja �aid�j�(NE IP)")
    public boolean ban(Player p, @CommandParameter(name = "�aid�jo ID/Dalis vardo")LtrpPlayer target,
                       @CommandParameter(name = "Blokavimo prie�astis")String reason,
                       @CommandParameter(name = "Valand� skai�ius, 0 - visam laikui")int hours) {
        LtrpPlayer player = LtrpPlayer.get(p);
        PenaltyPlugin penaltyPlugin = PenaltyPlugin.get(PenaltyPlugin.class);
        if(target == null || reason == null)
            return false;
        else if(target.getAdminLevel() > player.getAdminLevel())
            player.sendErrorMessage("Negalite blokuoti auk�tesnio lygio administratoriaus.");
        else {
            if(hours > 0) {
                LtrpPlayer.sendGlobalMessage(Color.LIGHTRED, String.format("Admin. %s u�draud� �aisti �aid�jui %s %d valandoms, prie�astis: %s",
                        player.getName(), target.getName(), hours, reason));
                penaltyPlugin.banPlayer(target, reason, hours, player);
                AdminLog.log(player, "U�blokavo " + target.getUUID() + " vartotoj� " + hours + " valandoms. Prie�astis: " + reason);
            } else {
                LtrpPlayer.sendGlobalMessage(Color.LIGHTRED, String.format("Admin. %s u�draud� �aisti �aid�jui %s, prie�astis: %s",
                        player.getName(), target.getName(), reason));
                penaltyPlugin.banPlayer(target, reason, player);
                AdminLog.log(player, "U�blokavo " + target.getUUID() + " vartotoj� visam laikui. Prie�astis: " + reason);
            }
        }
        return true;
    }

    @Command
    @CommandHelp("U�blokuoti �aid�j� ir jo IP adres�")
    public boolean ipBan(Player p, @CommandParameter(name = "�aid�jo ID/Dalis vardo")LtrpPlayer target,
                         @CommandParameter(name = "Blokavimo prie�astis")String reason,
                         @CommandParameter(name = "Valand� skai�ius, 0 - visam laikui")int hours) {
        LtrpPlayer player = LtrpPlayer.get(p);
        PenaltyPlugin penaltyPlugin = PenaltyPlugin.get(PenaltyPlugin.class);
        if(target == null || reason == null)
            return false;
        else if(target.getAdminLevel() > player.getAdminLevel())
            player.sendErrorMessage("Negalite blokuoti auk�tesnio lygio administratoriaus.");
        else {
            if(hours > 0) {
                LtrpPlayer.sendGlobalMessage(Color.LIGHTRED, String.format("Admin. %s u�draud� �aisti �aid�jui %s %d valandoms, prie�astis: %s",
                        player.getName(), target.getName(), hours, reason));
                penaltyPlugin.banIp(target, reason, hours, player);
                AdminLog.log(player, "U�blokavo " + target.getUUID() + " vartotojo IP " + target.getIp() +"  " + hours + " valandoms. Prie�astis " + reason);
            } else {
                LtrpPlayer.sendGlobalMessage(Color.LIGHTRED, String.format("Admin. %s u�draud� �aisti �aid�jui %s, prie�astis: %s",
                        player.getName(), target.getName(), reason));
                penaltyPlugin.banIp(target, reason, player);
                AdminLog.log(player, "U�blokavo " + target.getUUID() + " vartotojo IP " + target.getIp() + " visam laikui. Prie�astis: " + reason);
            }
        }
        return true;
    }

    @Command
    @CommandHelp("I�meta pasirinkt� �aid�j� i� serverio")
    public boolean kick(Player p, @CommandParameter(name = "�aid�jo ID/Dalis vardo")LtrpPlayer target,
                        @CommandParameter(name = "I�metimo prie�astis")String reason) {
        LtrpPlayer player = LtrpPlayer.get(p);
        if(target == null || reason == null)
            return false;
        else if(target.getAdminLevel() > player.getAdminLevel())
            player.sendErrorMessage("Negalite i�mesti auk�tesnio lygio administratoriaus.");
        else {
            LtrpPlayer.sendGlobalMessage(Color.LIGHTRED, String.format("Admin. %s i�spyr� �aid�j� %s i� serverio, prie�astis: %s",
                    player.getName(), target.getName(), reason));
            target.kick();
            AdminLog.log(player, "I�met� vartotoj� " + target.getUUID());
        }
        return true;
    }

    @Command
    @CommandHelp("Atkelia nurodyt� transporto priemon� � j�s� pozicij�")
    public boolean getoldcar(Player player, @CommandParameter(name = "Transporto priemon�s ID")Vehicle vehicle) {
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
    public boolean gotoloc(Player player, @CommandParameter(name = "Vietov�. Pvz: ls, bb")String pos) {
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
    @CommandHelp("Nukelia jus � pasirinktas koordinates")
    public boolean gotopos(Player player, Float x, Float y, Float z) {
        if(x == null || y == null || z == null) {
            player.sendMessage("Ne. ne taip");
            return false;
        }
        if(x != 0f && y != 0f && z != 0f) {
            player.setLocation(x, y, z);
        }
        return true;
    }

    @Command
    @CommandHelp("Nukelia jus prie pasirinktos transporot priemon�s")
    public boolean gotoCar(Player p, LtrpVehicle vehicle) {
        LtrpPlayer player = LtrpPlayer.get(p);
        if(vehicle == null) {
            player.sendErrorMessage("Tokios transporto priemon�s n�ra!");
        } else {
            player.setLocation(vehicle.getLocation());
            player.sendMessage(Color.NEWS, "Nusik�l�te prie " + vehicle.getModelName() + "(ID:" + vehicle.getId() + ")");
            return true;
        }
        return true;
    }

    @Command
    @CommandHelp("Atstato transporto priemon� � atsiradimo viet�")
    public boolean rc(Player player, @CommandParameter(name = "Transporto priemon�s ID")Vehicle veh) {
        LtrpPlayer p = LtrpPlayer.get(player);
        if(veh == null) {
            p.sendMessage(Color.LIGHTRED, "Transporto priemon�s su tokiu ID n�ra.");
        }
        return true;
    }


    @Command
    @CommandHelp("Paskiria nurodyt� �aid�j� frakcij� pri�i�r�toju")
    public boolean makeFactionManager(Player player, @CommandParameter(name = "�aid�jo ID/Dalis vardo")LtrpPlayer p2) {
        LtrpPlayer p = LtrpPlayer.get(player);
        if(p2 == null) {
            p.sendMessage(Color.LIGHTRED, "Tokio �aid�jo n�ra.");
        } else {
            PlayerController.get().getPlayerDao().setFactionManager(p2);
            p2.setFactionManager(true);

            LtrpPlayer.sendAdminMessage("Administratorius " + p.getName() + " suteik� �aid�jui " + p2.getName() + " frakcij� pri�i�r�tojo rang�.");

            p2.sendMessage(Color.NEWS, "Administratorius " + p.getName() + " paskyr� jus frakcij� pri�i�r�toju.");

            AdminLog.log(p, "Paskyr� �aid�j� " + p2.getName() + " frakcij� pri�ir�toju.");
        }
        return true;
    }

    @Command
    @CommandHelp("Paskiria �aid�j� frakcijos lyderiu")
    public boolean makeLeader(Player p, LtrpPlayer target) {
        LtrpPlayer player = LtrpPlayer.get(p);
        if(target == null) {
            player.sendErrorMessage("Tokio �aid�jo n�ra.");
        } else if(JobController.get().isLeader(target)) {
            player.sendErrorMessage(target.getName() + " jau yra kitos frakcijos lyderis. Pa�alinti j� galite su /removeLeader");
        } else {
            FactionListDialog.create(player, eventManager, JobController.get().getFactions())
                    .caption("Pasirinkite frakcij�")
                    .buttonOk("Pasirinkti")
                    .buttonCancel("I�eiti")
                    .onSelectFaction((d, f) -> {
                        String message = "Ar tikrai norite prid�ti " + target.getName() + " � frakcijos " + f.getName() + " lyderius?" +
                                "\n\nDabartinis(-ai) lyderis(-ai):\n";
                        for(int leaderId : f.getLeaders()) {
                            LtrpPlayer leader = LtrpPlayer.get(leaderId);
                            if(leader != null) {
                                message += leader.getName() + "(ID:" + leader.getId() + ")\n";
                            } else {
                                message += PlayerController.get().getPlayerDao().getUsername(leaderId) + "\n";
                            }
                        }

                        MsgboxDialog.create(player, eventManager)
                                .caption("D�mesio.")
                                .message(message)
                                .buttonOk("Taip")
                                .buttonCancel("Ne")
                                .onClickOk(dd -> {
                                    JobController.get().setJob(target, f, f.getRank(f.getRanks().size()-1));
                                    LtrpPlayer.sendAdminMessage("Administratorius " + player.getName() + " prid�jo " + target.getName() + " � frakcijos " + f.getName() + " lyderius.");
                                    target.sendMessage(Color.NEWS, "Administratorius " + player.getName() + " paskyr� jus, frakcijos " + f.getName() + " lyderiu!");
                                    f.sendMessage(Color.NEWS, target.getName() + " buvo paskirtas naujuoju frakcijos lyderiu!");
                                    eventManager.dispatchEvent(new PlayerSetFactionLeaderEvent(target, f));
                                })
                                .build()
                                .show();
                    })
                    .build()
                    .show();
        }
        return true;
    }

    @Command
    @CommandHelp("Pa�alina pasirinktos frakcijos lyder�, nesvarbu prisijung�s jis ar ne")
    public boolean removeLeader(Player p, String username) {
        LtrpPlayer player = LtrpPlayer.get(p);
        int userId = PlayerController.get().getPlayerDao().getUserId(username);
        if(userId == LtrpPlayer.INVALID_USER_ID) {
            player.sendErrorMessage("Tokio vartotojo n�ra!");
        } else if(!JobController.get().isLeader(userId)) {
            player.sendErrorMessage("Vartotojas " + username + " nevadovauja jokiai frakcijai.");
        } else {
            Optional<Faction> factionOp = JobController.get().getFactions().stream().filter(f -> f.getLeaders().contains(userId)).findFirst();
            if(factionOp.isPresent()) {
                Faction f = factionOp.get();
                MsgboxDialog.create(player, eventManager)
                        .caption("Frakcijos lyderio �alinimas")
                        .buttonOk("Taip")
                        .buttonCancel("Ne")
                        .message("Ar tikrai norite pa�alinti " + username + " i� frakcijos " + f.getName() + " lyderiu?" +
                            "\n�iuo metu frakcija turi " + f.getLeaders().size() + " lyderius.")
                        .onClickOk(d -> {
                            eventManager.dispatchEvent(new RemoveFactionLeaderEvent(userId, f));
                            LtrpPlayer.sendAdminMessage("Administratorius " + player.getName() + " pa�alino frakcijos " + f.getName() + " lyder� " + username + " i� pareig�.");
                            f.sendMessage(Color.NEWS, "Lyderis " + username + " buvo pa�alintas i� pareig�.");
                            JobController.get().getFactionDao().removeLeader(f, userId);
                        })
                        .build().show();
            }
        }

        return true;
    }
/*
    @Command
    @CommandHelp("Suteikia nurodyt� daikt� nurodytam �aid�jui")
    public boolean giveitem(Player player, LtrpPlayer p2, String itemClass, String args) {
        LtrpPlayer p = LtrpPlayer.get(player);
        if(p2 == null) {
            p.sendMessage(Color.LIGHTRED, "Tokio �aid�jo n�ra.");
        } else {
            List<String> matches = new ArrayList<>();
            Matcher m = Pattern.compile("([^\"][^-]*|\".+?\")\\s*").matcher(args); // strings with spaces can be made like this: "my string"
            while (m.find())
                matches.add(m.group(1).replace("\"", ""));
            Item item = null;
            try {
                item = Item.get(itemClass, matches.toArray(new String[0]));
            } catch (Exception e) {
                p.sendMessage(Color.SIENNA, e.getMessage());
                return false;
            }
            p.getInventory().add(item);
            p.sendMessage(Color.SIENNA, "It worked, wow");
            return true;

        }
        return true;
    }
*/

    @Command
    @CommandHelp("Atstato visas nenaudojamas kontraktinio darbo transporto priemones � atsiradimo viet�")
    public boolean rjc(Player p, ContractJob job) {
        LtrpPlayer player = LtrpPlayer.get(p);
        if(job != null) {
            int count = 0;
            for(JobVehicle jobVehicle : job.getVehicles()) {
                if(!jobVehicle.isUsed()) {
                    jobVehicle.respawn();
                    count++;
                }
            }
            player.sendMessage("Atstatytos " + count + " darbo " + job.getName() + " transporto priemon�s.");
            LtrpPlayer.sendGlobalMessage("Administratorius atstat� visas nenaudojamas darbo " + job.getName() + "transporto priemones.");
        } else
            player.sendErrorMessage("Darbo su tokiu ID n�ra.");
        return true;
    }


    @Command
    @CommandHelp("Atstato visas nenaudojamas frakcinio darbo transporto priemones � atsiradimo viet�")
    public boolean rfc(Player p, Faction faction) {
        LtrpPlayer player = LtrpPlayer.get(p);
        if(faction != null) {
            int count = 0;
            for(JobVehicle jobVehicle : faction.getVehicles()) {
                if(!jobVehicle.isUsed()) {
                    jobVehicle.respawn();
                    count++;
                }
            }
            player.sendMessage("Atstatytos " + count + " darbo " + faction.getName() + " transporto priemon�s.");
            LtrpPlayer.sendGlobalMessage("Administratorius atstat� visas nenaudojamas darbo " + faction.getName() + "transporto priemones.");
        } else
            player.sendErrorMessage("Frakcijos su tokiu ID n�ra.");
        return true;
    }

    @Command
    @CommandHelp("Priparkuoja transporto priemon�")
    public boolean dtc(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        PlayerVehicle playerVehicle = PlayerVehicle.getByVehicle(player.getVehicle());
        if(playerVehicle == null)
            playerVehicle = PlayerVehicle.getClosest(player, 4f);
        if(playerVehicle == null)
            player.sendErrorMessage("Turite b�ti transporto priemon�je arba prie jos(tinka tik �aid�jams priklausan�ios tr. priemon�s). ");
        else {
            String targetUsername = PlayerController.get().getUsernameByUUID(playerVehicle.getOwnerId());
            player.sendMessage(Color.GREEN, targetUsername + " automobilis " + playerVehicle.getModelName() + " s�kmingai priparkuotas.");
            player.destroy();
            LtrpPlayer target = LtrpPlayer.get(playerVehicle.getOwnerId());
            if(target != null)
                target.sendMessage(Color.GREEN, "Administratorius " + player.getName() + " priverstinai priparkavo j�s� " + playerVehicle.getModelName() + ", v�l j� i�parkuoti glaite su /v get.");
            AdminLog.log(player, "Priparkavo tr. priemon� " + playerVehicle.getUUID());
        }
        return true;
    }

    @Command()
    @CommandHelp("Pagydo �aid�j� bei prikelia j� i� komos")
    public boolean aheal(Player p, @CommandParameter(name = "�aid�jo ID/Dalis vardo")LtrpPlayer target) {
        LtrpPlayer player = LtrpPlayer.get(p);
        if(target == null) {
            player.sendErrorMessage("Tokio �aid�jo n�ra!");
        } else {
            target.setHealth(100f);
            if(target.isInAnyVehicle()) {
                target.getVehicle().repair();
            }
            if(target.isInComa()) {
                target.setInComa(false);
                if(player.getCountdown() != null)
                    player.getCountdown().forceStop();
            }
            target.sendMessage(Color.GREEN, "Administratorius " + player.getName() + " pagyd� jus.");
            player.sendMessage(Color.GREEN, "�aid�jas " + target.getName() + "(ID:" + target.getId() + ") pagydytas");
            AdminLog.log(player, "Healed user " + target.getName() + " uid: " + target.getUUID());
            return true;
        }
        return true;
    }

    @Command
    @CommandHelp("Dont")
    public boolean fly(Player player) {
        if(player.getSpecialAction() == SpecialAction.NONE) {
            player.setSpecialAction(SpecialAction.USE_JETPACK);
        } else
            player.setSpecialAction(SpecialAction.NONE);
        return true;
    }

    @Command
    public boolean vehicledmg(Player p, Vehicle v) {
        LtrpPlayer player = LtrpPlayer.get(p);
        LtrpVehicle vehicle;
        if(v == null) {
            vehicle = LtrpVehicle.getClosest(player, 5f);
        } else {
            vehicle = LtrpVehicle.getByVehicle(v);
        }
        if(vehicle != null) {
            VehicleDamage dmg = vehicle.getDamage();
            ListDialog.create(player, eventManager)
                    .caption(vehicle.getModelName())
                    .buttonOk("Pasirinkti")
                    .buttonCancel("I�eiti")
                    .item("Padangos " + Integer.toBinaryString(dmg.getTires()), i -> {
                        showBinaryInputDialog(player, "Padang� b�sena.",
                                "Dabartin� b�sena: " + Integer.toBinaryString(dmg.getTires()) + "(" + dmg.getTires() + ")",
                                (d, val) -> {
                                    dmg.setTires(val);
                                    player.sendMessage(Color.GREEN, "Padang� b�sena pakeista �" + Integer.toBinaryString(val));
                                }).show();
                    })
                    .item("Panel�s " + Integer.toBinaryString(dmg.getPanels()), i -> {
                        showBinaryInputDialog(player, "Paneli� b�sena.",
                                "Dabartin� b�sena: " + Integer.toBinaryString(dmg.getPanels()) + "(" + dmg.getPanels() + ")",
                                (d, val) -> {
                                    dmg.setPanels(val);
                                    player.sendMessage(Color.GREEN, "Paneli� b�sena pakeista �" + Integer.toBinaryString(val));
                                }).show();
                    })
                    .item("�viesos " + Integer.toBinaryString(dmg.getLights()), i -> {
                        showBinaryInputDialog(player, "�vies� b�sena.",
                                "Dabartin� b�sena: " + Integer.toBinaryString(dmg.getLights()) + "(" + dmg.getLights() + ")",
                                (d, val) -> {
                                    dmg.setLights(val);
                                    player.sendMessage(Color.GREEN, "�vies� b�sena pakeista �" + Integer.toBinaryString(val));
                                }).show();
                    })
                    .item("Durys " + Integer.toBinaryString(dmg.getDoors()), i -> {
                        showBinaryInputDialog(player, "Dur� b�sena.",
                                "Dur� b�sena: " + Integer.toBinaryString(dmg.getDoors()) + "(" + dmg.getDoors() + ")",
                                (d, val) -> {
                                    dmg.setDoors(val);
                                    player.sendMessage(Color.GREEN, "Dur� b�sena pakeista �" + Integer.toBinaryString(val));
                                }).show();
                    })
                    .build()
                    .show();
        }
        return true;
    }

    private InputDialog showBinaryInputDialog(LtrpPlayer player, String caption, String message, BinaryInputDiualogClickOkHandler inputHandler) {
        return InputDialog.create(player, eventManager)
                .caption(caption)
                .message(message)
                .onClickOk((d, s) -> {
                    s = s.trim().replaceAll(" ", "");
                    try {
                        int val = Integer.parseInt(s, 2);
                        inputHandler.onEnterBinary(d, val);
                    } catch (NumberFormatException e) {
                        player.sendErrorMessage("Pra�ome �vesti skai�i�, dvejetainiu formatu!");
                        d.show();
                    }
                })
                .build();
    }

    @FunctionalInterface
    private interface BinaryInputDiualogClickOkHandler {
        void onEnterBinary(InputDialog d, int val);
    }

    @Command
    @CommandHelp
    public boolean eventlog(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        ShoebillEventLoggerPlugin loggerPlugin = Shoebill.get().getResourceManager().getPlugin(ShoebillEventLoggerPlugin.class);
        if(loggerPlugin == null) {
            player.sendErrorMessage("�iuo metu plugin'as yra i�jungtas.");
        } else {
            ListDialog.create(p, eventManager)
                    .caption("Event logging")
                    .item("Globalus 'update' event loginimas[" + (loggerPlugin.isLogUpdateEvents() ? "+" : "-") + "]", i -> {
                        loggerPlugin.setLogUpdateEvents(!loggerPlugin.isLogUpdateEvents());
                        player.sendMessage("Update tipo event loginimas atnaujintas.");
                        i.getCurrentDialog().show();
                    })
                    .item("Mano loginimas[" + (loggerPlugin.isLoggingEnabled(p) ? "+" : "-") + "]", i -> {
                        if (loggerPlugin.isLoggingEnabled(p)) {
                            loggerPlugin.stopLogging(p);
                            player.sendMessage("Loginimas sustabdytas");
                        } else {
                            loggerPlugin.startLogging(p, false);
                            player.sendMessage("Loginimas prad�tas.");
                        }
                        i.getCurrentDialog().show();
                    })
                    .item("Mano 'update' event loginimas[" + (loggerPlugin.isUpdateLoggingEnabled(p) ? "+" : "-") + "]", i -> {
                        loggerPlugin.startLogging(p, !loggerPlugin.isUpdateLoggingEnabled(p));
                        player.sendMessage("Asmenin� 'update' tipo loginimo informacija atnaujinta.");
                        i.getCurrentDialog().show();
                    })
                    .buttonOk("Pasirinkti")
                    .buttonCancel("I�eiti")
                    .build()
                    .show();

        }
        return true;
    }


    @Command
    @CommandHelp("I�meta �aid�j� i� darbo")
    public boolean aUnInvite(Player p, @CommandParameter(name = "�aid�jo ID/Dalis vardo")LtrpPlayer target) {
        LtrpPlayer player = LtrpPlayer.get(p);
        if(target == null) {
            player.sendErrorMessage("Tokio �aid�jo n�ra!");
        } else {
            PlayerJobData jobData = JobController.get().getJobData(target);
            if(jobData.getJob() == null) {
                player.sendErrorMessage(target.getName() + " neturi darbo.");
            } else {
                Job job = jobData.getJob();
                JobController.get().setJob(target, null);
                player.sendMessage(Color.GREEN, String.format("�aid�jas %s(%d) i�mestas i� darbo \"%s\".", target.getName(), target.getId(), job.getName()));
                target.sendMessage(Color.GREEN, "Administratorius " + player.getName() + " i�met� jus i� darbo.");
            }
        }
        return true;
    }

    @Command
    @CommandHelp("Atidaro versl� preki� valdymo GUI")
    public boolean bizItems(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        BusinessController.get().showAvailableCommodityDialog(player);
        return true;
    }

    @Command
    public boolean abiz(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        BusinessController.get().showManagementDialog(player);
        return true;
    }

    @Command
    public boolean ahou(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        HouseController.get().showManagementDialog(player);
        return true;
    }

    @Command
    public boolean agarage(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        GarageController.get().showManagementDialog(player);
        return true;
    }

    @Command
    @CommandHelp("Atidaro serverio statistikos GUI")
    public boolean serverstats(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        ServerStatsMsgBoxDialog.create(player, eventManager)
                .show();
        return true;
    }

    // TODO aProperty
    // TODO cmd:ado
    // TOOD cmd:ao
}
