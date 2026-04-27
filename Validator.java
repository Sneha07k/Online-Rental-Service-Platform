package findmyroomie;

import java.util.regex.Pattern;


public class Validator {


    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");


    private static final Pattern AADHAR_PATTERN =
            Pattern.compile("^[2-9]{1}[0-9]{11}$");


    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[6-9][0-9]{9}$");

    
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    
    public static boolean isValidAadhar(String aadhar) {
        return aadhar != null && AADHAR_PATTERN.matcher(aadhar.trim()).matches();
    }

    
    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone.trim()).matches();
    }

    
    public static String getPasswordError(String password) {
        if (password == null || password.length() < 8) {
            return "Password must be at least 8 characters.";
        }
        if (!password.matches(".*[A-Z].*")) {
            return "Password must contain at least one uppercase letter.";
        }
        if (!password.matches(".*[a-z].*")) {
            return "Password must contain at least one lowercase letter.";
        }
        if (!password.matches(".*[0-9].*")) {
            return "Password must contain at least one digit.";
        }
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}|;':\",./<>?].*")) {
            return "Password must contain at least one special character (!@#$%^&* etc).";
        }


        String lower = password.toLowerCase();
        String[] banned = {"password", "123456", "abcdef", "qwerty", "letmein",
                           "111111", "000000", "iloveyou", "admin", "welcome"};
        for (String b : banned) {
            if (lower.contains(b)) {
                return "Password is too common or easy to guess. Avoid sequences like '" + b + "'.";
            }
        }


        if (lower.matches(".*0123.*|.*1234.*|.*2345.*|.*3456.*|.*4567.*|.*5678.*|.*6789.*")) {
            return "Password must not contain sequential numbers.";
        }

        return null; 
    }

    
    public static boolean isStrongPassword(String password) {
        return getPasswordError(password) == null;
    }
}


