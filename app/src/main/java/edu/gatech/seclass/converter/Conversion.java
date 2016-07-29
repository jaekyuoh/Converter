package edu.gatech.seclass.converter;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaekyuoh on 2/15/16.
 */
public class Conversion {
    private double input;
    private String fromUnit;
    private String toUnit;

    static final Map<String, Double> CONVERSION_TABLE;
    static {
        final Map<String, Double> valuesByName = new HashMap<>();
        valuesByName.put("miles_PER_miles",1.0 );
        valuesByName.put("miles_PER_km", 0.621371);
        valuesByName.put("miles_PER_m", 0.000621371);
        valuesByName.put("miles_PER_cm", 0.00000621371);
        valuesByName.put("miles_PER_inches", 0.0000157828);
        valuesByName.put("miles_PER_feet", 0.000189394);

        valuesByName.put("km_PER_miles",1.60934 );
        valuesByName.put("km_PER_km", 1.0);
        valuesByName.put("km_PER_m", 0.001);
        valuesByName.put("km_PER_cm", 0.00001);
        valuesByName.put("km_PER_inches", 0.0000254);
        valuesByName.put("km_PER_feet", 0.0003048);

        valuesByName.put("m_PER_miles",1609.34 );
        valuesByName.put("m_PER_km", 1000.0);
        valuesByName.put("m_PER_m", 1.0);
        valuesByName.put("m_PER_cm", 0.01);
        valuesByName.put("m_PER_inches", 0.0254);
        valuesByName.put("m_PER_feet", 0.3048);

        valuesByName.put("cm_PER_miles",160934.0 );
        valuesByName.put("cm_PER_km", 100000.0);
        valuesByName.put("cm_PER_m", 100.0);
        valuesByName.put("cm_PER_cm", 1.0);
        valuesByName.put("cm_PER_inches", 2.54);
        valuesByName.put("cm_PER_feet", 30.48);

        valuesByName.put("inches_PER_miles",63360.0 );
        valuesByName.put("inches_PER_km", 39370.1);
        valuesByName.put("inches_PER_m", 39.3701);
        valuesByName.put("inches_PER_cm", 0.393701);
        valuesByName.put("inches_PER_inches", 1.0);
        valuesByName.put("inches_PER_feet", 12.0);

        valuesByName.put("feet_PER_miles",5280.0 );
        valuesByName.put("feet_PER_km", 3280.84);
        valuesByName.put("feet_PER_m", 3.28084);
        valuesByName.put("feet_PER_cm", 0.0328084);
        valuesByName.put("feet_PER_inches", 0.0833333);
        valuesByName.put("feet_PER_feet", 1.0);

        CONVERSION_TABLE = Collections.unmodifiableMap(valuesByName);
    }

    public Conversion(double input, String fromUnit, String toUnit) {
        this.input = input;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
    }


    public String compute(){
        double result = 0.0;
        String method = this.fromUnit + "_PER_" + this.toUnit;
        result = this.input / CONVERSION_TABLE.get(method);
        DecimalFormat f = new DecimalFormat("#.#########");
        String newResult = f.format(result);

        //String resultStr = (String.valueOf(result)) + "  " + this.toUnit;
        String resultStr = newResult + "  " + this.toUnit;
        return resultStr;
    }
}
