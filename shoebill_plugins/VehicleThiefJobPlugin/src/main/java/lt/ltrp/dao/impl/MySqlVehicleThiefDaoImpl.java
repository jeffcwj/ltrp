package lt.ltrp.dao.impl;

import lt.ltrp.LoadingException;
import lt.ltrp.dao.JobVehicleDao;
import lt.ltrp.dao.VehicleThiefDao;
import lt.ltrp.object.VehicleThiefJob;
import lt.ltrp.object.impl.VehicleThiefJobImpl;
import net.gtaun.util.event.EventManager;

import javax.sql.DataSource;

/**
 * @author Bebras
 *         2016.05.23.
 */
public class MySqlVehicleThiefDaoImpl extends MySqlJobDaoImpl implements VehicleThiefDao {


    public MySqlVehicleThiefDaoImpl(DataSource dataSource, JobVehicleDao jobVehicleDao, EventManager eventManager) {
        super(dataSource, jobVehicleDao , eventManager);
    }

    @Override
    public VehicleThiefJob get(int id) {
        VehicleThiefJob job = null;
        if(isValid(id)) {
            job = new VehicleThiefJobImpl(id, getEventManager());
            try {
                super.load(job);
            } catch (LoadingException e) {
                e.printStackTrace();
            }
        }
        return job;
    }

    @Override
    public void update(VehicleThiefJob vehicleThiefJob) {
        super.update(vehicleThiefJob);
    }
}