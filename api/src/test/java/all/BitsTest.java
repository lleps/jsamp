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
package all;

/**
 * @author spell
 */
public class BitsTest {
    public void testBits() {
        int sourceValue = 0b1101;

        int bitToChange = 0;

        // convertir sourceValue de 1101 a 1001:
        // si es 0001, tengo q moverlo 2 lugares a la izquierda
        int bitWithFF = (bitToChange << 2) & 0b1111;
        System.out.println(Integer.toBinaryString(bitWithFF));

        int merged = sourceValue & ~0b00001;

        /*

        borrar un bit, ejemplo

        src: 0b1100

        quiero sacar el 3er uno, 0b0100 es el bit.

        src = src | ~0b0100

        = 1100 | 1011
        = 1000
         */
    }
}
