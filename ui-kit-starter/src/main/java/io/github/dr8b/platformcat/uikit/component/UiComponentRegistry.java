package io.github.dr8b.platformcat.uikit.component;

import java.util.List;

public class UiComponentRegistry {

    private static final String FORMS = "Формы";

    private static final String INDICATORS = "Индикаторы";

    private static final String OVERLAY = "Оверлеи";

    private final List<UiComponent> components = List.of(
            new UiComponent("pc-button", "pc-button", "Кнопка", FORMS,
                    "Кнопка действия с вариантами оформления и размерами.",
                    List.of(
                            new UiComponentAttribute("variant", "string",
                                    "Вариант оформления.", List.of("primary", "secondary", "danger", "ghost")),
                            new UiComponentAttribute("size", "string",
                                    "Размер.", List.of("small", "medium", "large")),
                            new UiComponentAttribute("disabled", "boolean",
                                    "Заблокированное состояние.", List.of("", "disabled"))),
                    List.of(
                            "<pc-button variant=\"primary\">Сохранить</pc-button>",
                            "<pc-button variant=\"secondary\">Отмена</pc-button>",
                            "<pc-button variant=\"danger\">Удалить</pc-button>",
                            "<pc-button variant=\"ghost\">Подробнее</pc-button>",
                            "<pc-button variant=\"primary\" size=\"small\">Small</pc-button>",
                            "<pc-button variant=\"primary\" size=\"large\">Large</pc-button>",
                            "<pc-button variant=\"primary\" disabled>Заблокировано</pc-button>")),

            new UiComponent("pc-input", "pc-input", "Поле ввода", FORMS,
                    "Текстовое поле ввода с меткой и подсказкой.",
                    List.of(
                            new UiComponentAttribute("label", "string",
                                    "Метка поля.", List.of("Имя", "E-mail")),
                            new UiComponentAttribute("placeholder", "string",
                                    "Подсказка внутри поля.", List.of("Введите текст")),
                            new UiComponentAttribute("type", "string",
                                    "Тип поля.", List.of("text", "password", "email", "number")),
                            new UiComponentAttribute("value", "string",
                                    "Значение.", List.of("")),
                            new UiComponentAttribute("disabled", "boolean",
                                    "Заблокированное состояние.", List.of("", "disabled"))),
                    List.of(
                            "<pc-input label=\"Имя\" placeholder=\"Введите имя\"></pc-input>",
                            "<pc-input label=\"E-mail\" type=\"email\" value=\"user@example.com\"></pc-input>",
                            "<pc-input label=\"Пароль\" type=\"password\" placeholder=\"••••••\"></pc-input>",
                            "<pc-input label=\"Отключено\" value=\"нельзя менять\" disabled></pc-input>")),

            new UiComponent("pc-checkbox", "pc-checkbox", "Чекбокс", FORMS,
                    "Флажок для выбора булевого значения.",
                    List.of(
                            new UiComponentAttribute("label", "string",
                                    "Подпись рядом с флажком.", List.of("Согласен с условиями")),
                            new UiComponentAttribute("checked", "boolean",
                                    "Отмечен ли флажок.", List.of("", "checked")),
                            new UiComponentAttribute("disabled", "boolean",
                                    "Заблокированное состояние.", List.of("", "disabled"))),
                    List.of(
                            "<pc-checkbox label=\"Запомнить меня\"></pc-checkbox>",
                            "<pc-checkbox label=\"Подписка на рассылку\" checked></pc-checkbox>",
                            "<pc-checkbox label=\"Недоступно\" disabled></pc-checkbox>")),

            new UiComponent("pc-radio", "pc-radio", "Радиокнопка", FORMS,
                    "Переключатель одного значения из группы (атрибут name).",
                    List.of(
                            new UiComponentAttribute("label", "string",
                                    "Подпись рядом с переключателем.", List.of("Вариант A")),
                            new UiComponentAttribute("name", "string",
                                    "Имя группы для взаимного исключения.", List.of("plan")),
                            new UiComponentAttribute("checked", "boolean",
                                    "Выбран ли элемент.", List.of("", "checked")),
                            new UiComponentAttribute("disabled", "boolean",
                                    "Заблокированное состояние.", List.of("", "disabled"))),
                    List.of(
                            "<pc-radio name=\"plan\" label=\"Базовый\" checked></pc-radio>",
                            "<pc-radio name=\"plan\" label=\"Стандарт\"></pc-radio>",
                            "<pc-radio name=\"plan\" label=\"Премиум\"></pc-radio>")),

            new UiComponent("pc-slider", "pc-slider", "Слайдер", FORMS,
                    "Ползунок для выбора числового значения из диапазона.",
                    List.of(
                            new UiComponentAttribute("min", "number",
                                    "Минимум диапазона.", List.of("0")),
                            new UiComponentAttribute("max", "number",
                                    "Максимум диапазона.", List.of("100")),
                            new UiComponentAttribute("step", "number",
                                    "Шаг изменения.", List.of("1", "5")),
                            new UiComponentAttribute("value", "number",
                                    "Текущее значение.", List.of("50")),
                            new UiComponentAttribute("disabled", "boolean",
                                    "Заблокированное состояние.", List.of("", "disabled"))),
                    List.of(
                            "<pc-slider min=\"0\" max=\"100\" value=\"50\"></pc-slider>",
                            "<pc-slider min=\"0\" max=\"10\" step=\"1\" value=\"3\"></pc-slider>",
                            "<pc-slider min=\"0\" max=\"100\" value=\"70\" disabled></pc-slider>")),

            new UiComponent("pc-upload", "pc-upload", "Загрузка файла", FORMS,
                    "Кнопка выбора файла с отображением выбранного имени.",
                    List.of(
                            new UiComponentAttribute("label", "string",
                                    "Текст кнопки.", List.of("Выберите файл")),
                            new UiComponentAttribute("accept", "string",
                                    "Допустимые типы файлов.", List.of("image/*", ".pdf")),
                            new UiComponentAttribute("multiple", "boolean",
                                    "Разрешить несколько файлов.", List.of("", "multiple")),
                            new UiComponentAttribute("disabled", "boolean",
                                    "Заблокированное состояние.", List.of("", "disabled"))),
                    List.of(
                            "<pc-upload label=\"Выберите файл\"></pc-upload>",
                            "<pc-upload label=\"Загрузить изображения\" accept=\"image/*\" multiple></pc-upload>",
                            "<pc-upload label=\"Недоступно\" disabled></pc-upload>")),

            new UiComponent("pc-spinner", "pc-spinner", "Спиннер", INDICATORS,
                    "Круговой индикатор неопределённой загрузки.",
                    List.of(
                            new UiComponentAttribute("size", "string",
                                    "Размер.", List.of("small", "medium", "large")),
                            new UiComponentAttribute("label", "string",
                                    "Подпись под спиннером.", List.of("Загрузка…"))),
                    List.of(
                            "<pc-spinner size=\"small\"></pc-spinner>",
                            "<pc-spinner size=\"medium\" label=\"Загрузка…\"></pc-spinner>",
                            "<pc-spinner size=\"large\"></pc-spinner>")),

            new UiComponent("pc-loader", "pc-loader", "Лоадер", INDICATORS,
                    "Линейный индикатор загрузки: определённый (value) или бесконечный.",
                    List.of(
                            new UiComponentAttribute("value", "number",
                                    "Прогресс 0–100. Без атрибута — бесконечный.", List.of("40", "75")),
                            new UiComponentAttribute("indeterminate", "boolean",
                                    "Бесконечный режим.", List.of("", "indeterminate"))),
                    List.of(
                            "<pc-loader value=\"40\"></pc-loader>",
                            "<pc-loader value=\"75\"></pc-loader>",
                            "<pc-loader indeterminate></pc-loader>")),

            new UiComponent("pc-banner", "pc-banner", "Баннер", INDICATORS,
                    "Информационное сообщение с вариантами статуса и кнопкой закрытия.",
                    List.of(
                            new UiComponentAttribute("variant", "string",
                                    "Статус.", List.of("info", "success", "warning", "danger")),
                            new UiComponentAttribute("closable", "boolean",
                                    "Показывать кнопку закрытия.", List.of("", "closable"))),
                    List.of(
                            "<pc-banner variant=\"info\">Доступно обновление системы.</pc-banner>",
                            "<pc-banner variant=\"success\">Изменения сохранены.</pc-banner>",
                            "<pc-banner variant=\"warning\" closable>Срок действия скоро истекает.</pc-banner>",
                            "<pc-banner variant=\"danger\" closable>Не удалось выполнить операцию.</pc-banner>")),

            new UiComponent("pc-modal-close", "pc-modal-close", "Кнопка закрытия", OVERLAY,
                    "Иконка-кнопка «крестик» для закрытия модальных окон и баннеров.",
                    List.of(
                            new UiComponentAttribute("size", "string",
                                    "Размер.", List.of("small", "medium", "large")),
                            new UiComponentAttribute("label", "string",
                                    "Доступное имя (aria-label).", List.of("Закрыть"))),
                    List.of(
                            "<pc-modal-close size=\"small\"></pc-modal-close>",
                            "<pc-modal-close size=\"medium\"></pc-modal-close>",
                            "<pc-modal-close size=\"large\"></pc-modal-close>")));

    public List<UiComponent> all() {
        return components;
    }
}
