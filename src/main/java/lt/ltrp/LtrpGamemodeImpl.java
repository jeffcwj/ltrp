package lt.ltrp;


import lt.ltrp.dao.DAOFactory;
import lt.ltrp.event.PaydayEvent;
import net.gtaun.shoebill.Shoebill;
import net.gtaun.shoebill.object.Server;
import net.gtaun.shoebill.object.Timer;
import net.gtaun.shoebill.object.World;
import net.gtaun.shoebill.resource.Gamemode;
import net.gtaun.util.event.EventManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class LtrpGamemodeImpl extends Gamemode implements LtrpGamemode {


    private static DAOFactory dao;
    private static final Logger logger = LoggerFactory.getLogger(LtrpGamemode.class);
    private Timer paydayTimer;
    //private JobManager jobManager;
    //private VehicleController vehicleManager;
   // private PropertyController propertyManager;
  //  private DmvManager dmvManager;
   // private ItemController itemController;


    @Override
    protected void onEnable() throws Throwable {

       // MapAndreas.Init(MapAndreasMode.Full);

        Server.get().setGamemodeText(NameShort + " " + Version);
        Server.get().setServerCodepage(1257);
        Server.get().setHostname(Name);

      /*  getEventManager().registerHandler(PlayerClickMapEvent.class, e -> {
            LtrpPlayer player = LtrpPlayer.get(e.getPlayer());
            if (player != null && player.getAdminLevel() > 0) {
                float z = MapAndreas.FindZ(e.getPosition().getX(), e.getPosition().getY());
                System.out.println("Player location: " + e.getPlayer().getLocation() + " selected pos:" + e.getPosition() + " MapAndreas z:" + z);
                player.setLocation(new Location(e.getPosition().getX(), e.getPosition().getY(), z+ 0.5f));
            }
        });
        */

        schedulePaydayTimer();
        logger.debug("DATA DIR: " + Shoebill.get().getResourceManager().getGamemode().getDataDir().getAbsolutePath());
        EventManager eventManager = getEventManager();

//        BankPlugin bankPlugin = Shoebill.get().getResourceManager().getPlugin(BankPlugin.class);

        try {
            //itemController = new ItemController(eventManager, dao.getItemDao());
            //vehicleManager = new VehicleControllerImpl(eventManager, VehicleController.get().getDao());
            //jobManager = new JobManager(eventManager, getDao().getJobDao(), vehicleManager);
            //propertyManager = new PropertyManager(eventManager, PropertyController.get().getHouseDao(), bankPlugin);
          //  dmvManager = new DmvManager(eventManager);
        } catch(Exception e) {
            e.printStackTrace();
        }

        new PawnCallbacks(eventManager);
    }

    private void schedulePaydayTimer() {
        LocalDateTime now = LocalDateTime.now();
        int seconds = (60 - now.getMinute()) * 60;
        seconds += 60 - now.getSecond();
        logger.info("Scheduling payday timer in " + seconds + " seconds");
        paydayTimer = Timer.create(seconds * 1000, ticks -> {
            logger.info("Sending PayDay event. Current hour is " + LocalDateTime.now().getHour() + ". ");
            logger.debug("Timer is running?" + paydayTimer.isRunning() + " is destryoed?" + paydayTimer.isDestroyed());
            int hour = LocalDateTime.now().getHour() + TIME_OFFSET;
            if(hour > 23)
                hour -= 24;
            World.get().setWorldTime(hour);

            getEventManager().dispatchEvent(new PaydayEvent(LocalDateTime.now().getHour()));
            schedulePaydayTimer();
        });
    }

    @Override
    protected void onDisable() throws Throwable {
        //jobManager.destroy();
        paydayTimer.destroy();
      //  vehicleManager.destroy();
        //propertyManager.destroy();
      //  dmvManager.destroy();
    }

    public static DAOFactory getDao() {
        if(dao == null) {
            dao = DAOFactory.getInstance();
        }
        return dao;
    }
}