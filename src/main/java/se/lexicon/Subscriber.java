package se.lexicon;

public class Subscriber {

    private int id;
    private String email;
    private Plan plan;
    private boolean active;
    private int monthsRemaining;


    // Constructor
    public Subscriber(int id, String email, Plan plan, boolean active, int monthsRemaining) {
        this.id = id;
        this.email = email;
        this.plan = plan;
        this.active = active;
        this.monthsRemaining = monthsRemaining;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getMonthsRemaining() {
        return monthsRemaining;
    }

    public void setMonthsRemaining(int monthsRemaining) {
        this.monthsRemaining = monthsRemaining;
    }

    //Override toString method
    @Override
    public String toString() {
        return "Subscriber{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", plan=" + plan +
                ", active=" + active +
                ", monthsRemaining=" + monthsRemaining +
                '}';
    }
}
