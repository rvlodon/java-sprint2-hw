public class Validator {
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
        if (str.isEmpty()) {
            return 9990; // обрабатываю ошибку пустой строки, так как ловил крэш (предполагаю, что команды 9990 никогда не будет)
        }
            for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return 0;
            }
        }
        return Integer.parseInt(str);
    }
}