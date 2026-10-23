import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class UsersApp {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();

        // Read the input file and keep only valid users
        try (Scanner reader = new Scanner(new File("users.txt"))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.trim().split("\\s+");

                if (parts.length != 2) {
                    System.err.println(
                            line + " - Expected username and password"
                    );
                    continue;
                }

                try {
                    User user = new User(parts[0], parts[1]);
                    users.add(user);
                } catch (IllegalArgumentException e) {
                    System.err.println(line + " " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not open users.txt");
            return;
        }

        // Sort valid users by username
        Collections.sort(users,
                (u1, u2) ->
                        u1.getUsername().compareTo(u2.getUsername()));

        // Write valid users to the output file
        try (PrintWriter writer = new PrintWriter("out.txt")) {
            for (User user : users) {
                writer.println(
                        user.getUsername() + " " + user.getPassword()
                );
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not create out.txt");
        }
    }
}
