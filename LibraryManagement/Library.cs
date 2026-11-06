using System;
using System.Collections.Generic;

namespace LibraryManagement
{
    /// <summary>
    /// Класс Библиотека (Library)
    /// Содержит реализацию задач 2, 4, 6, 9-24
    /// </summary>
    public class Library
    {
        // Задача 11: Статическое поле с названием библиотеки
        public static string LibraryName = "Центральная городская библиотека";

        // Задача 15: Статический счётчик общего количества книг
        public static int TotalBooks = 0;

        // Задача 13: Статическая ставка штрафа в день (руб.)
        public static decimal FinePerDay = 15.0m;

        // Коллекция книг и читателей конкретного объекта библиотеки
        private List<Book> books;
        private List<Reader> readers;

        // Конструктор
        public Library()
        {
            books = new List<Book>();
            readers = new List<Reader>();
        }

        // ==========================================
        // БАЗОВЫЙ УРОВЕНЬ (Задачи 2, 4)
        // ==========================================

        /// <summary>
        /// Задача 2: Метод без параметров.
        /// Выводит приветственное сообщение библиотеки.
        /// </summary>
        public void ShowWelcomeMessage()
        {
            Console.WriteLine("==================================================");
            Console.WriteLine(string.Format(" Добро пожаловать в {0}!", LibraryName));
            Console.WriteLine(" Мы рады видеть вас среди наших читателей.");
            Console.WriteLine("==================================================");
        }

        /// <summary>
        /// Задача 4: Метод с несколькими параметрами.
        /// Принимает информацию о книге и добавляет её в фонд библиотеки.
        /// </summary>
        /// <param name="title">Название</param>
        /// <param name="author">Автор</param>
        /// <param name="year">Год издания</param>
        public void AddBook(string title, string author, int year)
        {
            Book newBook = new Book(title, author, year);
            books.Add(newBook);
            
            // Задача 15: Увеличиваем статический счётчик
            IncrementTotalBooks();

            Console.WriteLine("Книга успешно добавлена!");
        }

        // ==========================================
        // МЕТОДЫ С ВОЗВРАЩАЕМЫМИ ЗНАЧЕНИЯМИ (Задачи 6, 9, 10)
        // ==========================================

        /// <summary>
        /// Задача 6: Метод с возвращаемым значением.
        /// Возвращает количество книг в библиотеке.
        /// </summary>
        /// <returns>Количество книг</returns>
        public int GetBookCount()
        {
            return books.Count;
        }

        /// <summary>
        /// Задача 9: Метод поиска.
        /// Ищет книгу по названию и возвращает найденную книгу (или null).
        /// </summary>
        /// <param name="title">Название книги</param>
        /// <returns>Найденная книга или null</returns>
        public Book FindBook(string title)
        {
            if (string.IsNullOrEmpty(title))
                return null;

            foreach (Book book in books)
            {
                if (book.Title.Equals(title.Trim(), StringComparison.OrdinalIgnoreCase))
                {
                    return book;
                }
            }
            return null;
        }

        /// <summary>
        /// Задача 10: Метод проверки читателя.
        /// Проверяет наличие читателя в системе по имени или фамилии.
        /// </summary>
        /// <param name="name">Имя читателя</param>
        /// <returns>true, если читатель зарегистрирован; иначе false</returns>
        public bool IsReaderRegistered(string name)
        {
            if (string.IsNullOrEmpty(name))
                return false;

            string query = name.Trim().ToLower();
            foreach (Reader reader in readers)
            {
                if (reader.Name.ToLower().Equals(query) ||
                    reader.LastName.ToLower().Equals(query) ||
                    reader.GetFullName().ToLower().Equals(query))
                {
                    return true;
                }
            }
            return false;
        }

        /// <summary>
        /// Вспомогательный метод получения объекта читателя
        /// </summary>
        public Reader FindReader(string name)
        {
            if (string.IsNullOrEmpty(name))
                return null;

            string query = name.Trim().ToLower();
            foreach (Reader reader in readers)
            {
                if (reader.Name.ToLower().Equals(query) ||
                    reader.LastName.ToLower().Equals(query) ||
                    reader.GetFullName().ToLower().Equals(query))
                {
                    return reader;
                }
            }
            return null;
        }

        // ==========================================
        // СТАТИЧЕСКИЕ И НЕСТАТИЧЕСКИЕ МЕТОДЫ (Задачи 11-15)
        // ==========================================

        /// <summary>
        /// Задача 11: Статический метод.
        /// Выводит название библиотеки. Вызывается без создания объекта через Library.ShowLibraryName().
        /// </summary>
        public static void ShowLibraryName()
        {
            Console.WriteLine("Название библиотеки: " + LibraryName);
        }

