package lt.ltrp.modelpreview;


import lt.ltrp.object.LtrpPlayer;
import lt.ltrp.util.Skin;
import net.gtaun.util.event.EventManager;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Bebras
 *         2016.03.05.
 */
public class SkinModelPreview  {

    private SkinModelPreview() {

    }


    public static ModelPreview create(LtrpPlayer player, EventManager eventManager, BasicModelPreview.SelectModelHandler handler) {
        Collection<Integer> models = new ArrayList<>();
        for(int skinId : Skin.SKIN_IDS)
            models.add(skinId);
        return BasicModelPreview.create(player, eventManager)
                .onSelectModel(handler)
                .models(models)
                .build();
    }


}