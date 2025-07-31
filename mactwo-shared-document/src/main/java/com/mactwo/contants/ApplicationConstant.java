package com.mactwo.contants;

public final class ApplicationConstant {
    private ApplicationConstant() {}

    /**
     * Trạng thái của một sự kiện trong Outbox Pattern.
     */
    public static class EventStatus {
        public static final String PENDING = "PENDING";
        public static final String SENT = "SENT";
        public static final String FAILED = "FAILED";
    }

    /**
     * Trạng thái của một đơn hàng. Sử dụng String để dễ đọc và mở rộng.
     */
    public static class OrderStatus {
        public static final String PENDING = "PENDING";       // Đang chờ xử lý
        public static final String PROCESSING = "PROCESSING";   // Đang xử lý
        public static final String SHIPPED = "SHIPPED";       // Đã giao hàng
        public static final String DELIVERED = "DELIVERED";     // Đã nhận hàng
        public static final String CANCELLED = "CANCELLED";     // Đã hủy
    }

    /**
     * Các phương thức thanh toán.
     */
    public static class PaymentMethod {
        public static final String COD = "COD"; // Thanh toán khi nhận hàng
        public static final String BANK_TRANSFER = "BANK_TRANSFER"; // Chuyển khoản
        public static final String ONLINE_PAYMENT = "ONLINE_PAYMENT"; // Thanh toán qua cổng online
    }

    /**
     * Loại hành động gây ra sự kiện.
     */
    public static class EventType {
        public static final String CREATE = "CREATE";
        public static final String UPDATE = "UPDATE";
        public static final String DELETE = "DELETE";
    }
}
