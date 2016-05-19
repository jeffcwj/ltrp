package lt.ltrp.object;

import lt.ltrp.ItemController;
import lt.ltrp.object.drug.DrugItem;
import net.gtaun.util.event.EventManager;

/**
 * @author Bebras
 *         2016.04.20.
 */
public interface WeedItem extends DrugItem {

    static WeedItem create(EventManager eventManager) {
        return create(eventManager, 1);
    }

    static WeedItem create(EventManager eventManager, int doses) {
        return ItemController.get().createWeed(eventManager, doses);
    }

}
