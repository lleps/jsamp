/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lleps.jsamp.dialog;

import com.lleps.jsamp.player.Player;

/**
 * @author spell
 */
public final class Dialog implements DialogResponseListener {
    private DialogResponseListener responseListener;

    private int id;
    private int style;
    private String caption;
    private String info;
    private String button1;
    private String button2;

    public Dialog(DialogResponseListener responseListener, int id, int style, String caption, String info, String button1, String button2) {
        this.responseListener = responseListener;
        this.id = id;
        this.style = style;
        this.caption = caption;
        this.info = info;
        this.button1 = button1;
        this.button2 = button2;
    }

    @Override
    public boolean onDialogResponse(Player player, int dialogId, boolean response, String inputtext, int itemIndex) {
        return responseListener != null
                && responseListener.onDialogResponse(player, dialogId, response, inputtext, itemIndex);
    }

    public int getId() {
        return id;
    }

    public int getStyle() {
        return style;
    }

    public String getCaption() {
        return caption != null ? caption : "";
    }

    public String getInfo() {
        return info != null ? info : "";
    }

    public String getButton1() {
        return button1 != null ? button1 : "";
    }

    public String getButton2() {
        return button2 != null ? button2 : "";
    }
}
