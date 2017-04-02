package dao.h2;


import dao.LessonDao;
import model.Lesson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class H2LessonsDao implements LessonDao {
    private Collection<Lesson> allLessons = new ArrayList<>();
    private static Logger Log = LogManager.getLogger(H2LessonsDao.class);
    private DataSource dataSource;

    private static final String GET_ALL_LESSONS = "SELECT * FROM Lesson";
    private static final String GET_BY_LESSON_ID = "SELECT * FROM Lesson WHERE lesson_id=?";
    private static final String GET_BY_USER_ID = "SELECT * FROM Lesson WHERE user_id=?";
    private static final String ADD_LESSON = "INSERT INTO Lesson(user_id, lesson_text) VALUES (?,?)";

    H2LessonsDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Collection<Lesson> getAll() {
        try (Connection connection = dataSource.getConnection();
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

        } catch (SQLException e) {
            Log.error("Can not getAll lessons", e);
        }


        return allLessons;
    }

    @Override
    public Lesson getByLessonId(int lessonId) {
        Lesson lesson = null;

        try (Connection connection = dataSource.getConnection();
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
        } catch (SQLException e) {
            Log.error("Can not get by id lessons", e);
        }
        return lesson;
    }

    @Override
    public Collection<Lesson> getLessonsByUserId(int userId) {
        Collection<Lesson> UserLesson = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
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
        } catch (SQLException e) {
            Log.error("Can not get lessons by userid ", e);
        }
        return UserLesson;
    }



    @Override
    public int addLesson(Lesson lesson) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_LESSON, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, lesson.getUserId());
            preparedStatement.setString(2, lesson.getLessonText());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }

        } catch (SQLException e) {
            Log.error("Can not add lesson", e);

        }
        return 0;
    }
}
