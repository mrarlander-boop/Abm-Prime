/**
 * Класс Report отвечает за формирование и вывод итогового структурированного отчёта.
 * Реализует:
 * - ТЗ 25: Итоговый отчёт о принятом системой решении со всеми параметрами.
 */
public class Report {

    private String customerName;
    private int age;
    private String ageCategory;
    private String userStatus;
    private boolean isVip;
    private String selectedCategory;
    private double budget;
    private double calculatedCost;
    private int discountPercent;
    private double finalPriceWithDiscount;
    private String financialStatus;
    private String safetyLevel;
    private String specificationScoreResult;
    private String complexityLevel;
    private String recommendation;
    private String finalDecision;

    // Конструктор отчёта
    public Report(String customerName, int age, String ageCategory, String userStatus, boolean isVip,
                  String selectedCategory, double budget, double calculatedCost, int discountPercent,
                  double finalPriceWithDiscount, String financialStatus, String safetyLevel,
                  String specificationScoreResult, String complexityLevel, String recommendation,
                  String finalDecision) {
        this.customerName = customerName;
        this.age = age;
        this.ageCategory = ageCategory;
        this.userStatus = userStatus;
        this.isVip = isVip;
        this.selectedCategory = selectedCategory;
        this.budget = budget;
        this.calculatedCost = calculatedCost;
        this.discountPercent = discountPercent;
        this.finalPriceWithDiscount = finalPriceWithDiscount;
        this.financialStatus = financialStatus;
        this.safetyLevel = safetyLevel;
        this.specificationScoreResult = specificationScoreResult;
        this.complexityLevel = complexityLevel;
        this.recommendation = recommendation;
        this.finalDecision = finalDecision;
    }

    /**
     * ТЗ 25: Печать полного итогового отчёта в консоль.
     */
    public void printReport() {
        System.out.println();
        System.out.println("===============================================================");
        System.out.println("          ИТОГОВЫЙ ОТЧЁТ СИСТЕМЫ: ЗАКАЗ МОДИФИКАЦИИ            ");
        System.out.println("===============================================================");
        System.out.println(" 1. КЛИЕНТ И ДОСТУП:");
        System.out.println("    - Имя клиента: " + customerName);
        System.out.println("    - Возраст: " + age + " лет (" + ageCategory + ")");
        System.out.println("    - Статус аккаунта: " + userStatus);
        System.out.println("    - VIP-статус: " + (isVip ? "ДА (Привилегированный клиент)" : "НЕТ (Обычный клиент)"));
        System.out.println("---------------------------------------------------------------");
        System.out.println(" 2. ПАРАМЕТРЫ ЗАКАЗА И КАТЕГОРИЯ:");
        System.out.println("    - Направление: " + selectedCategory);
        System.out.println("    - Уровень сложности проекта: " + complexityLevel);
        System.out.println("    - Оценка ТЗ: " + specificationScoreResult);
        System.out.println("    - Безопасность кода: " + safetyLevel);
        System.out.println("---------------------------------------------------------------");
        System.out.println(" 3. ФИНАНСОВЫЙ РАСЧЁТ:");
        System.out.println("    - Выделенный бюджет клиента: " + budget + " руб.");
        System.out.println("    - Базовый расчёт стоимости:  " + calculatedCost + " руб.");
        System.out.println("    - Скидка:                   " + discountPercent + "%");
        System.out.println("    - ИТОГОВАЯ ЦЕНА СО СКИДКОЙ: " + finalPriceWithDiscount + " руб.");
        System.out.println("    - Финансовый баланс:        " + financialStatus);
        System.out.println("---------------------------------------------------------------");
        System.out.println(" 4. ПЕРСОНАЛИЗИРОВАННЫЕ РЕКОМЕНДАЦИИ:");
        System.out.print(recommendation);
        System.out.println("---------------------------------------------------------------");
        System.out.println(" 5. ИТОГОВОЕ РЕШЕНИЕ СИСТЕМЫ:");
        System.out.println("    >>> " + finalDecision);
        System.out.println("===============================================================");
        System.out.println("Отчёт сформирован успешно. Спасибо за обращение в ModCraft Studio!");
        System.out.println();
    }
}
