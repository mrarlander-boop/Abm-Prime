import java.util.Scanner;

/**
 * Класс Menu отвечает за отображение консольного интерфейса и маршрутизацию действий.
 * Реализует:
 * - ТЗ 1: Главное меню с названием системы и списком функций.
 * - ТЗ 16: Переключение разделов с помощью switch (минимум 5 разделов).
 * - ТЗ 17: Выбор категории услуги с разными возможностями.
 * - ТЗ 19: Обработка ошибок ввода и повтор действий.
 * - ТЗ 20, 21, 22, 23, 24, 25: Индивидуальный модуль заказа мода и итоговый отчёт.
 */
public class Menu {

    private final Scanner scanner;
    private final DecisionSystem decisionSystem;
    private Report lastReport; // Хранение последнего сформированного отчёта

    public Menu(Scanner scanner, DecisionSystem decisionSystem) {
        this.scanner = scanner;
        this.decisionSystem = decisionSystem;
    }

    /**
     * ТЗ 1 и ТЗ 16: Основной цикл главного меню с использованием оператора switch.
     */
    public void start() {
        boolean isRunning = true;

        while (isRunning) {
            printMainMenu();

            // ТЗ 2 и 19: Безопасный ввод пункта меню с обработкой ошибок
            int choice = Validation.readIntInRange(scanner, "Выберите пункт меню (0-7): ", 0, 7);

            // ТЗ 16: Переключение между разделами с помощью switch (7 разделов + выход)
            switch (choice) {
                case 1:
                    // Полный мастер заказа мода (объединяет ТЗ 21-25)
                    runOrderWizard();
                    break;

                case 2:
                    // Проверка профиля и условий доступа (ТЗ 3, 6, 7, 8, 9)
                    runClientProfileCheck();
                    break;

                case 3:
                    // Калькулятор тарифов и скидок (ТЗ 4, 11, 18)
                    runCostAndDiscountCalculator();
                    break;

                case 4:
                    // Проверка ТЗ и безопасность кода (ТЗ 10, 13, 14)
                    runSafetyAndFeasibilityCheck();
                    break;

                case 5:
                    // Финансовый баланс, рекомендации и сценарии (ТЗ 12, 15, 20)
                    runFinancialAndRecommendationModule();
                    break;

                case 6:
                    // Просмотр последнего итогового отчёта (ТЗ 25)
                    showLastReport();
                    break;

                case 7:
                    // Запуск встроенного веб-сервера и открытие локального сайта
                    System.out.println("\nЗапуск локального веб-сайта магазина...");
                    WebServer.startServer(true);
                    break;

                case 0:
                    // Выход из системы
                    System.out.println("\nСпасибо за использование системы ModCraft Studio. До свидания!");
                    isRunning = false;
                    break;

                default:
                    // ТЗ 19: На случай непредвиденного выбора
                    System.out.println("[ОШИБКА]: Неверный пункт. Пожалуйста, повторите выбор.");
                    break;
            }

            if (isRunning) {
                System.out.println("\nНажмите Enter, чтобы вернуться в главное меню...");
                scanner.nextLine();
            }
        }
    }

    /**
     * ТЗ 1: Отображение шапки и списка функций главного меню.
     */
    private void printMainMenu() {
        System.out.println();
        System.out.println("===============================================================");
        System.out.println("            МАГАЗИН ПО ЗАКАЗУ МОДОВ: MODCRAFT STUDIO           ");
        System.out.println("===============================================================");
        System.out.println("1. [Заказ] Оформить индивидуальный заказ модификации (Мастер)");
        System.out.println("2. [Клиент] Проверка возраста, статуса и условий доступа (&&, ||, !)");
        System.out.println("3. [Калькулятор] Расчёт стоимости услуги и скидки (Тарифы)");
        System.out.println("4. [Аудит] Проверка безопасности мода и оценка ТЗ (Вложенные if)");
        System.out.println("5. [Аналитика] Финансовый баланс, рекомендации и сценарии (switch/if)");
        System.out.println("6. [Отчёт] Посмотреть итоговый отчёт последнего заказа");
        System.out.println("7. [Веб-сайт] Запустить локальный сайт в браузере (http://localhost:8088)");
        System.out.println("0. [Выход] Завершить работу программы");
        System.out.println("===============================================================");
    }

