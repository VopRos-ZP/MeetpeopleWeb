# Meetpeople Backend

Бэкенд приложения `Meetpeople`

* [Settings](#settings)
* [Auth](#auth)
  * [Login](#Login) 
  * [Registration](#registration)
* [Endpoints](#endpoints)
  * [Person](#person)
  * [Location](#location)
  * [Comment](#comment)
  * [Dialog](#dialog)
  * [Tag](#tag)
  * [Session](#session)

## Settings
Используйте порт указанный в `resources/application.properties`
```.properties
server.port=8088
```

## Auth
Каждый запрос к бэкенду требует авторизации пользователя, для этого необходимо получить `JWT`
токен и передавать в `Header` любого запроса.

### Login
```http request
# Post
http://localhost:8088/api/v0/auth/login
```
Request Body
```json
{
  "phone": String,
  "password": String
}
```
Ответ

```json
{
  "token": String,
  "result": T
}
```
`T` любой тип данных который вы запросите, сериализованный в `json`

### Registration
```http request
# Post
http://localhost:8088/api/v0/auth/registration
```
```json
{
  // person data
}
```

## Endpoints

Каждый из ниже приведенных классов имеет `endpoint'ы`
где `{{class}}` название класса с маленькой буквы во множественном числе 

Получить все записи
```http request
# GET
http://localhost:8088/api/v0/{{class}}/
```

Получение записи по id
```http request
# GET
http://localhost:8088/api/v0/{{class}}/{{id}}
```

Создать запись
```http request
# POST
http://localhost:8088/api/v0/{{class}}/
```

Обновить запись
```http request
# PUT
http://localhost:8088/api/v0/{{class}}/{{id}}
```

Удалить запись
```http request
# DELETE
http://localhost:8088/api/v0/{{class}}/{{id}}
```


### Person

Заменить `{{class}}` на `persons`

Поля класса:

```json
{
  "id": long,
  "firstname": string,
  "lastname": string,
  "password": string,
  "phone": string,
  "gender": string,
  "birthday": long,
  "location": Location,
  "maritalStatus": string,
  "status": string,
  "about": string,
  "premium": boolean,
  "vkId": string,
  "onlineStatus": string,
  "sessions": []
}
```

## Location
Заменить `{{class}}` на `locations`

Поля класса:

```json
{
  "id": long,
  "country": string,
  "city": string,
  "coordinates": string
}
```

## Comment
Заменить `{{class}}` на `comments`

Поля класса:

```json
{
  "id": long,
  "text": string,
  "author": Person,
  "meeting": Meeting,
  "date": long,
  "isEdited": boolean,
  "reply": Comment?
}
```

## Dialog
Заменить `{{class}}` на `dialogs`

Поля класса:

```json
{
  "id": long,
  "text": string,
  "author": Person,
  "meeting": Meeting,
  "date": long,
  "isEdited": boolean,
  "reply": Dialog?
}
```

## Tag
Заменить `{{class}}` на `tags`

Поля класса:

```json
{
  "id": long,
  "name": string
}
```

## ~~Session~~
_**(В разработке)**_

Заменить `{{class}}` на `sessions`

Поля класса:

```json
{
  "id": long,
  "loginDate": long,
  "ipAddress": string
}
```