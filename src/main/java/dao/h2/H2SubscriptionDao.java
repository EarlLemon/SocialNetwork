package dao.h2;

import dao.SubscriptionDao;
import model.Subscription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class H2SubscriptionDao implements SubscriptionDao {
    private static final Logger Log = LogManager.getLogger(H2SubscriptionDao.class);
    private Collection<Integer> userSubs;
    private DataSource dataSource;


    private static final String ADD_SUB = "INSERT INTO Subscription(user_id, subscription) VALUES (?,?)";
    private static final String FIND_SUB_ID = "SELECT * FROM Subscription WHERE user_id=? AND subscription=?";
    private static final String GET_USER_SUB = "SELECT  subscription, sub_id FROM Subscription WHERE user_id=?";
    private static final String REMOVE_SUB = "DELETE FROM Subscription WHERE sub_id=?";
    private static final String GET_FOLLOWERS = "SELECT user_id FROM Subscription WHERE subscription=?";

     H2SubscriptionDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void createSubscription(int userId, int subId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_SUB)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, subId);

            preparedStatement.execute();

        } catch (SQLException e) {
            Log.error("Cannot addSubscription from userId = " + userId + " to " + subId, e);
        }
    }

    @Override
    public void deleteSubscription(int userId, int subId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_SUB_ID);
             PreparedStatement delete = connection.prepareStatement(REMOVE_SUB)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, subId);
            int sub_id = 0;

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    sub_id = resultSet.getInt(1);
                }
            }
            delete.setInt(1, sub_id);
            delete.execute();

        } catch (SQLException e) {
            Log.error("Cannot unsubscribe from " + subId + " by " + userId, e);
        }
    }

    @Override
    public Collection<Subscription> getUserSubscriptions(int userId) {
        Collection<Subscription> subscriptions = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_SUB)) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Subscription subscription = new Subscription();
                    subscription.setSub_id(resultSet.getInt("sub_id"));
                    subscription.setUser_id(userId);
                    subscription.setSubscription(resultSet.getInt("subscription"));
                    subscriptions.add(subscription);
                }
            }
        } catch (SQLException e) {
            Log.warn(e.getMessage());
        }
        return subscriptions;
    }


//    @Override
   public Collection<Integer> getFollowers(int userId) {

       return null;
    }
//
//    private Collection<Integer> count(String that, int userId) {
//        String prepSt;
//        if (that.equals("followers")) {
//            prepSt = GET_FOLLOWERS;
//        } else {
//            prepSt = GET_USER_SUB;
//        }
//        userSubs = new LinkedList<>();
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(prepSt)) {
//            preparedStatement.setInt(1, userId);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    userSubs.add(resultSet.getInt(1));
//                }
//            }
//            return userSubs;
//        } catch (SQLException e) {
//            Log.error("cannot count followers from userId " + userId, e);
//            return userSubs;
//        }
//    }
}
