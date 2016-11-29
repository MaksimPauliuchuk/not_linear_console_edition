package not_linear.methods;

import not_linear.models.Equation;
import not_linear.models.Parametrs;
import not_linear.models.ParametrsString;
import not_linear.utils.Calculator;
import not_linear.utils.ParametrsMapper;
import not_linear.utils.TridiagonalMatrixSolution;

public class TwoLayersHybrid {
	private Equation equation;
	private Parametrs parametrs;
	private double h;
	private double tao;
	private double t;
	private double vector[];

	public TwoLayersHybrid(Equation equation, ParametrsString parametrs) {
		super();
		this.equation = equation;
		this.parametrs = ParametrsMapper.getParametrs(parametrs);
		initialization();
	}

	private void initialization() {

		this.h = this.parametrs.getLengthX() / this.parametrs.getFragmentationX();
		this.tao = this.parametrs.getLengthT() / this.parametrs.getFragmentationT();
		this.vector = new double[this.parametrs.getFragmentationX() + 1];

		for (int i = 0; i < this.vector.length; i++) {
			this.vector[i] = Calculator.mu_down(equation.getMu_down(), this.parametrs.getxFrom(), i * h);
		}
	}

	public double[] getAnswer() {
		vector = findMLayer(this.parametrs.getFragmentationT(), vector, tao);
		return vector;
	}

	private double[] findMLayer(final int M, final double[] stroka, final double tao) {
		double[] strokaout = stroka.clone();
		for (int m = 1; m <= M; m++) {
			strokaout = progonkaSquare(m, strokaout, tao);
			System.out.println("-----------------------------------");
		}
		return strokaout;
	}

	private double[] progonkaSquare(final int m, final double[] stroka, final double tao) {
		// start data
		int N = this.parametrs.getFragmentationX();
		double tFrom = this.parametrs.gettFrom();
		double xFrom = this.parametrs.getxFrom();
		double xTo = this.parametrs.getxTo();

		// to solve system
		double vectorDelta_Xn[];
		double vectorF_Xn[] = new double[N + 1];
		double vectorX_M_iter[] = stroka.clone();
		double[] left = new double[N], center = new double[N + 1], right = new double[N];

		// parameters of our equation
		double k_u[] = new double[N + 1];
		double k[] = new double[N + 1];
		double g[] = new double[N + 1];
		double mu_left = Calculator.mu_left(equation.getMu_left(), xFrom, tFrom + tao * m);
		double mu_right = Calculator.mu_right(equation.getMu_right(), xTo, tFrom + tao * m);

		for (int n = 0; n < k_u.length; n++) {
			k_u[n] = Calculator.k_u(equation.getK_u(), xFrom + n * h, tFrom + tao * m, stroka[n]);
			k[n] = Calculator.k(equation.getK(), xFrom + n * h, tFrom + tao * m, stroka[n]);
			g[n] = Calculator.g(equation.getG(), xFrom + n * h, tFrom + tao * m, stroka[n]);
		}

		// solution parameters
		int numberBeta = 3; // neeeed replace
		double beta = 0.1;
		double gamma = beta * beta;
		double betaminus;
		double y2_y0 = 0.0;
		double norma_Xn = 0.0;
		double norma_XnPlus = 0.0;
		int n = 0;
		switch (numberBeta) {
		case 1: {
			beta = 1;
			break;
		}
		case 2: {
			beta = 0.1;
			break;
		}
		case 3: {
			beta = 0.1;
			gamma = beta * beta;
			break;
		}
		default:
			beta = 0.1;
			break;
		}

		while (true) {
			center[0] = center[N] = 1;
			vectorF_Xn[0] = vectorX_M_iter[0] - mu_left;
			vectorF_Xn[N] = vectorX_M_iter[N] - mu_right;
			for (n = 1; n < center.length - 1; n++) {
				y2_y0 = vectorX_M_iter[n + 1] - vectorX_M_iter[n - 1];
				left[n - 1] = k_u[n] * y2_y0 / (2 * h * h) - k[n] / (h * h);
				center[n] = 1.0 / tao + 2 * k[n] / (h * h);
				right[n] = -k_u[n] * y2_y0 / (2 * h * h) - k[n] / (h * h);
				vectorF_Xn[n] = (vectorX_M_iter[n] - stroka[n]) / tao
						- k_u[n] * Math.pow((vectorX_M_iter[n + 1] - vectorX_M_iter[n - 1]) / (2.0 * h), 2)
						- k[n] * (vectorX_M_iter[n + 1] - 2 * vectorX_M_iter[n] + vectorX_M_iter[n - 1]) / (h * h)
						- g[n];
			}

			norma_Xn = 0.0;
			for (n = 0; n < vectorF_Xn.length; n++) {
				norma_Xn += Math.pow(vectorF_Xn[n], 2);
				vectorF_Xn[n] *= -beta;
			}
			norma_Xn = Math.sqrt(norma_Xn);

			vectorDelta_Xn = TridiagonalMatrixSolution.Solve(left, center, right, vectorF_Xn);
			for (n = 0; n < vectorX_M_iter.length; n++) {
				vectorX_M_iter[n] += vectorDelta_Xn[n];
			}

			norma_XnPlus = 0.0;
			norma_XnPlus += Math.pow(vectorX_M_iter[0] - mu_left, 2);
			norma_XnPlus += Math.pow(vectorX_M_iter[N] - mu_right, 2);
			for (n = 1; n < vectorX_M_iter.length - 1; n++) {
				norma_XnPlus += Math.pow((vectorX_M_iter[n] - stroka[n]) / tao
						- k_u[n] * Math.pow((vectorX_M_iter[n + 1] - vectorX_M_iter[n - 1]) / (2 * h), 2)
						- k[n] * ((vectorX_M_iter[n + 1] - 2 * vectorX_M_iter[n] + vectorX_M_iter[n - 1])) / (h * h)
						- g[n], 2);
			}
			norma_XnPlus = Math.sqrt(norma_XnPlus);

			System.out.println(norma_XnPlus);
			if (norma_XnPlus <= 1e-1) {
				return progonkaCube(m, stroka, tao, vectorX_M_iter);
			} else {
				switch (numberBeta) {
				case 1: {
					beta = 1;
					break;
				}
				case 2: {
					beta = Math.min(1.0, beta * norma_Xn / norma_XnPlus);
					break;
				}
				case 3: {
					betaminus = beta;
					beta = Math.min(1.0, (gamma * norma_Xn) / (norma_XnPlus * beta));
					gamma = gamma * (norma_Xn / norma_XnPlus) * (beta / betaminus);
					break;
				}
				default:
					beta = Math.min(1.0, beta * norma_Xn / norma_XnPlus);
					break;
				}
			}
		}
	}

