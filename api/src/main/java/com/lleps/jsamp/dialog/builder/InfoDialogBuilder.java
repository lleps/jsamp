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

import com.lleps.jsamp.SAMPConstants;
import com.lleps.jsamp.dialog.Dialog;

/**
 * @author spell
 */
public class InfoDialogBuilder extends BaseDialogBuilder<InfoDialogBuilder> {
    private String title;
    private String content;

    public InfoDialogBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public InfoDialogBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public Dialog build() {
        return new Dialog(this, id, SAMPConstants.DIALOG_STYLE_MSGBOX,
                title, content, positiveButtonString, negativeButtonString);
    }
}
