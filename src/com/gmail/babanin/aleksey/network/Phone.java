package com.gmail.babanin.aleksey.network;

import java.util.Formatter;

public class Phone {
    private String number;
    private Network network;
    private boolean busy;
    private boolean registered;

    public Phone() {
        super();
    }

    public Phone(Network network) {
        super();
        this.number = getNewPhoneNumber(network);
        this.network = network;
        busy = false;
        registered = false;
    }

    private static final String getNewPhoneNumber(Network network) {
        int number = network.getNextNumber();
        if (number < 0) {
            throw new PhoneException("End of phone number space.");
        }
        Formatter f = new Formatter();
        String phoneNumber = f.format("%04d", number).toString();
        f.close();
        return phoneNumber;
    }

    public String getNumber() {
        return number;
    }

    public Network getNetwork() {
        return network;
    }

    protected boolean isBusy() {
        return busy;
    }

    public String toString() {
        return "(" + this.network.getCodeNetwork() + ") " + number;
    }

    public void registration() {
        if (this.registered) {
            return;
        }
        this.network.registration(this);
        this.registered = true;
    }

    public String call(String phoneNumber) {
        if (!this.registered) {
            return "This phone isn't registered in network.";
        }

        try {
            int number = Integer.parseInt(phoneNumber);
            if ((number < 0) || (number > 9999999)) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            return "Invalid phone number format.";
        }

        this.busy = true;
        String answer = network.call(this, phoneNumber);
        this.busy = false;
        return answer;
    }

    protected String incomingCall(Phone caller) {
        if (this.isBusy()) {
            return "Busy...";
        }

        System.out.println(this + "> " + caller + " calling");
        return "Ok!";
    }

}
