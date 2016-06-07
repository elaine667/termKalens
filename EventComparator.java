import java.util.Comparator;


public class EventComparator implements Comparator{

    public int compare(Object lhs, Object rhs) throws ClassCastException{
      Event a=(Event)lhs;
      Event b=(Event)rhs;
      if(a.getMonth() != b.getMonth())
        return a.getMonth() - b.getMonth();
      else if(a.getDay() != b.getDay())
        return a.getDay() - b.getDay();
      else if(a.getPriority() != b.getPriority())
        return a.getPriority() - b.getPriority();
      else if(a.getStart() != b.getStart())
        return a.getStart() - b.getStart();
      else if(a.getEnd() != b.getEnd())
        return a.getEnd() - b.getEnd();
      return 0;
    }
}
