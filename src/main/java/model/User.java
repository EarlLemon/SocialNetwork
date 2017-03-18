package model;

public class User {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String username;
    private final String groupN;


    public User(int id, String firstName, String lastName, String email, String password, String groupN, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.groupN = groupN;
        this.username = username;

    }



    @Override
    public boolean equals(Object o) {
        //Self Check perfomance benefit
        if (this == o) {
            return true;
        }
        //Null Check  type check and cast
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        if (password != null ? !password.equals(user.password) : user.password != null) {
            return false;
        }
        if (groupN != null ? !groupN.equals(user.groupN) : user.groupN != null) {
            return false;
        }
        return username != null ? username.equals(user.username) : user.username == null;
    }

    // переопределение хеша по заветам Блоха
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (groupN != null ? groupN.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getGroupN() {
        return groupN;
    }

    public int getId() {
        return id;
    }


}

