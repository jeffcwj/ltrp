package lt.ltrp.player.vehicle.command;

import lt.ltrp.command.Commands;
import lt.ltrp.constant.LtrpVehicleModel;
import lt.ltrp.object.PlayerCountdown;
import lt.ltrp.vehicle.util.VehicleUtils;
import net.gtaun.shoebill.common.command.BeforeCheck;
import net.gtaun.shoebill.common.command.Command;
import net.gtaun.shoebill.common.command.CommandHelp;
import net.gtaun.shoebill.common.command.CommandParameter;
import net.gtaun.shoebill.constant.PlayerState;
import net.gtaun.shoebill.constant.VehicleComponentSlot;
import net.gtaun.shoebill.constant.VehicleModel;
import net.gtaun.shoebill.data.VehicleState;
import net.gtaun.shoebill.entities.Player;
import net.gtaun.shoebill.entities.VehicleParam;
import net.gtaun.util.event.EventManager;

import java.util.Map;

/**
 * @author Bebras
 *         2015.12.06.
 */
public class VehicleCommands extends Commands {

    private Map<LtrpPlayer, Float> maxSpeeds;
    private EventManager eventManager;

    public VehicleCommands(Map<LtrpPlayer, Float> speeds, EventManager eventManager) {
        this.maxSpeeds = speeds;
        this.eventManager = eventManager;
    }



    @BeforeCheck
    public boolean beforeCheck(Player p, String cmd, String params) {
        getLogger().debug("beforeCheck cmd " + cmd);
        getLogger().debug("beforeCheck. Player find by player instance" + LtrpPlayer.get(p));
        LtrpPlayer player = LtrpPlayer.get(p);
        LtrpVehicle vehicle = LtrpVehicle.getClosest(player, 6.0f);
        if(vehicle != null) {
            return true;
        } else
            getLogger().debug("No vehicle near player");
        return false;
    }


    @Command
    @CommandHelp("Parodo transporto priemon�s inventori�")
    public boolean trunk(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        getLogger().info("trunk command called");
        LtrpVehicle vehicle = LtrpVehicle.getClosest(player, 4.0f);
        if(vehicle != null) {
            if(vehicle.getState().getBoot() == VehicleParam.PARAM_ON) {
                vehicle.getInventory().show(player);
            } else
                player.sendErrorMessage(vehicle.getModelName() + " baga�in� u�daryta. Naudokite /trunko");
        } else
            player.sendErrorMessage("Prie j�s� n�ra jokios transporto priemon�s");
        return true;
    }

    @Command
    @CommandHelp("U�daro/atidaro automobilio baga�in�")
    public boolean trunko(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        LtrpVehicle vehicle = LtrpVehicle.getClosest(player, 6.0f);
        if(vehicle != null) {
            if(!vehicle.isLocked()) {
                if(vehicle.getState().getBoot() != VehicleParam.PARAM_ON) {
                    vehicle.getState().setBoot(VehicleParam.PARAM_ON);
                    player.sendActionMessage("atidaro " + vehicle.getModelName() + " baga�in�.");
                } else {
                    vehicle.getState().setBoot(VehicleParam.PARAM_OFF);
                    player.sendActionMessage("u�daro " + vehicle.getModelName() + " baga�in�.");
                }
                player.playSound(1057);
            } else {
                player.sendErrorMessage("Transporto priemon� u�rakinta.");
            }
        } else
            player.sendErrorMessage("Prie j�s� n�ra transporto priemon�s.");
        return true;
    }


    @Command
    @CommandHelp("U�daro/atidaro automobilio kapot�")
    public boolean bonnet(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        LtrpVehicle vehicle = LtrpVehicle.getByVehicle(player.getVehicle());
        if(vehicle != null) {
            if(player.getState() == PlayerState.DRIVER) {
                VehicleParam state = vehicle.getState();
                if(state.getBonnet() != VehicleParam.PARAM_ON) {
                    state.setBonnet(VehicleParam.PARAM_ON);
                    player.sendActionMessage("atidaro " + vehicle.getModelName() + " kapot�.");
                } else {
                    state.setBonnet(VehicleParam.PARAM_OFF);
                    player.sendActionMessage("u�daro " + vehicle.getModelName() + " kapot�.");
                }
                player.playSound(1057);
            } else {
                player.sendErrorMessage("J�s neesate transporto priemon�s vairuotojas.");
            }
        } else
            player.sendErrorMessage("J�s� neesate transporto priemon�je.");
        return true;
    }


