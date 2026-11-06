import java.util.Scanner;

/**
 * Класс Validation отвечает за проверку корректности введённых пользователем данных.
 * Реализует:
 * - ТЗ 2: Проверка данных (пустое значение, недопустимое число -> сообщение об ошибке).
 * - ТЗ 19: Обработка ошибок ввода и повторный запрос данных.
 */
public class Validation {

    /**
     * ТЗ 2: Считывание непустой строки.
     * Если пользователь вводит пустую строку или одни пробелы, выводится ошибка и запрос повторяется.
     */
    public static String readNonEmptyString(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                return "";
            }
            input = scanner.nextLine().trim().replace("\uFEFF", ""); // Удаляем BOM если есть

            // Проверка: пустое ли значение введено
            if (input.isEmpty()) {
                System.out.println("[ОШИБКА]: Поле не может быть пустым! Пожалуйста, введите значение.");
            } else {
                return input; // Данные корректны
            }
        }
    }

    /**
     * ТЗ 2 и 19: Считывание целого числа в заданном диапазоне [min, max].
     * Защищает от ввода букв вместо чисел и чисел вне диапазона.
     */
    public static int readIntInRange(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                return min;
            }
            String input = scanner.nextLine().trim().replace("\uFEFF", "");

            // Проверка на пустоту
            if (input.isEmpty()) {
                System.out.println("[ОШИБКА]: Вы ничего не ввели! Введите число от " + min + " до " + max + ".");
                continue;
            }

            try {
                int value = Integer.parseInt(input);
                // Проверка допустимого диапазона чисел
                if (value < min || value > max) {
                    System.out.println("[ОШИБКА]: Число вне диапазона! Допустимо от " + min + " до " + max + ".");
                } else {
                    return value; // Число корректно
                }
            } catch (NumberFormatException e) {
                // Если пользователь ввёл буквы или спецсимволы
                System.out.println("[ОШИБКА]: Некорректный формат! Нужно ввести целое число.");
            }
        }
    }

    /**
     * ТЗ 2: Считывание положительного вещественного числа (например, суммы денег, бюджета).
     */
    public static double readPositiveDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                return 0.0;
            }
            String input = scanner.nextLine().trim().replace(',', '.').replace("\uFEFF", ""); // Поддержка запятой

            if (input.isEmpty()) {
                System.out.println("[ОШИБКА]: Сумма не может быть пустой!");
                continue;
            }

            try {
                double value = Double.parseDouble(input);
                if (value <= 0) {
                    System.out.println("[ОШИБКА]: Число должно быть строго больше нуля!");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("[ОШИБКА]: Введите корректное число (например: 1500 или 2500.50).");
            }
        }
    }

    /**
     * Считывание ответа Да/Нет (1 - Да, 2 - Нет).
     */
    public static boolean readBoolean(Scanner scanner, String prompt) {
        System.out.println(prompt);
        System.out.println("  1. Да");
        System.out.println("  2. Нет");
        int choice = readIntInRange(scanner, "Ваш выбор (1 или 2): ", 1, 2);
        return choice == 1;
    }
}
