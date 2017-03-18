package dao.h2;

import common.ConnectionPool;
import dao.LessonDao;
import model.Lesson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class H2LessonsDao implements LessonDao {

    private static ConnectionPool connectionPool;
    private static H2LessonsDao h2LessonsDao;
    private Collection<Lesson> allLessons = new ArrayList<>();
    private static Logger Log = LogManager.getLogger(H2LessonsDao.class);

    private static final String GET_ALL_LESSONS = "SELECT * FROM Lesson";
    private static final String GET_BY_LESSON_ID = "SELECT * FROM Lesson WHERE lesson_id=?";
    private static final String GET_BY_USER_ID = "SELECT * FROM Lesson WHERE user_id=?";
    private static final String ADD_LESSON = "INSERT INTO Lesson(user_id, post) VALUES (?,?)";


    public static H2LessonsDao getInstance() {
        if (h2LessonsDao == null) {
            Log.debug("getting instance of SqlPostDao");
            h2LessonsDao = new H2LessonsDao();
        }
        return h2LessonsDao;
    }


    @Override
    public Collection<Lesson> getAll() {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_LESSONS)) {
            while (resultSet.next()) {
                allLessons.add(new Lesson(
                                resultSet.getInt("lesson_id"),
                                resultSet.getInt("user_id"),
                                resultSet.getString("lesson_text")
                        )
                );
            }

        } catch (InterruptedException | SQLException e) {
            Log.error("Can not getAll lessons", e);
        }


        return allLessons;
    }

    @Override
    public Lesson getByLessonId(int lessonId) {
        Lesson lesson = null;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStPostData = connection.prepareStatement(GET_BY_LESSON_ID)) {

            prepStPostData.setInt(1, lessonId);
            try (ResultSet resultSet = prepStPostData.executeQuery()) {
                while (resultSet.next()) {
                    lesson = new Lesson(
                            resultSet.getInt("lesson_id"),
                            resultSet.getInt("user_id"),
                            resultSet.getString("lesson_text")
                    );
                }
            }
        } catch (InterruptedException | SQLException e) {
            Log.error("Can not get by id lessons", e);
        }
        return lesson;
    }

    @Override
    public Collection<Lesson> getLessonsByUserId(int userId) {
        Collection<Lesson> UserLesson = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStUserPosts = connection.prepareStatement(GET_BY_USER_ID)) {

            prepStUserPosts.setInt(1, userId);
            try (ResultSet resultSet = prepStUserPosts.executeQuery()) {
                while (resultSet.next()) {
                    UserLesson.add(new Lesson(
                            resultSet.getInt("lesson_id"),
                            resultSet.getInt("user_id"),
                            resultSet.getString("lesson_text")
                    ));
                }
            }
        } catch (InterruptedException | SQLException e) {
            Log.error("Can not get lessons by userid ", e);
        }
        return UserLesson;
    }

    @Override
    public void addLesson(String lessonText, int userId) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_LESSON)) {


            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, lessonText);

            preparedStatement.execute();

        } catch (InterruptedException | SQLException e) {
            Log.error("Can not add lesson", e);

        }
    }
}
