import java.util.Comparator;


public class EventComparator<E> implements Comparator<E>{

	// negative is higher priority
    public int compare(E lhs, E rhs) throws ClassCastException{
	Event a=(Event)lhs;
	Event b=(Event)rhs;
	if(a.getMonth() != b.getMonth()){ //lower # months first
	    return (a.getMonth()- b.getMonth()); 
	else if(a.getDay() != b.getDay()) //lower days first
	    return a.getDay() - b.getDay();
	else if(a.getPriority() != b.getPriority()) //lower # means higher priority
	    return a.getPriority() - b.getPriority();
	else if (a.getEnd()> b.getStart() || b.getEnd() > a.getStart()) //if times over lap
	    return -100; //indicating a conflict
	return 0;
    }
}

