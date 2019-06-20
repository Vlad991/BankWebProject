package com.bank.dto;

import java.util.Objects;

public class CreditCard {
    private Long id; // 1414 0000 0000 0000
    private String date;  // 00/00
    private Long clientId;
    private int code;  // 000
    private int pin;
    private Long sum;
    private CreditCardStatus status;

    public CreditCard() {
        status = CreditCardStatus.UNREGISTERED;
    }

    public CreditCard(Long id, String date, Long clientId, int code, int pin, Long sum) {
        this.id = id;
        this.date = date;
        this.clientId = clientId;
        this.code = code;
        this.pin = pin;
        this.sum = sum;
        status = CreditCardStatus.OPEN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public CreditCardStatus getStatus() {
        return status;
    }

    public void setStatus(CreditCardStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return code == that.code &&
                pin == that.pin &&
                Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(sum, that.sum) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, clientId, code, pin, sum, status);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", clientId=" + clientId +
                ", code=" + code +
                ", pin=" + pin +
                ", sum=" + sum +
                ", status=" + status +
                '}';
    }
}
