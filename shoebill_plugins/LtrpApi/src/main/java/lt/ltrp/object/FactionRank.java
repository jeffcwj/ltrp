package lt.ltrp.object;

/**
 * @author Bebras
 *         2016.04.14.
 */
public interface FactionRank extends Rank{

    void setNumber(int number);
    void setName(String name);
    void setJob(Faction faction);
    Faction getJob();

}
