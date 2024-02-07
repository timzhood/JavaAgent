package test.enums;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Verify;
import com.google.common.collect.EnumBiMap;

public final class EnumMapping<DE extends Enum<DE> & IDisplayEnum<DE>, CE extends Enum<CE> & IEncodedEnum<CE>>
{
    private final EnumBiMap<CE, DE> canonicalToDisplayMapping;

    private final EnumBiMap<DE, CE> displayToCanonicalMapping;

    private final Map<String, CE> displayValueToCanonicalMapping;

    public EnumMapping(final Class<DE> displayEnum, final Class<CE> encodedEnum)
    {
        super();

        // ensure bijection
        Verify.verify(displayEnum.getEnumConstants().length == encodedEnum.getEnumConstants().length);

        // two way mapping between the enums
        this.displayToCanonicalMapping = EnumBiMap.create(displayEnum, encodedEnum);
        this.canonicalToDisplayMapping = EnumBiMap.create(encodedEnum, displayEnum);

        // string to canonical mapping
        this.displayValueToCanonicalMapping = new HashMap<>(displayEnum.getEnumConstants().length);
        for (final DE display : displayEnum.getEnumConstants())
        {
            this.displayValueToCanonicalMapping.put(display.getDisplayValue(),
                    this.displayToCanonicalMapping.get(display));
        }
    }

    public DE canonicalToDisplay(final CE value)
    {
        return this.canonicalToDisplayMapping.get(value);
    }

    public String canonicalToDisplayString(final CE value)
    {
        return this.canonicalToDisplayMapping.get(value).getDisplayValue();
    }

    public CE displayStringToCanonical(final String value)
    {
        return this.displayValueToCanonicalMapping.get(value);
    }

    /**
     * Get the canonical ordinal value to store from a display value.
     * <p>
     * Sometimes used as the record canonical value.
     *
     * @param value - a value displayed to the user
     * @return - the ordinal of the canonical enum value
     */
    public int displayStringToCanonicalOrdinal(final String value)
    {
        return this.displayValueToCanonicalMapping.get(value).ordinal();
    }

    public CE displayToCanonical(final DE value)
    {
        return this.displayToCanonicalMapping.get(value);
    }

    public static <DE extends Enum<DE> & IDisplayEnum<DE>, CE extends Enum<CE> & IEncodedEnum<CE>> EnumMapping<DE, CE> makeMapping(
            final Class<DE> displayEnum, final Class<CE> encodedEnum)
    {
        return new EnumMapping<>(displayEnum, encodedEnum);
    }
}
