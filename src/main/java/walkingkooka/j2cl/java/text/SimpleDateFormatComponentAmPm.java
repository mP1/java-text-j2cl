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

package walkingkooka.j2cl.java.text;

import java.util.Calendar;

/**
 * <code>a</code> within a pattern holding the am or pm.
 */
final class SimpleDateFormatComponentAmPm extends SimpleDateFormatComponent2 {

    final static char LETTER = AMPM;

    static SimpleDateFormatComponentAmPm with(final int length) {
        return new SimpleDateFormatComponentAmPm(length);
    }

    private SimpleDateFormatComponentAmPm(final int length) {
        super(length);
    }

    // format...........................................................................................................

    @Override
    void formatDate(final SimpleDateFormatRequest request) {
        final int ampm = request.calendar.get(Calendar.AM_PM);
        request.text.append(request.symbols.getAmPmStrings()[ampm]);
    }

    @Override
    char letter() {
        return LETTER;
    }
}