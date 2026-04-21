package findmyroomie;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;


    private String aadhar;           
    private String smokingHabit;     
    private String drinkingHabit;    
    private String occupation;       
    private String gender;           
    private String preferredCity;    
    private int age;
    private String bio;              


    public User(int id, String name, String email, String password, String phone, String role,
                String aadhar, String smokingHabit, String drinkingHabit,
                String occupation, String gender, String preferredCity, int age, String bio) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.aadhar = aadhar;
        this.smokingHabit = smokingHabit;
        this.drinkingHabit = drinkingHabit;
        this.occupation = occupation;
        this.gender = gender;
        this.preferredCity = preferredCity;
        this.age = age;
        this.bio = bio;
    }


    public User(int id, String name, String email, String password, String phone, String role) {
        this(id, name, email, password, phone, role,
                "", "NEVER", "NEVER", "", "", "", 0, "");
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getRole() { return role; }
    public String getAadhar() { return aadhar; }
    public String getSmokingHabit() { return smokingHabit; }
    public String getDrinkingHabit() { return drinkingHabit; }
    public String getOccupation() { return occupation; }
    public String getGender() { return gender; }
    public String getPreferredCity() { return preferredCity; }
    public int getAge() { return age; }
    public String getBio() { return bio; }


    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setPassword(String password) { this.password = password; }
    public void setAadhar(String aadhar) { this.aadhar = aadhar; }
    public void setSmokingHabit(String smokingHabit) { this.smokingHabit = smokingHabit; }
    public void setDrinkingHabit(String drinkingHabit) { this.drinkingHabit = drinkingHabit; }
    public void setOccupation(String occupation) { this.occupation = occupation; }
    public void setGender(String gender) { this.gender = gender; }
    public void setPreferredCity(String preferredCity) { this.preferredCity = preferredCity; }
    public void setAge(int age) { this.age = age; }
    public void setBio(String bio) { this.bio = bio; }

    
    public int compatibilityScore(User other) {
        int score = 0;
        if (this.smokingHabit != null && this.smokingHabit.equalsIgnoreCase(other.smokingHabit)) score += 30;
        if (this.drinkingHabit != null && this.drinkingHabit.equalsIgnoreCase(other.drinkingHabit)) score += 30;
        if (this.preferredCity != null && !this.preferredCity.isEmpty()
                && this.preferredCity.equalsIgnoreCase(other.preferredCity)) score += 25;
        if (this.occupation != null && this.occupation.equalsIgnoreCase(other.occupation)) score += 15;
        return score;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name + " | " + email + " | " + role;
    }
}


