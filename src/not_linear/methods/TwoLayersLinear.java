package not_linear.methods;

import not_linear.models.Equation;
import not_linear.models.Parametrs;
import not_linear.models.ParametrsString;
import not_linear.utils.Calculator;
import not_linear.utils.ParametrsMapper;
import not_linear.utils.TridiagonalMatrixSolution;

public class TwoLayersLinear
{
    private Equation equation;
    private Parametrs parametrs;
    private double h;
    private double tao;
    private double t;
    private double vector[];

    public TwoLayersLinear(Equation equation, ParametrsString parametrs)
    {
        this.equation = equation;
        this.parametrs = ParametrsMapper.getParametrs(parametrs);
        initialization();
    }

    private void initialization()
    {
        this.h = this.parametrs.getLengthX() / this.parametrs.getFragmentationX();
        this.tao = this.parametrs.getLengthT() / this.parametrs.getFragmentationT();
        this.vector = new double[this.parametrs.getFragmentationX() + 1];

        for (int i = 0; i < this.vector.length; i++)
        {
            this.vector[i] = i * i * h * h;
        }
    }

    public double[] getAnswer()
    {
        vector = findMLayer(this.parametrs.getFragmentationT(), vector, tao);
        realFunctionAndNeviazka();
        return vector;
    }

    public double[] getAnswerUseRungeRule()
    {
        int iterRunge = 0;
        int i = 1;
        int M = parametrs.getFragmentationT();
        double norma = 0.0;
        double[] vectorIter;
        vector = findMLayer(1, vector, tao);
        while (iterRunge++ < 100)
        {
            vectorIter = findMLayer((int) Math.pow(2, i), vector, tao * Math.pow(2, -i));
            norma = Calculator.norma(vector, vectorIter);
            if (norma < this.parametrs.geteRunge())
            {
                break;
            }
            else
            {
                vector = vectorIter.clone();
                M *= 2;
                tao /= 2;
                i++;
            }
        }
        System.out.println(M + " " + tao);
        vector = findMLayer(M, vector, tao);
        vectorIter = findMLayer(M * 2, vector, tao / 2);
        System.out.printf("Norma runge %2.15f\n", Calculator.norma(vector, vectorIter));
        realFunctionAndNeviazka();
        return vector;
    }

    private double[] findMLayer(final int M, final double[] stroka, final double tao)
    {
        double gama = tao / (h * h);
        int N = this.parametrs.getFragmentationX();

        double[][] massA = new double[N + 1][N + 1];
        massA[0][0] = massA[massA.length - 1][massA.length - 1] = 1;
        for (int i = 1; i < massA.length - 1; i++)
        {
            massA[i][i] = 1 + 2 * gama;
            massA[i][i - 1] = massA[i][i + 1] = -gama;
        }

        double[] strokaout = stroka.clone();
        for (int m = 1; m <= M; m++)
        {
            strokaout = getLayer(m, strokaout, tao, massA);
        }
        return strokaout;
    }

    private double[] getLayer(final int M, final double[] stroka, final double tao, final double[][] massA)
    {
        int N = this.parametrs.getFragmentationX();
        double xFrom = this.parametrs.getxFrom();
        double xTo = this.parametrs.getxTo();
        double[] vectorF_Xn = new double[N + 1];
        // left
        vectorF_Xn[0] = M * xFrom;
        // right
        vectorF_Xn[vectorF_Xn.length - 1] = xTo * M * M * tao * tao + xTo * xTo;
        for (int j = 1; j < vectorF_Xn.length - 1; j++)
        {
            vectorF_Xn[j] = tao * function(h * j, tao * M) + stroka[j];
        }

        double[] rowX = TridiagonalMatrixSolution.Solve(massA, vectorF_Xn);
        return rowX;
    }

    public void realFunctionAndNeviazka()
    {
        double func;
        System.out.println();
        double nev = 0;
        for (int j = 0; j < vector.length; j++)
        {
            func = j * h * this.parametrs.gettTo() * this.parametrs.gettTo() + j * j * h * h;
            nev += Math.pow((Math.abs(func - vector[j])), 2);
            System.out.println(func + " " + vector[j]);
        }
        nev = Math.sqrt(nev);
        System.out.printf("%20.15f\n", nev);
    }

    private double function(double x, double t)
    {
        return 2 * x * t - 2;
    }
}
