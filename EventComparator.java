import java.util.Comparator;


public class EventComparator<E> implements Comparator<E>{

	// negative is higher priority
    public int compare(E lhs, E rhs) throws ClassCastException{
      Event a=(Event)lhs;
      Event b=(Event)rhs;
      if(a.getMonth() != b.getMonth())
        return b.getMonth() - a.getMonth();
      else if(a.getDay() != b.getDay())
        return b.getDay() - a.getDay();
      else if(a.getPriority() != b.getPriority())
        return b.getPriority() - a.getPriority();
      else if(a.getStart() != b.getStart())
        return b.getStart() - a.getStart();
      else if(a.getEnd() != b.getEnd())
        return b.getEnd() - a.getEnd();
      return 0;
    }
}
