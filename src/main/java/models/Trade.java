package models;

import java.time.LocalDateTime;

public class Trade {


    private LocalDateTime timestamp;
    private int quantity;
    private TradeType tradeType;
    private double price;

    public Trade(LocalDateTime timestamp, int quantity, TradeType tradeType, double price) {
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.tradeType = tradeType;
        this.price = price;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
