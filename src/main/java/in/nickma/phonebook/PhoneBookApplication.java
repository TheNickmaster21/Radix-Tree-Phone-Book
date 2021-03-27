package in.nickma.phonebook;

import java.io.IOException;

public class PhoneBookApplication {

    public static void main(String[] args) {
        System.out.println("Started");
        PhoneBook phonebook = new PhoneBook();
        try {
            phonebook.fill("src/main/resources/phone_book.csv");
        } catch (IOException e) {
            System.out.println("File Error!");
        }

        System.out.println(phonebook.search("Nick Main"));
        System.out.println(phonebook.search("Fred Spends"));
        System.out.println(phonebook.search("Not Found"));
        System.out.println("Finished");
    }

}

