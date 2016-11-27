package not_linear.models;

public class Equation
{
    private String mu_left;
    private String mu_down;
    private String mu_right;
    private String k;
    private String k_u;
    private String g;
    private String realFunction;

    public Equation()
    {

    }

    public Equation(String mu_left, String mu_down, String mu_right, String k, String k_u, String g,
            String realFunction)
    {
        super();
        this.mu_left = mu_left;
        this.mu_down = mu_down;
        this.mu_right = mu_right;
        this.k = k;
        this.k_u = k_u;
        this.g = g;
        this.realFunction = realFunction;
    }

    public String getMu_left()
    {
        return mu_left;
    }

    public void setMu_left(String mu_left)
    {
        this.mu_left = mu_left;
    }

    public String getMu_down()
    {
        return mu_down;
    }

    public void setMu_down(String mu_down)
    {
        this.mu_down = mu_down;
    }

    public String getMu_right()
    {
        return mu_right;
    }

    public void setMu_right(String mu_right)
    {
        this.mu_right = mu_right;
    }

    public String getK()
    {
        return k;
    }

    public void setK(String k)
    {
        this.k = k;
    }

    public String getK_u()
    {
        return k_u;
    }

    public void setK_u(String k_u)
    {
        this.k_u = k_u;
    }

    public String getG()
    {
        return g;
    }

    public void setG(String g)
    {
        this.g = g;
    }

    public String getRealFunction()
    {
        return realFunction;
    }

    public void setRealFunction(String realFunction)
    {
        this.realFunction = realFunction;
    }

}
