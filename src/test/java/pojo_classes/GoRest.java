package pojo_classes;

public class GoRest {

    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;

    public GoRest(int id, String name, String email, String gender, String status) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    public GoRest() {

    }

    public int getID() { return id; }
    public void setID(int value) { this.id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }

    public String getGender() { return gender; }
    public void setGender(String value) { this.gender = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    @Override
    public String toString()
    {
        return "ClassPOJO [id = "+ id +", name = "+ name+", email = "+ email +", gender = "+ gender + ", status = "+ status +"]";
    }
}