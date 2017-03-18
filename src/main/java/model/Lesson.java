package model;

public class Lesson {
    private final int lessonId;
    private final int userId;
    private final String post;

    public Lesson(int lessonId, int userId, String post) {
        this.lessonId = lessonId;
        this.userId = userId;
        this.post = post;
    }

    public int getLessonId() {
        return lessonId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString(){

        return "Lesson{"+
                "lessonId = " + lessonId +
                ", UserId = " + userId +
                ", Post = " + post +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;}
        if (o == null || getClass() != o.getClass()){
            return false;}

         Lesson lessons = (Lesson) o;

        if (userId != lessons.userId){
            return false;}
        if (lessonId != lessons.lessonId){
            return false;}
        return post != null ? post.equals(lessons.post) : lessons.post == null;
        }

        @Override
        public int hashCode(){
            int result = userId;
            result = 31 * result + userId;
            result = 31 * result + lessonId;
            result = 31 * result + (post != null ? post.hashCode():0);
            return result;
        }

}
