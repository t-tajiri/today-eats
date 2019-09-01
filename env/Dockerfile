FROM mysql:8

# TODO create new user with non-super user privileges
ENV MYSQL_ROOT_PASSWORD root
ENV MYSQL_DATABASE today_eats

COPY ./sql /docker-entrypoint-initdb.d
COPY ./my.cnf /etc/mysql/conf.d/my.cnf

EXPOSE 3306
