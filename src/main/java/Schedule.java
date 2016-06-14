import java.util.ArrayList;
import java.util.Comparator;

public class Schedule{

    private ArrayList<Event> _list;
    private Comparator<Event> _c;


    public Schedule(){
        _list = new ArrayList<Event>();
        _c = new EventComparator();
    }

    public boolean isEmpty(){
        return _list.isEmpty();
    }

    public boolean add(Event e){
        if(_list.size() == 0)
            _list.add(e);
        else{
            for(int i =0;i < _list.size(); i++){
                if (_c.compare(e,_list.get(i))<= 0){
                    _list.add(i,e);
                    return true; }
            }
        }
        return false;
    }

    // O(logN)
    public Event remove(int i){
        Event ans = _list.get(i);
        Event x = _list.remove(_list.size() - 1);
        if (_list.size() > 0){
            _list.set(i,x);
            int pos = i;
            int mcPos = minChildPos(pos);
            while (mcPos != -1){
                if (_c.compare(_list.get(pos), _list.get(mcPos)) <= 0) break;
                _list.set(pos,_list.set(mcPos,_list.get(pos)));
                pos = mcPos;
                mcPos = minChildPos(pos);
            }
        }
        return ans;
    }

    public Event remove(Event a){
        return remove(eventPos(a));
    }
    public Event remove(){
        return remove(0);
    }

    private int eventPos(Event a){
        int pos=0;
        while (!_list.get(pos).equals(a)){
            pos++;
        }
        return pos;
    }

    // return -1 if  pos has no children
    // otherwise returns the minmum child
    private int minChildPos(int pos){
        int left = 2 * pos + 1;
        int right = left + 1;
        // no children
        if (left >= _list.size()) return -1;
        // only a left child
        if (right >= _list.size()) return left;
        // both children exist
        if (_c.compare(_list.get(left), _list.get(right)) <= 0) return left;
        return right;
    }



    // O(1)
    public Event peekMin(){
        return _list.get(0);
    }


    public String toString(){
        String formatedString = _list.toString()
                .replace(",", "<br><br>")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();
        return formatedString;
    }

    public static void main (String[] args){
        Schedule x = new Schedule();
        Event e = new Event ("a","a",1,1,1,1,1);
        x.add(e);
        System.out.println(x);
    }
}