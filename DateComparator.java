import java.util.Comparator;

public class DateComparator<E> implements Comparator<E>{
	
	public int compare(E lhs, E rhs) throws ClassCastException{
      Date a=(Date)lhs;
      Date b=(Date)rhs;
      if(a.getMonth() != b.getMonth())
        return b.getMonth() - a.getMonth();
      else if(a.getDate() != b.getDate())
        return b.getDate() - a.getDate();
      return 0;
    }
	
}