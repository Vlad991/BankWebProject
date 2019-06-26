package com.bank.exception;

public class BusinessExceptions extends RuntimeException {
    BusinessExceptions(String message) {
        super(message);
    }

    BusinessExceptions(Exception ex) {
        super(ex);
    }

    BusinessExceptions() {
        super();
    }
}
