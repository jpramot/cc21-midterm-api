# ใช้ OpenJDK 17
FROM eclipse-temurin:21-jdk-alpine

# ตั้ง working directory
WORKDIR /app

# copy project ทั้งหมด
COPY . .

# build project
RUN ./mvnw clean package -DskipTests

# expose port
EXPOSE 8080

# start spring boot
CMD java -Dserver.port=$PORT -jar target/api-0.0.1-SNAPSHOT.jar
