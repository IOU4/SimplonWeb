FROM postgres:15-alpine

WORKDIR /app
COPY ./init.sql .

CMD ["psql", "-f", "./init.sql", "-U", "postgres","-h", "db", "simplon"]
