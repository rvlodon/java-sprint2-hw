import java.util.*;

public class ReportEngine {

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

    public void verificationReports(HashMap<Integer, Double> yearMap, HashMap<Integer, Double> monthMap) {
        boolean equal = Objects.equals(yearMap, monthMap);
        if (equal) {
            System.out.println("Сверка завершена");
        } else {
            for (int i = 1; i <= yearMap.size(); i++) {
                if (!yearMap.get(i).equals(monthMap.get(i))) {
                    System.out.println("Найдено расхождение в месяце " + monthName(i) + "!");
                    System.out.println("Сумма в годовом отчёте ---> " + yearMap.get(i));
                    System.out.println((monthMap.get(i) == null) ? ("Отчет за " + i + " месяц не считан!") : ("Сумма в месячном отчёте: " + monthMap.get(i)));
                }
            }
        }
    }

    public void printAnnualStatistics(int yearOfReport, HashMap<Integer, Double> yearMap, ArrayList<String> yearlyList) {
        System.out.println("Статистика отчёта за " + yearOfReport + " год:");
        for (int i = 1; i <= yearMap.size(); i++) {
            System.out.println("Прибыль / убыток за " + monthName(i) + " " + yearOfReport + " года: " + yearMap.get(i));
        }
        int monthQuantity = yearlyList.size() - 1;
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

    public void printMonthStatistics(HashMap<Integer, ArrayList<String>> map) {
        for (int i = 1; i <= map.size(); i++) {
            System.out.println("Статистика за " + monthName(i) + ":");
            if (map.get(i).isEmpty()) {
                System.out.println("Отчет за " + monthName(i) + " не считан");
                continue;
            }
            String maxExpenseArticle = "";
            double maxExpense = 0;
            String maxProfitArticle = "";
            double maxProfit = 0;
            for (int j = 0; j < map.get(i).size(); j++) {
                String[] arr = map.get(i).get(j).split(",");
                if (Boolean.parseBoolean(arr[1])) {
                    if (Integer.parseInt(arr[2]) * Double.parseDouble(arr[3]) > maxExpense) {
                        maxExpense = (Integer.parseInt(arr[2]) * Double.parseDouble(arr[3]));
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