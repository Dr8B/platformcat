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

> HTTP-слой подключается через стартер `platform-ui-kit-starter` (тянет
> `spring-boot-starter-web`): при его подключении к приложению поднимается
> встроенный Tomcat и работает `server.port`.

## Структура

```
platformcat/
├── pom.xml                 # parent POM: версии, dependencyManagement, BOM
├── README.md
├── docs/                   # проектная документация (ТЗ, архитектура)
├── platform/               # основной модуль
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/io/github/dr8b/platformcat/
│       │   │   └── PlatformcatApplication.java
│       │   └── resources/
│       │       ├── application.yml
│       │       └── logback-spring.xml
│       └── test/java/io/github/dr8b/platformcat/
│           └── PlatformcatApplicationTests.java
├── platform-ui-kit-starter/  # стартер-плагин UI-kit
│   ├── pom.xml
│   └── src/main/
│       ├── java/io/github/dr8b/platformcat/uikit/
│       │   ├── UiKitAutoConfiguration.java
│       │   └── UiKitProperties.java
│       └── resources/META-INF/spring/
│           └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
└── platform-standard-kit-starter/  # стартер-плагин standard-kit (зависит от ui-kit)
    ├── pom.xml
    └── src/main/
        ├── java/io/github/dr8b/platformcat/standardkit/
        │   ├── StandardKitAutoConfiguration.java
        │   └── StandardKitProperties.java
        └── resources/META-INF/spring/
            └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
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
