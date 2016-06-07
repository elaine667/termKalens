import java.util.Comparator;


public class EventComparator implements Comparator{
    public int compare(Object a, Object b) throws ClassCastException{
		return ((Event)a).getKey() - ((Event)b).getKey();
    }


}