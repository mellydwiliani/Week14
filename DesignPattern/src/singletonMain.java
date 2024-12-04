public class singletonMain {
    public static void main(String[] args) {
        // Mengakses satu instance untuk mengatur tema
        ThemeManager themeManager = ThemeManager.getInstance();
        System.out.println("Tema saat ini: " + themeManager.getTheme());
        
        themeManager.setTheme("Dark");
        System.out.println("Tema saat ini: " + themeManager.getTheme());

        // Mengakses instance yang sama di bagian lain aplikasi
        ThemeManager anotherThemeManager = ThemeManager.getInstance();
        System.out.println("Tema dari instance lain: " + anotherThemeManager.getTheme());
    }
}
