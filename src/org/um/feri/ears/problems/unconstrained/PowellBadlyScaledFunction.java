package org.um.feri.ears.problems.unconstrained;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.um.feri.ears.problems.Problem;

import static java.lang.Math.*;

/*
http://al-roomi.org/benchmarks/unconstrained/2-dimensions/65-powell-s-badly-scaled-function
 */
public class PowellBadlyScaledFunction extends Problem {

    public PowellBadlyScaledFunction() {
        super(2, 0);

        lowerLimit = new ArrayList<Double>(Collections.nCopies(numberOfDimensions, -50.0));
        upperLimit = new ArrayList<Double>(Collections.nCopies(numberOfDimensions, 50.0));

        name = "Powellbadlyscaledfunction";

        optimum[0][0] = 1.09815933e-5;
        optimum[0][1] = 9.106146738;

    }

	@Override
    public double eval(double[] x) {
        double fitness = pow(10. * x[0] * x[1] - 1, 2) + pow(exp(-x[0]) + exp(-x[1]) - 1.0001, 2);
        return fitness;
    }
}
