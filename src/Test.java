import List.LinkedList;

import java.time.LocalDate;

public class Test {

    public static void listTest() {
        LinkedList<String> list = new LinkedList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        System.out.println(list.toString());
        list.delete(2);
        System.out.println("После удаления ");
        System.out.println(list.toString());
        System.out.println(list.getSize());
        System.out.println("После добавления ");
        list.add("4", 2);
        System.out.println(list.toString());
        System.out.println(list.getSize());

    }

    public static void lab4test() {
        Person person1 = new Person("Пупкин", "Петр");
        Person person2 = new Person("Акимов", "Иван");
        System.out.println(person1 + "\n" + person2);
        System.out.println("HashCode для 1 : " + person1.hashCode() + "\n" + "HashCode для 2 : " + person2.hashCode());
        System.out.println("Проверка на равенство : " + person1.equals(person2));

        IndividualAccount individualAccount = new IndividualAccount(2, person2);
        System.out.println(individualAccount);


    }

    public static void lab5test() {
        Person person1 = new Person(null, "Ivan");
        System.out.println(person1);
        LocalDate date1 = LocalDate.of(1914, 7, 28);
        LocalDate date2 = LocalDate.of(2902, 7, 28);
        Service one = new Service();
        Service two = new Service("Почта", 300, ServiceTypes.MAIL, date2);
        System.out.println(one);
        System.out.println(two);
    }
}