    /**
     * ТЗ 17: Выбор категории услуги со специальными возможностями через switch.
     */
    private String selectServiceCategory() {
        System.out.println("\n--- ВЫБОР КАТЕГОРИИ УСЛУГИ (ТЗ 17) ---");
        System.out.println("1. Текстуры и 3D-модели (Графический пак, ремоделинг мобов)");
        System.out.println("2. Скрипты и новый геймплей (Новые механики, квесты, способности)");
        System.out.println("3. Глобальная конверсия (Полное преображение игры, сюжетная ветка)");
        System.out.println("4. Оптимизация и устранение багов (Повышение FPS, фикс вылетов)");
        System.out.println("5. Портирование мода (Перенос на новую версию игры или другой загрузчик)");

        int catChoice = Validation.readIntInRange(scanner, "Выберите категорию (1-5): ", 1, 5);

        // ТЗ 17: В зависимости от категории отображаются разные возможности
        switch (catChoice) {
            case 1:
                System.out.println("[Инфо]: Включена работа с Blender, Blockbench и Photoshop.");
                return "Текстуры и 3D-модели";
            case 2:
                System.out.println("[Инфо]: Включено написание скриптов на Java/C#/Lua.");
                return "Скрипты и геймплей";
            case 3:
                System.out.println("[Инфо]: Выделяется команда из 3 специалистов (код + арт + геймдизайн).");
                return "Глобальная конверсия";
            case 4:
                System.out.println("[Инфо]: Профилирование памяти, оптимизация рендеринга и многопоточности.");
                return "Оптимизация и фиксы";
            case 5:
                System.out.println("[Инфо]: Адаптация под современные API и новые версии движка.");
                return "Портирование мода";
            default:
                return "Стандартная разработка";
        }
    }

