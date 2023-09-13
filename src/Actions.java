import java.util.*;

public class Actoins {

    YearlyReport yearlyReport = new YearlyReport();
    FileReader fileReader = new FileReader();
    ReportEngine reportEngine = new ReportEngine();
    MonthlyReport monthlyReport = new MonthlyReport();
    Scanner scanner = new Scanner(System.in);

    public void actoins() {
        while (true) {
            // Выводим меню действий
            printMenu();
            // Получаем команду от пользователя
            String commandInput = scanner.nextLine();
            int inputCommand = strCheck(commandInput);
            // Выбираем действие в соответствии с командой
            switch (inputCommand) {
                // Месячные отчеты
                case 1:
                    monthlyReport.loadMonthlyReports(scanner, fileReader, reportEngine);
                    break;
                // Годовой отчет
                case 2:
                    yearlyReport.loadYearlyReport(scanner, fileReader, reportEngine);
                    break;
                // Проводим сверку отчетов, если они были загружены
                case 3:
                    if (checkYearReportLoaded(yearlyReport) & checkMonthReportsLoaded(monthlyReport)) {
                        reportEngine.verificationReports(yearlyReport.profitLossMap, monthlyReport.monthlyProfitAndLoss);
                    }
                    break;
                // Выводим статистику по месячным отчетам, если они были загружены
                case 4:
                    if (checkMonthReportsLoaded(monthlyReport)) {
                        reportEngine.printMonthStatistics(monthlyReport.monthReports);
                    }
                    break;
                // Выводим статистику по месячным отчетам, если они были загружены
                case 5:
                    if (checkYearReportLoaded(yearlyReport)) {
                        reportEngine.printAnnualStatistics(yearlyReport.yearOfReport, yearlyReport.profitLossMap, yearlyReport.yearlyList);
                    }
                    break;
                // Выход из программы
                case 0:
                    return;
                default:
                    System.out.println("Нет такой команды!");
                    break;
            }
        }
    }

    // Выводим меню действий для пользователя
    public static void printMenu() {
        System.out.println("\nВыберите действие:");
        System.out.println("1 - Считать месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверка отчётов");
        System.out.println("4 - Информация о всех месячных отчётах");
        System.out.println("5 - Информация о годовом отчёте");
        System.out.println("0 - Выйти");
    }

    // Проверка года на валидность
    static String yearCheck(String userInputYear) {
        int year = strCheck(userInputYear);
        if (year > 1999 && year < 2099) {
            return "." + year;
        } else {
            return "Ошибка";
        }
    }

    // Проверка ввода пользователя на валидность (число или нет)
    public static int strCheck(String userInput) {
        String str = userInput.trim();
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return 0;
            }
        }
        return Integer.parseInt(str);
    }

    // Проверка, загружен ли годовой отчет
    public static boolean checkYearReportLoaded(YearlyReport yearlyReport) {
        if (yearlyReport.yearlyList.isEmpty()) {
            System.out.println("Необходимо считать годовой отчёт!");
            return false;
        }
        return true;
    }

    // Проверка, загружены ли месячные отчеты
    public static boolean checkMonthReportsLoaded(MonthlyReport monthlyReport) {
        if (monthlyReport.monthReports.isEmpty()) {
            System.out.println("Необходимо считать месячные отчёты!");
            return false;
        }
        return true;
    }
}