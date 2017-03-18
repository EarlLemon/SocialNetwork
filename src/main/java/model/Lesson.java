package model;

public class Lesson {
    private final int lessonId;
    private final int userId;
    private final String lessonText;

    public Lesson(int lessonId, int userId, String lessonText) {
        this.lessonId = lessonId;
        this.userId = userId;
        this.lessonText = lessonText;
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
                ", userId = " + userId +
                ",  lessonText= " + lessonText +
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
        return lessonText != null ? lessonText.equals(lessons.lessonText) : lessons.lessonText == null;
        }

        @Override
        public int hashCode(){
            int result = userId;
            result = 31 * result + userId;
            result = 31 * result + lessonId;
            result = 31 * result + (lessonText != null ? lessonText.hashCode():0);
            return result;
        }

}
