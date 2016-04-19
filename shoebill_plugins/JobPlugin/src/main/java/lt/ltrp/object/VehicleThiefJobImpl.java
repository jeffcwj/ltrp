package lt.ltrp.object;

import lt.ltrp.AbstractContractJob;
import lt.ltrp.data.NamedLocation;
import net.gtaun.shoebill.data.Location;
import net.gtaun.util.event.EventManager;

import java.util.*;

/**
 * @author Bebras
 *         2015.12.18.
 */
public class VehicleThiefJobImpl extends AbstractContractJob implements VehicleThiefJob {

    private Map<NamedLocation, Integer> vehicleBuyPoints;
    private int[] requiredModels;
    private int requiredModelCount;
    private Random random;


    public VehicleThiefJobImpl(int id, EventManager eventManager) {
        this(id, null, null, 0, eventManager, 0, 0, 0);
    }

    public VehicleThiefJobImpl(int id, String name, Location location, int basePaycheck, EventManager eventManager, int contractLength, int maxPaycheck, int minPaycheck) {
        super(id, name, location, basePaycheck, eventManager, contractLength, maxPaycheck, minPaycheck);
        this.vehicleBuyPoints = new HashMap<>();
        this.random = new Random();
        Instance.instance = this;
    }

    public void addBuyPoint(NamedLocation location, int model) {
        this.vehicleBuyPoints.put(location, model);
    }

    public void addBuyPoint(NamedLocation location) {
        this.addBuyPoint(location, getModel());
    }


    public Map<NamedLocation, Integer> getVehicleBuyPoints() {
        return vehicleBuyPoints;
    }

    public int[] getRequiredModels() {
        return requiredModels;
    }

    public void setRequiredModels(int[] requiredModels) {
        this.requiredModels = requiredModels;
    }

    public int getRequiredModelCount() {
        return requiredModelCount;
    }

    public void setRequiredModelCount(int requiredModelCount) {
        this.requiredModelCount = requiredModelCount;
    }

    public void resetRequiredModels() {
        List<Integer> models = getSpawnedVehicleModels();
        int[] requiredModels = new int[getRequiredModelCount()];
        for(int i = 0; i < requiredModels.length; i++) {
            int index = random.nextInt(models.size());
            requiredModels[ i ] = models.get(index);
            models.remove(index);
        }

        setRequiredModels(requiredModels);
    }

    private int getModel() {
        List<Integer> models = getSpawnedVehicleModels();
        int index = random.nextInt(models.size());
        int model = models.get(index);
        models.remove(index);
        return model;
    }

    private List<Integer> getSpawnedVehicleModels() {
        List<Integer> models = new ArrayList<>();
        // We only loop through player vehicles because other vehicles can NOT be stolen
        for(LtrpVehicle vehicle : LtrpVehicle.get()) {
            if(!(vehicle instanceof PlayerVehicle))
                continue;
            if (models.contains(vehicle.getModelId())) {
                models.add(vehicle.getModelId());
            }
        }
        // We don't want the same models twice in a row
        for(Integer model : getRequiredModels()) {
            if (models.contains(model)) {
                models.remove(model);
            }
        }
        return models;
    }

    public void log(String s) {
        System.out.println(s);
    }
}