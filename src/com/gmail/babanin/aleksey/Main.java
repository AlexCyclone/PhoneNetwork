package com.gmail.babanin.aleksey;

import com.gmail.babanin.aleksey.network.Network;
import com.gmail.babanin.aleksey.network.NetworkException;
import com.gmail.babanin.aleksey.network.Phone;
import com.gmail.babanin.aleksey.network.PhoneException;

public class Main {
    public static void main(String[] args) {
        Network[] network = new Network[3];

        // Create networks
        network[0] = addNetwork("Snake Telecom", "050");
        network[1] = addNetwork("Monkey Talk", "067");
        network[2] = addNetwork("Snake Telecom", "067");
        network[2] = addNetwork("Snake Telecom", "66");
        network[2] = addNetwork("Snake Telecom", "-1");
        network[2] = addNetwork("Snake Telecom", "a");
        network[2] = addNetwork("Snake Telecom", "066");
        System.out.println();

        // Create phones
        Phone[] snakesFull = new Phone[10000];
        Phone[] snakes = new Phone[5];
        Phone[] monkeys = new Phone[5];

        for (int i = 0; i < 5; i += 1) {
            monkeys[i] = addPhone(network[2], true);
            snakes[i] = addPhone(network[1], true);
        }
        System.out.println();
        
        System.out.println("Create phone numbers 0000-9999 in network " + network[0]);
        for (int i = 0; i < 10000; i += 1) {
            snakesFull[i] = addPhone(network[0], false);
        }
        System.out.println();
        
        System.out.println("Try create phone numbers 10000 in network " + network[0]);
        addPhone(network[0], true);
        System.out.println();
        
        // Phone registration
        for (int i = 0; i < 5; i += 1) {
            registration(monkeys[i], true);
            registration(snakes[i], true);
        }
        System.out.println();
        
        System.out.println("Registration all phones in network " + network[0]);
        for (int i = 0; i < 10000; i += 1) {
            registration(snakesFull[i], false);
        }
        System.out.println();

        // Call
        call(snakes[3], "0509745");
        call(monkeys[0], "0507777");
        call(monkeys[0], "0660000");
        call(monkeys[0], "0979545");
        call(monkeys[0], "0669545");
        call(snakes[4], "0505349");

    }

    private static Network addNetwork(String nameOperator, String codeNetwork) {
        Network network = null;
        try {
            network = new Network(nameOperator, codeNetwork);
            System.out.println("Network " + network + " created");
        } catch (NetworkException e) {
            System.out.println(e.toString());
        }
        return network;
    }

    private static Phone addPhone(Network operator, boolean trace) {
        Phone phone = null;
        try {
            phone = new Phone(operator);
            if (trace) {
                System.out.println("Phone " + phone + " created");
            }
        } catch (PhoneException e) {
            System.out.println(e.toString());
        }
        return phone;
    }

    private static void registration(Phone phone, boolean trace) {
        phone.registration();
        if (trace) {
            System.out.println("Phone " + phone + " regisrered in network " + phone.getNetwork());
        }
        
    }

    private static void call(Phone phone, String phoneNumber) {
        System.out.println(phone + "> calling " + phoneNumber + "...");
        String answer = phone.call(phoneNumber);
        System.out.println(phone + "> answer: " + answer);
        System.out.println();
    }
}
