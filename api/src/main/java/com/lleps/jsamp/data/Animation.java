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
package com.lleps.jsamp.data;

import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.SAMPFunctions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author spell
 */
public final class Animation {
    public static Animation of(String library, String name) {
        return new Animation(library, name);
    }

    private final static Map<Integer, Animation> animsByIndex = new HashMap<>();

    public static Animation of(int index) {
        if (!animsByIndex.containsKey(index)) {
            animsByIndex.put(index, new Animation(FunctionAccess.GetAnimationLibrary(index),
                    FunctionAccess.GetAnimationName(index)));
        }
        return animsByIndex.get(index);
    }

    private final String library;
    private final String name;

    public Animation(String library, String name) {
        this.library = library;
        this.name = name;
    }

    public String getLibrary() {
        return library;
    }

    public String getName() {
        return name;
    }
}