        /// <summary>
        /// Задача 12: Нестатический метод.
        /// Выводит список книг конкретного объекта библиотеки.
        /// </summary>
        public void ShowBooks()
        {
            Console.WriteLine("\n--- Список книг в библиотеке ---");
            if (books.Count == 0)
            {
                Console.WriteLine("В библиотеке пока нет книг.");
                return;
            }

            for (int i = 0; i < books.Count; i++)
            {
                Console.Write(string.Format("{0}. ", i + 1));
                books[i].DisplayInfo();
            }
        }

        /// <summary>
        /// Задача 13: Статический метод расчёта.
        /// Рассчитывает штраф за просрочку возврата книги.
        /// </summary>
        /// <param name="overdueDays">Количество дней просрочки</param>
        /// <returns>Сумма штрафа в рублях</returns>
        public static decimal CalculateFine(int overdueDays)
        {
            if (overdueDays <= 0)
                return 0.0m;

            return overdueDays * FinePerDay;
        }

        /// <summary>
        /// Задача 14: Статический метод сравнения.
        /// Возвращает большее количество книг.
        /// </summary>
        public static int GetMaxBooks(int first, int second)
        {
            return first > second ? first : second;
        }

        /// <summary>
        /// Задача 14: Нестатический метод.
        /// Возвращает список доступных книг конкретного объекта библиотеки.
        /// </summary>
        public List<Book> GetAvailableBooks()
        {
            List<Book> available = new List<Book>();
            foreach (Book book in books)
            {
                if (book.IsBookAvailable())
                {
                    available.Add(book);
                }
            }
            return available;
        }

        /// <summary>
        /// Задача 15: Метод, который увеличивает значение статического счётчика TotalBooks при добавлении книги.
        /// </summary>
        public static void IncrementTotalBooks()
        {
            TotalBooks++;
        }

        // ==========================================
        // ПЕРЕГРУЗКА И РЕКУРСИВНЫЕ МЕТОДЫ (Задачи 16-20)
        // ==========================================

        /// <summary>
        /// Задача 16: Перегрузка метода 1 - Поиск книги по названию.
        /// </summary>
        public List<Book> SearchBook(string title)
        {
            List<Book> result = new List<Book>();
            if (string.IsNullOrEmpty(title)) return result;

            foreach (Book b in books)
            {
                if (b.Title.IndexOf(title, StringComparison.OrdinalIgnoreCase) >= 0)
                {
                    result.Add(b);
                }
            }
            return result;
        }

        /// <summary>
        /// Задача 16: Перегрузка метода 2 - Поиск книги по автору.
        /// </summary>
        public List<Book> SearchBook(string author, bool searchByAuthor)
        {
            List<Book> result = new List<Book>();
            if (!searchByAuthor || string.IsNullOrEmpty(author))
                return result;

            foreach (Book b in books)
            {
                if (b.Author.IndexOf(author, StringComparison.OrdinalIgnoreCase) >= 0)
                {
                    result.Add(b);
                }
            }
            return result;
        }

        /// <summary>
        /// Задача 16: Перегрузка метода 3 - Поиск книги по году издания.
        /// </summary>
        public List<Book> SearchBook(int year)
        {
            List<Book> result = new List<Book>();
            foreach (Book b in books)
            {
                if (b.Year == year)
                {
                    result.Add(b);
                }
            }
            return result;
        }

        /// <summary>
        /// Задача 17: Перегрузка метода AddReader - вариант 1: только по имени.
        /// </summary>
        public void AddReader(string name)
        {
            Reader reader = new Reader(name);
            readers.Add(reader);
            Console.WriteLine(string.Format("Читатель {0} успешно зарегистрирован!", name));
        }

        /// <summary>
        /// Задача 17: Перегрузка метода AddReader - вариант 2: по имени и фамилии.
        /// </summary>
        public void AddReader(string firstName, string lastName)
        {
            Reader reader = new Reader(firstName, lastName);
            readers.Add(reader);
            Console.WriteLine(string.Format("Читатель {0} {1} успешно зарегистрирован!", firstName, lastName));
        }

        /// <summary>
        /// Задача 17: Перегрузка метода AddReader - вариант 3: по имени и номеру телефона.
        /// </summary>
        public void AddReader(string name, long phoneNumber)
        {
            Reader reader = new Reader(name, phoneNumber);
            readers.Add(reader);
            Console.WriteLine(string.Format("Читатель {0} (тел. {1}) успешно зарегистрирован!", name, phoneNumber));
        }

        /// <summary>
        /// Дополнительный метод добавления читателя со всеми полями
        /// </summary>
        public void AddReader(string firstName, string lastName, string phone)
        {
            Reader reader = new Reader(firstName, lastName, phone);
            readers.Add(reader);
            Console.WriteLine(string.Format("Читатель {0} {1} (тел. {2}) успешно зарегистрирован!", firstName, lastName, phone));
        }

