package dao;


public interface DaoFactory extends AutoCloseable {
    UserDao getUserDao();
    LessonDao getLessonDao();
    SubscriptionDao getSubscriptionDao();
}
