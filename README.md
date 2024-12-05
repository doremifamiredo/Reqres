# Арі тесты  Rest Assured 
Тестируемый сервис

https://reqres.in/

_Для демонстрации владения способами проверки данных использовались разные средства._

## Тестовое задание:

- [X] Кейс 1:

Протестировать регистрацию пользователя в системе, необходимо создание 2 тестов:
- успешная регистрация с валидными данными
> _Для получения валидных данных используется [метод](https://github.com/doremifamiredo/Reqres/blob/844f7b46757b187ccf810d982b2e722378707db4/src/test/java/data/DataHelper.java#L16-L30), который возвращает email одного из зарегестрированных и генерирует случайный пароль._
- регистрация с ошибкой из-за отсутствия пароля и проверить,что статус-код 
в ответе `400`
> _[REST assured](https://github.com/doremifamiredo/Reqres/blob/844f7b46757b187ccf810d982b2e722378707db4/src/test/java/data/APIHelper.java#L42-L51) проверяет код ответа, а [JUnit](https://github.com/doremifamiredo/Reqres/blob/844f7b46757b187ccf810d982b2e722378707db4/src/test/java/test/ReqresTest.java#L24-L29) проверяет сообщение тела ответа._

- [X] Кейс 2:

Получить список пользователей страницы 
> _[Этот метод](https://github.com/doremifamiredo/Reqres/blob/844f7b46757b187ccf810d982b2e722378707db4/src/test/java/data/APIHelper.java#L53-L70) реализован с учётом того, что список пользователей расположен на 2 страницах и будет работать с любым количеством страниц._

- Убедиться, что email пользователей имеет окончание @reqres.in
> _Для проверки использовался [Stream API](https://github.com/doremifamiredo/Reqres/blob/844f7b46757b187ccf810d982b2e722378707db4/src/test/java/test/ReqresTest.java#L35)._

- [X] Кейс 3:
Удалить второго пользователя и проверить что статус-код 204
> _Для проверки кода ответа показаны два способа `ResponseSpecification` [REST assured](https://github.com/doremifamiredo/Reqres/blob/844f7b46757b187ccf810d982b2e722378707db4/src/test/java/data/APIHelper.java#L73-L80) и `Assertions` [JUnit](https://github.com/doremifamiredo/Reqres/blob/844f7b46757b187ccf810d982b2e722378707db4/src/test/java/test/ReqresTest.java#L41)._

- [X] Кейс 4:
[Обновить информацию](https://github.com/doremifamiredo/Reqres/blob/844f7b46757b187ccf810d982b2e722378707db4/src/test/java/data/APIHelper.java#L82-L90) о пользователе методом patch и [сравнить дату](https://github.com/doremifamiredo/Reqres/blob/844f7b46757b187ccf810d982b2e722378707db4/src/test/java/test/ReqresTest.java#L47-L50) обновления с текущей датой в системе
> _Для генерации тестовых данных используется библиотека [Java Faker](https://github.com/doremifamiredo/Reqres/blob/844f7b46757b187ccf810d982b2e722378707db4/src/test/java/data/DataHelper.java#L68)._


---    
- [X] В реализации использоввались [request/response specifications](https://github.com/doremifamiredo/Reqres/blob/844f7b46757b187ccf810d982b2e722378707db4/src/test/java/data/APIHelper.java#L16-L28).
