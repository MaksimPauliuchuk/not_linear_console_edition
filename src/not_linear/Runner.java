package not_linear;

import not_linear.methods.TwoLayersLinear;
import not_linear.models.Equation;
import not_linear.models.ParametrsString;
import not_linear.utils.TridiagonalMatrixSolution;

public class Runner
{
    public static void main(String[] args)
    {
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
        parametrs.seteSystemSquare("1E0");
        parametrs.seteSystemCube("1E-10");
        parametrs.seteRunge("1e-6");
        parametrs.setMaxIterProgonka("10");
        parametrs.setMaxIterBigDecimal("3");
        parametrs.setFragmentationT("10");
        parametrs.setFragmentationX("10");
        parametrs.settFrom("0.0");
        parametrs.settTo("1.0");
        parametrs.setxFrom("0.0");
        parametrs.setxTo("1.0");

        // TwoLayersHybrid twoLayersHybrid = new TwoLayersHybrid(equation, parametrs);
        // double[] a = twoLayersHybrid.getAnswerUseRungeRule();
        // TridiagonalMatrixSolution.Print(a);
        //
        // double norm = 0.0;
        // for (int i = 0; i < 11; i++)
        // {
        // norm += Math.pow(a[i] - (i * i * 0.1 * 0.1 + 1 + i * i * 0.1 * 0.1 * 1), 2);
        // }
        // System.out.printf("%2.16f\n", Math.sqrt(norm));

        // TwoLayersHybrid twoLayersHybridRungeRule = new
        // TwoLayersHybrid(equation, parametrs);
        // a = twoLayersHybridRungeRule.getAnswerUseRungeRule();
        // TridiagonalMatrixSolution.Print(a);
        //
        // norm = 0.0;
        // for (int i = 0; i < 11; i++) {
        // norm += Math.pow(a[i] - (i * i * 0.1 * 0.1 + 1 + i * i * 0.1 * 0.1 *
        // 1), 2);
        // }
        // System.out.printf("\n%2.16f\n", Math.sqrt(norm));

        TwoLayersLinear twoLayersLinear = new TwoLayersLinear(equation, parametrs);
        double[] a1 = twoLayersLinear.getAnswerUseRungeRule();
        TridiagonalMatrixSolution.Print(a1);

    }
}
