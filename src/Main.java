import java.util.Scanner;

/**
 * Главный класс приложения (точка входа).
 * Реализует:
 * - ТЗ 1: Запуск программы, приветствие и инициализация главного меню.
 * - ТЗ 5: Проверка доступа (ввод логина и пароля с валидацией).
 */
public class Main {

    // Тестовые учётные данные для авторизации (ТЗ 5)
    private static final String CORRECT_LOGIN = "admin";
    private static final String CORRECT_PASSWORD = "123";

    public static void main(String[] args) {
        // Подсказка: используем стандартный Scanner для считывания ввода из консоли
        Scanner scanner = new Scanner(System.in);

        System.out.println("===============================================================");
        System.out.println("              MODCRAFT STUDIO: SMART DECISION                  ");
        System.out.println("         Система автоматизации заказа игровых модов            ");
        System.out.println("===============================================================");
        System.out.println("Программа успешно запущена!");
        System.out.println();

        // =========================================================================
        // ТЗ 5: Добавить проверку доступа (логин и пароль)
        // =========================================================================
        boolean isAuthenticated = false;
        int loginAttempts = 0;
        final int MAX_ATTEMPTS = 3;

        System.out.println("--- АВТОРИЗАЦИЯ В СИСТЕМЕ (ТЗ 5) ---");
        System.out.println("[Подсказка для входа]: Логин: admin | Пароль: 123");

        while (!isAuthenticated && loginAttempts < MAX_ATTEMPTS) {
            String login = Validation.readNonEmptyString(scanner, "Введите логин: ");
            String password = Validation.readNonEmptyString(scanner, "Введите пароль: ");

            // Проверка логина и пароля
            if (CORRECT_LOGIN.equals(login) && CORRECT_PASSWORD.equals(password)) {
                isAuthenticated = true;
                System.out.println("\n[УСПЕХ]: Доступ разрешён! Добро пожаловать, " + login + ".");
            } else {
                loginAttempts++;
                int remaining = MAX_ATTEMPTS - loginAttempts;
                System.out.println("[ПРЕДУПРЕЖДЕНИЕ]: Неверный логин или пароль!");
                if (remaining > 0) {
                    System.out.println("Осталось попыток: " + remaining + ". Попробуйте снова.\n");
                } else {
                    System.out.println("[ОШИБКА]: Превышено число попыток входа. Завершение работы программы.");
                }
            }
        }

        // Если авторизация пройдена успешно — открываем систему (ТЗ 1, 16)
        if (isAuthenticated) {
            DecisionSystem decisionSystem = new DecisionSystem();
            Menu menu = new Menu(scanner, decisionSystem);
            menu.start();
        }

        // Закрываем Scanner при завершении работы программы
        scanner.close();
    }
}
