package test.realcode;

public class FreeTextEditorController
extends
BaseEditorController
{

    public FreeTextEditorController()
    {
        super();
    }

    @Override
    public Object canonicalToDisplayValue(final Object canonicalValue)
    {
        System.out.println("CanonicalValue=" + canonicalValue);
        return canonicalValue;
    }

    @Override
    public Object displayToCanonicalValue(final Object displayValue)
    {
        System.out.println("DisplayValue=" + displayValue);
        return displayValue;
    }

}
