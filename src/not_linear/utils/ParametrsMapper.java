package not_linear.utils;

import not_linear.models.Parametrs;
import not_linear.models.ParametrsString;

public class ParametrsMapper
{
    private ParametrsMapper()
    {

    }

    public static Parametrs getParametrs(final ParametrsString parametrsString)
    {
        Parametrs parametrs = new Parametrs();

        parametrs.seteSystem(Double.parseDouble(parametrsString.geteSystem()));
        parametrs.setFragmentationT(Integer.parseInt(parametrsString.getFragmentationT()));
        parametrs.setFragmentationX(Integer.parseInt(parametrsString.getFragmentationX()));
        parametrs.settFrom(Double.parseDouble(parametrsString.gettFrom()));
        parametrs.settTo(Double.parseDouble(parametrsString.gettTo()));
        parametrs.setxFrom(Double.parseDouble(parametrsString.getxFrom()));
        parametrs.setxTo(Double.parseDouble(parametrsString.getxTo()));
        parametrs.setLengthT(parametrs.gettTo() - parametrs.gettFrom());
        parametrs.setLengthX(parametrs.getxTo() - parametrs.getxFrom());

        return parametrs;
    }
}
