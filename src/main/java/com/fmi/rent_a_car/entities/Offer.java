package com.fmi.rent_a_car.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Offer {
    private int id;
    private int carId;
    private int clientId;
    private int weekDaysCount;
    private int weekendDaysCount;
    private BigDecimal totalPrice;
    private boolean isAccepted;
    private boolean isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getWeekDaysCount() {
        return weekDaysCount;
    }

    public void setWeekDaysCount(int weekDaysCount) {
        this.weekDaysCount = weekDaysCount;
    }

    public int getWeekendDaysCount() {
        return weekendDaysCount;
    }

    public void setWeekendDaysCount(int weekendDaysCount) {
        this.weekendDaysCount = weekendDaysCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
