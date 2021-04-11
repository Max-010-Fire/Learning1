import java.util.Date;

public class Human {
    String lastName;
    String firstName;
    Address address;
    Date birthday;

    public Human(String lastName, String firstName, Address address, Date birthday) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.birthday = birthday;
    }
}
