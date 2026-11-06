using System;
using System.Collections.Generic;

namespace LibraryManagement
{
    /// <summary>
    /// Главный класс программы
    /// Задача 5: Демонстрация создания объектов Book и вызова DisplayInfo()
    /// Задача 25: Финальная интеграция проекта с интерактивным меню (ShowMenu)
    /// </summary>
    public class Program
    {
        public static void Main(string[] args)
        {
            // Настройка корректного отображения кириллицы в консоли
            try
            {
                Console.OutputEncoding = System.Text.Encoding.UTF8;
                Console.InputEncoding = System.Text.Encoding.UTF8;
            }
            catch { }

            // Создаем экземпляр библиотеки
            Library library = new Library();

            // Задача 2: Метод без параметров - приветственное сообщение
            library.ShowWelcomeMessage();

            // Задача 11: Вызов статического метода без создания экземпляра
            Console.WriteLine("\n[Задача 11: Вызов статического метода ShowLibraryName()]");
            Library.ShowLibraryName();

            // Заполняем библиотеку тестовыми данными для удобства работы
            library.SeedInitialData();

            bool exit = false;
            while (!exit)
            {
                Console.WriteLine();
                // Задача 24: Метод главного меню
                library.ShowMenu();

                string choice = Console.ReadLine();
                Console.WriteLine();

                switch (choice != null ? choice.Trim() : "")
                {
                    case "1":
                        // Задача 4: Добавление книги (AddBook)
                        Console.WriteLine("--- Добавление новой книги ---");
                        Console.Write("Название: ");
                        string title = Console.ReadLine();
                        Console.Write("Автор: ");
                        string author = Console.ReadLine();
                        Console.Write("Год: ");
                        int year;
                        if (!int.TryParse(Console.ReadLine(), out year))
                        {
                            year = DateTime.Now.Year;
                        }
                        library.AddBook(title, author, year);
                        break;

                    case "2":
                        // Задача 12: Нестатический метод ShowBooks()
                        library.ShowBooks();
                        break;

                    case "3":
                        // Задача 9, 16: Поиск книги (перегрузка SearchBook)
                        Console.WriteLine("--- Поиск книги ---");
                        Console.WriteLine("1. Поиск по названию");
                        Console.WriteLine("2. Поиск по автору");
                        Console.WriteLine("3. Поиск по году издания");
                        Console.Write("Выберите критерий поиска: ");
                        string searchType = Console.ReadLine();

                        List<Book> searchResults = new List<Book>();

                        if (searchType == "1")
                        {
                            Console.Write("Введите название (или часть): ");
                            string query = Console.ReadLine();
                            searchResults = library.SearchBook(query);
                        }
                        else if (searchType == "2")
                        {
                            Console.Write("Введите автора (или часть): ");
                            string query = Console.ReadLine();
                            searchResults = library.SearchBook(query, true);
                        }
                        else if (searchType == "3")
                        {
                            Console.Write("Введите год: ");
                            int qYear;
                            if (int.TryParse(Console.ReadLine(), out qYear))
                            {
                                searchResults = library.SearchBook(qYear);
                            }
                            else
                            {
                                Console.WriteLine("Некорректный год!");
                            }
                        }
                        else
                        {
                            Console.WriteLine("Неверный пункт.");
                            break;
                        }

                        Console.WriteLine(string.Format("\nНайдено совпадений: {0}", searchResults.Count));
                        for (int i = 0; i < searchResults.Count; i++)
                        {
                            Console.Write(string.Format("{0}. ", i + 1));
                            searchResults[i].DisplayInfo();
                        }
                        break;

                    case "4":
                        // Задача 17: Перегрузка метода AddReader()
                        Console.WriteLine("--- Регистрация читателя ---");
                        Console.Write("Имя: ");
                        string rName = Console.ReadLine();
                        Console.Write("Фамилия (нажмите Enter, если пропустить): ");
                        string rLastName = Console.ReadLine();
                        Console.Write("Телефон (нажмите Enter, если пропустить): ");
                        string rPhone = Console.ReadLine();

                        if (!string.IsNullOrEmpty(rLastName) && !string.IsNullOrEmpty(rPhone))
                        {
                            library.AddReader(rName, rLastName, rPhone);
                        }
                        else if (!string.IsNullOrEmpty(rLastName))
                        {
                            library.AddReader(rName, rLastName);
                        }
                        else if (!string.IsNullOrEmpty(rPhone))
                        {
                            long phoneNum;
                            if (long.TryParse(rPhone.Replace("-", "").Replace("+", "").Replace(" ", ""), out phoneNum))
                            {
                                library.AddReader(rName, phoneNum);
                            }
                            else
                            {
                                library.AddReader(rName, rPhone, "Не указан");
                            }
                        }
                        else
                        {
                            library.AddReader(rName);
                        }
                        break;

                    case "5":
                        // Список читателей
                        library.ShowReaders();
                        break;

                    case "6":
                        // Задача 21: Метод выдачи книги (BorrowBook)
                        Console.WriteLine("--- Оформление выдачи книги ---");
                        Console.Write("Введите название книги: ");
                        string bTitle = Console.ReadLine();
                        Console.Write("Введите имя читателя: ");
                        string bReader = Console.ReadLine();
                        library.BorrowBook(bTitle, bReader);
                        break;

                    case "7":
                        // Задача 18, 22: Метод возврата книги (ReturnBook с расчётом штрафа)
                        Console.WriteLine("--- Оформление возврата книги ---");
                        Console.Write("Введите название книги: ");
                        string retTitle = Console.ReadLine();
                        Console.Write("Сколько дней книга была на руках у читателя: ");
                        int days;
                        if (!int.TryParse(Console.ReadLine(), out days))
                        {
                            days = 0;
                        }
                        library.ReturnBook(retTitle, days);
                        break;

                    case "8":
                        // Задача 23: Метод статистики (ShowStatistics)
                        library.ShowStatistics();
                        break;

                    case "9":
                        // Демонстрация выполнения лабораторных задач 1–5, 8, 14, 19, 20
                        RunLaboratoryTasksDemo(library);
                        break;

                    case "0":
                        Console.WriteLine("Завершение работы программы. До свидания!");
                        exit = true;
                        break;

                    default:
                        Console.WriteLine("Неизвестный пункт меню. Повторите ввод.");
                        break;
                }

                if (!exit)
                {
                    Console.WriteLine("\nНажмите Enter для продолжения...");
                    Console.ReadLine();
                }
            }
        }

