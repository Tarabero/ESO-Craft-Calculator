package gui;

public enum LookAndFeelThemes {
    METAL("Metal", "javax.swing.plaf.metal.MetalLookAndFeel"),
    NIMBUS("Nimbus", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"),
    MOTIF("Motif", "com.sun.java.swing.plaf.motif.MotifLookAndFeel"),
    WINDOWS("Windows", "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"),
    WINDOWS_CLASSIC("Windows Classic", "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");

    private final String name;
    private final String themeCall;


    LookAndFeelThemes(String name, String themeCall) {
        this.name = name;
        this.themeCall = themeCall;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getThemeCall() {
        return themeCall;
    }
}
