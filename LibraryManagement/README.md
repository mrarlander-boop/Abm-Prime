# Проект: Управление библиотекой (C# / .NET)

Реализация всех 25 задач лабораторной / практической работы по теме **«Создание и использование методов класса в C#»**.

---

## Структура файлов проекта

- `Book.cs` — класс книги:
  - Поля: `Title`, `Author`, `Year`, `IsAvailable`, `BorrowedBy`, `BorrowDate`.
  - Задача 1: метод `DisplayInfo()`.
  - Задача 3: метод `SetTitle(string title)`.
  - Задача 7: метод проверки `IsBookAvailable()`.
  - Задача 8: метод расчёта `CalculateBookAge(int year)`.
- `Reader.cs` — класс читателя:
  - Поля: `Name`, `LastName`, `Phone`, `BorrowedBookTitles`.
  - Задачи 10, 17, 21.
- `Library.cs` — класс библиотеки:
  - Задача 2: `ShowWelcomeMessage()`.
  - Задача 4: `AddBook(string title, string author, int year)`.
  - Задача 6: `GetBookCount()`.
  - Задача 9: `FindBook(string title)`.
  - Задача 10: `IsReaderRegistered(string name)`.
  - Задача 11: статический метод `ShowLibraryName()`.
  - Задача 12: нестатический метод `ShowBooks()`.
  - Задача 13: статический метод расчёта штрафа `CalculateFine(int overdueDays)`.
  - Задача 14: сравнение методов `GetMaxBooks(int first, int second)` и `GetAvailableBooks()`.
  - Задача 15: статический счётчик `TotalBooks` и метод `IncrementTotalBooks()`.
  - Задача 16: перегрузка метода `SearchBook()` (по названию, по автору, по году).
  - Задача 17: перегрузка метода `AddReader()` (по имени; по имени и фамилии; по имени и телефону).
  - Задача 18: метод с условием `ReturnBook(string title)`.
  - Задача 19: рекурсивный метод подсчёта книг `CountBooksRecursive()`.
  - Задача 20: метод сортировки `SortBooksByYear()`.
  - Задача 21: метод выдачи книги `BorrowBook(string title, string readerName)`.
  - Задача 22: метод возврата книги с расчётом штрафа `ReturnBook(string title, int daysBorrowed)`.
  - Задача 23: метод статистики `ShowStatistics()`.
  - Задача 24: метод главного меню `ShowMenu()`.
- `Program.cs` — точка входа:
  - Задача 5: создание трех объектов `Book` и вызов `DisplayInfo()`.
  - Задача 25: финальная интеграция проекта с интерактивным циклом меню.
- `LibraryManagement.exe` — готовый скомпилированный исполняемый файл.
- `LibraryManagement.csproj` — файл проекта для сборки через современные .NET SDK / Visual Studio.

---

## Способы запуска программы

### Способ 1: Прямой запуск готового EXE (без установки SDK)
Файл `LibraryManagement.exe` уже скомпилирован встроенным в Windows компилятором C#. Вы можете запустить его прямо из терминала:
```powershell
cd LibraryManagement
.\LibraryManagement.exe
```
или дважды кликнуть по файлу `LibraryManagement.exe` в Проводнике Windows.

### Способ 2: Компиляция с помощью встроенного компилятора Windows (csc.exe)
Если вы внесли изменения в файлы `.cs`:
```powershell
C:\Windows\Microsoft.NET\Framework64\v4.0.30319\csc.exe /nologo /out:LibraryManagement.exe /target:exe Book.cs Reader.cs Library.cs Program.cs
.\LibraryManagement.exe
```

### Способ 3: Запуск через .NET SDK (команда `dotnet run`)
Если у вас установлен .NET SDK 8.0+:
```bash
cd LibraryManagement
dotnet run
```

---

## Руководство: перевод в ASP.NET Core MVC с базой данных SQLite

Если ваше учебное задание требует веб-версию сайта на ASP.NET Core MVC с базой данных SQLite:

### 1. Создание проекта
```bash
dotnet new mvc -n LibraryWeb
cd LibraryWeb
```

### 2. Установка пакетов для SQLite и Entity Framework Core
```bash
dotnet add package Microsoft.EntityFrameworkCore.Sqlite
dotnet add package Microsoft.EntityFrameworkCore.Design
```

### 3. Настройка строки подключения в `appsettings.json`
```json
{
  "ConnectionStrings": {
    "LibraryDb": "Data Source=library.db"
  }
}
```

### 4. Создание контекста базы данных `LibraryDbContext.cs`
```csharp
using Microsoft.EntityFrameworkCore;

public class LibraryDbContext : DbContext
{
    public LibraryDbContext(DbContextOptions<LibraryDbContext> options) : base(options) { }

    public DbSet<Book> Books { get; set; }
    public DbSet<Reader> Readers { get; set; }
}
```

### 5. Регистрация в `Program.cs`
```csharp
builder.Services.AddDbContext<LibraryDbContext>(options =>
    options.UseSqlite(builder.Configuration.GetConnectionString("LibraryDb")));
```

### 6. Создание миграции и запуск
```bash
dotnet ef migrations add InitialCreate
dotnet ef database update
dotnet run
```