	private double[] progonkaCube(final int m, final double[] stroka, final double tao, final double[] x_m_stroka) {
		// start data
		int N = this.parametrs.getFragmentationX();
		double tFrom = this.parametrs.gettFrom();
		double xFrom = this.parametrs.getxFrom();
		double xTo = this.parametrs.getxTo();

		// to solve system
		double vectorDelta_Xn[];
		double vectorDelta_Yn[];
		double vectorF_Xn[] = new double[N + 1];
		double vectorF_Yn[] = new double[N + 1];
		double vectorX_M_iter[] = x_m_stroka.clone();
		double vectorY_M_iter[] = x_m_stroka.clone();
		double[] left = new double[N], center = new double[N + 1], right = new double[N];
		double fun[] = new double[N + 1];

		// parameters of our equation
		double k_u[] = new double[N + 1];
		double k[] = new double[N + 1];
		double g[] = new double[N + 1];
		double mu_left = Calculator.mu_left(equation.getMu_left(), xFrom, tFrom + tao * m);
		double mu_right = Calculator.mu_right(equation.getMu_right(), xTo, tFrom + tao * m);

		for (int n = 1; n < k_u.length - 1; n++) {
			k_u[n] = Calculator.k_u(equation.getK_u(), xFrom + n * h, tFrom + tao * m, stroka[n]);
			k[n] = Calculator.k(equation.getK(), xFrom + n * h, tFrom + tao * m, stroka[n]);
			g[n] = Calculator.g(equation.getG(), xFrom + n * h, tFrom + tao * m, stroka[n]);
		}

		// solution parameters
		double beta = 0.1;
		double gamma = beta * beta;
		double betaminus;

		double y2_y0 = 0.0;
		double norma_Xn = 0.0;
		double norma_XnPlus = 0.0;
		int n = 0;
		int iter = 0;

		while (true) {
			iter++;
			center[0] = center[N] = 1.0;
			vectorF_Xn[0] = vectorX_M_iter[0] - mu_left;
			vectorF_Xn[N] = vectorX_M_iter[N] - mu_right;
			for (n = 1; n < center.length - 1; n++) {
				y2_y0 = vectorX_M_iter[n + 1] - vectorX_M_iter[n - 1];
				left[n - 1] = k_u[n] * y2_y0 / (2 * h * h) - k[n] / (h * h);
				center[n] = 1.0 / tao + 2 * k[n] / (h * h);
				right[n] = -k_u[n] * y2_y0 / (2 * h * h) - k[n] / (h * h);
				vectorF_Xn[n] = (vectorX_M_iter[n] - stroka[n]) / tao
						- k_u[n] * Math.pow((vectorX_M_iter[n + 1] - vectorX_M_iter[n - 1]) / (2 * h), 2)
						- k[n] * (vectorX_M_iter[n + 1] - 2 * vectorX_M_iter[n] + vectorX_M_iter[n - 1]) / (h * h)
						- g[n];
			}

			norma_Xn = 0.0;
			for (n = 0; n < vectorF_Xn.length; n++) {
				norma_Xn += Math.pow(vectorF_Xn[n], 2);
				vectorF_Xn[n] *= -1.0;
			}
			norma_Xn = Math.sqrt(norma_Xn);

			vectorDelta_Yn = TridiagonalMatrixSolution.Solve(left, center, right, vectorF_Xn);
			for (n = 0; n < vectorX_M_iter.length; n++) {
				vectorY_M_iter[n] = vectorX_M_iter[n] + vectorDelta_Yn[n];
			}

			vectorF_Yn[0] = vectorY_M_iter[0] - mu_left;
			vectorF_Yn[N] = vectorY_M_iter[N] - mu_right;
			for (n = 1; n < center.length - 1; n++) {
				vectorF_Yn[n] = (vectorY_M_iter[n] - stroka[n]) / tao
						- k_u[n] * Math.pow((vectorY_M_iter[n + 1] - vectorY_M_iter[n - 1]) / (2 * h), 2)
						- k[n] * (vectorY_M_iter[n + 1] - 2 * vectorY_M_iter[n] + vectorY_M_iter[n - 1]) / (h * h)
						- g[n];
			}
			for (n = 0; n < fun.length; n++) {
				fun[n] = vectorF_Xn[n] - beta * vectorF_Yn[n];
			}
			vectorDelta_Xn = TridiagonalMatrixSolution.Solve(left, center, right, fun);

			for (n = 0; n < vectorDelta_Xn.length; n++) {
				vectorX_M_iter[n] += beta * vectorDelta_Xn[n];
			}

			norma_XnPlus = 0.0;
			norma_XnPlus += Math.pow(vectorX_M_iter[0] - mu_left, 2);
			norma_XnPlus += Math.pow(vectorX_M_iter[N] - mu_right, 2);
			for (n = 1; n < vectorX_M_iter.length - 1; n++) {
				norma_XnPlus += Math.pow((vectorX_M_iter[n] - stroka[n]) / tao
						- k_u[n] * Math.pow((vectorX_M_iter[n + 1] - vectorX_M_iter[n - 1]) / (2 * h), 2)
						- k[n] * ((vectorX_M_iter[n + 1] - 2 * vectorX_M_iter[n] + vectorX_M_iter[n - 1])) / (h * h)
						- g[n], 2);
			}
			norma_XnPlus = Math.sqrt(norma_XnPlus);
			System.out.println("Layer:" + m + ", " + norma_XnPlus);

			if (norma_XnPlus <= parametrs.geteSystem() || iter > 100) {
				return vectorX_M_iter;
			} else if (norma_XnPlus < norma_Xn) {
				beta = 1;
				gamma = beta * beta;
			} else {
				betaminus = beta;
				beta = Math.min(1.0, (gamma * norma_Xn) / (norma_XnPlus * beta));
				gamma = (beta / betaminus) * (gamma * norma_Xn) / (betaminus * norma_XnPlus);
			}
		}
	}
}
