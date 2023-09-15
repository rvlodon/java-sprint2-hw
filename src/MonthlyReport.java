import java.util.*;

public class MonthlyReport {

    HashMap<Integer, Double> monthlyProfitAndLoss;
    HashMap<Integer, ArrayList<String>> monthReports;
    ReportEngine reportEngine = new ReportEngine();
    int yearOfMonthlyReport;

    MonthlyReport() {
        monthReports = new HashMap<>();
    }

    // Загрузка месячных отчетов
    public void loadMonthlyReports(Scanner sc, FileReader fileReader, ReportEngine reportEngine) {
        System.out.println("За какой год считать месячные отчеты?\nВведите год в формате \"YYYY\"");
        String inputYear = sc.nextLine();
        String yearReportName = Validator.yearCheck(inputYear);
        if (yearReportName.equals("ERROR")) {
            System.out.println("[" + inputYear + "] - неверный формат года!");
        } else {
            System.out.println("За сколько месяцев вы хотите считать отчёты?");
            String userInputQuantity = sc.nextLine();
            int quantity = Validator.strCheck(userInputQuantity);
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
                yearOfMonthlyReport = Validator.strCheck(inputYear);
                System.out.println(count + " отчетов считаны и сохранены!");
            }
        }
    }

    // Проверка, загружены ли месячные отчеты
    public static boolean checkMonthReportsLoaded(MonthlyReport monthlyReport) {
        if (monthlyReport.monthReports.isEmpty()) {
            System.out.println("Необходимо считать месячные отчёты!");
            return false;
        }
        return true;
    }

    void printMonthStatistics(MonthlyReport monthlyReport) {
        if (!MonthlyReport.checkMonthReportsLoaded(monthlyReport)) return;

        for (int i = 1; i <= monthlyReport.monthReports.size(); i++) {
            System.out.println("Статистика за " + reportEngine.monthName(i) + ":");
            if (monthlyReport.monthReports.get(i).isEmpty()) {
                System.out.println("Отчет за " + reportEngine.monthName(i) + " не считан");
                continue;
            }
            String maxExpenseArticle = "";
            double maxExpense = 0;
            String maxProfitArticle = "";
            double maxProfit = 0;
            for (int j = 0; j < monthlyReport.monthReports.get(i).size(); j++) {
                String[] arr = monthlyReport.monthReports.get(i).get(j).split(",");
                if (Boolean.parseBoolean(arr[1])) {
                    if (Integer.parseInt(arr[2]) * Double.parseDouble(arr[3]) > maxExpense) {
                        maxExpense = (Integer.parseInt(arr[2]) * Double.parseDouble(arr[3])); //вернул прошлую логику обратно, что то меня переклинило в последний раз
                        maxExpenseArticle = arr[0];
                    }
                } else {
                    if (Integer.parseInt(arr[2]) * Double.parseDouble(arr[3]) > maxProfit) {
                        maxProfit = (Integer.parseInt(arr[2]) * Double.parseDouble(arr[3]));
                        maxProfitArticle = arr[0];
                    }
                }
            }
            System.out.println("Самый прибыльный товар - \"" + maxProfitArticle + "\" на сумму: " + maxProfit);
            System.out.println("Самая большая трата - \"" + maxExpenseArticle + "\" на сумму: " + maxExpense);
        }
    }
}