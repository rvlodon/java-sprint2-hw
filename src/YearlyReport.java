import java.util.*;

public class YearlyReport {

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
        String yearReportName = Actoins.yearCheck(userInputYear);
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
                yearOfReport = Actoins.strCheck(userInputYear);
            }
        }
    }
}