    /**
     * ТЗ 21-25: Полный мастер оформления заказа мода.
     */
    private void runOrderWizard() {
        System.out.println("\n===============================================================");
        System.out.println("          МАСТЕР ОФОРМЛЕНИЯ ЗАКАЗА МОДИФИКАЦИИ (ТЗ 21-25)      ");
        System.out.println("===============================================================");

        // Сбор данных о клиенте
        String name = Validation.readNonEmptyString(scanner, "Введите ваше имя / никнейм: ");
        int age = Validation.readIntInRange(scanner, "Введите ваш возраст (6-100): ", 6, 100);
        String ageCategory = decisionSystem.checkAgeCategory(age); // ТЗ 6
        System.out.println("-> " + ageCategory);

        System.out.println("\nВыберите текущий статус вашего аккаунта:");
        System.out.println("1. Активный клиент");
        System.out.println("2. Неактивный клиент");
        System.out.println("3. Заблокированный клиент");
        int statusChoice = Validation.readIntInRange(scanner, "Статус (1-3): ", 1, 3);
        String userStatus = decisionSystem.determineUserStatus(statusChoice); // ТЗ 3
        boolean isBlocked = (statusChoice == 3);

        // ТЗ 9: Проверка оператора ! (пользователь не заблокирован)
        if (!decisionSystem.canPlaceOrder(isBlocked)) {
            System.out.println("\n[ОТКАЗ СИСТЕМЫ]: Заблокированные пользователи не могут создавать новые заказы!");
            System.out.println("Пожалуйста, обратитесь в службу поддержки для снятия блокировки.");
            return;
        }

        // ТЗ 8: Проверка VIP статуса (||)
        double totalSpent = Validation.readPositiveDouble(scanner, "Сколько рублей вы потратили в нашем магазине ранее?: ");
        int prevOrders = Validation.readIntInRange(scanner, "Сколько заказов вы уже выполнили у нас?: ", 0, 100);
        boolean isVip = decisionSystem.isEligibleForVipStatus(totalSpent, prevOrders); // ТЗ 8
        if (isVip) {
            System.out.println("-> Поздравляем! Вам присвоен VIP-статус заказчика.");
        } else {
            System.out.println("-> Статус: Стандартный клиент.");
        }

        // ТЗ 17: Выбор категории услуги
        String category = selectServiceCategory();

        // ТЗ 21 и 22: Ввод 5 параметров для предметной области модов
        System.out.println("\n--- ПАРАМЕТРЫ ПРОЕКТА МОДА (ТЗ 21, 22) ---");
        System.out.println("Выберите игровой движок / игру:");
        System.out.println("  1. Minecraft (Java / Fabric / NeoForge)");
        System.out.println("  2. GTA / Skyrim (ScriptHook / C++ / Papyrus)");
        System.out.println("  3. Unity / Unreal Engine (C# / C++ / Blueprints)");
        int engineType = Validation.readIntInRange(scanner, "Движок (1-3): ", 1, 3);

        int scriptsCount = Validation.readIntInRange(scanner, "Количество скриптов/механик (1-50): ", 1, 50);
        int modelsCount = Validation.readIntInRange(scanner, "Количество 3D-моделей/текстур (0-100): ", 0, 100);
        int deadlineDays = Validation.readIntInRange(scanner, "Желаемый срок выполнения в днях (1-60): ", 1, 60);
        double budget = Validation.readPositiveDouble(scanner, "Ваш максимальный бюджет (руб.): ");

        // ТЗ 4: Проверка числового показателя бюджета
        System.out.println("-> " + decisionSystem.evaluateBudgetTier(budget));

        // Дополнительные опции
        boolean withSourceCode = Validation.readBoolean(scanner, "Требуется ли передача исходного кода проекта?");
        boolean hasMultiplayer = Validation.readBoolean(scanner, "Нужна ли поддержка сетевой игры (мультиплеер)?");
        boolean isExpress = Validation.readBoolean(scanner, "Требуется ли экспресс-разработка в приоритетном порядке?");

        // ТЗ 7: Проверка нескольких условий одновременно через &&
        if (isExpress) {
            boolean expressAllowed = decisionSystem.canOrderExpressMod(age, budget);
            if (!expressAllowed) {
                System.out.println("[ВНИМАНИЕ]: Экспресс-заказ доступен только клиентам от 18 лет с бюджетом от 3000 руб.");
                System.out.println("Опция 'Экспресс' автоматически отключена для данного заказа.");
                isExpress = false;
            } else {
                System.out.println("-> Опция 'Экспресс-заказ' подтверждена!");
            }
        }

        // ТЗ 23: Определение уровня сложности (3 уровня)
        String complexityLevel = decisionSystem.determineModComplexityLevel(engineType, scriptsCount, modelsCount, deadlineDays, budget);
        System.out.println("-> Сложность проекта: " + complexityLevel);

        // ТЗ 18: Расчет стоимости услуги по тарифу и опциям
        int tariff = (budget < 2500) ? 1 : ((budget <= 6000) ? 2 : 3);
        double calculatedCost = decisionSystem.calculateServiceCost(tariff, withSourceCode, isExpress, hasMultiplayer);

        // ТЗ 11: Расчет скидки
        String clientStatusStr = isVip ? "VIP" : (prevOrders > 0 ? "REGULAR" : "NEW");
        int discountPercent = decisionSystem.calculateDiscountPercent(calculatedCost, clientStatusStr);
        double finalPrice = calculatedCost * (1.0 - (discountPercent / 100.0));

        // ТЗ 12: Финансовое состояние
        String financialStatus = decisionSystem.analyzeFinancialState(budget, finalPrice);

        // ТЗ 13: Оценка качества ТЗ мода
        int specScore = 50 + (scriptsCount > 5 ? 20 : 0) + (deadlineDays >= 7 ? 20 : 0);
        String specResult = decisionSystem.evaluateSpecificationScore(specScore);

        // ТЗ 14: Проверка безопасности мода
        String safetyLevel = decisionSystem.evaluateSafetyRisk(false, false, engineType == 3);

        // ТЗ 10: Проверка осуществимости проекта (3 уровня вложенных if)
        boolean hasFreeDev = true; // Свободный кодер есть
        boolean engineOk = true;   // Движок поддерживается
        String feasibilityResult = decisionSystem.evaluateOrderFeasibility(hasFreeDev, engineOk, deadlineDays);

        // ТЗ 24: Персонализированные рекомендации
        String recommendations = decisionSystem.getPersonalizedRecommendation(complexityLevel, engineType, hasMultiplayer);

        // ТЗ 25: Формирование и сохранение итогового отчёта
        lastReport = new Report(
                name, age, ageCategory, userStatus, isVip,
                category, budget, calculatedCost, discountPercent,
                finalPrice, financialStatus, safetyLevel,
                specResult, complexityLevel, recommendations,
                feasibilityResult
        );

        // Вывод отчёта на экран
        lastReport.printReport();
    }

