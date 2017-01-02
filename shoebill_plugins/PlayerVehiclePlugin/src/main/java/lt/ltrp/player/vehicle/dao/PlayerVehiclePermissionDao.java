package lt.ltrp.player.vehicle.dao;

import lt.ltrp.player.vehicle.constant.PlayerVehiclePermission;
import lt.ltrp.player.object.LtrpPlayer;
import lt.ltrp.player.vehicle.object.PlayerVehicle;

import java.util.Collection;
import java.util.Map;

/**
 * @author Bebras
 *         2016.05.31.
 */
public interface PlayerVehiclePermissionDao {


    void remove(PlayerVehicle vehicle, int userId);
    void remove(PlayerVehicle vehicle);
    void remove(PlayerVehicle vehicle, int userId, PlayerVehiclePermission permission);
    void add(PlayerVehicle vehicle, LtrpPlayer player, PlayerVehiclePermission permission);
    void add(PlayerVehicle vehicle, int userId, PlayerVehiclePermission permission);
    void add(int vehicleId, int userId, PlayerVehiclePermission permission);
    Collection<PlayerVehiclePermission> get(int vehicleId, int userId);
    Map<Integer, PlayerVehiclePermission> get(int vehicleId);

}
