package com.lleps.test;

import com.lleps.jsamp.MainCallbackListener;
import com.lleps.jsamp.SAMPFunctions;
import com.lleps.jsamp.anticheat.event.AnticheatEvent;
import com.lleps.jsamp.server.SAMPServer;
import com.lleps.jsamp.player.Player;

/**
 * @author leandro on 28/01/16.
 */
public class MainTest extends SAMPServer {
    @Override
    public void onInit() throws Exception {
        super.onInit();

        printLine("Loading MainTest server..");

        TestCallbackDispatcher dispatcher = new TestCallbackDispatcher();
        MainCallbackListener.addCallbackListener(dispatcher, MainCallbackListener.ListenerPriority.LOW);

        //dispatcher.addCommandListener(new DialogTest());
        //dispatcher.addCommandListener(new VehicleTest());
        dispatcher.addCommandListener(new AnticheatAliveTest());
        dispatcher.addCommandListener(new AnticheatHealthArmourTest());
        dispatcher.addCommandListener(new AnticheatWeaponsTest());
        dispatcher.addCommandListener(new AnticheatVehicleTest());
        dispatcher.addCommandListener(new AnticheatGetTests());
        dispatcher.addCommandListener(new AnticheatPosTests());
        dispatcher.addCommandListener(new AnticheatMoneyTest());
        dispatcher.addCommandListener(new SavePositionTest());

        printLine("Loaded..");
    }

    @Override
    public boolean onAnticheatEvent(int playerId, AnticheatEvent anticheatEvent) {
        int color = 0x000000FF;//black
        if (anticheatEvent.getRecommendedAction() == AnticheatEvent.RecommendedAction.BAN) color = 0xFF0000FF;//red
        else if (anticheatEvent.getRecommendedAction() == AnticheatEvent.RecommendedAction.KICK) color = 0xFE9A2EFF;//orange
        else if (anticheatEvent.getRecommendedAction() == AnticheatEvent.RecommendedAction.WARN) color = 0xF3F781FF;//yellow

        SAMPFunctions.SendClientMessageToAll(color, SAMPFunctions.GetPlayerName(playerId) + " - " + anticheatEvent.toString());
        return false;
    }

    @Override
    public void onPlayerConnect(Player player) {
        super.onPlayerConnect(player);
    }
}