        /// <summary>
        /// Показать всех зарегистрированных читателей
        /// </summary>
        public void ShowReaders()
        {
            Console.WriteLine("\n--- Список зарегистрированных читателей ---");
            if (readers.Count == 0)
            {
                Console.WriteLine("Читателей пока нет.");
                return;
            }

            for (int i = 0; i < readers.Count; i++)
            {
                Console.Write(string.Format("{0}. ", i + 1));
                readers[i].DisplayInfo();
            }
        }

        /// <summary>
        /// Задача 18: Метод с условием.
        /// Возвращает книгу в библиотеку, если она ранее была выдана.
        /// </summary>
        /// <param name="title">Название книги</param>
        public bool ReturnBook(string title)
        {
            Book book = FindBook(title);
            if (book == null)
            {
                Console.WriteLine(string.Format("Ошибка: Книга \"{0}\" не найдена в каталоге библиотеки.", title));
                return false;
            }

            if (book.IsBookAvailable())
            {
                Console.WriteLine(string.Format("Внимание: Книга \"{0}\" не была выдана, она уже находится в фонде библиотеки.", title));
                return false;
            }

            // Находим читателя и удаляем книгу из его списка
            if (!string.IsNullOrEmpty(book.BorrowedBy))
            {
                Reader reader = FindReader(book.BorrowedBy);
                if (reader != null)
                {
                    reader.BorrowedBookTitles.Remove(book.Title);
                }
            }

            book.IsAvailable = true;
            book.BorrowedBy = null;
            book.BorrowDate = null;

            Console.WriteLine(string.Format("Книга \"{0}\" успешно возвращена в библиотеку!", book.Title));
            return true;
        }

        /// <summary>
        /// Задача 19: Рекурсивный метод.
        /// Рекурсивно подсчитывает количество книг в списке.
        /// </summary>
        public int CountBooksRecursive()
        {
            return CountBooksInternal(books, 0);
        }

        /// <summary>
        /// Внутренняя рекурсивная функция подсчёта
        /// </summary>
        private int CountBooksInternal(List<Book> list, int index)
        {
            // Базовый случай: достигли конца списка
            if (index >= list.Count)
            {
                return 0;
            }

            // Шаг рекурсии: 1 + количество оставшихся элементов
            return 1 + CountBooksInternal(list, index + 1);
        }

        /// <summary>
        /// Задача 20: Метод сортировки.
        /// Сортирует книги по году издания (по возрастанию).
        /// </summary>
        public void SortBooksByYear()
        {
            books.Sort(delegate(Book a, Book b)
            {
                return a.Year.CompareTo(b.Year);
            });
            Console.WriteLine("Книги успешно отсортированы по году издания (по возрастанию)!");
        }

        // ==========================================
        // КОМПЛЕКСНЫЕ МЕТОДЫ И ИНТЕГРАЦИЯ (Задачи 21-24)
        // ==========================================

        /// <summary>
        /// Задача 21: Метод выдачи книги.
        /// Проверяет наличие книги и читателя, после чего оформляет выдачу.
        /// </summary>
        /// <param name="title">Название книги</param>
        /// <param name="readerName">Имя читателя</param>
        public bool BorrowBook(string title, string readerName)
        {
            // 1. Проверяем наличие читателя
            if (!IsReaderRegistered(readerName))
            {
                Console.WriteLine(string.Format("Ошибка: Читатель \"{0}\" не зарегистрирован в системе! Сначала добавьте читателя.", readerName));
                return false;
            }

            // 2. Проверяем наличие книги
            Book book = FindBook(title);
            if (book == null)
            {
                Console.WriteLine(string.Format("Ошибка: Книга \"{0}\" не найдена в библиотеке.", title));
                return false;
            }

            // 3. Проверяем доступность книги
            if (!book.IsBookAvailable())
            {
                Console.WriteLine(string.Format("Ошибка: Книга \"{0}\" уже выдана читателю {1}!", book.Title, book.BorrowedBy));
                return false;
            }

            // 4. Оформляем выдачу
            book.IsAvailable = false;
            book.BorrowedBy = readerName;
            book.BorrowDate = DateTime.Now;

            Reader reader = FindReader(readerName);
            if (reader != null)
            {
                reader.BorrowedBookTitles.Add(book.Title);
            }

            Console.WriteLine(string.Format("Успех: Книга \"{0}\" успешно выдана читателю {1}!", book.Title, readerName));
            return true;
        }

