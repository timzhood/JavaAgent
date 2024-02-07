package test.enums;

public interface IEncodedEnum<E extends Enum<E>>
{
    int getEncodedValue();
}
