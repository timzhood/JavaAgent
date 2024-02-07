package test.enums;

public interface IDisplayEnum<E extends Enum<E>>
{
    String getDisplayValue();
}
