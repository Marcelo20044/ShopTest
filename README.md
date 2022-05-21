## Для запуска автотестов нужно: 
### 1. Открыть проект на своей локальной машине
### 2. Запустить контейнеры командой docker-compose up
### 3. Для работы с MySQL:
* Запустить сервис командой java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
* Запустить тесты командой ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app" 
   ###   Для работы с Postgresql:
* Запустить сервис командой java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
* Запустить тесты командой ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"