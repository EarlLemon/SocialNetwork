package model;


public class Subscription {
    private int sub_id;
    private int user_id;
    private int subscription;

    /**
     * No-args constructor of the Subscription instance
     */

    public Subscription() {
    }

    public Subscription(int sub_id, int user_id, int subscription) {
        this.sub_id = sub_id;
        this.user_id = user_id;
        this.subscription = subscription;
    }


    public int getSubscription() {
        return subscription;
    }

    public void setSubscription(int subscription) {
        this.subscription = subscription;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }
}
