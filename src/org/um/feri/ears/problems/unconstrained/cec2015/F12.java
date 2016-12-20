package org.um.feri.ears.problems.unconstrained.cec2015;

import java.util.List;

import org.um.feri.ears.problems.unconstrained.cec.Functions;

public class F12  extends CEC2015{
	
	public F12(int d) {
		super(d,12);

		name = "F12 Hybrid Function 3";
	}

	@Override
	public double eval(List<Double> ds) {
		double F;
		F = Functions.hf06(ds, numberOfDimensions, OShift, M, SS, 1, 1);
		F+= 100 * func_num;
		return F;
	}
	
	public double eval(double x[]) {
		double F;
		F = Functions.hf06(x, numberOfDimensions, OShift, M, SS, 1, 1);
		F+= 100 * func_num;
		return F;
	}
}
