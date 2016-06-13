import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class User implements Comparable{
    private String id, password, name;
    private ArrayList<User> friends;
    private PriorityQueue<Date> calendar;

    public User(String uname, String pword){
        id = uname + (int)(Math.random()*100);
        name = uname;
        password = pword;
        friends = new ArrayList<User>();
        Comparator<Date> comp = new DateComparator<Date>();
        PriorityQueue<Date> calendar = new PriorityQueue<Date>(1,comp);

    }

    public String getId(){
        return id;
    }

    public ArrayList<User> getFriends(){
        return friends;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public PriorityQueue<Date> getCalendar(){
        return calendar;
    }

    public int compareTo(Object rhs){
        String x = this.getId();
        String y = ((User)rhs).getId();
        return x.compareTo(y);
    }
}
