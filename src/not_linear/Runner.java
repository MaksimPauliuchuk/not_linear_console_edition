package not_linear;

import not_linear.methods.TwoLayersHybrid;
import not_linear.models.Equation;
import not_linear.models.ParametrsString;
import not_linear.utils.TridiagonalMatrixSolution;

public class Runner {
	public static void main(String[] args) {
		Equation equation = new Equation();

		equation.setG(
				"2*t-2*t*u-2*t*t*t*u-2*u*u*x-2*t*t*u*u*x-2*t*x*x-8*t*t*t*x*x-4*t*t*t*t*t*x*x-8*u*x*x*x-16*t*t*u*x*x*x-8*t*t*t*t*u*x*x*x");
		equation.setK("u*u*x+u*t");
		equation.setK_u("2*u*x+t");
		equation.setMu_down("x*x");
		equation.setMu_left("t*t");
		equation.setMu_right("x*x+t*t+x*x*t*t");
		equation.setRealFunction("x*x+t*t+x*x*t*t");

		ParametrsString parametrs = new ParametrsString();
		parametrs.seteSystem("1E-10");
		parametrs.setFragmentationT("10");
		parametrs.setFragmentationX("10");
		parametrs.settFrom("0.0");
		parametrs.settTo("1.0");
		parametrs.setxFrom("1.0");
		parametrs.setxTo("2.0");

		TwoLayersHybrid twoLayersHybrid = new TwoLayersHybrid(equation, parametrs);
		TridiagonalMatrixSolution.Print(twoLayersHybrid.getAnswer());

	}
}
