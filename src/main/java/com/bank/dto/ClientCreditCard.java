package com.bank.dto;

import java.util.Objects;

public class ClientCreditCard {
    private Long clientId;
    private Long creditCardId;

    public ClientCreditCard() {
    }

    public ClientCreditCard(Long clientId, Long creditCardId) {
        this.clientId = clientId;
        this.creditCardId = creditCardId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Long creditCardId) {
        this.creditCardId = creditCardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientCreditCard that = (ClientCreditCard) o;
        return Objects.equals(clientId, that.clientId) &&
                Objects.equals(creditCardId, that.creditCardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, creditCardId);
    }

    @Override
    public String toString() {
        return "ClientCreditCard{" +
                "clientId=" + clientId +
                ", creditCardId=" + creditCardId +
                '}';
    }
}
