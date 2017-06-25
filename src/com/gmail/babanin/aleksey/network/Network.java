package com.gmail.babanin.aleksey.network;

import java.util.ArrayList;

public class Network {
    private static ArrayList<Network> existingNetworks = new ArrayList<Network>();
    private String nameOperator;
    private String codeNetwork;
    private int maxNumber;
    private ArrayList<Phone> registeredPhones;

    public Network() {
        super();
    }

    public Network(String nameOperator, String codeNetwork) {
        super();
        this.codeNetwork = checkCodeNetwork(codeNetwork);
        this.nameOperator = nameOperator;
        this.maxNumber = -1;
        this.registeredPhones = new ArrayList<Phone>();
        existingNetworks.add(this);
    }

    private static final String checkCodeNetwork(String codeNetwork) {
        if (!checkCodeFormat(codeNetwork)) {
            throw new NetworkException(codeNetwork, "Invalid code format.");
        }

        for (Network network : existingNetworks) {
            if (network.getCodeNetwork().equals(codeNetwork)) {
                throw new NetworkException(codeNetwork, "Code network alredy in use.");
            }
        }
        return codeNetwork;
    }

    private static final boolean checkCodeFormat(String codeNetwork) {
        try {
            if (codeNetwork.length() != 3 || Integer.parseInt(codeNetwork) < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public String getCodeNetwork() {
        return codeNetwork;
    }

    public String getNameOperator() {
        return nameOperator;
    }

    protected int getNextNumber() {
        if (this.maxNumber == 9999) {
            return -1;
        } else {
            this.maxNumber += 1;
            return this.maxNumber;
        }
    }

    public String toString() {
        return "(" + this.codeNetwork + ") \"" + this.nameOperator + "\"";
    }

    protected void registration(Phone phone) {
        for (Phone phones : registeredPhones) {
            if (phones == phone) {
                return;
            }
        }
        registeredPhones.add(phone);
    }

    protected String call(Phone caller, String phoneNumber) {
        Network dialedNetwork;
        dialedNetwork = findNetwork(phoneNumber);
        if (dialedNetwork == null) {
            return "Dialed network is not available";
        }

        return dialedNetwork.commutation(caller, phoneNumber);
    }

    private Network findNetwork(String phoneNumber) {
        String codeNetwork = phoneNumber.substring(0, 3);
        for (Network network : existingNetworks) {
            if (codeNetwork.equals(network.getCodeNetwork())) {
                return network;
            }
        }
        return null;
    }

    private String commutation(Phone caller, String phoneNumber) {
        if (!isNumberExist(phoneNumber)) {
            return "Dialed number does not exist";
        }

        Phone called;
        called = findPhone(phoneNumber);
        if (called == null) {
            return "The subscriber is unavailable";
        }

        return called.incomingCall(caller);
    }

    private boolean isNumberExist(String phoneNumber) {
        int shortDecPhoneNumber = Integer.parseInt(phoneNumber) % 10000;
        if (shortDecPhoneNumber > this.maxNumber) {
            return false;
        } else {
            return true;
        }
    }

    private Phone findPhone(String phoneNumber) {
        String shortPhoneNumber = phoneNumber.substring(3, 7);

        for (Phone phones : registeredPhones) {
            if (shortPhoneNumber.equals(phones.getNumber())) {
                return phones;
            }
        }
        return null;
    }

}