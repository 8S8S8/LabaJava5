package Exception;

public class IllegalAccountNumber extends RuntimeException{

    public IllegalAccountNumber(){
        super();
    }

    public IllegalAccountNumber(String message){
        super(message);
    }
}
