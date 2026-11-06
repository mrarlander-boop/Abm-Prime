using System;
using System.Collections.Generic;

namespace LibraryManagement
{
    /// <summary>
    /// Класс Читатель (Reader)
    /// Задачи: 10, 17, 21, 23
    /// </summary>
    public class Reader
    {
        public string Name { get; set; }
        public string LastName { get; set; }
        public string Phone { get; set; }
        public List<string> BorrowedBookTitles { get; set; }

        public Reader(string name)
        {
            Name = name;
            LastName = "";
            Phone = "Не указан";
            BorrowedBookTitles = new List<string>();
        }

        public Reader(string name, string lastName)
        {
            Name = name;
            LastName = lastName;
            Phone = "Не указан";
            BorrowedBookTitles = new List<string>();
        }

        public Reader(string name, string lastName, string phone)
        {
            Name = name;
            LastName = lastName;
            Phone = phone;
            BorrowedBookTitles = new List<string>();
        }

        public Reader(string name, long phoneNumber)
        {
            Name = name;
            LastName = "";
            Phone = phoneNumber.ToString();
            BorrowedBookTitles = new List<string>();
        }

        public string GetFullName()
        {
            if (string.IsNullOrEmpty(LastName))
                return Name;
            return string.Format("{0} {1}", Name, LastName);
        }

        public void DisplayInfo()
        {
            Console.WriteLine(string.Format("Читатель: {0} | Телефон: {1} | Взято книг: {2}",
                GetFullName(), Phone, BorrowedBookTitles.Count));
        }
    }
}
