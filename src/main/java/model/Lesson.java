package model;

public class Lesson {
    private int lessonId;
    private int userId;
    private String lessonText;

    public Lesson(int lessonId, int userId, String lessonText) {
        this.lessonId = lessonId;
        this.userId = userId;
        this.lessonText = lessonText;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLessonText() {
        return lessonText;
    }

    public void setLessonText(String lessonText) {
        this.lessonText = lessonText;
    }
    @Override
    public String toString() {

        return "Lesson{" +
                "lessonId = " + lessonId +
                ", userId = " + userId +
                ",  lessonText= " + lessonText +
                '}';
    }
}
