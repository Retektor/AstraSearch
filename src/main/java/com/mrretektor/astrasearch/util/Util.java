package com.mrretektor.astrasearch.util;

import java.math.BigDecimal;

public class Util {
	public static BigDecimal convertToBigDecimal(Object value) {
        if (value == null) {
            return null;
        }
        
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        } else if (value instanceof Integer) {
            return BigDecimal.valueOf((Integer) value);
        } else if (value instanceof Long) {
            return BigDecimal.valueOf((Long) value);
        } else if (value instanceof Double) {
            return BigDecimal.valueOf((Double) value);
        } else if (value instanceof Float) {
            return BigDecimal.valueOf((Float) value);
        } else if (value instanceof String) {
        return new BigDecimal((String) value);
        }
        return null;
	}
	
	public static float convertToFloat(Object value) {
		if (value == null) {
	        return 0.0f;
	    }
	    
	    if (value instanceof Float) {
	        return (Float) value;
	    } else if (value instanceof Integer) {
	        return ((Integer) value).floatValue();
	    } else if (value instanceof Long) {
	        return ((Long) value).floatValue();
	    } else if (value instanceof Double) {
	        return ((Double) value).floatValue();
	    } else if (value instanceof BigDecimal) {
	        return ((BigDecimal) value).floatValue();
	    } else if (value instanceof String) {
	        return Float.parseFloat((String) value);
	    }
        return 0.0f;
	}
	
	public static Long convertToLong(Object value) {
	    if (value == null) return null;
	    if (value instanceof Number) {
	        return ((Number) value).longValue();
	    }
	    try {
	        return Long.parseLong(value.toString());
	    } catch (NumberFormatException e) {
	        return null;
	    }
	}
}
