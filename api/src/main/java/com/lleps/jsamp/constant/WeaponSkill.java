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

import com.lleps.jsamp.constant.model.WeaponModel;

/**
 * @author spell
 */
public enum WeaponSkill {
    PISTOL				(0, WeaponModel.COLT45),
    PISTOL_SILENCED		(1, WeaponModel.SILENCED_COLT45),
    DESERT_EAGLE		(2, WeaponModel.DEAGLE),
    SHOTGUN				(3, WeaponModel.SHOTGUN),
    SAWNOFF_SHOTGUN		(4, WeaponModel.SAWEDOFF),
    SPAS12_SHOTGUN		(5, WeaponModel.SHOTGSPA),
    MICRO_UZI			(6, WeaponModel.UZI),
    MP5					(7, WeaponModel.MP5),
    AK47				(8, WeaponModel.AK47),
    M4					(9, WeaponModel.M4),
    SNIPERRIFLE			(10, WeaponModel.SNIPER);

    public static WeaponSkill get(int value) {
        return values() [value];
    }

    private final int value;
    private final WeaponModel weapon;

    WeaponSkill(int value, WeaponModel weapon) {
        this.value = value;
        this.weapon = weapon;
    }

    public WeaponModel getWeapon() {
        return weapon;
    }

    public int getValue() {
        return value;
    }
}