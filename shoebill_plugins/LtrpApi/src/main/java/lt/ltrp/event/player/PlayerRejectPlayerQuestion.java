package lt.ltrp.event.player;


import lt.ltrp.object.LtrpPlayer;

/**
 * @author Bebras
 *         2016.04.10.
 */
public class PlayerRejectPlayerQuestion extends PlayerEvent {

    private LtrpPlayer target;
    private String question;

    public PlayerRejectPlayerQuestion(LtrpPlayer player, LtrpPlayer target, String question) {
        super(player);
        this.target = target;
        this.question = question;
    }

    public LtrpPlayer getTarget() {
        return target;
    }

    public String getQuestion() {
        return question;
    }
}
