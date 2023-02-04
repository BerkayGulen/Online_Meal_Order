package models;

public class User {
    private String email;
    private String password;

    //CONSTRUCTORS
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public  User(){}

    //Accessors and Mutators
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //ToString
    @Override
    public String toString() {
        return "email: "+email+"\n"+"password: "+password;
    }
}