        /// <summary>
        /// Задача 22: Метод возврата книги с расчётом штрафа.
        /// Реализует ReturnBook() с дополнительной проверкой статуса книги и расчётом штрафа за просрочку.
        /// </summary>
        /// <param name="title">Название книги</param>
        /// <param name="daysBorrowed">Сколько дней книга была на руках</param>
        public bool ReturnBook(string title, int daysBorrowed)
        {
            Book book = FindBook(title);
            if (book == null)
            {
                Console.WriteLine(string.Format("Ошибка: Книга \"{0}\" не найдена в библиотеке.", title));
                return false;
            }

            if (book.IsBookAvailable())
            {
                Console.WriteLine(string.Format("Внимание: Книга \"{0}\" не была выдана, она уже в библиотеке.", title));
                return false;
            }

            // Проверка просрочки: стандартный срок пользования - 14 дней
            const int allowedDays = 14;
            int overdueDays = daysBorrowed - allowedDays;

            if (overdueDays > 0)
            {
                decimal fine = CalculateFine(overdueDays);
                Console.WriteLine(string.Format("ВНИМАНИЕ: Срок пользования книгой превышен на {0} дн.! Начислен штраф: {1:0.00} руб. (по тарифу {2:0.00} руб./день).",
                    overdueDays, fine, FinePerDay));
            }
            else
            {
                Console.WriteLine("Книга возвращена вовремя, штраф не начислен.");
            }

            // Возврат книги в фонд
            if (!string.IsNullOrEmpty(book.BorrowedBy))
            {
                Reader reader = FindReader(book.BorrowedBy);
                if (reader != null)
                {
                    reader.BorrowedBookTitles.Remove(book.Title);
                }
            }

            book.IsAvailable = true;
            book.BorrowedBy = null;
            book.BorrowDate = null;

            Console.WriteLine(string.Format("Книга \"{0}\" успешно возвращена в библиотеку!", book.Title));
            return true;
        }

        /// <summary>
        /// Задача 23: Метод статистики.
        /// Выводит: общее количество книг, количество доступных книг, количество выданных книг, количество читателей.
        /// </summary>
        public void ShowStatistics()
        {
            int total = books.Count;
            int available = 0;
            int borrowed = 0;

            foreach (Book b in books)
            {
                if (b.IsBookAvailable())
                    available++;
                else
                    borrowed++;
            }

            int registeredReaders = readers.Count;

            Console.WriteLine("\n==============================");
            Console.WriteLine("     СТАТИСТИКА БИБЛИОТЕКИ    ");
            Console.WriteLine("==============================");
            Console.WriteLine(string.Format("Общее количество книг:          {0} (Всего добавлено за всё время: {1})", total, TotalBooks));
            Console.WriteLine(string.Format("Количество доступных книг:      {0}", available));
            Console.WriteLine(string.Format("Количество выданных книг:       {0}", borrowed));
            Console.WriteLine(string.Format("Зарегистрированных читателей:   {0}", registeredReaders));
            Console.WriteLine("==============================\n");
        }

        /// <summary>
        /// Задача 24: Метод главного меню.
        /// Отображает доступные действия библиотеки.
        /// </summary>
        public void ShowMenu()
        {
            Console.WriteLine("==============================");
            Console.WriteLine("     УПРАВЛЕНИЕ БИБЛИОТЕКОЙ   ");
            Console.WriteLine("==============================");
            Console.WriteLine("1. Добавить книгу");
            Console.WriteLine("2. Показать все книги");
            Console.WriteLine("3. Найти книгу");
            Console.WriteLine("4. Добавить читателя");
            Console.WriteLine("5. Показать читателей");
            Console.WriteLine("6. Выдать книгу");
            Console.WriteLine("7. Вернуть книгу");
            Console.WriteLine("8. Статистика библиотеки");
            Console.WriteLine("9. Демонстрация всех базовых задач (1-5, 8, 11, 14, 19, 20)");
            Console.WriteLine("0. Выход");
            Console.WriteLine("==============================");
            Console.Write("Выберите действие: ");
        }

        /// <summary>
        /// Инициализация библиотеки начальными тестовыми данными
        /// </summary>
        public void SeedInitialData()
        {
            AddBook("Чистый код", "Роберт Мартин", 2008);
            AddBook("Война и мир", "Лев Толстой", 1869);
            AddBook("1984", "Джордж Оруэлл", 1949);
            AddBook("Совершенный код", "Стив Макконнелл", 2004);
            AddBook("Мастер и Маргарита", "Михаил Булгаков", 1967);

            AddReader("Алексей", "Иванов", "+7 (999) 111-22-33");
            AddReader("Елена", "Смирнова", "+7 (999) 222-33-44");
            AddReader("Дмитрий", "Кузнецов", "+7 (999) 333-44-55");
        }
    }
}