    /**
     * Раздел 2: Проверка условий профиля (ТЗ 3, 6, 7, 8, 9).
     */
    private void runClientProfileCheck() {
        System.out.println("\n--- РАЗДЕЛ 2: ПРОВЕРКА ПРОФИЛЯ И УСЛОВИЙ ДОСТУПА ---");
        int age = Validation.readIntInRange(scanner, "Введите возраст клиента: ", 1, 120);

        // ТЗ 6: Возрастные категории (if - else if)
        System.out.println("Результат проверки возраста (ТЗ 6):");
        System.out.println("  " + decisionSystem.checkAgeCategory(age));

        // ТЗ 3: Статус клиента (if-else)
        System.out.println("\nВыберите статус клиента для теста (ТЗ 3):");
        System.out.println("  1. Активный | 2. Неактивный | 3. Заблокированный");
        int st = Validation.readIntInRange(scanner, "Выбор (1-3): ", 1, 3);
        System.out.println("  Статус: " + decisionSystem.determineUserStatus(st));

        // ТЗ 9: Проверка оператора отрицания !
        boolean isBlocked = (st == 3);
        System.out.println("\nПроверка оператора отрицания '!' (ТЗ 9):");
        if (!decisionSystem.canPlaceOrder(isBlocked)) {
            System.out.println("  Клиент заблокирован (!isBlocked == false) -> Заказы ЗАПРЕЩЕНЫ.");
        } else {
            System.out.println("  Клиент не заблокирован (!isBlocked == true) -> Заказы РАЗРЕШЕНЫ.");
        }

        // ТЗ 7: Проверка нескольких условий через &&
        System.out.println("\nПроверка совместных условий через '&&' (ТЗ 7 - Экспресс заказ):");
        double budget = Validation.readPositiveDouble(scanner, "Введите бюджет для проверки: ");
        boolean canExpress = decisionSystem.canOrderExpressMod(age, budget);
        System.out.println("  Условие (возраст >= 18 && бюджет >= 3000): " +
                (canExpress ? "ВЫПОЛНЕНО (Экспресс разрешён)" : "НЕ ВЫПОЛНЕНО (Отказано в экспрессе)"));

        // ТЗ 8: Проверка условий через ||
        System.out.println("\nПроверка альтернативных условий через '||' (ТЗ 8 - VIP-клуб):");
        double spent = Validation.readPositiveDouble(scanner, "Сумма прошлых покупок: ");
        int orders = Validation.readIntInRange(scanner, "Количество завершенных заказов: ", 0, 100);
        boolean isVip = decisionSystem.isEligibleForVipStatus(spent, orders);
        System.out.println("  Условие (потрачено >= 10000 || заказов >= 5): " +
                (isVip ? "ВЫПОЛНЕНО (Клиент VIP)" : "НЕ ВЫПОЛНЕНО (Обычный клиент)"));
    }

    /**
     * Раздел 3: Калькулятор стоимости и расчет скидок (ТЗ 4, 11, 18).
     */
    private void runCostAndDiscountCalculator() {
        System.out.println("\n--- РАЗДЕЛ 3: КАЛЬКУЛЯТОР ТАРИФОВ И СКИДОК ---");

        // ТЗ 4: Проверка числового показателя
        double testBudget = Validation.readPositiveDouble(scanner, "Введите ориентировочный бюджет (руб.): ");
        System.out.println("Классификация бюджета (ТЗ 4): " + decisionSystem.evaluateBudgetTier(testBudget));

        // ТЗ 18: Расчет по тарифу
        System.out.println("\nВыберите базовый тариф разработки (ТЗ 18):");
        System.out.println("  1. Базовый (1500 руб.)");
        System.out.println("  2. Стандарт (3800 руб.)");
        System.out.println("  3. Эксперт (7500 руб.)");
        int tariff = Validation.readIntInRange(scanner, "Тариф (1-3): ", 1, 3);

        boolean source = Validation.readBoolean(scanner, "Включить исходный код (+25%)?");
        boolean express = Validation.readBoolean(scanner, "Срочный дедлайн (+40%)?");
        boolean mp = Validation.readBoolean(scanner, "Сетевая синхронизация (+30%)?");

        double rawCost = decisionSystem.calculateServiceCost(tariff, source, express, mp);
        System.out.println("Базовая стоимость услуг: " + rawCost + " руб.");

        // ТЗ 11: Расчет скидки
        System.out.println("\nВыберите статус клиента для скидки (ТЗ 11):");
        System.out.println("  1. VIP | 2. REGULAR | 3. NEW");
        int statusOption = Validation.readIntInRange(scanner, "Выбор (1-3): ", 1, 3);
        String statusStr = (statusOption == 1) ? "VIP" : (statusOption == 2 ? "REGULAR" : "NEW");

        int discount = decisionSystem.calculateDiscountPercent(rawCost, statusStr);
        double finalPrice = rawCost * (1.0 - (discount / 100.0));

        System.out.println("Размер скидки: " + discount + "%");
        System.out.println("ИТОГОВАЯ СТОИМОСТЬ К ОПЛАТЕ: " + finalPrice + " руб.");
    }

