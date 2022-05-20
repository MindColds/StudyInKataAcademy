import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        action = action.replace(" ", "");//удаление пробелов на случай несоблюдения правил

        String regex = "[+/\\-*]";// разделитель по оператору
        String regexArabic = "[1234567890]{1,2}[+/\\-*][1234567890]{1,2}";//образец арифметического выражения с арабскими цифрами
        String regexRoman = "[Ⅰ-Ⅹ][+/\\-*][Ⅰ-Ⅹ]";//образец арифметического выражения с римскими цифрами

        if (!(action.matches(regexArabic) || action.matches(regexRoman))) {//Проверка на соответствие образцам
            try {
                throw new Exception("""
                        При введении арифметического выражения выявлена ошибка. Напоминаю, что запрещено следующее:\s
                        -вводить в арифметическое выражение римские и арабские числа значением более десяти и менее 1;
                        -одновременно вводить в арифметическое выражение арабские и римские цифры;
                        -одновременно вводить в арифметическое выражение более одного оператора;
                        -введение более двух чисел в арифметическое выражение;
                        -введение в арифметическое выражение пробелов.""");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } if (action.matches(regexArabic)) {//исполняемый код в случае применения арабских цифр, каждая цифра на всякий случай побрита
            String[] symbols = action.split(regex);//разделение строки по символу оператора
            for (int i = 0; i < symbols.length-1; i++){
                if (Integer.parseInt(symbols[i]) > 0 && Integer.parseInt(symbols[i]) < 11 &&
                        Integer.parseInt(symbols[i+1]) > 0 && Integer.parseInt(symbols[i+1]) < 11 && action.contains("+")){
                    System.out.println(Integer.parseInt(symbols[i].trim()) + Integer.parseInt(symbols[i+1].trim()));
                }if ((Integer.parseInt(symbols[i].trim()) >= 11 || Integer.parseInt(symbols[i+1].trim()) >= 11) && action.contains("+")){
                    //System.out.println("При введении арифметического выражения выявлена ошибка - введено число более 10");
                    try {
                        throw new Exception("При введении арифметического выражения выявлена ошибка - введено число более 10");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }if(Integer.parseInt(symbols[i].trim()) > 0 && Integer.parseInt(symbols[i].trim()) <= 10 &&
                        Integer.parseInt(symbols[i+1].trim()) > 0 && Integer.parseInt(symbols[i+1].trim()) <= 10 && action.contains("*")){
                    System.out.println(Integer.parseInt(symbols[i].trim()) * Integer.parseInt(symbols[i+1].trim()));
                }if ((Integer.parseInt(symbols[i].trim()) >= 11 || Integer.parseInt(symbols[i+1].trim()) >= 11) && action.contains("*")){
                    //System.out.println("При введении арифметического выражения выявлена ошибка - введено число более 10");
                    try {
                        throw new Exception("При введении арифметического выражения выявлена ошибка - введено число более 10");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                if(Integer.parseInt(symbols[i].trim()) > 0 && Integer.parseInt(symbols[i].trim()) <= 10 &&
                        Integer.parseInt(symbols[i+1].trim()) > 0 && Integer.parseInt(symbols[i+1].trim()) <= 10 && action.contains("-")){
                    System.out.println(Integer.parseInt(symbols[i].trim()) - Integer.parseInt(symbols[i+1].trim()));
                }if ((Integer.parseInt(symbols[i].trim()) >= 11 || Integer.parseInt(symbols[i+1].trim()) >= 11) && action.contains("-")){
                    //System.out.println("При введении арифметического выражения выявлена ошибка - введено число более 10");
                    try {
                        throw new Exception("При введении арифметического выражения выявлена ошибка - введено число более 10");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                if (Integer.parseInt(symbols[i].trim()) > 0 && Integer.parseInt(symbols[i].trim()) <= 10 &&
                        Integer.parseInt(symbols[i+1].trim()) > 0 && Integer.parseInt(symbols[i+1].trim()) <= 10 && action.contains("/")){
                    System.out.println(Integer.parseInt(symbols[i].trim()) / Integer.parseInt(symbols[i+1].trim()));
                }if ((Integer.parseInt(symbols[i].trim()) >= 11 || Integer.parseInt(symbols[i+1].trim()) >= 11) && action.contains("/")){
                    //System.out.println("При введении арифметического выражения выявлена ошибка - введено число более 10");
                    try {
                        throw new Exception("При введении арифметического выражения выявлена ошибка - введено число более 10");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }scanner.close();
        if (action.matches(regexRoman)) {//исполняемый код в случае применения римских цифр, и здесь каждая цифра на всякий случай побрита

            String[] symbols = action.split(regex);
            String[] romanNumbers =  { "Ⅰ","Ⅱ","Ⅲ","Ⅳ","Ⅴ","Ⅵ","Ⅶ","Ⅷ","Ⅸ","Ⅹ","Ⅼ","Ⅽ"};

            int num = 0;

            for (String romanNumber : romanNumbers) {
                for (int j = 0; j < symbols.length - 1; j++) {
                    if (romanNumber.equals(symbols[j])) {
                        if (action.contains("+")) {
                            num += romanToArabic(symbols[j].trim()) + romanToArabic(symbols[j + 1].trim());
                        }
                        if (action.contains("*")) {
                            num += romanToArabic(symbols[j].trim()) * romanToArabic(symbols[j + 1].trim());
                        }
                        if (action.contains("-")) {
                            num += romanToArabic(symbols[j].trim()) - romanToArabic(symbols[j + 1].trim());
                        }
                        if (action.contains("/")) {
                            num += romanToArabic(symbols[j].trim()) / romanToArabic(symbols[j + 1].trim());
                        }
                    }
                }
            }

            if (num < 1){//Переработка арабской цифры в римскую, и предъявление оговоренного исключения с римскими цифрами
                //System.out.println("Результат невозможен. В Римской нумерации нет нуля и отрицательных чисел");
                try {
                    throw new Exception("Результат невозможен. В Римской нумерации нет нуля и отрицательных чисел");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }else  {num = Math.min(100, num);
                StringBuilder result = new StringBuilder();

                String[] romanNumbs = {"Ⅰ", "Ⅴ", "Ⅹ", "Ⅼ", "Ⅽ"};
                int i = 0;
                while (num > 0) {
                    switch (num % 10) {
                        case 1 -> result.insert(0, romanNumbs[i]);
                        case 2 -> result.insert(0, romanNumbs[i] + romanNumbs[i]);
                        case 3 -> result.insert(0, romanNumbs[i] + romanNumbs[i] + romanNumbs[i]);
                        case 4 -> result.insert(0, romanNumbs[i] + romanNumbs[i + 1]);
                        case 5 -> result.insert(0, romanNumbs[i + 1]);
                        case 6 -> result.insert(0, romanNumbs[i + 1] + romanNumbs[i]);
                        case 7 -> result.insert(0, romanNumbs[i + 1] + romanNumbs[i] + romanNumbs[i]);
                        case 8 -> result.insert(0, romanNumbs[i + 1] + romanNumbs[i] + romanNumbs[i] + romanNumbs[i]);
                        case 9 -> result.insert(0, romanNumbs[i] + romanNumbs[i + 2]);
                    }
                    num = num / 10;
                    i += 2;
                }
                System.out.println(result);
            }
        }scanner.close();
    }

        static int romanToArabic(String romanNumber){//"цифровизация" символов римских чисел
        return switch (romanNumber) {
            case "Ⅰ" -> 1;
            case "Ⅱ" -> 2;
            case "Ⅲ" -> 3;
            case "Ⅳ" -> 4;
            case "Ⅴ" -> 5;
            case "Ⅵ" -> 6;
            case "Ⅶ" -> 7;
            case "Ⅷ" -> 8;
            case "Ⅸ" -> 9;
            case "Ⅹ" -> 10;
            case "Ⅼ" -> 50;
            case "Ⅽ" -> 100;
            default -> -1;
        };
    }
}





