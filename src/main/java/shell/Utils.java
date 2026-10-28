package shell;

public class Utils {

    public static String capitalize(String str) {
        return str.isEmpty() ? str
                : str.length() == 1 ? str.toUpperCase()
                : str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
