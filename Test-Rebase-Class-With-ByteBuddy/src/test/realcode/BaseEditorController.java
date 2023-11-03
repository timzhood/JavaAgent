package test.realcode;

public abstract class BaseEditorController
{
    public abstract Object canonicalToDisplayValue(Object canonicalValue);

    public abstract Object displayToCanonicalValue(Object displayValue);
}
