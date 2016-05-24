package lt.ltrp.dialog;

import lt.ltrp.object.LtrpPlayer;
import net.gtaun.shoebill.common.dialog.AbstractDialog;
import net.gtaun.shoebill.common.dialog.DialogTextSupplier;
import net.gtaun.shoebill.common.dialog.InputDialog;
import net.gtaun.util.event.EventManager;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Bebras
 *         2016.05.22.
 */
public class HexIntegerInputDialog extends InputDialog {


    protected ClickOkHandler handler;
    protected InputErrorHandler inputErrorHandler;

    public HexIntegerInputDialog(LtrpPlayer player, EventManager parentEventManager) {
        super(player, parentEventManager);
    }

    public static AbstractHexIntegerInputDialogBuilder<?, ?> create(LtrpPlayer player, EventManager parentEventManager) {
        return new HexIntegerInputDialogBuilder(player, parentEventManager);
    }

    public void setInputErrorHandler(InputErrorHandler h) {
        this.inputErrorHandler = h;
    }

    @Override
    @Deprecated
    public void setClickOkHandler(net.gtaun.shoebill.common.dialog.InputDialog.ClickOkHandler handler) {
        throw new NotImplementedException();
    }

    public void setClickOkHandler(ClickOkHandler handler) {
        this.handler = handler;
    }

    @Override
    public void onClickOk(String text) {
        try {
            int val = Integer.parseInt(text, 16);
            if(handler != null)
                handler.onClickOk(this, val);
        } catch(java.lang.NumberFormatException e) {
            if(inputErrorHandler != null)
                inputErrorHandler.onInputError(this, text);
            show();
        }
    }

    @FunctionalInterface
    public interface ClickOkHandler {
        void onClickOk(HexIntegerInputDialog dialog, int hexValue);
    }

    @FunctionalInterface
    public interface InputErrorHandler {
        void onInputError(HexIntegerInputDialog dialog, String input);
    }

    @SuppressWarnings("unchecked")
    public static abstract class AbstractHexIntegerInputDialogBuilder
            <DialogType extends HexIntegerInputDialog, DialogBuilderType extends AbstractHexIntegerInputDialogBuilder<DialogType, DialogBuilderType>>
            extends AbstractDialog.AbstractDialogBuilder<DialogType, DialogBuilderType> {
        protected AbstractHexIntegerInputDialogBuilder(DialogType dialog) {
            super(dialog);
        }

        public DialogBuilderType message(String message) {
            dialog.setMessage(message);
            return (DialogBuilderType) this;
        }

        public DialogBuilderType message(DialogTextSupplier messageSupplier) {
            dialog.setMessage(messageSupplier);
            return (DialogBuilderType) this;
        }

        public DialogBuilderType onInputError(InputErrorHandler handler1) {
            dialog.setInputErrorHandler(handler1);
            return (DialogBuilderType) this;
        }

        public DialogBuilderType onClickOk(ClickOkHandler handler) {
            dialog.setClickOkHandler(handler);
            return (DialogBuilderType) this;
        }

        public DialogBuilderType line(String line) {
            dialog.addLine(line);
            return (DialogBuilderType) this;
        }
    }

    public static class HexIntegerInputDialogBuilder extends AbstractHexIntegerInputDialogBuilder<HexIntegerInputDialog, HexIntegerInputDialogBuilder> {
        private HexIntegerInputDialogBuilder(LtrpPlayer player, EventManager parentEventManager) {
            super(new HexIntegerInputDialog(player, parentEventManager));
        }
    }

}
