# File: mactwo-command-service/Dockerfile (Cập nhật)
# SỬ DỤNG MULTI-STAGE BUILD ĐỂ TỐI ƯU HÓA KÍCH THƯỚC IMAGE

# --- Giai đoạn 1: Build ứng dụng ---
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

# Sao chép mã nguồn của cả hai project
COPY mactwo-shared-document ./mactwo-shared-document
COPY mactwo-command-service ./mactwo-command-service

# Build và cài đặt thư viện shared vào local Maven repo (.m2) trước
RUN mvn -f mactwo-shared-document/pom.xml clean install

# Sau đó, build service chính. Maven sẽ tìm thấy dependency trong local repo.
RUN mvn -f mactwo-command-service/pom.xml clean install -DskipTests

# --- Giai đoạn 2: Chạy ứng dụng ---
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# SỬA LỖI: Sử dụng wildcard (*) để sao chép file .jar một cách linh hoạt.
# Điều này giúp tránh lỗi "not found" nếu tên file có thay đổi nhỏ.
COPY --from=build /app/mactwo-command-service/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]