public class Programmer extends Person {
    private String favoriteIDE;

    public Programmer(String name, String favoriteIDE) {
        super(name);
        this.favoriteIDE = favoriteIDE;
    }

    public Programmer(String name, String birthDate, String favoriteIDE) {
        super(name, birthDate);
        this.favoriteIDE = favoriteIDE;
    }

    public String getFavoriteIDE() {
        return this.favoriteIDE;
    }

    @Override
    public String toString() {
        //Cand afisez un programator, voi afisa si IDE-ul lui preferat
        if(super.getBirthDate() != null) {
            return "Programmer " + super.getName() + " with birthdate " + super.getBirthDate() + " and favorite IDE " + this.favoriteIDE;
        }
        else {
            return super.getName();
        }
    }
}
