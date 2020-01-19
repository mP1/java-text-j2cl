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

import walkingkooka.ToStringBuilder;
import walkingkooka.collect.map.Maps;
import walkingkooka.javautillocalej2cl.WalkingkookaLanguageTag;
import walkingkooka.javautillocalej2cl.WalkingkookaLocale;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DecimalFormatSymbols {

    /**
     * Language tag to symbols, this is used internally to "get" the symbols for a given Locale.
     */
    private final static Map<String, DecimalFormatSymbols> LANGUAGE_TAG_TO_SYMBOLS = Maps.ordered();

    static {
        DecimalFormatSymbolProvider.register();
    }

    /**
     * Used by {@link DecimalFormatSymbolProvider#register()} to register individual symbols
     */
    static void register(final String locales,
                         final String currency,
                         final String currencySymbol,
                         final char decimalSeparator,
                         final char digit,
                         final String exponentSeparator,
                         final char groupingSeparator,
                         final String infinity,
                         final String internationalCurrencySymbol,
                         final char minusSign,
                         final char monetaryDecimalSeparator,
                         final String nan,
                         final char patternSeparator,
                         final char percent,
                         final char perMill,
                         final char zeroDigit) {
        final DecimalFormatSymbols symbols = new DecimalFormatSymbols(currency,
                currencySymbol,
                decimalSeparator,
                digit,
                exponentSeparator,
                groupingSeparator,
                infinity,
                internationalCurrencySymbol,
                minusSign,
                monetaryDecimalSeparator,
                nan,
                patternSeparator,
                percent,
                perMill,
                zeroDigit);

        for (final String locale : locales.split("\t")) {
            LANGUAGE_TAG_TO_SYMBOLS.put(locale, symbols);
        }
    }

    private DecimalFormatSymbols(final String currency,
                                 final String currencySymbol,
                                 final char decimalSeparator,
                                 final char digit,
                                 final String exponentSeparator,
                                 final char groupingSeparator,
                                 final String infinity,
                                 final String internationalCurrencySymbol,
                                 final char minusSign,
                                 final char monetaryDecimalSeparator,
                                 final String nan,
                                 final char patternSeparator,
                                 final char percent,
                                 final char perMill,
                                 final char zeroDigit) {
        super();
        this.currency = currency;
        this.currencySymbol = currencySymbol;
        this.decimalSeparator = decimalSeparator;
        this.digit = digit;
        this.exponentSeparator = exponentSeparator;
        this.groupingSeparator = groupingSeparator;
        this.infinity = infinity;
        this.internationalCurrencySymbol = internationalCurrencySymbol;
        this.minusSign = minusSign;
        this.monetaryDecimalSeparator = monetaryDecimalSeparator;
        this.nan = nan;
        this.patternSeparator = patternSeparator;
        this.percent = percent;
        this.perMill = perMill;
        this.zeroDigit = zeroDigit;
    }

    /**
     * All available {@link Locale locales} also provide decimal format symbols.
     */
    public static Locale[] getAvailableLocales() {
        return LANGUAGE_TAG_TO_SYMBOLS.keySet()
                .stream()
                .map(Locale::forLanguageTag)
                .sorted((l, r) -> l.toLanguageTag().compareTo(r.toLanguageTag()))
                .toArray(Locale[]::new);
    }

    public DecimalFormatSymbols() {
        this(Locale.getDefault());
    }

    public DecimalFormatSymbols(final Locale locale) {
        this(toWalkingkookaLocale(locale).orElse(toWalkingkookaLocale(Locale.getDefault()).orElseThrow(() -> new IllegalStateException())));
    }

    private static Optional<WalkingkookaLocale> toWalkingkookaLocale(final Locale locale) {
        // TODO extract a js version that calls Locale#toWalkingkookaLanguageTag
        return WalkingkookaLocale.forLanguageTag(WalkingkookaLanguageTag.parse(locale.toLanguageTag()));
    }

    private DecimalFormatSymbols(final WalkingkookaLocale locale) {
        super();

        final DecimalFormatSymbols symbols = LANGUAGE_TAG_TO_SYMBOLS.get(locale.languageTag().toLanguageTag());
        this.currency = symbols.getCurrency();
        this.currencySymbol = symbols.getCurrencySymbol();
        this.decimalSeparator = symbols.getDecimalSeparator();
        this.digit = symbols.getDigit();
        this.exponentSeparator = symbols.getExponentSeparator();
        this.groupingSeparator = symbols.getGroupingSeparator();
        this.infinity = symbols.getInfinity();
        this.internationalCurrencySymbol = symbols.getInternationalCurrencySymbol();
        this.minusSign = symbols.getMinusSign();
        this.monetaryDecimalSeparator = symbols.getMonetaryDecimalSeparator();
        this.nan = symbols.getNaN();
        this.patternSeparator = symbols.getPatternSeparator();
        this.percent = symbols.getPercent();
        this.perMill = symbols.getPerMill();
        this.zeroDigit = symbols.getZeroDigit();
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    private String currency;

    public String getCurrencySymbol() {
        return this.currencySymbol;
    }

    public void setCurrencySymbol(final String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    private String currencySymbol;

    public char getDecimalSeparator() {
        return this.decimalSeparator;
    }

    public void setDecimalSeparator(final char decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
    }


    private char decimalSeparator;

    public char getDigit() {
        return this.digit;
    }

    public void setDigit(final char digit) {
        this.digit = digit;
    }

    private char digit;

    public String getExponentSeparator() {
        return this.exponentSeparator;
    }

    public void setExponentSeparator(final String exponentSeparator) {
        this.exponentSeparator = exponentSeparator;
    }

    private String exponentSeparator;

    public char getGroupingSeparator() {
        return this.groupingSeparator;
    }

    public void setGroupingSeparator(final char groupingSeparator) {
        this.groupingSeparator = groupingSeparator;
    }

    private char groupingSeparator;

    public String getInfinity() {
        return this.infinity;
    }

    public void setInfinity(final String infinity) {
        this.infinity = infinity;
    }

    private String infinity;

    public String getInternationalCurrencySymbol() {
        return this.internationalCurrencySymbol;
    }

    public void setInternationalCurrencySymbol(final String internationalCurrencySymbol) {
        this.internationalCurrencySymbol = internationalCurrencySymbol;
    }

    private String internationalCurrencySymbol;

    public char getMinusSign() {
        return this.minusSign;
    }

    public void setMinusSign(final char minusSign) {
        this.minusSign = minusSign;
    }

    private char minusSign;

    public char getMonetaryDecimalSeparator() {
        return this.monetaryDecimalSeparator;
    }

    public void setMonetaryDecimalSeparator(final char monetaryDecimalSeparator) {
        this.monetaryDecimalSeparator = monetaryDecimalSeparator;
    }

    private char monetaryDecimalSeparator;

    public String getNaN() {
        return this.nan;
    }

    public void setNaN(final String nan) {
        this.nan = nan;
    }

    private String nan;

    public char getPatternSeparator() {
        return this.patternSeparator;
    }

    public void setPatternSeparator(final char patternSeparator) {
        this.patternSeparator = patternSeparator;
    }

    private char patternSeparator;

    public char getPercent() {
        return this.percent;
    }

    public void setPercent(final char percent) {
        this.percent = percent;
    }

    private char percent;

    public char getPerMill() {
        return this.perMill;
    }

    public void setPerMill(final char perMill) {
        this.perMill = perMill;
    }

    private char perMill;

    public char getZeroDigit() {
        return this.zeroDigit;
    }

    public void setZeroDigit(final char zeroDigit) {
        this.zeroDigit = zeroDigit;
    }

    private char zeroDigit;

    // clone...........................................................................................................

    /**
     * Used internally to make a defensive copy
     */
    final DecimalFormatSymbols cloneState() {
        return new DecimalFormatSymbols(this.currency,
                this.currencySymbol,
                this.decimalSeparator,
                this.digit,
                this.exponentSeparator,
                this.groupingSeparator,
                this.infinity,
                this.internationalCurrencySymbol,
                this.minusSign,
                this.monetaryDecimalSeparator,
                this.nan,
                this.patternSeparator,
                this.percent,
                this.perMill,
                this.zeroDigit);
    }

    // Object...........................................................................................................

    @Override
    public int hashCode() {
        return Objects.hash(this.currency,
                this.currencySymbol,
                this.decimalSeparator,
                this.digit,
                this.exponentSeparator,
                this.groupingSeparator,
                this.infinity,
                this.internationalCurrencySymbol,
                this.minusSign,
                this.monetaryDecimalSeparator,
                this.nan,
                this.patternSeparator,
                this.percent,
                this.perMill,
                this.zeroDigit);
    }

    public boolean equals(final Object other) {
        return this == other || other instanceof DecimalFormatSymbols && this.equals0((DecimalFormatSymbols) other);
    }

    private boolean equals0(final DecimalFormatSymbols other) {
        return Objects.equals(this.currency, other.currency) &&
                Objects.equals(this.currencySymbol, other.currencySymbol) &&
                this.decimalSeparator == other.decimalSeparator &&
                this.digit == other.digit &&
                Objects.equals(this.exponentSeparator, other.exponentSeparator) &&
                this.groupingSeparator == other.groupingSeparator &&
                Objects.equals(this.infinity, other.infinity) &&
                Objects.equals(this.internationalCurrencySymbol, other.internationalCurrencySymbol) &&
                this.minusSign == other.minusSign &&
                this.monetaryDecimalSeparator == other.monetaryDecimalSeparator &&
                Objects.equals(this.nan, other.nan) &&
                this.patternSeparator == other.patternSeparator &&
                this.percent == other.percent &&
                this.perMill == other.perMill &&
                this.zeroDigit == other.zeroDigit;
    }

    @Override
    public String toString() {
        return ToStringBuilder.empty()
                .label("currency").value(this.currency)
                .label("currencySymbol").value(this.currencySymbol)
                .label("decimalSeparator").value(this.decimalSeparator)
                .label("digit").value(this.digit)
                .label("exponentSeparator").value(this.exponentSeparator)
                .label("groupingSeparator").value(this.groupingSeparator)
                .label("infinity").value(this.infinity)
                .label("internationalCurrencySymbol").value(this.internationalCurrencySymbol)
                .label("minusSign").value(this.minusSign)
                .label("monetaryDecimalSeparator").value(this.monetaryDecimalSeparator)
                .label("nan").value(this.nan)
                .label("patternSeparator").value(this.patternSeparator)
                .label("percent").value(this.percent)
                .label("perMill").value(this.perMill)
                .label("zeroDigit").value(this.zeroDigit)
                .build();
    }
}
