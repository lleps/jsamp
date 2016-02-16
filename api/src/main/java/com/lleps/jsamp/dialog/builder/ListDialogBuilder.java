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
package com.lleps.jsamp.dialog.builder;

import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.SAMPConstants;
import com.lleps.jsamp.dialog.Dialog;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author spell
 */
public class ListDialogBuilder extends BaseDialogBuilder<ListDialogBuilder> {
    public enum DialogResponse {POSITIVE, NEGATIVE};

    public interface OnItemSelectedListener {
        void onItemSelected(Player player, DialogResponse response);
    }

    public static String columns(String... columnNames) {
        return StringUtils.join(columnNames, '\t');
    }

    private String title;
    private String header;
    private StringBuffer info = new StringBuffer();

    private int currentItemIndex;
    private Map<Integer, OnItemSelectedListener> itemSelectionListeners = new HashMap<>();

    public ListDialogBuilder() {
        this("");
    }

    public ListDialogBuilder(String title) {
        this.title = title;
    }

    public ListDialogBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ListDialogBuilder setHeader(String item) {
        this.header = item;
        return this;
    }

    public ListDialogBuilder addItem(String item) {
        return addItem(item, null);
    }

    public ListDialogBuilder addItem(String item, OnItemSelectedListener listener) {
        if (listener != null) {
            itemSelectionListeners.put(currentItemIndex, listener);
        }

        if (item.isEmpty()) item = " ";
        if (item.contains("\n")) item = item.replace("\n", "");

        info.append(item).append("\n");

        currentItemIndex++;
        return this;
    }

    @Override
    public boolean onDialogResponse(Player player, int dialogId, boolean response, String inputtext, int itemIndex) {
        if (super.onDialogResponse(player, dialogId, response, inputtext, itemIndex)) {
            OnItemSelectedListener listener = itemSelectionListeners.get(itemIndex);
            if (listener != null) {
                listener.onItemSelected(player, response ? DialogResponse.POSITIVE : DialogResponse.NEGATIVE);
            }
            return true;
        }
        return false;
    }

    @Override
    public Dialog build() {
        int style = header != null ? SAMPConstants.DIALOG_STYLE_TABLIST_HEADERS : SAMPConstants.DIALOG_STYLE_TABLIST;

        String infoString = (header != null) ? (header + "\n" + info.toString()) : info.toString();

        return new Dialog(this, id, style, title, infoString, positiveButtonString, negativeButtonString);
    }
}