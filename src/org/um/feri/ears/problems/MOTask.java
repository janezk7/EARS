package org.um.feri.ears.problems;

import org.um.feri.ears.problems.moo.MOProblemBase;
import org.um.feri.ears.problems.moo.MOSolutionBase;
import org.um.feri.ears.problems.moo.ParetoSolution;
import org.um.feri.ears.qualityIndicator.QualityIndicator;

public abstract class MOTask<T , P extends MOProblemBase<T>> extends TaskBase<P>{
	
    /**
     * Task constructor for multiobjective optimization.
     * @param stop
     * @param eval
     * @param epsilon
     * @param p
     * @param qi
     */
    public MOTask(EnumStopCriteria stop, int eval, double epsilon, P p) {
    	
    	this(stop, eval, epsilon, p,  (int) Math.log10((1./epsilon)+1));
	}
    
    abstract public boolean areDimensionsInFeasableInterval(ParetoSolution<T> bestByALg);

    /**
     * Task constructor for multiobjective optimization.
     * @param stop
     * @param eval
     * @param epsilon
     * @param p
     * @param qi
     * @param precisonOfRealNumbers
     */
	public MOTask(EnumStopCriteria stop, int eval, double epsilon, P p, int precisonOfRealNumbers) {
		
		precisionOfRealNumbersInDecimalPlaces = precisonOfRealNumbers;
        stopCriteria = stop;
        maxEvaluations = eval;
        numberOfEvaluations = 0;
        this.epsilon = epsilon;
        isStop = false;
        isGlobal = false;
        super.p = p; // TODO generic type in TaskBase
	}
	
	/**
	 * Use only for multiobjective problems! 
	 * @return The number of objectives
	 */
	public int getNumberOfObjectives() {
		return p.getNumberOfObjectives();
	}
	
	/**
	 * Use only for multiobjective problems! 
	 * @return The file name of the problem
	 */
	public String getProblemFileName() {
		return p.getFileName();
	}
	
	public int getNumberOfConstrains() {
	    return p.getNumberOfConstraints();
	}
	
	abstract public MOSolutionBase<T> getRandomMOIndividual() throws StopCriteriaException;
	
	public boolean isFirstBetter(ParetoSolution<T> x, ParetoSolution<T> y, QualityIndicator<T> qi) {
		return p.isFirstBetter(x, y, qi);
	}
	
	public P getProblem(){
		return p;
	}
	
	/**
	 * Use only on multiobjective problems!
	 * @param ind <code>MOIndividual</code> to be evaluated
	 * @throws StopCriteriaException
	 */
	public void eval(MOSolutionBase<T> ind) throws StopCriteriaException {
		if (stopCriteria == EnumStopCriteria.EVALUATIONS) {
			incEvaluate();
			p.evaluate(ind);
			p.evaluateConstraints(ind);
		}
		assert false; // Execution should never reach this point!
	}

    /**
     * Works only for basic interval setting!
     * Sets interval!
     * for example -40<x_i<40 <p>
     * if x_i <-40 -> -40 same for 40!
     * 
     * @param d value
     * @param i index of dimension
     * @return
     */
    /*public double feasible(double d, int i){
        return p.feasible(d, i);
    }*/
    
    @Override
    public String toString() {
        return "Task [stopCriteria=" + stopCriteria + ", maxEvaluations=" + maxEvaluations + ", numberOfEvaluations=" + numberOfEvaluations + ", epsilon="
                + epsilon + ", isStop=" + isStop + ", isGlobal=" + isGlobal + ", precisionOfRealNumbersInDecimalPlaces="
                + precisionOfRealNumbersInDecimalPlaces + ", p=" + p + "]";
    }
}