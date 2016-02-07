package lt.ltrp.player;

import lt.ltrp.LtrpGamemode;
import lt.ltrp.Util.PawnFunc;
import lt.ltrp.dao.PlayerDao;
import lt.ltrp.event.player.PlayerSpawnSetUpEvent;
import net.gtaun.shoebill.amx.AmxCallable;
import net.gtaun.shoebill.amx.types.ReferenceFloat;
import net.gtaun.shoebill.data.Location;
import net.gtaun.shoebill.data.WeaponData;
import net.gtaun.shoebill.event.player.PlayerSpawnEvent;
import net.gtaun.shoebill.object.Player;
import net.gtaun.util.event.EventManager;
import net.gtaun.util.event.EventManagerNode;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Bebras
 *         2015.11.12.
 */
public class SpawnController implements Runnable {

    private LtrpPlayer player;
    private SpawnData spawnData;
    private EventManagerNode managerNode;

    public SpawnController(EventManager manager, LtrpPlayer player) {
        Logger.getLogger(SpawnController.class.getSimpleName()).log(Level.INFO, "SpawnController :: constructor");
        this.player = player;
        this.managerNode = manager.createChildNode();

        managerNode.registerHandler(PlayerSpawnEvent.class, e-> {
           if(spawnData != null) {
               if(spawnData.getSkin() != player.getSkin()) {
                   player.setSkin(spawnData.getSkin());
               }
           }
        });
    }

    @Override
    public void run() {
        Logger.getLogger(SpawnController.class.getSimpleName()).log(Level.INFO, "SpawnController :: run");
        PlayerDao playerDao = LtrpGamemode.getDao().getPlayerDao();
        spawnData = playerDao.getSpawnData(player);
        CrashData crashData = playerDao.getCrashData(player);
        JailData jailData = playerDao.getJailData(player);
        Logger.getLogger(SpawnController.class.getSimpleName()).log(Level.INFO, "SpawnController :: run. All data received");

        // The player doesn't exist
        if(spawnData == null)
            return;


        if(jailData != null) {
            player.jail(jailData);
        } else {
            if(crashData != null) {
                player.setSpawnInfo(crashData.getLocation(), 0.0f, spawnData.getSkin(), Player.NO_TEAM, spawnData.getWeaponData()[0], spawnData.getWeaponData()[1], spawnData.getWeaponData()[2]);
            } else {
                Location location;
                float x = 0.0f, y = 0.0f, z = 0.0f;
                ReferenceFloat refX = new ReferenceFloat(x);
                ReferenceFloat refY = new ReferenceFloat(y);
                ReferenceFloat refZ = new ReferenceFloat(z);
                int interior, world, index;
                switch(spawnData.getType()) {
                    case House:
                        AmxCallable getIndex = PawnFunc.getNativeMethod("GetHouseIndex"),
                            getPos = PawnFunc.getNativeMethod("GetHouseEntrancePos"),
                            getInt = PawnFunc.getNativeMethod("GetHouseEntranceInteriorID"),
                            getWorld = PawnFunc.getNativeMethod("GetHouseEntranceVirtualWorld");
                        index = (Integer)getIndex.call(spawnData.getId());
                        getPos.call(index, refX, refY, refZ);
                        interior = (Integer)getInt.call(index);
                        world = (Integer)getWorld.call(index);
                        location = new Location(refX.getValue(), refY.getValue(), refZ.getValue(), interior, world);
                        break;
                    case Business:
                        getIndex = PawnFunc.getNativeMethod("GetBusinessIndex");
                        getPos = PawnFunc.getNativeMethod("GetBusinessEntrancePos");
                        getInt = PawnFunc.getNativeMethod("GetBusinessEntranceVirtualWorld");
                        getWorld = PawnFunc.getNativeMethod("GetBusinessEntranceInteriorID");
                        index = (Integer)getIndex.call(spawnData.getId());
                        getPos.call(index, refX, refY, refZ);
                        interior = (Integer)getInt.call(index);
                        world = (Integer)getWorld.call(index);
                        location = new Location(refX.getValue(), refY.getValue(), refZ.getValue(), interior, world);
                        break;
                    case Faction:
                        getIndex = PawnFunc.getNativeMethod("GetFactionIndex");
                        getPos = PawnFunc.getNativeMethod("GetFactionPosition");
                        getInt = PawnFunc.getNativeMethod("GetFactionVirtualWorld");
                        getWorld = PawnFunc.getNativeMethod("GetFactionInterior");
                        index = (Integer)getIndex.call(spawnData.getId());
                        getPos.call(index, refX, refY, refZ);
                        interior = (Integer)getInt.call(index);
                        world = (Integer)getWorld.call(index);
                        location = new Location(refX.getValue(), refY.getValue(), refZ.getValue(), interior, world);
                        break;
                    case Garage:
                        getIndex = PawnFunc.getNativeMethod("GetGarageIndex");
                        getPos = PawnFunc.getNativeMethod("GetGarageEntrancePos");
                        getInt = PawnFunc.getNativeMethod("GetGarageEntranceVirtualWorld");
                        getWorld = PawnFunc.getNativeMethod("GetGarageEntranceInteriorID");
                        index = (Integer)getIndex.call(spawnData.getId());
                        getPos.call(index, refX, refY, refZ);
                        interior = (Integer)getInt.call(index);
                        world = (Integer)getWorld.call(index);
                        location = new Location(refX.getValue(), refY.getValue(), refZ.getValue(), interior, world);
                        break;
                    default:
                        getPos = PawnFunc.getNativeMethod("Data_GetCoordinates");
                        getInt = PawnFunc.getNativeMethod("Data_GetInterior");
                        getWorld = PawnFunc.getNativeMethod("Data_GetVirtualWorld");
                        int wat = (Integer)getPos.call("default_spawn", refX, refY, refZ);
                        System.out.println("Wat:" + wat);
                        interior = (Integer)getInt.call("default_spawn");
                        world = (Integer)getWorld.call("default_spawn");
                        System.out.println("SpawnController :: run. X: " + refX.getValue() + " Y:" + refY.getValue() + " Z:" + refZ.getValue() + " Int:" + interior + " ww:" + world);
                        location = new Location(refX.getValue(), refY.getValue(), refZ.getValue(), interior, world);
                        break;
                }
                System.out.println("SpawnController :: run. Spawn skin: " + spawnData.getSkin());
                player.setSpawnInfo(location, 0.0f, spawnData.getSkin(), Player.NO_TEAM, spawnData.getWeaponData()[0], spawnData.getWeaponData()[1], spawnData.getWeaponData()[2]);
                Logger.getLogger(SpawnController.class.getSimpleName()).log(Level.INFO, "SpawnController :: run. Spawn info set");
            }
            Logger.getLogger(SpawnController.class.getSimpleName()).log(Level.INFO, "SpawnController :: run. Dispatching event");
            managerNode.dispatchEvent(new PlayerSpawnSetUpEvent(player));
        }
        if(crashData != null) {
            playerDao.remove(player, crashData);
        }
        Logger.getLogger(SpawnController.class.getSimpleName()).log(Level.INFO, "SpawnController :: run. Finished");
    }
}