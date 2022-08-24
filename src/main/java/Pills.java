import java.util.Objects;

public class Pills {
    int number;
    String serija;

    public Pills(int number, String serija) {
        this.number = number;
        this.serija = serija;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSerija() {
        return serija;
    }

    public void setSerija(String serija) {
        this.serija = serija;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pills pills = (Pills) o;
        return number == pills.number && Objects.equals(serija, pills.serija);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, serija);
    }

    @Override
    public String toString() {
        return   number + "  " +
                 serija;
    }
}
