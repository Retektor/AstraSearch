# AstraSearch

## Содержание
- [Установка проекта](#установка-проекта)
- [Зависимости](#зависимости)
- [Подключение базы данных](#подключение-базы-данных)
- [Документация](#документация)

## Установка проекта
1. Устанавливаем необходимые [зависимости](#зависимости)
2. Копируем репозиторий с кодом проекта
3. [Настраиваем базу данных](#подключение-базы-данных)
4. Используя командную консоль, переходим в корень проекта и вводим команду `mvnw spring-boot:run` (Spring использует Maven Wrapper)
5. API будет доступен по адресу `localhost:8080`

## Зависимости
### Обязательно
- [Java Development Kit 25](https://www.oracle.com/java/technologies/downloads/) – официальный набор инструментов, включающий компилятор языка Java
- [PostgreSQL](https://www.postgresql.org/download/) – СУБД, используемая в проекте

### Опционально
- [Docker](https://www.docker.com/products/docker-desktop/) – проверка работы тестов с БД (тесты используют `Testcontainers`)
- [Postman](https://www.postman.com/downloads/) – проверка работы API (в качестве альтернативы можно использовать утилиту `curl`)

## Подключение базы данных
Проект использует **PostgreSQL** – СУБД с клиентско-серверной архитектурой.
Для создания БД:
1. Скачайте и установите [PostgreSQL](https://www.postgresql.org/download/)
2. В корне проекта предоставлен файл `dump.sql` со структурой таблиц и тестовыми данными
3. Создайте пустую БД с названием `astrasearch`
4. Используйте pgAdmin 4 или командную утилиту `psql` для импорта базы данных

Для подключения к БД Spring Framework использует JDBC и параметры, настроенные в файле `application-local.properties` по пути `src/main/resources`. По умолчанию параметры равны:
- Хост: localhost
- Порт: 5432
- База данных: astrasearch
- Пользователь: postgres
- Пароль: <СВОЙ_ПАРОЛЬ>

## Документация
Для взаимодействия с API необходимо использовать средства, которые умеют отправлять HTTP-запросы (`curl`, `Postman`).

На данный момент в API приложения доступны следующие методы:

`(GET)  /api/users/{username}` - возвращает объект пользователя, если такой существует или 404, если его нет в БД.

`(POST) /api/create` - создаёт и возвращает объект небесного тела в зависимости от передаваемых данных. Для подробного описания процесса см. [создание небесного тела](#создание-небесного-тела).

### Описание таблиц базы данных
Пользователи:
| Атрибут | Тип данных | Описание |
|---------|------------|----------|
| id | Auto-increment integer | Скрытый атрибут для PostgreSQL |
| username | String | Имя пользователя |
| password | String | Пароль |
| firstName | String | Имя |
| lastName | String | Фамилия |
| email | String | Электронная почта |

Небесные тела:
| Атрибут | Тип данных | Описание |
|---------|------------|----------|
| id | Auto-increment integer | Скрытый атрибут для PostgreSQL
| userId | Long | ID пользователя, добавившего небесное тело
| name | String | Название небесного тела
| description | String | Описание тела
| bodyType | BodyType (Enum) | Тип тела (доступны PLANET, STAR)
| discoveryTime | LocalDateTime | Время открытия
| imageId | Long | ID изображения, которое можно прикрепить к объекту
| rightAscension | BigDecimal | Прямое восхождение – координата объекта на небе, которая не меняется при вращении Земли (постоянна)
| declination | BigDecimal | Склонение – вторая координата объекта на небе, которая не меняется при вращении Земли (постоянна)

Изображения:
| Атрибут | Тип данных | Описание |
|---------|------------|----------|
| id | Auto-increment integer | Скрытый атрибут для PostgreSQL
| url | String | Ссылка на изображение
| caption | String | Подпись к изображению

Звёзды:
| Атрибут | Тип данных | Описание |
|---------|------------|----------|
| id | Auto-increment integer | Скрытый атрибут для PostgreSQL
| constellation | String | Созвездие
| apparentMagnitude | BigDecimal | Видимая звёздная величина
| absoluteMagnitude | BigDecimal | Абсолютная звёздная величина
| massSolar | BigDecimal | Масса звезды в Солнцах
| radiusSolar | BigDecimal| Радиус звезды в Солнцах
| luminositySolar | BigDecimal | Яркость звезды в Солнцах
| temperature | float | Температура поверхности
| spectralClass | String | Спектральный класс звезды

Планеты:
| Атрибут | Тип данных | Описание |
|---------|------------|----------|
| id | Auto-increment integer | Скрытый атрибут для PostgreSQL
| orbitalPeriodDays | float | Период вращения вокруг звезды в земных днях
| earthMeanRadius | float | Средний радиус в Землях
| earthVolume | float | Объём планеты в Землях
| meanDensity | float | Средняя плотность планеты в Землях
| surfaceGravity | float | Гравитация на поверхности в м/с^2
| sufaceTemperatureKelvin | float | Температура поверхности в Кельвинах


************

### Создание небесного тела
При создании небесного тела используются почти все (кроме id) атрибуты таблицы небесных тел. Помимо этого в запрос добавляется словарь `type_specific_data`. В зависимости от `bodyType`, приложение запрашивает релевантные словари, обеспечивая создание объекта через единую точку входа в API, например:
#### Добавление планеты
```json
{
  "name": "Меркурий",
  "description": "Наименьшая планета Солнечной системы, первая планета от Солнца",
  "body_type": "PLANET",
  "discovery_time": "2026-02-01T00:14:00",
  "image_url": "https://upload.wikimedia.org/wikipedia/commons/3/30/Mercury_in_color_-_Prockter07_centered.jpg",
  "image_caption": "Изображение Меркурия, полученное во время первого пролёта космического аппарата «Мессенджер»",
  "right_ascension": 281.01,
  "declination": 61.41,
  "type_specific_data": {
    "star_id": 1,
    "orbital_period_days": 87.9691,
    "earth_mean": 0.3829,
    "earth_volume": 0.056,
    "mean_density": 5.427,
    "surface_gravity": 3.7,
    "surface_temperature_kelvin": 440.15
  }
}
```
#### Добавление звезды
```json
{
  "name": "Сириус",
  "description": "Самая яркая звезда ночного неба, двойная звезда в созвездии Большого Пса",
  "body_type": "STAR",
  "discovery_time": "1844-01-31T10:30:00",
  "image_url": "https://upload.wikimedia.org/wikipedia/commons/f/f3/Sirius_A_and_B_artwork.jpg",
  "image_caption": "Художественное изображение Сириуса A и его белого карлика Сириус B",
  "right_ascension": 101.287154,
  "declination": -16.71611667,
  "type_specific_data": {
    "constellation": "Большой Пёс",
    "apparent_magnitude": 8.44,
    "absolute_magnitude": 11.18,
    "mass_solar": 1.018,
    "radius_solar": 0.008098,
    "luminosity_solar": 0.02448,
    "temperature": 25014,
    "spectral_class": "A1V"
  }
}
```
