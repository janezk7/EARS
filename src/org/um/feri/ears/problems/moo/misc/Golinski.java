//  Golinski.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.
package org.um.feri.ears.problems.moo.misc;

import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;
import org.um.feri.ears.problems.moo.DoubleMOProblem;
import org.um.feri.ears.problems.moo.MOSolutionBase;
import org.um.feri.ears.problems.moo.functions.Golinski_F1;
import org.um.feri.ears.problems.moo.functions.Golinski_F2;

public class Golinski extends DoubleMOProblem {
	
	// defining lowerLimits and upperLimits for the problem
	public static final double [] LOWERLIMIT = {2.6, 0.7, 17.0, 7.3, 7.3, 2.9, 5.0};
	public static final double [] UPPERLIMIT = {3.6, 0.8, 28.0, 8.3, 8.3, 3.9, 5.5};  
	
	public Golinski() {
	     
		super(7,11,2);

		file_name = "Golinski";
		name = "Golinski";

	    upperLimit = new ArrayList<Double>(numberOfDimensions);
		lowerLimit = new ArrayList<Double>(numberOfDimensions);

		for (int var = 0; var < numberOfDimensions; var++){
			lowerLimit.add(LOWERLIMIT[var]);
			upperLimit.add(UPPERLIMIT[var]);
		}

		this.addObjective(new Golinski_F1());
		this.addObjective(new Golinski_F2());
	}

	@Override
	public void evaluate(MOSolutionBase<Double> solution) {
		
		double[] x = solution.getVariables().stream().mapToDouble(i->i).toArray();

		double obj[] = new double[functions.size()];
		for (int i = 0; i < obj.length; i++) {
			obj[i] = functions.get(i).eval(x);
		}
		solution.setObjectives(obj);
		
	}

	@Override
	public void evaluateConstraints(MOSolutionBase<Double> solution) {
		double[] constraints = new double[numberOfConstraints];
		
		double[] dv = solution.getVariables().stream().mapToDouble(i->i).toArray();
		
	    double x1,x2,x3,x4,x5,x6,x7;
	    
	    x1 = dv[0];
	    x2 = dv[1];
	    x3 = dv[2];
	    x4 = dv[3];
	    x5 = dv[4];
	    x6 = dv[5];
	    x7 = dv[6];
		
		constraints[0] = -((1.0/(x1*x2*x2*x3)) -(1.0/27.0));
		constraints[1] = -((1.0/(x1*x2*x2*x3*x3))-(1.0/397.5));
		constraints[2] = -((x4*x4*x4)/(x2*x3*x3*x6*x6*x6*x6)-(1.0/1.93));
		constraints[3] = -((x5*x5*x5)/(x2*x3*x7*x7*x7*x7)- (1.0/1.93));
		constraints[4] = -(x2*x3 - 40.0);
		constraints[5] = -((x1/x2) - 12.0);
		constraints[6] = -(5.0 - (x1/x2));
		constraints[7] = -(1.9 - x4 + 1.5*x6);
	    constraints[8] = -(1.9 - x5 + 1.1*x7);
	    
	    double aux = 745.0 * x4 / (x2 * x3);
	    double f2 = java.lang.Math.sqrt((aux*aux)+1.69e7) / (0.1*x6*x6*x6);
	    constraints[9] = -(f2 - 1300);
	    double a = 745.0*x5/(x2*x3);
	    double b = 1.575e8;
	    constraints[10] = -(java.lang.Math.sqrt(a*a+b)/(0.1*x7*x7*x7)-1100.0);

		solution.setConstraints(constraints);

	    double total = 0.0;
	    int number = 0;
		for (int i = 0; i < constraints.length; i++) {
			if (constraints[i]<0.0){
		        total+=constraints[i];
		        number++;
			}
		}
	    solution.setOverallConstraintViolation(total);    
	    solution.setNumberOfViolatedConstraint(number); 
		
	}

	@Override
	public double[] evaluate(Double[] ds) {
		double[] x = new double[numberOfDimensions];
		for (int i = 0; i < numberOfDimensions; i++)
			x[i] = ds[i];

		double obj[] = new double[functions.size()];
		for (int i = 0; i < obj.length; i++) {
			obj[i] = functions.get(i).eval(x);
		}

		return obj;
	}

}
