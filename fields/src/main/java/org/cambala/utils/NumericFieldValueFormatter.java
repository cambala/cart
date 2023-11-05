package org.cambala.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class NumericFieldValueFormatter {

    public static final DecimalFormat FORMATTER_3 = new DecimalFormat("#.###", new DecimalFormatSymbols(Locale.ENGLISH));
    private static final int FORMATTER_3_MAXIMUM_FRACTION_DIGITS = 3;
    static {
        FORMATTER_3.setMaximumFractionDigits(FORMATTER_3_MAXIMUM_FRACTION_DIGITS);
    }
}
