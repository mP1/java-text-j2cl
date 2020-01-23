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

import org.junit.jupiter.api.Test;
import walkingkooka.HashCodeEqualsDefinedTesting2;
import walkingkooka.ToStringTesting;
import walkingkooka.collect.set.Sets;
import walkingkooka.javautillocalej2cl.WalkingkookaLocale;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class DecimalFormatSymbolsTest implements ClassTesting<DecimalFormatSymbols>,
        HashCodeEqualsDefinedTesting2<DecimalFormatSymbols>,
        ToStringTesting<DecimalFormatSymbols> {

    // getAvailableLocales..............................................................................................

    @Test
    public void testGetAvailableLocales() {
        final Comparator<Locale> comparator = (l, r) -> l.toLanguageTag().compareTo(r.toLanguageTag());

        final Set<Locale> jdk = Sets.sorted(comparator);
        jdk.addAll(Arrays.asList(java.text.DecimalFormatSymbols.getAvailableLocales()));
        jdk.removeIf(l -> l.toString().equals("th_TH_TH_#u-nu-thai"));
        jdk.removeIf(l -> l.toString().equals("ja_JP_JP_#u-ca-japanese"));

        final Set<Locale> emulated = Sets.sorted(comparator);
        emulated.addAll(Arrays.asList(DecimalFormatSymbols.getAvailableLocales()));

        assertEquals(jdk, emulated);
    }

    // getInstance......................................................................................................

    @Test
    public void testGetInstance() {
        DecimalFormatSymbols.DEFAULT = null;

        final java.util.Locale locale = Locale.FRENCH;
        java.util.Locale.setDefault(locale);

        this.check(DecimalFormatSymbols.getInstance(),
                java.text.DecimalFormatSymbols.getInstance(),
                locale);
    }

    @Test
    public void testGetInstance2() {
        DecimalFormatSymbols.DEFAULT = null;

        final java.util.Locale locale = Locale.GERMAN;
        java.util.Locale.setDefault(locale);

        this.check(DecimalFormatSymbols.getInstance(),
                java.text.DecimalFormatSymbols.getInstance(),
                locale);
    }

    @Test
    public void testGetInstanceSingleton() {
        DecimalFormatSymbols.DEFAULT = null;

        final java.util.Locale locale = Locale.GERMAN;
        java.util.Locale.setDefault(locale);

        assertEquals(java.text.DecimalFormatSymbols.getInstance(), java.text.DecimalFormatSymbols.getInstance());
    }

    @Test
    public void testGetInstanceCloned() {
        DecimalFormatSymbols.DEFAULT = null;

        final java.util.Locale locale = Locale.ITALIAN;
        java.util.Locale.setDefault(locale);

        final DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setInfinity("symbols was not cloned if this property value is present!!!");

        assertNotEquals(symbols, DecimalFormatSymbols.getInstance());
    }

    // getInstance(Locale)...............................................................................................

    @Test
    public void testGetInstanceLocale() {
        final java.util.Locale locale = Locale.FRENCH;
        java.util.Locale.setDefault(locale);
        Locale.setDefault(Locale.forLanguageTag(locale.toLanguageTag()));

        this.check(DecimalFormatSymbols.getInstance(locale),
                new java.text.DecimalFormatSymbols(locale),
                locale);
    }

    @Test
    public void testGetInstanceLocaleAllLocales() {
        for (final Locale locale : Locale.getAvailableLocales()) {
            if(WalkingkookaLocale.isUnsupported(locale.toLanguageTag())) {
                continue;
            }
            this.check(DecimalFormatSymbols.getInstance(locale),
                    java.text.DecimalFormatSymbols.getInstance(locale),
                    locale);
        }
    }

    // new..............................................................................................................

    @Test
    public void testNew() {
        final Locale locale = Locale.FRENCH;
        Locale.setDefault(locale);
        Locale.setDefault(Locale.forLanguageTag(locale.toLanguageTag()));

        this.check(new DecimalFormatSymbols(),
                new java.text.DecimalFormatSymbols(),
                locale);
    }

    @Test
    public void testNewLocale() {
        final Locale locale = Locale.FRENCH;
        Locale.setDefault(locale);
        Locale.setDefault(Locale.forLanguageTag(locale.toLanguageTag()));

        this.check(new DecimalFormatSymbols(locale),
                new java.text.DecimalFormatSymbols(locale),
                locale);
    }

    private void check(final DecimalFormatSymbols emulated,
                       final java.text.DecimalFormatSymbols expected,
                       final Locale locale) {

        assertEquals(expected.getCurrency().toString(), emulated.getCurrency(), () -> "currency " + locale.toLanguageTag());
        assertEquals(expected.getCurrencySymbol(), emulated.getCurrencySymbol(), () -> "currencySymbol " + locale.toLanguageTag());
        assertEquals(expected.getDecimalSeparator(), emulated.getDecimalSeparator(), () -> "decimalSeparator " + locale.toLanguageTag());
        assertEquals(expected.getDigit(), emulated.getDigit(), () -> "digit " + locale.toLanguageTag());
        assertEquals(expected.getExponentSeparator(), emulated.getExponentSeparator(), () -> "exponentSeparator " + locale.toLanguageTag());
        assertEquals(expected.getGroupingSeparator(), emulated.getGroupingSeparator(), () -> "groupingSeparator " + locale.toLanguageTag());
        assertEquals(expected.getInfinity(), emulated.getInfinity(), "infinity " + locale.toLanguageTag());
        assertEquals(expected.getInternationalCurrencySymbol(), emulated.getInternationalCurrencySymbol(), () -> "internationalCurrencySymbol " + locale.toLanguageTag());
        assertEquals(expected.getMinusSign(), emulated.getMinusSign(), () -> "minusSign " + locale.toLanguageTag());
        assertEquals(expected.getMonetaryDecimalSeparator(), emulated.getMonetaryDecimalSeparator(), () -> "monetaryDecimalSeparator " + locale.toLanguageTag());
        assertEquals(expected.getNaN(), emulated.getNaN(), () -> "nan " + locale.toLanguageTag());
        assertEquals(expected.getPatternSeparator(), emulated.getPatternSeparator(), () -> "patternSeparator " + locale.toLanguageTag());
        assertEquals(expected.getPercent(), emulated.getPercent(), () -> "percent " + locale.toLanguageTag());
        assertEquals(expected.getPerMill(), emulated.getPerMill(), () -> "perMill " + locale.toLanguageTag());
        assertEquals(expected.getZeroDigit(), emulated.getZeroDigit(), () -> "zeroDigit " + locale.toLanguageTag());
    }

    // clone............................................................................................................

    @Test
    public void testCloneState() {
        this.cloneStateAndCheck(new DecimalFormatSymbols());
    }

    @Test
    public void testCloneState2() {
        final DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setCurrency("AUD");
        symbols.setCurrencySymbol("$");
        symbols.setDecimalSeparator('.');
        symbols.setDigit('D');
        symbols.setExponentSeparator("E");
        symbols.setGroupingSeparator('G');
        symbols.setInfinity("I");
        symbols.setInternationalCurrencySymbol("@");
        symbols.setMinusSign('-');
        symbols.setMonetaryDecimalSeparator('M');
        symbols.setNaN("N");
        symbols.setPatternSeparator('P');
        symbols.setPercent('%');
        symbols.setPerMill('T');
        symbols.setZeroDigit('Z');

        this.cloneStateAndCheck(symbols);
    }

    private void cloneStateAndCheck(final DecimalFormatSymbols symbols) {
        this.checkEquals(symbols, symbols.cloneState());
    }

    // equals...........................................................................................................

    @Test
    public void testDifferentLocale() {
        this.checkNotEquals(new DecimalFormatSymbols(Locale.FRANCE));
    }

    @Test
    public void testDifferentCurrency() {
        final DecimalFormatSymbols symbols = this.createObject();
        symbols.setCurrency("Q");
        this.checkNotEquals(symbols);
    }

    @Test
    public void testDifferentCurrencySymbol() {
        final DecimalFormatSymbols symbols = this.createObject();
        symbols.setCurrencySymbol("Q");
        this.checkNotEquals(symbols);
    }

    @Test
    public void testDifferentDecimalSeparator() {
        final DecimalFormatSymbols symbols = this.createObject();
        symbols.setDecimalSeparator('Q');
        this.checkNotEquals(symbols);
    }

    @Test
    public void testDifferentDigit() {
        final DecimalFormatSymbols symbols = this.createObject();
        symbols.setDigit('Q');
        this.checkNotEquals(symbols);
    }

    @Test
    public void testDifferentExponentSymbol() {
        final DecimalFormatSymbols symbols = this.createObject();
        symbols.setExponentSeparator("Q");
        this.checkNotEquals(symbols);
    }

    @Test
    public void testDifferentGroupingSeparator() {
        final DecimalFormatSymbols symbols = this.createObject();
        symbols.setGroupingSeparator('Q');
        this.checkNotEquals(symbols);
    }

    @Test
    public void testDifferentInfinity() {
        final DecimalFormatSymbols symbols = this.createObject();
        symbols.setInfinity("Q");
        this.checkNotEquals(symbols);
    }

    @Test
    public void testDifferentInternationalCurrencySymbol() {
        final DecimalFormatSymbols symbols = this.createObject();
        symbols.setInternationalCurrencySymbol("Q");
        this.checkNotEquals(symbols);
    }

    @Test
    public void testDifferentMinusSign() {
        final DecimalFormatSymbols symbols = this.createObject();
        symbols.setMinusSign('Q');
        this.checkNotEquals(symbols);
    }

    @Test
    public void testDifferentMonetaryDecimalSeparator() {
        final DecimalFormatSymbols symbols = this.createObject();
        symbols.setMonetaryDecimalSeparator('Q');
        this.checkNotEquals(symbols);
    }

    @Test
    public void testDifferentNaN() {
        final DecimalFormatSymbols symbols = this.createObject();
        symbols.setNaN("Q");
        this.checkNotEquals(symbols);
    }

    @Test
    public void testDifferentPatternSeparator() {
        final DecimalFormatSymbols symbols = this.createObject();
        symbols.setPatternSeparator('Q');
        this.checkNotEquals(symbols);
    }

    @Test
    public void testDifferentPercent() {
        final DecimalFormatSymbols symbols = this.createObject();
        symbols.setPercent('Q');
        this.checkNotEquals(symbols);
    }

    @Test
    public void testDifferentPerMill() {
        final DecimalFormatSymbols symbols = this.createObject();
        symbols.setPerMill('Q');
        this.checkNotEquals(symbols);
    }

    @Test
    public void testDifferentZeroDigit() {
        final DecimalFormatSymbols symbols = this.createObject();
        symbols.setZeroDigit('Q');
        this.checkNotEquals(symbols);
    }

    // String...........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createObject(),
                "currency=\"XXX\" currencySymbol=\"¤\" decimalSeparator='.' digit='#' exponentSeparator=\"E\" groupingSeparator=',' infinity=\"∞\" internationalCurrencySymbol=\"XXX\" minusSign='-' monetaryDecimalSeparator='.' nan=\"NaN\" patternSeparator=';' percent='%' perMill='‰' zeroDigit='0'");
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<DecimalFormatSymbols> type() {
        return DecimalFormatSymbols.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }

    // HashCodeEqualsDefinedTesting2....................................................................................

    @Override
    public DecimalFormatSymbols createObject() {
        return new DecimalFormatSymbols(Locale.ENGLISH);
    }
}
