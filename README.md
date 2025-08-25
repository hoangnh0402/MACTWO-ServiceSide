Nền tảng Thương mại điện tử MACTWO
Chào mừng bạn đến với dự án MACTWO! Đây là một hệ thống backend cho một trang web thương mại điện tử chuyên bán các thiết bị điện tử, được xây dựng dựa trên các kiến trúc hiện đại như Microservices, CQRS và Event-Driven.

🚀 Tổng quan Kiến trúc
Dự án được thiết kế để đảm bảo khả năng mở rộng, hiệu năng cao và dễ bảo trì. Các nguyên lý kiến trúc chính được áp dụng bao gồm:

Microservices: Hệ thống được chia thành các dịch vụ nhỏ, độc lập, mỗi dịch vụ đảm nhiệm một chức năng nghiệp vụ riêng biệt.

CQRS (Command Query Responsibility Segregation): Tách biệt hoàn toàn giữa các luồng xử lý ghi dữ liệu (Command) và đọc dữ liệu (Query), cho phép tối ưu hóa riêng cho từng tác vụ.

Event-Driven Architecture: Các dịch vụ giao tiếp với nhau một cách bất đồng bộ thông qua một hàng đợi sự kiện (Event Bus), giúp giảm sự phụ thuộc và tăng khả năng phục hồi.

Clean Architecture: Cấu trúc code được tổ chức thành các tầng (Domain, Application, Infrastructure) để tách biệt logic nghiệp vụ khỏi các chi tiết kỹ thuật.

## Kiến trúc hệ thống
![Kiến trúc hệ thống](https://raw.githubusercontent.com/hoangnh0402/MACTWO-ServiceSide/main/assets/kientruchethong.png)


🛠️ Công nghệ sử dụng
Backend: Java 17, Spring Boot 3

Database:

    Write: MySQL (triển khai trên Amazon RDS)
    
    Read: Elasticsearch
    
    Messaging: Apache Kafka
    
    Caching: Redis
    
    Bảo mật: Spring Security, JSON Web Token (JWT)
    
    API Documentation: Springdoc OpenAPI (Swagger UI)
    
    Containerization: Docker, Docker Compose
    
    Giám sát & Truy vết (Monitoring & Tracing):
    
    Metrics: Prometheus
    
    Visualization: Grafana
    
    Tracing: Zipkin
    
    Build Tool: Maven

📂 Cấu trúc Dự án
        MACTWO-ServiceSide/
        ├── docker-compose.yml # File triển khai production
        ├── mactwo-command-service/ 
        │ ├── Dockerfile
        │ └── src/
        ├── mactwo-query-service/ 
        │ ├── Dockerfile
        │ └── src/
        └── mactwo-shared-document/ 
        └── src/

🏃 Hướng dẫn Khởi chạy

1. Chạy ở môi trường Local (Để phát triển)
   Yêu cầu:

JDK 17

Maven 3.8+

Docker Desktop

Các bước:

Khởi chạy các dịch vụ hạ tầng:

# Sử dụng file docker-compose-local.yml (nếu có)

docker-compose -f docker-compose-local.yml up -d

Chạy ứng dụng Spring Boot:

Mở project mactwo-command-service bằng IDE (IntelliJ, VSCode,...).

Chạy file MactwoCommandServiceApplication.java. Dịch vụ sẽ chạy trên cổng 8081.

2. Chạy ở môi trường Production (Sử dụng Docker Build)
   Yêu cầu:

Docker

Docker Compose

Các bước:

Clone repository về máy chủ.

Di chuyển vào thư mục gốc của dự án.

Chạy lệnh:

docker-compose up -d --build

Docker sẽ tự động build image từ mã nguồn và khởi chạy toàn bộ hệ thống.

🔗 Các liên kết hữu ích
API Documentation (Swagger UI):

URL: http://<địa_chỉ_server>:8081/api/v1/swagger-ui/index.html

Hệ thống Giám sát:

Prometheus: http://<địa_chỉ_server>:9090

Grafana: http://<địa_chỉ_server>:3000 (user: admin, pass: admin)

Zipkin: http://<địa_chỉ_server>:9411

👥 Tác giả: Nguyễn Huy Hoàng

Cảm ơn bạn đã xem qua dự án!
