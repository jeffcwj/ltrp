package lt.ltrp;

/**
 * @author Bebras
 *         2015.11.29.
 */
public class EntityImpl {

    private int uuid;

    public EntityImpl(int id) {
        this.uuid = id;
    }

    public EntityImpl() {

    }

    public int getUUID() {
        return uuid;
    }

    public void setUUID(int uuid) {
        this.uuid = uuid;
    }
}