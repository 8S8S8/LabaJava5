import java.time.LocalDate;

public interface Account {
    public long getNumber();
    public Person getPerson();
    public Tariff getTariff();
    public void setTariff(Tariff tariff);
    public LocalDate getAccountRegDate();
}
