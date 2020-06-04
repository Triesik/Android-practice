package organizer.app.data.data;

public class Person {

    private String name;
    private int id;
    private String phoneNumber;

    public Person(int id, String name, String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.name = name;
    }

    public Person()
    {

    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String password) {

        this.phoneNumber = password;
    }

    public void setName(String name) {

        this.name = name;
    }


    public String getName() {

        return name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

}
