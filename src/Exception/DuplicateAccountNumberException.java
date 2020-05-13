package Exception;

public class DuplicateAccountNumberException extends Exception {

    public DuplicateAccountNumberException(){
        super();
    }

    DuplicateAccountNumberException(String message){
        super(message);
    }

    DuplicateAccountNumberException(String message, Throwable errorObject){
        super(message,errorObject);
    }
}