    @Command
    @CommandHelp("U�daro/atidaro automobilio langus")
    public boolean windows(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        LtrpVehicle vehicle = LtrpVehicle.getByVehicle(player.getVehicle());
        if(vehicle != null) {
            if(LtrpVehicleModel.HasWindows(vehicle.getModelId())) {
                VehicleState windows = vehicle.getWindows();
                int seat = player.getVehicleSeat();
                if(seat < 4) {
                    int newstate = -1;
                    switch(seat) {
                        case 0:
                            if(windows.getDriver() != VehicleParam.PARAM_OFF) {
                                windows.setDriver(VehicleParam.PARAM_OFF);
                                newstate = VehicleParam.PARAM_OFF;
                            } else {
                                windows.setDriver(VehicleParam.PARAM_ON);
                                newstate = VehicleParam.PARAM_ON;
                            }
                            break;
                        case 1:
                            if(windows.getPassenger() != VehicleParam.PARAM_OFF) {
                                windows.setPassenger(VehicleParam.PARAM_OFF);
                                newstate = VehicleParam.PARAM_OFF;
                            } else {
                                windows.setPassenger(VehicleParam.PARAM_ON);
                                newstate = VehicleParam.PARAM_ON;
                            }
                            break;
                        case 2:
                            if(windows.getBackLeft() != VehicleParam.PARAM_OFF) {
                                windows.setBackLeft(VehicleParam.PARAM_OFF);
                                newstate = VehicleParam.PARAM_OFF;
                            } else {
                                windows.setBackLeft(VehicleParam.PARAM_ON);
                                newstate = VehicleParam.PARAM_ON;
                            }
                            break;
                        case 3:
                            if(windows.getBackRight() != VehicleParam.PARAM_OFF) {
                                windows.setBackRight(VehicleParam.PARAM_OFF);
                                newstate = VehicleParam.PARAM_OFF;
                            } else {
                                windows.setBackRight(VehicleParam.PARAM_ON);
                                newstate = VehicleParam.PARAM_ON;
                            }
                            break;
                    }
                    vehicle.setWindows(windows);
                    if(newstate == VehicleParam.PARAM_ON) {
                        player.sendActionMessage("u�daro tr. priemon�s lang�");
                    } else
                        player.sendActionMessage("atidaro tr. priemon�s lang�");
                    player.playSound(1057);
                } else {
                    player.sendErrorMessage("�is langas neatsidaro!");
                }
            } else {
                player.sendErrorMessage("J�s neesate transporto priemon�s vairuotojas.");
            }
        } else
            player.sendErrorMessage("J�s� neesate transporto priemon�je.");
        return true;
    }


    @Command
    @CommandHelp("U�sega/atesga suagos dir�us")
    public boolean seatbelt(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        LtrpVehicle vehicle = LtrpVehicle.getByVehicle(player.getVehicle());
        if(vehicle != null) {
            if(player.getVehicleSeat() == 0) {
                if(!player.isSeatbelt()) {
                    player.sendActionMessage("patempia saugos dir�� ir juos u�sisega.");
                } else {
                    player.sendActionMessage("atsisega saugos dir�us.");
                }
                player.setSeatbelt(!player.isSeatbelt());
            } else
                player.sendErrorMessage("J�s neesate transporto priemon�s vairuotojas.");
        } else
            player.sendErrorMessage("J�s� neesate transporto priemon�je.");
        return true;
    }


    @Command
    @CommandHelp("Nustato/pa�alina maksimal� automobilio greit�")
    public boolean maxSpeed(Player p, @CommandParameter(name = "Maksimalus greitis")Float speed) {
        LtrpPlayer player = LtrpPlayer.get(p);
        LtrpVehicle vehicle = LtrpVehicle.getByVehicle(player.getVehicle());
        if(vehicle == null) {
            player.sendErrorMessage("J�s neesate transporto priemon�je!");
        } else if(speed == null) {
            player.sendErrorMessage("Naudojimas /maxspeed [Greitis(30 - 130)]");
        } else if(speed < 30 || speed > 130) {
            if(maxSpeeds.containsKey(player)) {
                maxSpeeds.remove(player);
                player.sendMessage(Color.NEWS, "Grei�io ribotuvas i�jungtas.");
            }
        } else {
            maxSpeeds.put(player, speed);
            player.sendMessage(Color.WHITE, "* Grei�io ribotuvas buvo nustatytas: " + speed + " Km/h ");
        }
        return true;
    }

