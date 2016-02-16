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
public class TextMaterial implements BodyMaterial {
    public enum Size {
        SIZE_32x32(10),
        SIZE_64x32(20),
        SIZE_64x64(30),
        SIZE_128x32(40),
        SIZE_128x64(50),
        SIZE_128x128(60),
        SIZE_256x32(70),
        SIZE_256x64(80),
        SIZE_256x128(90),
        SIZE_256x256(100),
        SIZE_512x64(110),
        SIZE_512x128(120),
        SIZE_512x256(130),
        SIZE_512x512(140);
        
        private int value;
        
        Size(int value) { this.value = value; }
    }

    public enum Alignment {
        LEFT(0), CENTER(1), RIGHT(2);

        private int value;

        Alignment(int value) { this.value = value; }
    }

    private String text;
    private Size materialSize;
    private String fontFace;
    private int fontSize;
    private boolean bold;
    private Color fontColor;
    private Color backgroundColor;
    private Alignment alignment;

    public TextMaterial() {
        text = "";
        materialSize = Size.SIZE_256x128;
        fontFace = "Arial";
        fontSize = 24;
        bold = true;
        fontColor = Color.WHITE;
        backgroundColor = Color.ofRGBA(0);
        alignment = Alignment.LEFT;
    }

    @Override
    public void apply(int playerId, int playerObjectId, int materialIndex) {
        FunctionAccess.SetPlayerObjectMaterialText(playerId, playerObjectId, text, materialIndex,
                materialSize.value, fontFace, fontSize, bold, fontColor.getARGB(), backgroundColor.getARGB(),
                alignment.value);
    }

    @Override
    public void apply(int objectId, int materialIndex) {
        FunctionAccess.SetObjectMaterialText(objectId, text, materialIndex,
                materialSize.value, fontFace, fontSize, bold, fontColor.getARGB(), backgroundColor.getARGB(),
                alignment.value);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Size getMaterialSize() {
        return materialSize;
    }

    public void setMaterialSize(Size materialSize) {
        this.materialSize = materialSize;
    }

    public String getFontFace() {
        return fontFace;
    }

    public void setFontFace(String fontFace) {
        this.fontFace = fontFace;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public Alignment getAlignment() {
        return alignment;
    }
}