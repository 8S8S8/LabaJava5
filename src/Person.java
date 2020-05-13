import java.util.Objects;

public class Person implements Cloneable {
    private String fName ;
    private String sName;

    public Person(String fName,String sName){
        Objects.requireNonNull(fName);
        Objects.requireNonNull(sName);

        setfName(fName);
        setsName(sName);
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }


    @Override
    public String toString() {
        return String.format("Фамилия: %s Имя: %s",getfName(),getsName());
    }

    @Override
    public int hashCode() {
        return fName.hashCode() ^ sName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return fName.equals(person.getfName()) && sName.equals(person.getsName());
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}

