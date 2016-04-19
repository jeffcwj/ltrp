package lt.ltrp.property.data;



import lt.ltrp.player.object.LtrpPlayer;
import lt.ltrp.property.event.PlayerEnterHouseEvent;
import lt.ltrp.property.event.PlayerExitHouseEvent;
import lt.ltrp.property.object.House;
import lt.ltrp.radio.object.AbstractRadio;
import lt.ltrp.radio.object.RadioStation;
import net.gtaun.util.event.EventManager;

/**
 * Created by Bebras on 2016.03.26.
 */
public class HouseRadio extends AbstractRadio {

    private House house;

    public HouseRadio(House house, EventManager eventManager1) {
        super(eventManager1);
        this.house = house;
        this.eventManager.registerHandler(PlayerEnterHouseEvent.class, e -> {
            if(isPlaying()) {
                e.getPlayer().playAudioStream(getStation().getUrl());
            }
        });
        this.eventManager.registerHandler(PlayerExitHouseEvent.class, e -> {
            if(isPlaying()) {
                e.getPlayer().stopAudioStream();
            }
        });
    }

    @Override
    public void play(RadioStation radioStation) {
        if(radioStation != null) {
            LtrpPlayer.get()
                    .stream()
                    .filter(p -> house.equals(p.getProperty()))
                    .forEach(p -> p.playAudioStream(radioStation.getUrl()));
        }
        super.play(radioStation);
    }

    @Override
    public void setVolume(int vol) {
        LtrpPlayer.get()
                .stream()
                .filter(p -> house.equals(p.getProperty()))
                .forEach(p -> p.setVolume(vol));
        super.setVolume(vol);
    }

    @Override
    public void stop() {
        LtrpPlayer.get()
                .stream()
                .filter(p -> house.equals(p.getProperty()))
                .forEach(LtrpPlayer::stopAudioStream);
        super.stop();
    }

}