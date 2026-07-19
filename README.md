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

> HTTP-слой подключается через стартер `ui-kit-starter` (тянет
> `spring-boot-starter-web`): при его подключении к приложению поднимается
> встроенный Tomcat и работает `server.port`.

## Структура

```
platformcat/
├── pom.xml                 # parent POM: версии, dependencyManagement, BOM
├── README.md
├── docs/                   # проектная документация (ТЗ, архитектура)
├── app/                    # основной модуль
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/io/github/dr8b/platformcat/
│           │   └── Main.java
│           └── resources/
│               ├── application.yml
│               └── logback-spring.xml
├── ui-kit-starter/         # стартер-плагин UI-kit (компоненты + выдача .js)
│   ├── pom.xml
│   └── src/main/
│       ├── java/io/github/dr8b/platformcat/uikit/
│       │   ├── UiKit.java                 # базовые пути (groupId/artifactId)
│       │   ├── config/                    # автоконфигурация и свойства
│       │   ├── controllers/               # выдача компонентов как .js
│       │   ├── services/                  # реестр компонентов
│       │   └── model/                     # модель компонентов
│       └── resources/
│           ├── META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
│           └── ui-kit/static/             # исходники веб-компонентов pc-*.js
├── ui-kit-gallery/         # standalone-приложение галереи (зависит от ui-kit-starter)
│   ├── pom.xml
│   └── src/main/
│       ├── java/io/github/dr8b/platformcat/uikit/gallery/
│       │   ├── Main.java                  # standalone-запуск галереи
│       │   └── controllers/               # контроллер галереи
│       └── resources/
│           └── templates/ui-kit/gallery.html
└── standard-kit-starter/   # стартер-плагин standard-kit (зависит от ui-kit)
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
mvn -pl app spring-boot:run
```

### Тесты
```bash
mvn test
```

### Галерея UI Kit
Галерея живёт в отдельном модуле `ui-kit-gallery` (standalone-приложение,
зависит от `ui-kit-starter`). Сам стартер `ui-kit-starter` галерею не содержит —
только компоненты и выдачу их `.js`. Запуск галереи:
```bash
mvn -pl ui-kit-gallery spring-boot:run
```
Затем открыть <http://localhost:8080/io.github.dr8b.platformcat/ui-kit-starter>.
Компоненты выбираются в дереве слева, превью — сверху по центру, атрибуты и
примеры значений — снизу. Сами компоненты отдаются как `.js` по адресу
`/io.github.dr8b.platformcat/ui-kit-starter/static/<component>.js`.

## Документация

- [Техническое задание](docs/tz.md)
- [Архитектура](docs/architecture.md)

## Соглашения разработки

- Префикс конфигурационных свойств стартера = `groupId.artifactId` (например,
  `io.github.dr8b.platformcat.ui-kit-starter`). Тот же префикс используется в
  `@ConfigurationProperties` и `@ConditionalOnProperty`.

Остальные правила для работы (в т.ч. для ассистента) описаны в
[CLAUDE.md](CLAUDE.md): управление версиями зависимостей через parent POM и BOM,
порядок коммитов, фиксация изменений требований в документации.