    /**
     * Раздел 4: Аудит безопасности и анализ ТЗ (ТЗ 10, 13, 14).
     */
    private void runSafetyAndFeasibilityCheck() {
        System.out.println("\n--- РАЗДЕЛ 4: АУДИТ БЕЗОПАСНОСТИ И ОЦЕНКА ТЗ ---");

        // ТЗ 13: Оценка баллов ТЗ
        int score = Validation.readIntInRange(scanner, "Введите оценку ТЗ в баллах (0-100): ", 0, 100);
        System.out.println(decisionSystem.evaluateSpecificationScore(score));

        // ТЗ 14: Проверка уровня риска безопасности
        System.out.println("\nПараметры безопасности мода (ТЗ 14):");
        boolean cheats = Validation.readBoolean(scanner, "Содержит ли мод читы / аимбот / спидхак?");
        boolean sysFiles = Validation.readBoolean(scanner, "Модифицирует ли мод системные файлы ОС?");
        boolean dlls = Validation.readBoolean(scanner, "Использует ли мод внешние DLL библиотеки?");

        String safety = decisionSystem.evaluateSafetyRisk(cheats, sysFiles, dlls);
        System.out.println(safety);

        // ТЗ 10: Вложенные if (3 уровня проверок)
        System.out.println("\nПроверка выполнимости заказа по 3 уровням вложенных if (ТЗ 10):");
        boolean freeDev = Validation.readBoolean(scanner, "Есть ли свободный разработчик?");
        boolean engineOk = Validation.readBoolean(scanner, "Поддерживается ли версия движка игры?");
        int days = Validation.readIntInRange(scanner, "Количество дней на работу: ", 1, 30);

        String feasibility = decisionSystem.evaluateOrderFeasibility(freeDev, engineOk, days);
        System.out.println("Результат: " + feasibility);
    }

    /**
     * Раздел 5: Финансовый анализ, рекомендации и сложные сценарии (ТЗ 12, 15, 20).
     */
    private void runFinancialAndRecommendationModule() {
        System.out.println("\n--- РАЗДЕЛ 5: ФИНАНСОВЫЙ АНАЛИЗ И СЛОЖНЫЕ СЦЕНАРИИ ---");

        // ТЗ 12: Финансовое состояние (доходы vs расходы)
        System.out.println("1. Анализ баланса проекта (ТЗ 12):");
        double clientBudget = Validation.readPositiveDouble(scanner, "Бюджет заказчика (доход студии): ");
        double devCost = Validation.readPositiveDouble(scanner, "Себестоимость разработки (расход студии): ");
        System.out.println(decisionSystem.analyzeFinancialState(clientBudget, devCost));

        // ТЗ 15: Рекомендации по жанру и опыту
        System.out.println("\n2. Подбор рекомендаций по модификациям (ТЗ 15):");
        System.out.println("Выберите жанр игры:");
        System.out.println("  1. RPG (Сюжет, диалоги, прокачка)");
        System.out.println("  2. Песочница (Строительство, автоматизация)");
        System.out.println("  3. Шутер / Экшен (Стрельба, оружие, физика)");
        int genre = Validation.readIntInRange(scanner, "Жанр (1-3): ", 1, 3);

        System.out.println("Ваш опыт в игре:");
        System.out.println("  1. Новичок | 2. Опытный игрок");
        int exp = Validation.readIntInRange(scanner, "Опыт (1-2): ", 1, 2);
        System.out.println(decisionSystem.recommendModType(genre, exp));

        // ТЗ 20: Комбинированная система switch, if-else, &&, ||, ! (5 сценариев)
        System.out.println("\n3. Комплексный сценарий утверждения проекта (ТЗ 20):");
        int cat = Validation.readIntInRange(scanner, "Категория мода (1-5): ", 1, 5);
        int days = Validation.readIntInRange(scanner, "Срок в днях: ", 1, 60);
        double bg = Validation.readPositiveDouble(scanner, "Бюджет заказа: ");
        boolean texturesReady = Validation.readBoolean(scanner, "Готовы ли текстуры заранее?");
        boolean blocked = Validation.readBoolean(scanner, "Клиент находится в чёрном списке?");

        String verdict = decisionSystem.processComplexModApproval(cat, days, bg, texturesReady, blocked);
        System.out.println("Вердикт системы: " + verdict);
    }

    /**
     * Раздел 6: Показ последнего сформированного отчёта (ТЗ 25).
     */
    private void showLastReport() {
        if (lastReport == null) {
            System.out.println("\n[ИНФО]: Заказов пока не было оформлено.");
            System.out.println("Выберите пункт 1 в главном меню, чтобы оформить новый заказ мода!");
        } else {
            lastReport.printReport();
        }
    }
}
