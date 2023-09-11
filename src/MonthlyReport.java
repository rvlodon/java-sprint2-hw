import java.util.*;

public class MonthlyReport {

    HashMap<Integer, Double> monthlyProfitAndLoss;
    HashMap<Integer, ArrayList<String>> monthReports;
    int yearOfMonthlyReport;

    MonthlyReport() {
        monthReports = new HashMap<>();
    }

    // Загрузка месячных отчетов
    public void loadMonthlyReports(Scanner sc, FileReader fileReader, ReportEngine reportEngine) {
        System.out.println("За какой год считать месячные отчеты?\nВведите год в формате \"YYYY\"");
        String inputYear = sc.nextLine();
        String yearReportName = Actoins.yearCheck(inputYear);
        if (yearReportName.equals("ERROR")) {
            System.out.println("[" + inputYear + "] - неверный формат года!");
        } else {
            System.out.println("За сколько месяцев вы хотите считать отчёты?");
            String userInputQuantity = sc.nextLine();
            int quantity = Actoins.strCheck(userInputQuantity);
            if (quantity < 1 || quantity > 12) {
                System.out.println("[" + userInputQuantity + "] - Неверное количество отчетов");
            } else {
                int count = 0;
                for (int i = 1; i <= quantity; i++) {
                    String fileName = String.format("m%s%d%d.csv", yearReportName, 0, i);
                    ArrayList<String> list = fileReader.readFileContents(fileName);
                    if (list.isEmpty()) {
                        System.out.println("Попробуйте снова!");
                        monthReports.put(i, list);
                    } else {
                        list.remove(0);
                        monthReports.put(i, list);
                        count++;
                        System.out.println("Отчет за " + reportEngine.monthName(i) + " успешно считан и сохранен!");
                    }
                }
                monthlyProfitAndLoss = reportEngine.monthlyReportsToProfitLoss(monthReports);
                yearOfMonthlyReport = Actoins.strCheck(inputYear);
                System.out.println(count + " отчетов считаны и сохранены!");
            }
        }
    }
}