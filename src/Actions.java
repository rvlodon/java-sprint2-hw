import java.util.*;

public class Actions {

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
            int inputCommand = Validator.strCheck(commandInput);
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
                    reportEngine.verificationReports(yearlyReport, monthlyReport);
                    break;
                // Выводим статистику по месячным отчетам, если они были загружены
                case 4:
                    monthlyReport.printMonthStatistics(monthlyReport);
                    break;
                // Выводим статистику по годовым отчетам, если они были загружены
                case 5:
                    yearlyReport.printAnnualStatistics(yearlyReport.yearOfReport, yearlyReport.profitLossMap, yearlyReport.yearlyList);
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
}