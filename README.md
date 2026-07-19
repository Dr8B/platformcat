# platformcat

Расширяемая платформа на базе Spring Boot: сам Spring Boot — платформа, а каждая
фича подключается как отдельный стартер-плагин. Код следует принципам SOLID и в
первую очередь опирается на встроенные возможности Spring. Подробнее — в
[ТЗ](docs/tz.md) и [архитектуре](docs/architecture.md).

## Стек

- **Java**: 25
- **Spring Boot**: 4.1.0
- **Сборка**: Maven (multi-module)
- **Логирование**: SLF4J + Logback

> Веб-стартер не подключён — встроенного HTTP-сервера сейчас нет.

## Структура

```
platformcat/
├── pom.xml                 # parent POM: версии, dependencyManagement, BOM
├── README.md
├── docs/                   # проектная документация (ТЗ, архитектура)
└── platform/               # основной модуль
    ├── pom.xml
    └── src/
        ├── main/
        │   ├── java/com/example/
        │   │   └── PlatformcatApplication.java
        │   └── resources/
        │       ├── application.yml
        │       └── logback-spring.xml
        └── test/java/com/example/
            └── PlatformcatApplicationTests.java
```

## Быстрый старт

### Сборка
```bash
mvn clean package
```

### Запуск
```bash
mvn -pl platform spring-boot:run
```

### Тесты
```bash
mvn test
```

## Документация

- [Техническое задание](docs/tz.md)
- [Архитектура](docs/architecture.md)

## Соглашения разработки

Правила для работы (в т.ч. для ассистента) описаны в [CLAUDE.md](CLAUDE.md):
управление версиями зависимостей через parent POM и BOM, порядок коммитов,
фиксация изменений требований в документации.
