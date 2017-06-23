package com.gmail.babanin.aleksey.network;

public class NetworkException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NetworkException() {
        super();
    }
    
    public NetworkException(String codeNetwork, String msg) {
        super("Network with code (" + codeNetwork + ") not created. " + msg);
    }

}
