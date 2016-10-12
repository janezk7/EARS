package org.um.feri.ears.problems.unconstrained.cec2010;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.unconstrained.cec2010.base.RosenbrockShifted;
import org.um.feri.ears.problems.unconstrained.cec2010.base.SphereShifted;
import org.um.feri.ears.util.Util;

/**
 * Problem function!
 * 
 * @author Niki Vecek
 * @version 1
 * 
 **/

public class F13 extends Problem {
	
	int[] P;
	int m;
	RosenbrockShifted rosenbrock_shifted;
	SphereShifted sphere_shifted;
	
	// F13 CEC 2010
	// D/2m-group Shifted m-dimensional Rosenbrock's Function
	public F13(int d) {
		super(d,0);
		rosenbrock_shifted = new RosenbrockShifted(numberOfDimensions);
		sphere_shifted= new SphereShifted(numberOfDimensions);
		
		lowerLimit = new ArrayList<Double>(Collections.nCopies(numberOfDimensions, -100.0));
		upperLimit = new ArrayList<Double>(Collections.nCopies(numberOfDimensions, 100.0));
		
		name = "F13 D/2m-group Shifted m-dimensional Rosenbrock's Function";
		
		P = new int[numberOfDimensions];

		int rand_place = 0;
		for (int i=numberOfDimensions-1; i>0; i--){
			rand_place = Util.nextInt(numberOfDimensions);
			P[i] = rand_place;			
		}
		
		m = 2;
	}
	
	public double eval(double x[]) {
		double F = 0;
		for (int k=0; k<numberOfDimensions/(2*m); k++){
			F = F + rosenbrock_shifted.eval(x,P,k*m+1,(k+1)*m);
		}
		F = F + sphere_shifted.eval(x,P,numberOfDimensions/2,numberOfDimensions);
		return F;
	}
	
	@Override
	public double eval(List<Double> ds) {
		double F = 0;
		for (int k=0; k<numberOfDimensions/(2*m); k++){
			F = F + rosenbrock_shifted.eval(ds,P,k*m+1,(k+1)*m);
		}
		F = F + sphere_shifted.eval(ds,P,numberOfDimensions/2,numberOfDimensions);
		return F;
	}

	public double getOptimumEval() {
		return 0;
	}

	@Override
	public boolean isFirstBetter(List<Double> x, double eval_x, List<Double> y,
			double eval_y) {
		return eval_x < eval_y;
	}



}