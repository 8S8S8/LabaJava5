import java.time.LocalDate;

public class IndividualAccount extends AbstractAccount implements Account,Cloneable{

    //Проверка на нулы есть в супер классе!
    public IndividualAccount(long number, Person person) {
        super(number,person);
    }

    public IndividualAccount(long number, Person person, Tariff tariff, LocalDate date) {
        super(number, person, tariff, date);
    }

    @Override
    public String toString() {
        return "Individual account\n" +
                "holder: "+getPerson().toString()+
                '\n'+super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode()*97*getPerson().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
