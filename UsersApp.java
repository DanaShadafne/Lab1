import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class UsersApp {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<User> users = new ArrayList<>();

        File file = new File("users.txt");
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] parts = line.trim().split("\\s+");

            if (parts.length != 2) {
                System.err.println(line + " - Expected username and password");
                continue;
            }

            try {
                User user = new User(parts[0], parts[1]);
                users.add(user);
            } catch (IllegalArgumentException e) {
                System.err.println(line + " " + e.getMessage());
            }
        }

        reader.close();
        Collections.sort(users,
                (u1, u2) -> u1.getUsername().compareTo(u2.getUsername()));

        for (User user : users) {
            System.out.println(user.getUsername() + " " + user.getPassword());
        }
    }
}
