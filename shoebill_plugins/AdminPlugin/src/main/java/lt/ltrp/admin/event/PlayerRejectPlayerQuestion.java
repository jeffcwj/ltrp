package lt.ltrp.event;


import lt.ltrp.data.PlayerQuestion;
import lt.ltrp.event.player.PlayerEvent;import lt.ltrp.player.object.LtrpPlayer;

/**
 * @author Bebras
 *         2016.04.10.
 */
public class PlayerRejectPlayerQuestion extends PlayerEvent {

    private PlayerQuestion question;


    public PlayerRejectPlayerQuestion(LtrpPlayer player, PlayerQuestion question) {
        super(player);
        this.question = question;
    }

    public PlayerQuestion getQuestion() {
        return question;
    }
}
