package rs.sbnz.gunorama.model;


public class Korisnik extends BaseEntity {

    private String email;

    private String password;

    public Korisnik() {}

    public Korisnik(Integer id, String email, String password) {
        super(id);
        this.email = email;
        this.password = password;
    }

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
}
