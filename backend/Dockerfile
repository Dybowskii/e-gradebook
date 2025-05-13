# 1. Użyj oficjalnego obrazu JDK
FROM openjdk:17-jdk-slim as build

# 2. Ustaw katalog roboczy
WORKDIR /app

# 3. Skopiuj pliki źródłowe do kontenera
COPY . /app

# 4. Zainstaluj Maven (jeśli nie jest dostępny w obrazie) i zbuduj aplikację
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# 5. Użyj obrazu JDK do uruchomienia aplikacji
FROM openjdk:17-jdk-slim

# 6. Skopiuj plik JAR z poprzedniego etapu
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar /app/app.jar

# 7. Eksponuj port 8080 (domyślny port Spring Boot)
EXPOSE 8080

# 8. Uruchom aplikację Spring Boot (a nie JShell)
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
