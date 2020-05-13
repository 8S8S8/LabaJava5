import java.time.LocalDate;
import java.util.Objects;

final class Service implements  Cloneable{
    private String title;
    private double cost;
    private ServiceTypes serviceTypes;
    private LocalDate serviceActivateDate;

    public Service() {
        this("интернет 100мб\\сек", 300, ServiceTypes.INTERNET,LocalDate.now());
    }

    public Service(String title, double cost, ServiceTypes serviceType, LocalDate date) {
        this.title = Objects.requireNonNull(title);
        this.cost = Objects.requireNonNull(cost);
        if(cost<0) throw new IllegalArgumentException("Cost cant less than zero");
        this.serviceTypes = Objects.requireNonNull(serviceType);
        this.serviceActivateDate = Objects.requireNonNull(date);
        if(date.isAfter(LocalDate.now())) throw new IllegalArgumentException("Incorrect date");
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        if(serviceActivateDate.plusMonths(1).isAfter(LocalDate.now())){
            cost+=LocalDate.now().getDayOfYear()-serviceActivateDate.getDayOfYear() *
                    cost/LocalDate.now().getDayOfMonth();
        }
        return cost;
    }

    public ServiceTypes getServiceTypes() {
        return serviceTypes;
    }

    public LocalDate getServiceActivateDate(){
        return serviceActivateDate;
    }

    @Override
    public String toString() {
        return "Наименование услуги: " + getTitle() + "\\" +
                getCost() + " р. ("+serviceTypes +") Activating Date: "+serviceActivateDate.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Double.compare(service.cost, cost) == 0 &&
                Objects.equals(title, service.title) &&
                serviceTypes == service.serviceTypes &&
                serviceActivateDate.equals(service.getServiceActivateDate());
    }

    @Override
    public int hashCode() {
        Double cost = getCost();
        return title.hashCode() *
                cost.hashCode() *
                serviceTypes.hashCode() *
                serviceActivateDate.hashCode();
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
