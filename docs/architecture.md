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
| `app`         | Основной модуль приложения. Точка входа — `Main`. |
| `ui-kit-starter` | Стартер-плагин UI-kit: `@AutoConfiguration` + `@ConfigurationProperties("io.github.dr8b.platformcat.ui-kit-starter")`. Набор веб-компонентов (`pc-*`), контроллер выдачи их `.js`, Thymeleaf-галерея и собственный `Main` для standalone-запуска. Зависит от `spring-boot-starter-web`, `spring-boot-starter-thymeleaf`. |
| `standard-kit-starter` | Стартер-плагин standard-kit: `@AutoConfiguration` + `@ConfigurationProperties("io.github.dr8b.platformcat.standard-kit-starter")`. Зависит от `ui-kit-starter`; подключён к `app`. |

## Управление зависимостями

- Версии зависимостей задаются в parent POM в секции `dependencyManagement`.
- В модулях зависимости объявляются без версий.
- Если версия уже управляется импортированным BOM (`spring-boot-dependencies`),
  повторно её прописывать не нужно.

## Конфигурация

- `application.yml` — параметры приложения (имя, уровни логирования).
- `logback-spring.xml` — конфигурация логирования: только консольный аппендер
  (stdout).
- Префикс конфигурационных свойств стартера = `groupId.artifactId`
  (например, `io.github.dr8b.platformcat.ui-kit-starter`). Он же используется в
  `@ConfigurationProperties` и `@ConditionalOnProperty`.

## UI Kit

Модуль `ui-kit-starter` поставляет набор веб-компонентов (нативные custom
elements с префиксом `pc-`): `pc-button`, `pc-input`, `pc-checkbox`, `pc-radio`,
`pc-slider`, `pc-upload`, `pc-spinner`, `pc-loader`, `pc-banner`,
`pc-modal-close`.

- **Исходники** компонентов — `resources/ui-kit/static/<tag>.js`.
- **Выдача статики**: `UiKitComponentController` отдаёт их как `text/javascript`
  по пути `/<groupId>/<artifactId>/static/<component>.js`, то есть
  `/io.github.dr8b.platformcat/ui-kit-starter/static/pc-button.js`. Базовый путь
  централизован в классе `UiKit`.
- **Метаданные** (описание, атрибуты, примеры значений) — `UiComponentRegistry`.
- **Галерея**: `UiKitGalleryController` + Thymeleaf-шаблон `ui-kit/gallery.html`
  на `/<groupId>/<artifactId>`. Слева — дерево компонентов по категориям,
  сверху по центру — превью выбранного компонента, снизу — таблица атрибутов и
  примеры разметки. Контроллер галереи создаётся только при заданной настройке
  `io.github.dr8b.platformcat.ui-kit-starter.gallery` (по умолчанию выключена):
  когда стартер подключён как зависимость, галерея не поднимается.
- **Standalone-запуск**: `io.github.dr8b.platformcat.uikit.Main`
  (`mvn -pl ui-kit-starter spring-boot:run`) — поднимает только этот модуль, всё
  остальное приходит через автоконфигурацию. `Main` перед стартом Spring Boot
  выставляет системную проперть `…ui-kit-starter.gallery=true`, поэтому в
  standalone-режиме галерея доступна. Контроллеры и реестр регистрируются в
  `UiKitAutoConfiguration` (`@Import` + `@Bean`), поэтому доступны и при
  подключении стартера как зависимости.

## Логирование

SLF4J как API, Logback как реализация. Уровни: `root = INFO`,
`io.github.dr8b.platformcat = DEBUG`.

## Развитие

- HTTP-слой подключён через стартер `ui-kit-starter` (зависит от
  `spring-boot-starter-web`): при его подключении поднимается встроенный Tomcat
  и работает `server.port`.
- Прикладные слои (controller/service/repository) добавляются в модуль
  `app` по мере формирования требований (см. [tz.md](tz.md)).