    @Command
    @CommandHelp("Pakabina transporto priemon�")
    public boolean towUp(Player p) {
        LtrpPlayer player = LtrpPlayer.get(p);
        LtrpVehicle vehicle = LtrpVehicle.getByVehicle(player.getVehicle());
        if(vehicle == null || vehicle.getModelId() != VehicleModel.TOW_TRUCK || player.getVehicleSeat() != 0)
            player.sendErrorMessage("�i� komand� galite naudoti tik b�dami u� Tow Truck vairo.");
        else {
            LtrpVehicle trailer = LtrpVehicle.getClosest(VehicleUtils.getBehind(vehicle), 6f);
            if(trailer == null)
                player.sendErrorMessage("Tow Truck gale n�ra jokios transporto priemon�s.");
            else if(vehicle.getTrailer() != null) {
                vehicle.detachTrailer();
                player.sendActionMessage("paspaud�ia ka�kok� mygtuk� Tow Truck");
                vehicle.sendStateMessage("kablys paleid�ia " + trailer.getModelName());
            } else {
                player.sendActionMessage("paspaud�ia mygtuk� Tow Truck");
                vehicle.sendActionMessage("kablys nusileid�ia");
                PlayerCountdown.create(player, 10, true, (pp, finished) -> {
                    if(finished) {
                        if(trailer.getLocation().distance(vehicle.getLocation()) > 10f)
                            player.sendErrorMessage(trailer.getModelName() + " pasitrauk� per toli kad gal�tum�te j� u�kabinti.");
                        else {
                            vehicle.attachTrailer(trailer);
                            player.sendActionMessage("U�kabina " + trailer.getModelName() + " u� a�ies ir pradeda kelti...");
                            vehicle.sendStateMessage("kablys pakyla pakeldamas dal� " + trailer.getModelName());
                        }
                    }
                }, true, null).start();
            }
        }
        return true;
    }

    @Command
    @CommandHelp("U�deda stog� kabrioletui.")
    public boolean roof(Player p, @CommandParameter(name = "Stogo tipas 1-2") int type) {
        LtrpPlayer player = LtrpPlayer.get(p);
        LtrpVehicle vehicle = LtrpVehicle.getByVehicle(player.getVehicle());
        if(vehicle == null)
            player.sendErrorMessage("�i� komand� galite naudoti tik b�damas transporto priemon�je.");
        else if(vehicle.getModelId() != 567 && vehicle.getModelId() != 536)
            player.sendErrorMessage("Stog� u�d�ti galima tik Savanna ir Blade automobiliams");
        else if(vehicle.getComponent().get(VehicleComponentSlot.ROOF) != 0)
            player.sendErrorMessage("Stogas jau yra u�d�tas, naudokite /roof nuimti kad j� nuimtum�te.");
        else if(vehicle.getSpeed() > 5)
            player.sendErrorMessage("Jud�dami stogo u�sid�ti negalite, pra�ome sustoti.");
        else {
            int componentId = 0;
            switch(vehicle.getModelId()) {
                case 567:
                    switch(type) {
                        case 1:
                            componentId = 1130;
                            break;
                        case 2:
                            componentId = 1131;
                            break;
                    }
                    break;
                case 536:
                    switch(type) {
                        case 1:
                            componentId = 1103;
                            break;
                        case 2:
                            componentId = 1128;
                            break;
                    }
                    break;
            }
            final int fcomponentId = componentId;
            player.sendActionMessage("paspaud�ia mygtuk� ant " + vehicle.getName() + " prietais� skydelio");
            vehicle.sendActionMessage("Stogas pradeda i�silanksyti i� automobilio galo");
            PlayerCountdown countdown = PlayerCountdown.create(player, 15, true, (pl, finished) -> {
                if(finished) {
                    vehicle.getComponent().add(fcomponentId);
                    vehicle.sendActionMessage("Stogas pilnai i�silanksto ir u�dengia automobilio salon�.");
                }
            } , true, "Stogas");
            player.setCountdown(countdown);
            countdown.start();
        }
        return true;
    }

    @Command
    @CommandHelp("Nuima kabrioleto stog�")
    public boolean roof(Player p, @CommandParameter(name = "Veiksmas")String action) {
        LtrpPlayer player = LtrpPlayer.get(p);
        LtrpVehicle vehicle = LtrpVehicle.getByVehicle(player.getVehicle());
        if(vehicle == null)
            player.sendErrorMessage("�i� komand� galite naudoti tik b�damas transporto priemon�je.");
        else if(vehicle.getModelId() != 567 && vehicle.getModelId() != 536)
            player.sendErrorMessage("Stog� nuimti galima tik nuo Savanna ir Blade automobili�");
        else if(vehicle.getComponent().get(VehicleComponentSlot.ROOF) == 0)
            player.sendErrorMessage("Stogas jau nuimtas, naudokite /roof [ Tipas ] kad j� u�d�tum�te.");
        else if(vehicle.getSpeed() > 5)
            player.sendErrorMessage("Jud�dami stogo nuimti negalite, pra�ome sustoti.");
        else if(!action.equalsIgnoreCase("nuimti"))
            return false; // Should send usage message
        else {
            player.sendActionMessage("paspaud�ia mygtuk� ant " + vehicle.getName() + " prietais� skydelio");
            vehicle.sendStateMessage("Stogas pradeda atsiverti");
            PlayerCountdown countdown = PlayerCountdown.create(player, 15, true, (pl, finished) -> {
                if(finished) {
                    vehicle.getComponent().remove(VehicleComponentSlot.ROOF);
                    vehicle.sendStateMessage("Stogas pilnai susilanksto ir pasislepia transporto priemon�s gale");
                }
            } , true, "Stogas");
            player.setCountdown(countdown);
            countdown.start();
        }
        return true;
    }
}
