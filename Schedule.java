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
   
    // adds in order : O(logN)
    public void add(Event e){
		_list.add(e);
       	int pos = _list.size() - 1;
		while (pos > 0){
	    int parentPos = (pos - 1) / 2;
	    if( _c.compare(_list.get(pos), _list.get((pos - 1)/ 2)) >= 0) break; 
	    _list.set(pos, _list.set(parentPos,_list.get(pos)));
	    pos = parentPos;
		}
    }
    
    // O(logN)
    public Event removeMin(){
    	Event ans = _list.get(0);
    	Event x = _list.remove(_list.size() - 1);
    	if (_list.size() > 0){
    		_list.set(0,x);
    		int pos = 0;
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
	return _list.toString();
    }
}