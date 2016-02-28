package com.lleps.test;

import com.lleps.jsamp.MainCallbackListener;
import com.lleps.jsamp.SAMPFunctions;
import com.lleps.jsamp.anticheat.event.AnticheatEvent;
import com.lleps.jsamp.gamemode.GameMode;
import com.lleps.jsamp.player.Player;

/**
 * @author leandro on 28/01/16.
 */
public class MainTest extends GameMode {
    @Override
    public void onInit() throws Exception {
        super.onInit();

        printLine("Loading MainTest gamemode..");

        TestCallbackDispatcher dispatcher = new TestCallbackDispatcher();
        MainCallbackListener.addCallbackListener(dispatcher, MainCallbackListener.ListenerPriority.LOW);

        dispatcher.addCommandListener(new DialogTest());
        dispatcher.addCommandListener(new VehicleTest());
        dispatcher.addCommandListener(new AnticheatAliveTest());
        dispatcher.addCommandListener(new AnticheatHealthArmourTest());
        dispatcher.addCommandListener(new AnticheatWeaponsTest());
        dispatcher.addCommandListener(new AnticheatGetTests());
        dispatcher.addCommandListener(new AnticheatPosTests());

        printLine("Loaded..");
    }

    @Override
    public boolean onAnticheatEvent(int playerId, AnticheatEvent anticheatEvent) {
        SAMPFunctions.SendClientMessageToAll(-1, SAMPFunctions.GetPlayerName(playerId) + " cheat! ");
        SAMPFunctions.SendClientMessageToAll(-1, anticheatEvent.toString());
        return false;
    }

    @Override
    public void onPlayerConnect(Player player) {
        super.onPlayerConnect(player);
    }
}