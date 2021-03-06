package org.um.feri.ears.problems.unconstrained;

import org.um.feri.ears.problems.Problem;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.*;

/*
https://www.al-roomi.org/benchmarks/unconstrained/2-dimensions/128-rump-function
 */
public class Rump extends Problem {

    public Rump() {
        super(2, 0);
        lowerLimit = new ArrayList<Double>(Collections.nCopies(numberOfDimensions, -500.0));
        upperLimit = new ArrayList<Double>(Collections.nCopies(numberOfDimensions, 500.0));
        name = "Rump";
    }

    @Override
    public double eval(double[] x) {
        // modified equation to prevent infinity
        double fitness = abs((333.75 - pow(x[0], 2)) * pow(x[1], 6) + pow(x[0], 2) * (11 * pow(x[0], 2) * pow(x[1], 2) - 121 * pow(x[1], 4) - 2) + 5.5 * pow(x[1], 8) + x[0] / (501 + x[1]));
        return fitness;
    }
}