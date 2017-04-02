package dao.h2;

import dao.DaoFactory;
import dao.LessonDao;
import dao.SubscriptionDao;
import dao.UserDao;

import javax.sql.DataSource;


public class H2DaoFactory implements DaoFactory {
    private final UserDao userDao;
    private final LessonDao lessonDao;
    private final SubscriptionDao subscriptionDao;

    /**
     * Constructor, which creates all specified DAOs using the DataSource from the argument.
     */

    public H2DaoFactory(DataSource dataSource) {
        userDao = new H2UserDao(dataSource);
        lessonDao = new H2LessonsDao(dataSource);
        subscriptionDao = new H2SubscriptionDao(dataSource);
    }

    /**
     * Methods returns Dao instances for H2 database
     */


    @Override
    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public LessonDao getLessonDao() {
        return lessonDao;
    }

    @Override
    public SubscriptionDao getSubscriptionDao() {
        return subscriptionDao;
    }

    @Override
    public void close() throws Exception {
    }

}

