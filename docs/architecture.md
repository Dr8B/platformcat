# Архитектура

## Обзор

`platformcat` — многомодульный Maven-проект на Spring Boot. Родительский POM
задаёт версии платформы и управление зависимостями; прикладной код размещается
в модулях.

## Архитектурные принципы

1. **Spring Boot как платформа.** Ядро приложения — тонкая платформа на базе
   Spring Boot. Вся прикладная функциональность строится поверх неё.
2. **Фича = стартер (плагин).** Каждая новая фича оформляется как отдельный
   Spring Boot стартер: собственный модуль с автоконфигурацией
   (`@AutoConfiguration`, `META-INF/spring/...AutoConfiguration.imports`).
   Подключение фичи = добавление зависимости на её стартер; ядро о конкретных
   фичах не знает.

   **Что такое фича.** Фича — комплексное решение конкретной задачи целиком
   (например: основное хранение данных в БД, авторизация пользователя, проверка
   ACL объектов). Отдельное дополнение, изменение или частная функциональность
   фичей не является — это составляющая какой-то фичи и живёт внутри её стартера,
   отдельного модуля под неё не заводится.

   **Зависимости между стартерами.** Стартеры могут зависеть друг от друга — это
   нормально и ожидаемо. Например, стартер проверки ACL объектов может зависеть
   от стартера авторизации пользователя и от стартера хранения данных в БД. Такие
   зависимости выражаются обычными Maven-зависимостями между модулями-стартерами.
3. **Spring-first.** Сначала используем встроенные возможности Spring, затем —
   самописные решения. Примеры:
   - логика на событиях — шина событий Spring
     (`ApplicationEventPublisher`, `@EventListener`), а не свой механизм;
   - конфигурация — `@ConfigurationProperties`;
   - расширяемость — DI и внедрение коллекций бинов вместо ручных реестров.
4. **SOLID.** Код максимально соответствует принципам SOLID: узкие интерфейсы,
   зависимость от абстракций, расширение через новые стартеры без изменения
   ядра (open/closed).

### Скелет фича-стартера

```
feature-foo/
├── pom.xml
└── src/main/
    ├── java/io/github/dr8b/platformcat/foo/
    │   ├── FooAutoConfiguration.java   # @AutoConfiguration + @ConditionalOn...
    │   └── FooProperties.java          # @ConfigurationProperties("platformcat.foo")
    └── resources/META-INF/spring/
        └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
```

## Модули

| Модуль        | Назначение                                    |
|---------------|-----------------------------------------------|
| `platformcat` | Родительский POM: версии, `dependencyManagement`, импорт BOM `spring-boot-dependencies`. |
| `platform`    | Основной модуль приложения. Точка входа — `PlatformcatApplication`. |
| `platform-ui-kit-starter` | Стартер-плагин UI-kit: `@AutoConfiguration` + `@ConfigurationProperties("platformcat.ui-kit")`. Зависит от `spring-boot-starter-web`. |
| `platform-standard-kit-starter` | Стартер-плагин standard-kit: `@AutoConfiguration` + `@ConfigurationProperties("platformcat.standard-kit")`. Зависит от `platform-ui-kit-starter`; подключён к `platform`. |

## Управление зависимостями

- Версии зависимостей задаются в parent POM в секции `dependencyManagement`.
- В модулях зависимости объявляются без версий.
- Если версия уже управляется импортированным BOM (`spring-boot-dependencies`),
  повторно её прописывать не нужно.

## Конфигурация

- `application.yml` — параметры приложения (имя, уровни логирования).
- `logback-spring.xml` — конфигурация логирования: только консольный аппендер
  (stdout).

## Логирование

SLF4J как API, Logback как реализация. Уровни: `root = INFO`,
`io.github.dr8b.platformcat = DEBUG`.

## Развитие

- HTTP-слой подключён через стартер `platform-ui-kit-starter` (зависит от
  `spring-boot-starter-web`): при его подключении поднимается встроенный Tomcat
  и работает `server.port`.
- Прикладные слои (controller/service/repository) добавляются в модуль
  `platform` по мере формирования требований (см. [tz.md](tz.md)).
