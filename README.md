# Процедура запуска авто-тестов:

1) Открыть проект в IntelliJ IDEA
2) Запустить Docker
3) В терминале IntelliJ IDEA выполнить команду "docker-compose up"
4) В терминале IntelliJ IDEA выполнить команду для запуска приложения:
- MySQL: java -jar ./artifacts/aqa-shop.jar --spring.datasource.url=jdbc:mysql://localhost:3306/app
- Postgres: java -jar ./artifacts/aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app
5) В терминале IntelliJ IDEA выполнить команду для прогона автотестов:
- MySQL: ./gradlew clean test -DdbUrl=jdbc:mysql://localhost:3306/app -D dbUser=app -DdbPass=pass
- Postgres: ./gradlew clean test -DdbUrl=jdbc:postgresql://localhost:5432/app -D dbUser=app -DdbPass=pass
6) Для получения отчета в Allure выполнить команду: "./gradlew allureServe"

