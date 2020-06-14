package organizer.app.data.data;
import com.google.gson.annotations.SerializedName;


public class Person
{
    @SerializedName("personId")
    public String PersonId;
    @SerializedName("name")
    public String Name;
    @SerializedName("phoneNumber")
    public String PhoneNumber;
    @SerializedName("address")
    public String Address;


    public Person(String PersonId ,String Name, String Address, String PhoneNumber)
    {
        this.Name = Name;
        this.PersonId = PersonId;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
    }

    public Person()
    {

    }

    public void setName(String name)
    {
        this.Name = name;
    }

    public void setPersonId(String id)
    {
        this.PersonId = PersonId;
    }

    public void setAddress(String address)
    {
        this.Address = address;
    }

    public void setPhoneNumber(String PhoneNumber)
    {
        this.PhoneNumber = PhoneNumber;
    }

    public String getPersonId()
    {
        return PersonId;
    }

    public String getName()
    {
        return Name;
    }

    public String getPhoneNumber()
    {
        return PhoneNumber;
    }

    public String getAddress()
    {
        return Address;
    }

}

