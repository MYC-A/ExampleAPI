## Условия задачи
___
Необходимо реализовать микросервис, который будет выполнять роль хранилища различных файлов и их атрибутов.
Микросервис должен предоставлять HTTP API и принимать/отдавать запросы/ответы в формате JSON.


## Реализация
___
+ Языки программирования: Java
+ Веб-фреймворки и библиотеки: Spring Boot, Lombok
+ Работа с базами данных Spring Data JPA, PostgreSQL
+ Тестирование: JUnit
+ Контейнеризация: Docker
+ Формат данных: JSON

## Запуск приложения
___
### Требования для запуска
* Установленные Docker и Docker Compose

### Шаги для запуска

1. **Необходимо клонировать или скачать репозиторий**
    ```shell
    https://github.com/MYC-A/ExampleAPI.git
2. **Перейти в директорию с проектом:**
    ```shell
   cd ExampleAPI
3. **Перейти в директорию docker:**
    ```shell
   cd docker
4. Соберать и запустить контейнеры: 
    ```shell
   docker-compose up --build

## Описание

Микросервис предоставляет HTTP API, которое позволяет пользователям:

- Создавать файлы
- Получать файл по идентификатору
- Получать список всех файлов, отсоритированных по дате 

Настройка UI-интерфейса не требуется, поэтому для тестирования API можно использовать Postman или другой аналогичный инструмент.


## Реализованные API-методы и тестовые запросы
___
### 1. Создание файла <br>
- Адрес: http://localhost:8080/api/put
- HTTP-метод: POST
- Формат запроса: JSON
- При некорректном теле запроса предусмотрено возвращение `JSON` c соответствующей ошибкой

___
Пример корректного входного JSON:
```json
{
  "title": "Report1",
  "data": "TmV3IHJlcG9ydA0K",
  "description": "material consumption",
  "creation_date": "2023-11-16T12:00:00Z"
}
```

Выходной JSON
```json
{
    "id": "1"
}
```
___
Пример некорректного входного JSON:
```json
{
  "data": "TmV3IHJlcG9ydA0K",
  "description": "material consumption",
  "creation_date": "2023-11-16T12:00:00Z"
}
```

Выходной JSON:
```json
{
  "Ошибка валидации": "Необходимо указать название"
}
```
___
### 2. Получение файла

- Адрес: `http://localhost:8080/api/get?id={id}`
- HTTP-метод: GET  
- Входные параметры:
    - `id` - Идентификатор файла, который вы хотите получить.  
- При вводе несуществующего `id` предусмотрено возвращение  `JSON`, который содержит описание ошибки
___
Пример корректного запроса: `http://localhost:8080/api/get?id=1`  
Выходной JSON:
```json

{
  "title": "Report1",
  "data": "TmV3IHJlcG9ydA0K",
  "description": "material consumption",
  "creation_date": "2023-11-16T12:00:00Z"
}
```
---
Пример некорректного запроса: `http://localhost:8080/api/get?id=5` (Файла с данным id нет)  
Выходной JSON:
```json

{
  "Ошибка запроса": "Файл с id = 5 не существует"
}
```
---
### 3. Получение списка файлов отсортированных по времени создания
- Адрес: `http://localhost:8080/api/storage?size={size}&page={page}`
- HTTP-метод: GET
- Входные параметры:
  - `size` - устанавливает количество отображаемых элементов на данной странице (по умолчанию size = 3)
  - `page` - устанавливаетс номер страницы (по умолчанию page = 0)
___
#### Создадим новые файлы для тестирования метода (только они будут рассматриваться в текущем методе)
```json
{
  "title": "Documentation1",
  "data": "RG9jdW1lbnRhdGlvbiBmb3IgdGhlIHByb2plY3Qx",
  "description": "Documentation version 1",
  "creation_date": "2023-11-16T12:00:00Z"
}
```
```json
  {
  "title": "Documentation2",
  "data": "RG9jdW1lbnRhdGlvbiBmb3IgdGhlIHByb2plY3Qx",
  "description": "Documentation version 2",
  "creation_date": "2023-11-16T12:30:00Z"
  }
```
  ```json
  {
  "title": "Documentation3",
  "data": "RG9jdW1lbnRhdGlvbiBmb3IgdGhlIHByb2plY3Qx",
  "description": "Documentation version 3",
  "creation_date": "2024-01-16T12:00:00Z"
  }
  ```
---
Запрос для первой страницы: `http://localhost:8080/api/storage?size=2&page=0`  
Выходной JSON:
```json
[
    {
        "title": "Documentation1",
        "data": "RG9jdW1lbnRhdGlvbiBmb3IgdGhlIHByb2plY3Qx",
        "description": "Documentation version 1",
        "creation_date": "2023-11-16T12:00:00"
    },
    {
        "title": "Documentation2",
        "data": "RG9jdW1lbnRhdGlvbiBmb3IgdGhlIHByb2plY3Qx",
        "description": "Documentation version 2",
        "creation_date": "2023-11-16T12:30:00"
    }
]
```

Запрос для второй страницы: `http://localhost:8080/api/storage?size=2&page=1`  
Выходной JSON:
```json
[
    {
        "title": "Documentation3",
        "data": "RG9jdW1lbnRhdGlvbiBmb3IgdGhlIHByb2plY3Qx",
        "description": "Documentation version 3",
        "creation_date": "2024-01-16T12:00:00"
    }
]
```