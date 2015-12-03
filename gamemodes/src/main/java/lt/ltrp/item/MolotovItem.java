package lt.ltrp.item;

import lt.ltrp.object.Fire;
import lt.ltrp.player.LtrpPlayer;
import net.gtaun.shoebill.Shoebill;
import net.gtaun.shoebill.data.AngledLocation;
import net.gtaun.shoebill.data.Location;
import net.gtaun.shoebill.object.Timer;

import java.util.Random;

/**
 * @author Bebras
 *         2015.12.03.
 */
public class MolotovItem extends BasicItem {

    public MolotovItem(String name, int id, ItemType type) {
        super(name, id, type, false);
    }

    @ItemUsageOption(name = "Mesti")
    public boolean tHrow(LtrpPlayer player, Inventory inventory) {
        // Player cant use other inventories for this
        if(player.getInventory() != inventory) {
            return false;
        }

        LtrpPlayer[] closestPlayers = player.getClosestPlayers(5.0f);
        // Molotovs are only allowed to use when an administrator is near.
        LtrpPlayer admin = null;
        if(player.getAdminLevel() > 2)
            admin = player;
        else {
            for(LtrpPlayer p : closestPlayers) {
                if(p.getAdminLevel() > 2) {
                    admin = p;
                    break;
                }
            }
        }

        if(admin != null) {
            Item item = inventory.getItem(ItemType.Lighter);
            if(item != null) {
                player.applyAnimation("lol", "wat");
                player.sendActionMessage("u�dega molotovo kokteil� ir meta j�");

                final Location explosionLoc = new Location();
                float  distanceThrown = new Random().nextFloat()*20 + 8.0f;
                AngledLocation playerLoc = player.getLocation();

                explosionLoc.setX(playerLoc.getX() + (float)(distanceThrown * Math.sin(-Math.toRadians(playerLoc.getAngle()))));
                explosionLoc.setY(playerLoc.getY() + (float) (distanceThrown * Math.cos(-Math.toRadians(playerLoc.getAngle()))));
                // We play the animation until we find the Z coordination asynchronously
                new Thread(() -> {
                    explosionLoc.setZ(MapAndreas.FindZ(explosionLoc.getX(), explosionLoc.getY()));
                    // Once we get the Z coordinate we run explosion creation on SAMP thread
                    Shoebill.get().runOnSampThread(() -> {
                        Fire.create(player, explosionLoc, 6, 11, 1);
                    });
                }).start();

            }
        }
    }
}

