import java.util.ArrayList;

public class UserBase {

    private ArrayList<User> _users;

    public UserBase(){
        _users = new ArrayList<User>();
    }

    private int binaryPoint(String id) {
        int low = 0;
        int high = _users.size() - 1;

        while (low <= high) {
            int mid = (low + high)/2;
            String midVal = _users.get(mid).getId();
            if (midVal.compareTo(id) < 0)
                low = mid + 1;
            else if (midVal.compareTo(id) > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    private int insertionPoint(String id){
        int binPoint = binaryPoint(id);
        if (binPoint < 0)
            return -(binPoint + 1);
        return binPoint;
    }

    public void add(User user){
        if (_users.size() == 0)
            _users.add(user);
        else {
            int pos = insertionPoint(user.getId());
            _users.add(pos, user);
        }
    }

    public void remove(String id){
        int pos = binaryPoint(id);
        _users.remove(pos);
    }


    public String toString(){
        String ans = "";
        for (int i = 0; i < _users.size(); i++){
            ans += _users.get(i).getId() + ", ";
        }
        return ans;
    }

    public int getSize(){
        return _users.size();
    }

    public User getUser(int i){
        return _users.get(i);
    }

    public User getUser(String id){
        int pos = binaryPoint(id);
        return _users.get(pos);
    }

    /*
    public static void main(String [] args){
        UserBase a = new UserBase();
        User b = new User("b", "b");
        User c = new User("c", "c");
        User d = new User("d", "d");
        a.add(b);
        System.out.println(a);
        a.add(d);
        System.out.println(a);
        a.add(c);
        System.out.println(a);
        a.remove(c.getId());
        System.out.println(a);
        a.remove(b.getId());
        System.out.println(a);
        a.remove(d.getId());
        System.out.println(a);
    }
    */

    
}
