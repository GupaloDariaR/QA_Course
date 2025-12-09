package Lesson_6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneGuide {
    private HashMap<String, String> listOfPhonesAndLastnames; // key - phone number, value - last name

    public PhoneGuide () {
        this.listOfPhonesAndLastnames = new HashMap<>();
    }

    public void add(String phoneNumber, String lastname) {
        listOfPhonesAndLastnames.put(phoneNumber, lastname);
    }

    public ArrayList<String> get(String lastname) {
        ArrayList<String> phones = new ArrayList<>();
        for (Map.Entry<String, String> o: listOfPhonesAndLastnames.entrySet()) {
            if (o.getValue().equals(lastname))
                phones.add(o.getKey());
        }
        return phones;
    }
}
