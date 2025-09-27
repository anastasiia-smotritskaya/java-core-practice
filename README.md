# Java Core Practice

Проект для практики решения задач по Java Core с использованием JUnit 5 и Gradle.

## Структура проекта

- `src/main/java` - исходный код
- `src/test/java` - тесты
- `build.gradle.kts` - конфигурация сборки

## Технологии

- Java 21
- JUnit 5
- Gradle (Kotlin DSL)
- Git

## Запуск проекта

```bash
# Сборка проекта
./gradlew build

# Запуск тестов
./gradlew test

# Запуск конкретного тестового класса
./gradlew test --tests "StringPracticeTest"
```

## План развития

- Блок 1: Базовый синтаксис

Примитивные типы и операторы  
Условные операторы (if-else, switch)  
Циклы (for, while, do-while)

- Блок 2: Работа с данными

Массивы (одномерные, многомерные)  
String vs StringBuilder (методы, производительность)  
Ввод-вывод (чтение/запись файлов, консоль)

- Блок 3: ООП основы

Классы и объекты (конструкторы, геттеры/сеттеры)  
Наследование и полиморфизм  
Абстрактные классы и интерфейсы  
Enum

- Блок 4: Коллекции и итераторы

List (ArrayList, LinkedList)  
Set (HashSet, TreeSet)  
Map (HashMap, TreeMap)  
Итераторы и for-each

- Блок 5: Обработка ошибок

Checked vs Unchecked исключения  
Try-catch-finally  
Создание собственных исключений

- Блок 6: Функциональное программирование

Лямбда-выражения  
Functional interfaces  
Stream API (filter, map, reduce)

- Блок 7: Многопоточность

Thread и Runnable  
Synchronized коллекции  
ConcurrentHashMap  