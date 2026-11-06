using System;

namespace LibraryManagement
{
    /// <summary>
    /// Класс Книга (Book)
    /// Задачи: 1, 3, 7, 8
    /// </summary>
    public class Book
    {
        // Задача 1: Поля Title, Author, Year
        public string Title;
        public string Author;
        public int Year;

        // Поля состояния для выдачи и возврата (Задачи 7, 21, 22)
        public bool IsAvailable;
        public string BorrowedBy;
        public DateTime? BorrowDate;

        // Конструктор по умолчанию
        public Book()
        {
            Title = "Без названия";
            Author = "Неизвестен";
            Year = DateTime.Now.Year;
            IsAvailable = true;
            BorrowedBy = null;
            BorrowDate = null;
        }

        // Конструктор с параметрами
        public Book(string title, string author, int year)
        {
            Title = title;
            Author = author;
            Year = year;
            IsAvailable = true;
            BorrowedBy = null;
            BorrowDate = null;
        }

        /// <summary>
        /// Задача 1: Метод DisplayInfo() выводит информацию о книге.
        /// </summary>
        public void DisplayInfo()
        {
            string status = IsAvailable ? "Доступна" : string.Format("Выдана читателю: {0}", BorrowedBy);
            Console.WriteLine(string.Format("Название: \"{0}\" | Автор: {1} | Год издания: {2} (Возраст: {3} г.) | Статус: {4}",
                Title, Author, Year, CalculateBookAge(Year), status));
        }

        /// <summary>
        /// Задача 3: Метод с одним параметром SetTitle(string title), изменяет название книги.
        /// </summary>
        /// <param name="title">Новое название книги</param>
        public void SetTitle(string title)
        {
            Title = title;
        }

        /// <summary>
        /// Задача 7: Метод проверки IsBookAvailable(), возвращает true, если доступна, и false, если выдана.
        /// </summary>
        public bool IsBookAvailable()
        {
            return IsAvailable;
        }

        /// <summary>
        /// Задача 8: Метод расчёта CalculateBookAge(int year), возвращает возраст книги.
        /// </summary>
        /// <param name="year">Год издания</param>
        /// <returns>Возраст книги в годах</returns>
        public static int CalculateBookAge(int year)
        {
            int currentYear = DateTime.Now.Year;
            int age = currentYear - year;
            return age < 0 ? 0 : age;
        }

        /// <summary>
        /// Экземплярный вариант CalculateBookAge() для текущей книги
        /// </summary>
        public int CalculateBookAge()
        {
            return CalculateBookAge(this.Year);
        }
    }
}
