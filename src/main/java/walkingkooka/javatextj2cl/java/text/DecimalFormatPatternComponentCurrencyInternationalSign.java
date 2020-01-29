/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
 *
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
 *
 */

package walkingkooka.javatextj2cl.java.text;

/**
 * Placeholder for the international currency sign
 */
final class DecimalFormatPatternComponentCurrencyInternationalSign extends DecimalFormatPatternComponent {

    /**
     * Singleton
     */
    final static DecimalFormatPatternComponentCurrencyInternationalSign INSTANCE = new DecimalFormatPatternComponentCurrencyInternationalSign();

    private DecimalFormatPatternComponentCurrencyInternationalSign() {
        super();
    }

    @Override
    boolean isCurrency() {
        return true;
    }

    @Override
    int multiplier() {
        return 0;
    }

    final static String LITERAL = "\u00A4\u00A4";

    @Override
    public String toString() {
        return LITERAL;
    }
}