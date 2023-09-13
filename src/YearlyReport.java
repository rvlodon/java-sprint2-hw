import java.util.*;

public class YearlyReport {

    YearlyReport yearlyReport;
    ReportEngine reportEngine = new ReportEngine();
    int yearOfReport;
    ArrayList<String> yearlyList;
    HashMap<Integer, Double> profitLossMap;
    YearlyReport() {
        yearlyList = new ArrayList<>();
    }

    // Загрузка годового отчета
    public void loadYearlyReport(Scanner sc, FileReader fileReader, ReportEngine reportEngine) {
        System.out.println("За какой год считать годовой отчет?\nВведите год в формате \"YYYY\"");
        String userInputYear = sc.nextLine();
        String yearReportName = Validator.yearCheck(userInputYear);
        if (yearReportName.equals("ERROR")) {
            System.out.println("[" + userInputYear + "] - неверный формат года!");
        } else {
            String fileName = "y" + yearReportName + ".csv";
            ArrayList<String> list = fileReader.readFileContents(fileName);
            if (list.isEmpty()) {
                System.out.println("Попробуйте снова!");
            } else {
                yearlyList = list;
                yearlyList.remove(0);
                System.out.println("Отчет за " + userInputYear + " год считан и сохранен.");
                profitLossMap = reportEngine.yearlyListToProfitLoss(yearlyList);
                yearOfReport = Validator.strCheck(userInputYear);
            }
        }
    }

    // Проверка, загружен ли годовой отчет
    public static boolean checkYearReportLoaded(YearlyReport yearlyReport) {
        if (yearlyReport == null || yearlyReport.yearlyList == null || yearlyReport.yearlyList.isEmpty()) {
            System.out.println("Необходимо считать годовой отчёт!");
            return false;
        }
        return true;
    }

    public void printAnnualStatistics(int yearOfReport, HashMap<Integer, Double> yearMap, ArrayList<String> yearlyList) {
        if (!YearlyReport.checkYearReportLoaded(yearlyReport)) return;
        System.out.println("Статистика отчёта за " + yearOfReport + " год:");
        for (int i = 1; i <= yearMap.size(); i++) {
            System.out.println("Прибыль / убыток за " + reportEngine.monthName(i) + " " + yearOfReport + " года: " + yearMap.get(i));
        }
        int monthQuantity = yearlyList.size();
        double income = 0;
        double expenses = 0;
        for (int i = 0; i < monthQuantity; i++) {
            String[] arr = yearlyList.get(i).split(",");
            if (Boolean.parseBoolean(arr[2])) {
                expenses += Double.parseDouble(arr[1]);
            } else {
                income += Double.parseDouble(arr[1]);
            }
        }
        System.out.println("Средний расход за все имеющиеся операции в " + yearOfReport + " году: " + (expenses / monthQuantity));
        System.out.println("Средний доход за все имеющиеся операции в " + yearOfReport + " году: " + (income / monthQuantity));
    }
}