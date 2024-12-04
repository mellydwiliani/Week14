public class ThemeManager {
    private static ThemeManager instance;
    private String currentTheme;

    private ThemeManager() {
        currentTheme = "Light"; // Default theme
    }

    public static ThemeManager getInstance() {
        if (instance == null) {
            instance = new ThemeManager();
        }
        return instance;
    }

    public void setTheme(String theme) {
        currentTheme = theme;
        System.out.println("Tema diubah ke: " + currentTheme);
    }

    public String getTheme() {
        return currentTheme;
    }
}