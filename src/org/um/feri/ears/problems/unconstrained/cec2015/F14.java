package org.um.feri.ears.problems.unconstrained.cec2015;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.um.feri.ears.problems.unconstrained.cec.Functions;

public class F14 extends CEC2015 {

    public F14(int d) {
        super(d, 14);

        name = "F14 Composition Function 2";
    }

    @Override
    public double eval(double[] x) {
        double F;
        F = Functions.cf03(x, numberOfDimensions, OShift, M, 1);
        F += 100 * func_num;
        return F;
    }
}
