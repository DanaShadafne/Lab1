public class User {
    private String username;
    private String password;

    public User(String username, String password){
        if (username.length()>50 ){
            throw new IllegalArgumentException("Username is too long, try something shorter");
        }

        if (!username.matches("[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9][a-zA-Z0-9.\\-]*\\.[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("Please enter a valid Email as username");
        }

        if (password.length()< 8) {
            throw new IllegalArgumentException("Your password is too short, add more characters");
        }

        if (password.length()> 12) {
            throw new IllegalArgumentException("Your password is too long, try a shorter one");
        }

        String allowedSymbols = "!@#$%^&*()-_+=";

        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                hasLetter = true;
            } else if (c >= '0' && c <= '9') {
                hasDigit = true;
            } else if (allowedSymbols.indexOf(c) != -1) {
                hasSymbol = true;
            } else {
                throw new IllegalArgumentException(
                        "Please enter a valid password"
                );
            }
        }

        if (!hasLetter || !hasDigit || !hasSymbol) {
            throw new IllegalArgumentException(
                    "Please enter a valid password"
            );
        }

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
