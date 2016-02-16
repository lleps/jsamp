package com.lleps.test;

import com.lleps.jsamp.MainCallbackListener;
import com.lleps.jsamp.gamemode.GameMode;

/**
 * @author leandro on 28/01/16.
 */
public class MainTest extends GameMode {
    @Override
    public void onInit() throws Exception {
        super.onInit();

        System.out.println("onInit");

        TestCallbackDispatcher dispatcher = new TestCallbackDispatcher();
        MainCallbackListener.addCallbackListener(dispatcher, MainCallbackListener.ListenerPriority.LOW);

        dispatcher.addCommandListener(new DialogTest());
        dispatcher.addCommandListener(new VehicleTest());

        System.out.println("all done..");

    }
}