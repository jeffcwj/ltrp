package lt.ltrp.api.dialog;

import lt.ltrp.api.object.LtrpPlayer;
import net.gtaun.shoebill.common.dialog.DialogTextSupplier;
import net.gtaun.shoebill.common.dialog.InputDialog;
import net.gtaun.util.event.EventManager;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;import java.lang.Deprecated;import java.lang.FunctionalInterface;import java.lang.Integer;import java.lang.Override;import java.lang.String;import java.lang.SuppressWarnings;

/**
 * @author Bebras
 *         2016.03.01.
 */
public class IntegerInputDialog extends InputDialog {

    private ClickOkHandler handler;
    private InputErrorHandler inputErrorHandler;

    public IntegerInputDialog(LtrpPlayer player, EventManager parentEventManager) {
        super(player, parentEventManager);
    }

    public static AbstractIntegerInputDialogBuilder<?, ?> create(LtrpPlayer player, EventManager parentEventManager) {
        return new IntegerInputDialogBuilder(player, parentEventManager);
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
            int val = Integer.parseInt(text);
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
        void onClickOk(IntegerInputDialog dialog, int val);
    }

    @FunctionalInterface
    public interface InputErrorHandler {
        void onInputError(IntegerInputDialog dialog, String input);
    }

    @SuppressWarnings("unchecked")
    public static abstract class AbstractIntegerInputDialogBuilder
            <DialogType extends IntegerInputDialog, DialogBuilderType extends AbstractIntegerInputDialogBuilder<DialogType, DialogBuilderType>>
            extends AbstractDialogBuilder<DialogType, DialogBuilderType> {
        protected AbstractIntegerInputDialogBuilder(DialogType dialog) {
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

    public static class IntegerInputDialogBuilder extends AbstractIntegerInputDialogBuilder<IntegerInputDialog, IntegerInputDialogBuilder> {
        private IntegerInputDialogBuilder(LtrpPlayer player, EventManager parentEventManager) {
            super(new IntegerInputDialog(player, parentEventManager));
        }
    }

}