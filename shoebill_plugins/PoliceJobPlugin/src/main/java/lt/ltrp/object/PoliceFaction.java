package lt.ltrp.object;

import lt.ltrp.job.object.Faction;

/**
 * @author Bebras
 *         2016.04.14.
 */
public interface PoliceFaction extends Faction {

    boolean isTaserEnabled();
    void setTaserEnabled(boolean set);

}
