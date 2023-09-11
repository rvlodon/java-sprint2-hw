import java.util.Scanner;

public class Actoins {

    Scanner scanner = new Scanner(System.in);
    public void actions() {
        while (true) {
            String inputCommand = scanner.nextLine();
            int command = strCheck(inputCommand);

            printMenu();

            switch (command) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 0:

                    return;
                default:
                    System.out.println("Такой команды нет");
                    break;
            }
        }
    }

    // Метод, который печатает меню
    public static void printMenu() {
        System.out.println("Выберете действие");
        System.out.println("1 - Считать месячные отчеты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Сверить отчеты");
        System.out.println("4 - Вывести информацию обо всех месячных отчетах");
        System.out.println("5 - Вывести информацию о годовом отчете");
        System.out.println("0 - Выйти");
    }

    // Метод, проверяющий ввод пользователя на валидность (число или нет)
    public static int strCheck(String userInput) {
        String str = userInput.trim();
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return 0;
            }
        }
        return Integer.parseInt(str);
    }
}
