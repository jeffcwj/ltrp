package lt.ltrp.command;


import lt.ltrp.LtrpWorld;
import lt.ltrp.constant.Currency;
import lt.ltrp.data.Color;
import lt.ltrp.event.property.garage.GarageBuyEvent;
import lt.ltrp.event.property.garage.PlayerEnterGarageEvent;
import lt.ltrp.event.property.garage.PlayerExitGarageEvent;
import lt.ltrp.object.Garage;
import lt.ltrp.object.LtrpPlayer;
import lt.ltrp.object.LtrpVehicle;
import net.gtaun.shoebill.common.command.Command;
import net.gtaun.shoebill.common.command.CommandHelp;
import net.gtaun.shoebill.object.Player;
import net.gtaun.util.event.EventManager;

/**
 * @author Bebras
 *         2015.12.05.
 */
public class GarageCommands {

    private EventManager eventManager;

    public GarageCommands(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /*@BeforeCheck
    public boolean beforeCheck(Player p, String cmd, String params) {
        LtrpPlayer player = LtrpPlayer.get(p);
        if(player != null) {
            Property property = player.getProperty();
            if(property != null && property instanceof Garage || Garage.getClosest(player.getLocation(), 5f) != null) {
                return true;
            }
        }
        return false;
    }*/

    @Command
    @CommandHelp("garagehelp")
    public boolean garageHelp(Player p) {
        p.sendMessage(Color.GREEN, "|__________________GARA�O VALDYMO INFORMACIJA__________________|");
        p.sendMessage(Color.LIGHTGREY,"  /ginv - gara�e laikomi daiktai.");
        p.sendMessage(Color.WHITE,"  /buygarage - naudojamas gara�o pirkimui.");
        p.sendMessage(Color.LIGHTGREY,"  /lock - gara�o u�rakinimas/atrakinimas.");
        p.sendMessage(Color.WHITE,"  /sellgarage [�aid�jo ID/ Dalis vardo] [SUMA] - parduoti savo gara��");
        p.sendMessage(Color.GREEN, "__________________________________________________________________");
        return true;
    }


    @Command
    public boolean enter(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        Garage garage = Garage.getClosest(p.getLocation(), 8f);
        if(garage == null)
            return false;
        if(garage.isLocked())
            player.sendErrorMessage("Gara�as u�rakintas");
        else if(garage.getExit() == null)
            player.sendErrorMessage("Gara�as dar ne�rengtas!");
        else if(garage.getVehicle() != null)
            player.sendErrorMessage("Gara�e jau stovi ma�ina, antra netilps!");
        else {
            LtrpVehicle vehicle = null;
            if(!player.isInAnyVehicle()) {
                player.setLocation(garage.getExit());
            } else {
                vehicle = LtrpVehicle.getByVehicle(player.getVehicle());
                garage.setVehicle(vehicle);
                vehicle.setLocation(garage.getVehicleExit());
            }
            eventManager.dispatchEvent(new PlayerEnterGarageEvent(garage, player, vehicle));
        }
        return true;
    }


    @Command
    public boolean exit(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        Garage garage = Garage.getClosest(p.getLocation(), 8f);
        if(garage == null)
            return false;
        if(garage.isLocked())
            player.sendErrorMessage("Gara�as u�rakintas");
        else {
            LtrpVehicle vehicle = null;
            if(player.isInAnyVehicle()) {
                vehicle = LtrpVehicle.getByVehicle(player.getVehicle());
                garage.setVehicle(null);
                vehicle.setLocation(garage.getVehicleEntrance());
                //vehicle.setInterior(garage.getVehicleEntrance().getInteriorId());
            } else {
                player.setLocation(garage.getEntrance());
            }
            eventManager.dispatchEvent(new PlayerExitGarageEvent(garage, player, vehicle));
        }
        return true;
    }

    @Command
    @CommandHelp("Nuperka gara��")
    public boolean buyGarage(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        Garage garage = Garage.getClosest(p.getLocation(), 5f);
        if(garage == null)
            player.sendErrorMessage("�i� komand� galite naudoti tik b�dami prie gara�o!");
        else if(garage.getOwner() != LtrpPlayer.INVALID_USER_ID)
            player.sendErrorMessage("�is gara�as jau turi savinink�");
        else if(garage.getPrice() > player.getMoney())
            player.sendErrorMessage("J�s neturite tiek pinig�.");
        else {
            int price = garage.getPrice();
            player.giveMoney(-price);
            LtrpWorld.get().addMoney(price);
            garage.setOwner(player.getUUID());
            player.sendMessage(Color.GARAGE, "S�kmingai �sigijote gara�� u� " + price + Currency.SYMBOL);
            eventManager.dispatchEvent(new GarageBuyEvent(garage, null, player));
        }
        return true;
    }


}