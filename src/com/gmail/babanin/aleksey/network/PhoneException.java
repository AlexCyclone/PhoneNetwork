package com.gmail.babanin.aleksey.network;

public class PhoneException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public PhoneException() {
        super();
    }
    
    public PhoneException(String msg) {
        super("New phone not created. " + msg);
    }
    
}
