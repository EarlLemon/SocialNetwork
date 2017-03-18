package model;

public class Lessons {
    private final int idLesson;
    private final int idUser;
    private final String post;

    public Lessons(int idLesson, int idUser, String post) {
        this.idLesson = idLesson;
        this.idUser = idUser;
        this.post = post;
    }

    public int getIdLesson() {
        return idLesson;
    }

    public int getIdUser() {
        return idUser;
    }

    @Override
    public String toString(){

        return "Lesson{"+
                "idLesson = " + idLesson +
                ", idUser = " + idUser +
                ", Post = " + post +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;}
        if (o == null || getClass() != o.getClass()){
            return false;}

         Lessons lessons = (Lessons) o;

        if (idUser != lessons.idUser){
            return false;}
        if (idLesson != lessons.idLesson){
            return false;}
        return post != null ? post.equals(lessons.post) : lessons.post == null;
        }

        @Override
        public int hashCode(){
            int result = idUser;
            result = 31 * result + idUser;
            result = 31 * result + idLesson;
            result = 31 * result + (post != null ? post.hashCode():0);
            return result;
        }

}
