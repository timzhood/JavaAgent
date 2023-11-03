package test.realcode;

public class Main
{
    public Main()
    {
        super();
    }

    public static void main(final String[] args)
    {
        final FreeTextEditorController editor = new FreeTextEditorController();

        System.out.println("canonicalToDisplayValue");
        System.out.println("DisplayValue=" + editor.canonicalToDisplayValue("Bob.29.Y"));
        System.out.println("----");

        System.out.println("displayToCanonicalValue");
        System.out.println("CanonicalValue=" + editor.displayToCanonicalValue("Bob.29.9a"));
        System.out.println("----");
    }
}
