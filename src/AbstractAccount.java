import java.time.LocalDate;
import java.util.Objects;
import Exception.IllegalAccountNumber;

public abstract class AbstractAccount implements Account,Cloneable {
    private long number;
    private Person person;
    private Tariff tariff;
    private LocalDate accountRegDate;

    protected AbstractAccount(long number, Person person) {
        this(number,person,new IndividualsTariff(),LocalDate.now());
    }

    public AbstractAccount(long number, Person person, Tariff tariff,LocalDate accountRegDate) {
        this.number = Objects.requireNonNull(number);
        if(number<001000000000001L || number>999999999999999L) throw new IllegalAccountNumber();
        this.person = Objects.requireNonNull(person);
        this.tariff = Objects.requireNonNull(tariff);
        this.accountRegDate = Objects.requireNonNull(accountRegDate);
        if(accountRegDate.isAfter(LocalDate.now()));
    }

    public LocalDate getAccountRegDate(){
        return accountRegDate;
    }

    public long getNumber() {
        return number;
    }

    public Person getPerson() {
        return person;
    }

    public Tariff getTariff() {

        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = Objects.requireNonNull(tariff);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("number:" + getNumber() + '\n');
        sb.append(getTariff().toString()).append('\n');
        sb.append(getAccountRegDate().toString());
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return (int)number * tariff.getSize()*accountRegDate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractAccount absAccount = (AbstractAccount) obj;
        return  absAccount.tariff.equals(this.tariff) &&
                absAccount.person.equals(this.person) &&
                absAccount.accountRegDate.equals(this.accountRegDate);
    }

    public Object clone() throws CloneNotSupportedException{
        return new IndividualAccount(this.getNumber(),this.getPerson(),this.getTariff(),this.getAccountRegDate());
    }
}
