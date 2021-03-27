package in.nickma.phonebook;

import in.nickma.radixtree.RadixTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class PhoneBook {
    private final RadixTree<Long> tree = new RadixTree<>();

    /**
     * @param filePath File with lines in the format of "name,0000000000"
     *                 where lines are already alphabetically sorted.
     */
    public void fill(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();

        while (line != null) {
            String[] lineSplit = line.split(",");
            String name = lineSplit[0];
            Long number = Long.parseLong(lineSplit[1]);
            System.out.println(name + ' ' + number);
            tree.insert(name, number);
            line = reader.readLine();
        }
        reader.close();
    }

    /**
     * @param name Name to search for in the phone book
     * @return Phone number in the form of an Integer for the given name or null
     */
    public Long search(String name) {
        return tree.search(name);
    }

}
