public interface Tariff {
    public boolean add(Service service);
    public boolean add(Service service, int number);
    public Service get(int index);
    public Service get(String title);
    public boolean isContain(String  title);
    public Service set(int index,Service service);
    public Service delete(int index);
    public int getSize();
    public Service[] getServices();
    public Service[] SortedServicesByCost();
    public double cost();
    public Service[] getTypedServices(ServiceTypes serviceTypes);
    String toString();
    int hashCode();
    boolean equals(Object obj);
    public Tariff clone() throws CloneNotSupportedException;
    public boolean delete(Service service);
    public int indexOf(Service service);
    public int lastIndexOf(Service service);
}
