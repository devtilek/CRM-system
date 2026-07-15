# План: Исправление логики добавления курса

## Контекст
В настоящее время метод `addCourse` в сервисе `CourseImpl` не привязывает создаваемый курс к пользователю (`userId` принимается в контроллере, но игнорируется в сервисе). Нужно реализовать корректную привязку сущности `Course` к `User`.

## Предлагаемый подход
1. В `CourseImpl.addCourse` добавить поиск `User` по `userId` через `UserRepo`.
2. Если пользователь не найден, выбросить `EntityNotFoundException`.
3. Установить найденного пользователя в сущность `Course` (`course.setUser(user)`).
4. Сохранить сущность.

## Файлы для модификации
- `C:/Users/aktil/Desktop/JavaPractice/SpringCRM/src/main/java/practice/springcrm/service/impl/CourseImpl.java`

## Верификация
- Протестировать эндпоинт `POST /courses/addCourse` через Postman/Swagger, убедившись, что в БД в таблице `course` поле `user_id` заполняется корректно.
