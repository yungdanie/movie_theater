# movie_theater
В данном проекте был разработать сайт по покупке билетов в кинотеатр.
Для реализации сайта были использованы Spring boot, Thymeleaf, Bootstrap, JDBC.

# Настройка окружения
Для работы с данным проектом вам понадобится:
    1. Java 17;
    2. Maven 3.8;
    3. PostgreSQL: 14.
    4. DBCP2 2.9.0;
    5. JCIP 1.0.

# Инструкция по запуску проекта

Для начала необходимо инициализировать базу данных PostgreSQL, данные для входа в ДБ назначаются в "pom.xml".

После этого выполнить скрипты инициализации таблиц, находящиеся по пути "db/scripts". 

Затем запустить метод "main" класса MovieTheaterApplication. По-умолчанию сервер запускается на порте 8081, управлять 
этим можно в файле "application.properties".

# Изображения страниц приложения

+ Главная страница

![Image alt](https://github.com/yungdanie/movie_theater/raw/master/src/main/resources/img/main_page.png)
____
+ Страница покупки билета

![Image alt](https://github.com/yungdanie/movie_theater/raw/master/src/main/resources/img/buy_ticket.png)
