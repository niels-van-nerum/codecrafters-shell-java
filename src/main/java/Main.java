import java.util.Scanner;

public class Main {
    private static final String SHELL_PROMPT = "$ ";
    public static void main(String[] args) throws Exception {
        System.out.print(SHELL_PROMPT);

        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        System.out.printf("%s: command not found%n", command);
    }
}
