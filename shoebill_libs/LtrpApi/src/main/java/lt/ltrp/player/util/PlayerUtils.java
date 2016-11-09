package lt.ltrp.player.util;

import lt.ltrp.object.LtrpPlayer;
import net.gtaun.shoebill.constant.PlayerAttachBone;
import net.gtaun.shoebill.data.AngledLocation;
import net.gtaun.shoebill.data.Location;
import net.gtaun.shoebill.object.PlayerAttach;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bebras
 *         2016.05.16.
 */
public final class PlayerUtils {

    private static Map<PlayerAttachBone, Integer> boneToSlotMap = new HashMap<>();

    static {
        boneToSlotMap.put(PlayerAttachBone.HAND_LEFT, 5);
    }

    public static PlayerAttach.PlayerAttachSlot getSlotByBone(LtrpPlayer player, PlayerAttachBone bone) {
        int slot = boneToSlotMap.containsKey(bone) ? boneToSlotMap.get(bone) : -1;
        if(slot != -1)
            return player.getAttach().getSlot(slot);
        else {
            for(PlayerAttach.PlayerAttachSlot s : player.getAttach().getSlots()) {
                if(!s.isUsed())
                    return s;
            }
        }
        return player.getAttach().getSlot(9);
    }


    public static Location getInFront(LtrpPlayer player, float distance) {
        AngledLocation loc = player.getLocation().clone();
        loc.x += (distance * Math.sin(Math.toRadians(-loc.angle + 180)));
        loc.y += (distance * Math.cos(Math.toRadians(-loc.angle + 180)));
        return loc;
    }


}