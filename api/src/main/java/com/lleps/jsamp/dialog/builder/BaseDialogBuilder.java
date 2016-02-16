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
import com.lleps.jsamp.dialog.DialogResponseListener;
import org.apache.commons.lang3.RandomUtils;

/**
 * @author spell
 */
public abstract class BaseDialogBuilder<T extends DialogBuilder> implements DialogBuilder, DialogResponseListener {
    public interface OnDialogClickListener {
        void onDialogClick(Player player, String input, int whichItem);
    }

    private OnDialogClickListener positiveButtonListener;
    private OnDialogClickListener negativeButtonListener;

    protected String positiveButtonString = "";
    protected String negativeButtonString = "";

    protected final int id;

    protected BaseDialogBuilder() {
        // dialog ids goes from 0 to ~32k.
        // id is used to compare if the response dialog id is equal to this dialog
        id = RandomUtils.nextInt(1, (0xFFFF / 2));
    }

    @Override
    public boolean onDialogResponse(Player player, int dialogId, boolean response, String inputtext, int itemIndex) {
        if (dialogId != id) {
            return false;
        }

        if (response) {
            if (positiveButtonListener != null) {
                positiveButtonListener.onDialogClick(player, inputtext, itemIndex);
            }
        } else {
            if (negativeButtonListener != null) {
                negativeButtonListener.onDialogClick(player, inputtext, itemIndex);
            }
        }
        return true;
    }


    public T setPositiveButton(String name) {
        return setPositiveButton(name, null);
    }

    // Parameter T is used for return this by this subclasses.
    @SuppressWarnings("unchecked")
    public T setPositiveButton(String name, OnDialogClickListener positiveButtonListener) {
        this.positiveButtonString = name;
        this.positiveButtonListener = positiveButtonListener;
        return (T) this;
    }

    public T setNegativeButton(String name) {
        return setNegativeButton(name, null);
    }

    @SuppressWarnings("unchecked")
    public T setNegativeButton(String name, OnDialogClickListener negativeButtonListener) {
        this.negativeButtonString = name;
        this.negativeButtonListener = negativeButtonListener;
        return (T) this;
    }
}