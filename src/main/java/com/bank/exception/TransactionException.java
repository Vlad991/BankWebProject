package com.bank.exception;

public class TransactionException extends BusinessExceptions {

    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(Exception e) {
        super(e);
    }

    public TransactionException() {
        super();
    }
}
