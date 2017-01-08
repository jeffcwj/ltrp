package lt.ltrp.player.vehicle.event;


import lt.ltrp.player.object.LtrpPlayer;
import lt.ltrp.player.vehicle.object.PlayerVehicle;
import lt.ltrp.player.vehicle.constant.PlayerVehiclePermission;

/**
 * @author Bebras
 *         2016.03.11.
 */
public class PlayerVehicleAddPermissionEvent extends PlayerVehicleEvent {

    private int target;
    private PlayerVehiclePermission permission;

    public PlayerVehicleAddPermissionEvent(LtrpPlayer player, PlayerVehicle vehicle, int target, PlayerVehiclePermission permission) {
        super(player, vehicle);
        this.target = target;
        this.permission = permission;
    }

    public int getTarget() {
        return target;
    }

    public PlayerVehiclePermission getPermission() {
        return permission;
    }
}