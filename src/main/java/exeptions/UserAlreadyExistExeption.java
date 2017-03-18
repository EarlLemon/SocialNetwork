package exeptions;

public class UserAlreadyExistExeption extends Exception {
    @Override
    public String toString(){
        return "this user already exist";
    }
}
