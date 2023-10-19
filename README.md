# **Итоговое задание Exam_Cucumber**
##  _Разработать проект автотестов с использованием Cucumber, maven, Rest Assured, allure_
### В пакете src/test/java/api находятся два класса с шагами:
#### 1. в классе CreateUser находятся шаги, которые описаны в src/test/resources/createuser.feature
##### Задача данного теста: 
######    Создать тест запрос для создания пользователя на сайте [reqres.in/api](https://reqres.in/api/users/ "https://reqres.in/api/users/"), передав в запрос body из ранее созданного файла с расширением .json (файл находится в src/test/resources), поменяв name и добавив поле Job.
######    Проверить, что получили ответ 201. Свериться, что полученный response имеет валидные данные по значениям key и value.
#### 2. в классе ApiSteps находятся шаги, которые описаны в src/test/resources/rickandmorty.feature
##### Задача данного теста: 
######    Найти информацию по персонажу Морти Смит на сайте [RickAndMorty](https://rickandmortyapi.com/api "https://rickandmortyapi.com/api"). Выбрать из ответа последний эпизод, где появлялся этот персонаж. Получить из списка последнего эпизода последнего персонажа. 
######    Получить данные по местонахождению и расе этого персонажа. Проверить, этот персонаж той же расы и находится там же где и Морти?
### Класс TestRunner запускает эти тесты.
***
#### Для запуска теста с предварительной очисткой в терминале введите команду:
#### `mvn clean test`
#### Для запуска сборки отчета в терминале введите команду:
#### `mvn allure:report`
#### Для запуска allure отчета в терминале введите команду:
#### `mvn allure:serve`
#### Для выхода из запуска в терминале введите команду:
#### `Ctrl + C`, а затем введите `Y`
