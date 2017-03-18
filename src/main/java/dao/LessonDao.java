package dao;

import model.Lesson;

import java.util.Collection;

public interface LessonDao {
    Collection<Lesson> getAll();

    Lesson getByLessonId(int lessonId);

    Collection<Lesson> getLessonsByUserId(int userId);

    void addPost(String postText, int userId);
}
