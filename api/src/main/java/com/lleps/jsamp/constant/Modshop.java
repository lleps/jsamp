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
package com.lleps.jsamp.constant;

import com.lleps.jsamp.data.Vector3D;

/**
 * @author spell
 */
public enum  Modshop {
    TRANSFENDER_LS("Los Santos Transfender", Vector3D.of(1041, -1021, 31), Vector3D.of(617, -2, 1000), Interior.INTERIOR_1),
    TRANSFENDER_SF("San Fierro Transfender", Vector3D.of(-1936, 244, 34), Vector3D.of(617, -2, 1000), Interior.INTERIOR_1),
    TRANSFENDER_LV("Las Vegas Transfender", Vector3D.of(2386, 1051, 10), Vector3D.of(617, -2, 1000), Interior.INTERIOR_1),
    WHEELARCHANGELS("Wheel archangels", Vector3D.of(-2719, 216, 4), Vector3D.of(615, -124, 997), Interior.INTERIOR_3),
    LOWRIDERS("Lowriders garage", Vector3D.of(2644, -2043, 13), Vector3D.of(616, -74, 997), Interior.INTERIOR_2);

    private String name;
    private Vector3D exteriorPosition;
    private Vector3D interiorPosition;
    private Interior interior;

    Modshop(String name, Vector3D exteriorPosition, Vector3D interiorPosition, Interior interior) {
        this.name = name;
        this.exteriorPosition = exteriorPosition;
        this.interiorPosition = interiorPosition;
        this.interior = interior;
    }

    public String getName() {
        return name;
    }

    public Vector3D getExteriorPosition() {
        return exteriorPosition;
    }

    public Vector3D getInteriorPosition() {
        return interiorPosition;
    }

    public Interior getInterior() {
        return interior;
    }

    public static Modshop getByExteriorPosition(Vector3D position, float ratio) {
        for (Modshop modshop : values()) {
            if (modshop.exteriorPosition.distanceTo(position) < ratio) {
                return modshop;
            }
        }
        return null;
    }
}