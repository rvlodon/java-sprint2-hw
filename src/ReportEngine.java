import java.util.*;

public class ReportEngine {

    // Конструктор, принимающий экземпляр YearlyReport
    YearlyReport yearlyReport;
    public ReportEngine() {
        this.yearlyReport = yearlyReport;
    }

    public HashMap<Integer, Double> monthlyReportsToProfitLoss(HashMap<Integer, ArrayList<String>> monthlyReports) {
        HashMap<Integer, Double> result = new HashMap<>();
        for (int i = 1; i <= monthlyReports.size(); i++) {
            double sum = 0;
            if (monthlyReports.get(i).isEmpty()) {
                result.put(i, sum);
                continue;
            }
            for (int j = 0; j < monthlyReports.get(i).size(); j++) {
                String[] arr = monthlyReports.get(i).get(j).split(",");

                // Переменная product хранит название продукта
                String product = arr[0];

                // Переменная isLoss хранит значение true, если продажа убыточна, и false, если нет
                boolean isLoss = Boolean.parseBoolean(arr[1]);

                // Переменная quantity хранит количество продукта
                double quantity = Double.parseDouble(arr[2]);

                // Переменная price хранит цену продукта
                double price = Double.parseDouble(arr[3]);

                if (isLoss) {
                    sum -= (quantity * price);
                } else {
                    sum += (quantity * price);
                }
            }
            result.put(i, sum);
        }
        return result;
    }

    public HashMap<Integer, Double> yearlyListToProfitLoss(ArrayList<String> yearlyList) {
        HashMap<Integer, Double> yearlyMap = new HashMap<>();
        int num = 1;
        double sum = 0;
        for (int i = 0; i < yearlyList.size() - 1; i += 2) {
            String[] arr1 = yearlyList.get(i).split(",");
            if (Boolean.parseBoolean(arr1[2])) {
                sum -= Double.parseDouble(arr1[1]);
            } else {
                sum += Double.parseDouble(arr1[1]);
            }
            String[] arr2 = yearlyList.get(i + 1).split(",");
            if (Boolean.parseBoolean(arr2[2])) {
                sum -= Double.parseDouble(arr2[1]);
            } else {
                sum += Double.parseDouble(arr2[1]);
            }
            yearlyMap.put(num, sum);
            sum = 0;
            num++;
        }
        return yearlyMap;
    }

    public void verificationReports(YearlyReport yearlyReport, MonthlyReport monthlyReport) {
        if (!YearlyReport.checkYearReportLoaded(yearlyReport) && !MonthlyReport.checkMonthReportsLoaded(monthlyReport)) {
            return;
        }

        HashMap<Integer, Double> yearMap = yearlyReport.profitLossMap;
        HashMap<Integer, Double> monthMap = monthlyReport.monthlyProfitAndLoss;

        if (yearMap == null || monthMap == null) {
            System.out.println("Один из отчетов не инициализирован!");
            return;
        }

        boolean equal = Objects.equals(yearMap, monthMap);

        if (equal) {
            System.out.println("Сверка завершена");
        } else {
            for (int i = 1; i <= yearMap.size(); i++) {
                if (yearMap.get(i) != null && !yearMap.get(i).equals(monthMap.get(i))) {
                    System.out.println("Найдено расхождение в месяце " + monthName(i) + "!");
                    System.out.println("Сумма в годовом отчёте ---> " + yearMap.get(i));
                    System.out.println((monthMap.get(i) == null) ? ("Отчет за " + i + " месяц не считан!") : ("Сумма в месячном отчёте: " + monthMap.get(i)));
                }
            }
        }
    }

    String monthName(int month) {
        switch (month) {
            case 1:
                return "январь";
            case 2:
                return "февраль";
            case 3:
                return "март";
            case 4:
                return "апрель";
            case 5:
                return "май";
            case 6:
                return "июнь";
            case 7:
                return "июль";
            case 8:
                return "август";
            case 9:
                return "сентябрь";
            case 10:
                return "октябрь";
            case 11:
                return "ноябрь";
            case 12:
                return "декабрь";
            default:
                return ("[" + month + "] - Некорректный номер месяца");
        }
    }
}