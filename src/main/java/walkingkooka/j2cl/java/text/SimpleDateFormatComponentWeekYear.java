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

final class SimpleDateFormatComponentWeekYear extends SimpleDateFormatComponent2 {

    final static char LETTER = WEEK_YEAR;

    static SimpleDateFormatComponentWeekYear with(final int length) {
        return new SimpleDateFormatComponentWeekYear(length);
    }

    private SimpleDateFormatComponentWeekYear(final int length) {
        super(length);
    }

    // formatDate.......................................................................................................

    @Override
    final void formatDate(final SimpleDateFormatRequest request) {
        final int year = request.calendar.get(Calendar.YEAR);
        final int length = this.length;

        this.formatNumericValue(request,
                2 == length ?
                        year % 100 : // length = 2 then two digits year
                        year,
                length);
    }

    // SimpleDateFormatComponent2.......................................................................................

    @Override
    char letter() {
        return LETTER;
    }
}