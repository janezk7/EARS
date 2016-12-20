package org.um.feri.ears.problems.unconstrained.cec2015;

import java.util.List;

import org.um.feri.ears.problems.unconstrained.cec.Functions;

public class F9 extends CEC2015 {
	
	public F9(int d) {
		super(d,9);

		name = "F09 Expanded Scaffer's Function";
	}

	@Override
	public double eval(List<Double> ds) {
		double F;
		F = Functions.escaffer6_func(ds, numberOfDimensions, OShift, M, 1, 1);
		F+= 100 * func_num;
		return F;
	}
	
	public double eval(double x[]) {
		double F;
		F = Functions.escaffer6_func(x, numberOfDimensions, OShift, M, 1, 1);
		F+= 100 * func_num;
		return F;
	}

}
