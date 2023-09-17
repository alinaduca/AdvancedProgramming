public class Designer extends Person {
    private String favoriteEditingProgram;

    public Designer(String name, String favoriteEditingProgram) {
        super(name);
        this.favoriteEditingProgram = favoriteEditingProgram;
    }

    public Designer(String name, String birthDate, String favoriteEditingProgram) {
        super(name, birthDate);
        this.favoriteEditingProgram = favoriteEditingProgram;
    }

    public String getFavoriteEditingProgram() {
        return this.favoriteEditingProgram;
    }

    @Override
    public String toString() {
        //Cand afisez un designer, voi afisa si programul lui de editare preferat
        if(super.getBirthDate() != null) {
            return "Designer " + super.getName() + " with birthdate " + super.getBirthDate() + " and favorite editing program " + this.favoriteEditingProgram;
        }
        else {
            return super.getName() + " with favorite editing program " + this.favoriteEditingProgram;
        }
    }
}
