package com.assessment.api_design.common.currency;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class DenominationUtil {
    public static BigDecimal majorToMinor(BigDecimal major){
        return major.divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal minorToMajor(BigDecimal minor){
        return minor.multiply(BigDecimal.valueOf(100));
    }
}