        /// <summary>
        /// Отдельный метод автоматической демонстрации лабораторных задач 1–5, 8, 13, 14, 19, 20
        /// </summary>
        public static void RunLaboratoryTasksDemo(Library library)
        {
            Console.WriteLine("=============================================================");
            Console.WriteLine("   ДЕМОНСТРАЦИЯ ВЫПОЛНЕНИЯ ЗАДАЧ ЛАБОРАТОРНОЙ РАБОТЫ");
            Console.WriteLine("=============================================================");

            // --- Задача 1 & 5: Создание трех объектов Book и вызов DisplayInfo() ---
            Console.WriteLine("\n[Задача 1 и 5: Создание 3 объектов Book и вызов DisplayInfo()]");
            Book demoBook1 = new Book("Архитектура компьютера", "Эндрю Таненбаум", 2013);
            Book demoBook2 = new Book("Паттерны проектирования", "Эрих Гамма", 1994);
            Book demoBook3 = new Book("Изучаем C#", "Эндрю Стиллмен", 2021);

            demoBook1.DisplayInfo();
            demoBook2.DisplayInfo();
            demoBook3.DisplayInfo();

            // --- Задача 3: Метод SetTitle() ---
            Console.WriteLine("\n[Задача 3: Метод SetTitle() - смена названия]");
            Console.WriteLine("Старое название demoBook3: " + demoBook3.Title);
            demoBook3.SetTitle("Изучаем C# (4-е издание)");
            Console.WriteLine("Новое название demoBook3: " + demoBook3.Title);

            // --- Задача 6: GetBookCount() ---
            Console.WriteLine("\n[Задача 6: GetBookCount() - количество книг в библиотеке]");
            Console.WriteLine("Всего книг в библиотеке: " + library.GetBookCount());

            // --- Задача 7: IsBookAvailable() ---
            Console.WriteLine("\n[Задача 7: IsBookAvailable() - доступность книги]");
            Console.WriteLine(string.Format("Книга \"{0}\" доступна: {1}", demoBook1.Title, demoBook1.IsBookAvailable()));

            // --- Задача 8: CalculateBookAge(int year) ---
            Console.WriteLine("\n[Задача 8: CalculateBookAge() - расчёт возраста книги]");
            int bookAge = Book.CalculateBookAge(1994);
            Console.WriteLine(string.Format("Возраст книги 1994 года издания: {0} лет", bookAge));

            // --- Задача 10: IsReaderRegistered() ---
            Console.WriteLine("\n[Задача 10: IsReaderRegistered() - проверка читателя]");
            Console.WriteLine("Читатель 'Алексей' зарегистрирован? " + library.IsReaderRegistered("Алексей"));
            Console.WriteLine("Читатель 'Неизвестный' зарегистрирован? " + library.IsReaderRegistered("Неизвестный"));

            // --- Задача 13: CalculateFine(int overdueDays) ---
            Console.WriteLine("\n[Задача 13: CalculateFine() - статический расчёт штрафа]");
            decimal fine5Days = Library.CalculateFine(5);
            Console.WriteLine(string.Format("Штраф за 5 дней просрочки: {0:0.00} руб.", fine5Days));

            // --- Задача 14: GetMaxBooks() и GetAvailableBooks() ---
            Console.WriteLine("\n[Задача 14: Сравнение методов GetMaxBooks() и GetAvailableBooks()]");
            Console.WriteLine("Большее число книг из (15, 42): " + Library.GetMaxBooks(15, 42));
            List<Book> available = library.GetAvailableBooks();
            Console.WriteLine("Количество доступных книг в библиотеке: " + available.Count);

            // --- Задача 15: Статический счётчик TotalBooks ---
            Console.WriteLine("\n[Задача 15: Статический счётчик Library.TotalBooks]");
            Console.WriteLine("Общий счетчик созданных книг: " + Library.TotalBooks);

            // --- Задача 19: Рекурсивный метод CountBooksRecursive() ---
            Console.WriteLine("\n[Задача 19: Рекурсивный подсчёт книг CountBooksRecursive()]");
            int recCount = library.CountBooksRecursive();
            Console.WriteLine("Количество книг, подсчитанное рекурсией: " + recCount);

            // --- Задача 20: Сортировка SortBooksByYear() ---
            Console.WriteLine("\n[Задача 20: Сортировка книг по году издания SortBooksByYear()]");
            library.SortBooksByYear();
            library.ShowBooks();

            Console.WriteLine("\n=============================================================");
            Console.WriteLine("   ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА");
            Console.WriteLine("=============================================================");
        }
    }
}
