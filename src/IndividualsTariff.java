import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class IndividualsTariff implements  Tariff,Cloneable{
    private Service[] services;
    private int size;

    public IndividualsTariff() {
        setServices(new Service[8]);
        setSize(0);
    }

    public IndividualsTariff(int size) {
        setServices(new Service[size]);
        setSize(size);
    }

    public IndividualsTariff(Service[] services) {
        setServices(services);
        size = services.length;
    }

    public boolean add(Service service){
        Objects.requireNonNull(service);
        if(checkFreeSpace()){
            extend();
            return false;
        }else{
            addService(service);
            size++;
            return true;
        }
    }

    private void addService(Service service){
        Objects.requireNonNull(service);
        for(int i = 0; i<services.length;i++){
            if(services[i] == null){
                services[i] = service;
                return;
            }
        }
    }

    private  boolean checkFreeSpace(){
        return  size == services.length;
    }

    public boolean add(Service service, int index){
        Objects.requireNonNull(service);
        if(index<0 || index>=services.length) throw new IndexOutOfBoundsException();
        else{
            if(services[index]==null){
                services[index] = service;
                size++;
                return true;
            }else{
                return false;
            }
        }
    }

    public Service get(int index){
        if(index<0 || index>=services.length) throw new IndexOutOfBoundsException();
        return services[index];
    }

    public Service get(String title){
        Objects.requireNonNull(title);
        for(Service service : services){
            if(service.getTitle().equals(title))
                return service;
        }
        throw new NoSuchElementException();
    }

    public  boolean isContain(String title){
        Objects.requireNonNull(title);
        for(Service service : services){
            if(service.getTitle().equals(title))
                return true;
        }
        return  false;
    }

    public  Service set(int index,Service service){
        Objects.requireNonNull(service);
        if(index<0 || index>=services.length) throw new IndexOutOfBoundsException();
        Service returnService = services[index];
        services[index] = service;
        return returnService;
    }

    public Service delete(int index){
        if(index<0 || index>=services.length) throw new IndexOutOfBoundsException();
        Service returnService = services[index];
        services[index] = null;
        return returnService;
    }

    public Service delete(String title){
        Objects.requireNonNull(title);
        for(int i = 0; i<services.length;i++){
            if(services[i].getTitle().equals(title)){
                Service returnService = services[i];
                services[i]= null;
                size--;
                return returnService;
            }
        }
        throw new NoSuchElementException();
    }

    public Service[] getServices() {
        trim();
        Service[] returnService = new Service[size];
        for(int i=0;i<returnService.length;i++){
            returnService[i] = services[i];
        }
        return returnService;
    }

    private void extend(){
        Service[] newServices = new Service[services.length*2];
        for(int i = 0; i<services.length;i++){
            newServices[i] = services[i];
        }
        services = newServices;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void trim(){
        for(int i = 0; i<services.length;i++){
            for (int j= 0; j<services.length-1;j++){
                if(services[j]==null && services[j+1]!=null){
                    services[j] = services[j+1];
                    services[j+1]= null;
                }
            }
        }
    }

    public void setServices(Service[] services) {
        this.services = services;
    }

    public Service[] SortedServicesByCost(){
        Service[] newServices = getServices();
        for(int i = 0; i<newServices.length;i++){
            for(int j = 0; j<newServices.length-1;j++){
                if(newServices[j].getCost()>newServices[j+1].getCost()){
                    Service C = newServices[j];
                    newServices[j] = newServices[j+1];
                    newServices[j+1] = C;
                }
            }
        }
        return newServices;
    }

    public double cost(){
        double cost = 50;
        Service[] newService = getServices();
        for(int i = 0; i<newService.length;i++){
            cost+=newService[i].getCost();
        }
        return cost;
    }

    public Service[] getTypedServices(ServiceTypes serviceTypes){
        Objects.requireNonNull(serviceTypes);
        ArrayList<Service> list = new ArrayList<Service>();
        for(int i = 0; i<services.length;i++){
            if(services[i]!=null && services[i].getServiceTypes().equals(serviceTypes)){
                list.add(services[i]);
            }
        }
        return (Service[]) list.toArray();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("services: \n");
        for(int i = 0; i<services.length;i++){
            if(services[i]!=null) sb.append(services[i].toString()).append('\n');
            else{
                sb.append("-------------\n");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        IndividualsTariff that = (IndividualsTariff) obj;
        boolean answer = that.getSize() == this.getSize();
        if(answer){
            for(int i = 0; i<getSize();i++){
                answer &= (that.getServices()[i].equals(this.getServices()[i]));
            }
        }
        return answer;
    }

    public int hashCode() {
        int hash = 31;
        for (int i = 0; i < services.length; i++) {
            hash *= services[i].hashCode();
        }
        return hash;
    }

    @Override
    public Tariff clone() throws CloneNotSupportedException{
        return new IndividualsTariff(services);
    }

    @Override
    public boolean delete(Service service) {
        Objects.requireNonNull(service);
        for(int i = 0; i<services.length;i++){
            if(services[i].equals(service)){
                services[i] = null;
                return true;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int indexOf(Service service) {
        Objects.requireNonNull(service);
        for(int i = 0;i<services.length;i++){
            if(services[i].equals(service)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Service service) {
        Objects.requireNonNull(service);
        for(int i = services.length-1;i>=0;i--){
            if(services[i].equals(service)){
                return i;
            }
        }
        return -1;
    }
}
