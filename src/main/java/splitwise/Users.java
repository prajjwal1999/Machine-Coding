package splitwise;

public class Users {
    private String id;

    public Users(String id, String name, String email, String phone_number) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String phone_number;
    private  String email;
    private String name;

}
