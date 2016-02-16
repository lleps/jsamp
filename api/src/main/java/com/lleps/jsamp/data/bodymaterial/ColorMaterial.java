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
package com.lleps.jsamp.data.bodymaterial;

import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.data.Color;

/**
 * @author spell
 */
public class ColorMaterial implements BodyMaterial {
    private Color color;

    public ColorMaterial(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void apply(int playerId, int playerObjectId, int materialIndex) {
        FunctionAccess.SetPlayerObjectMaterial(playerId, playerObjectId, materialIndex, -1, "none", "none",
                color.getARGB());
    }

    @Override
    public void apply(int objectId, int materialIndex) {
        FunctionAccess.SetObjectMaterial(objectId, materialIndex, -1, "none", "none", color.getARGB());
    }
}