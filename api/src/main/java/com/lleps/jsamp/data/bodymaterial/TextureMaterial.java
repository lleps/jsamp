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
import com.lleps.jsamp.SAMPFunctions;
import com.lleps.jsamp.constant.model.BodyModel;
import com.lleps.jsamp.data.Color;

/**
 * @author spell
 */
public class TextureMaterial implements BodyMaterial {
    private BodyModel model;
    private String txdName;
    private String textureName;
    private Color color;

    public TextureMaterial(BodyModel model, String txdName, String textureName) {
        this(model, txdName, textureName, Color.ofRGBA(0));
    }

    public TextureMaterial(BodyModel model, String txdName, String textureName, Color color) {
        this.model = model;
        this.txdName = txdName;
        this.textureName = textureName;
        this.color = color;
    }

    public BodyModel getModel() {
        return model;
    }

    public void setModel(BodyModel model) {
        this.model = model;
    }

    public String getTxdName() {
        return txdName;
    }

    public void setTxdName(String txdName) {
        this.txdName = txdName;
    }

    public String getTextureName() {
        return textureName;
    }

    public void setTextureName(String textureName) {
        this.textureName = textureName;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void apply(int playerId, int playerObjectId, int materialIndex) {
        FunctionAccess.SetPlayerObjectMaterial(playerId, playerObjectId, materialIndex, model.getId(), txdName,
                textureName, color.getARGB());
    }

    @Override
    public void apply(int objectId, int materialIndex) {
        FunctionAccess.SetObjectMaterial(objectId, materialIndex, model.getId(), txdName,
                textureName, color.getARGB());
    }
}