package exeptions;


public class UserNotFoundExeption extends Exception {
    @Override
    public String toString(){
        return "user not found";
    }
}
