public class Event{

	private String _event, _description;
	private int _month, _day, _startHr, _endHr, _priority;
	
	public Event(String event, String desc, int month, int day, int start, int end, int priority){
		_event = event;
		_description = desc;
		_month = month;
		_day = day;
		_startHr = start;
		_endHr = end;
		_priority = priority;
	}
	
	public Event(String event, String desc, int month, int day, int start, int end){
		_event = event;
		_description = desc;
		_month = month;
		_day = day;
		_startHr = start;
		_endHr = end;
		_priority = 5;
	}
	
	public String getEvent(){
		return _event;
	}
	
	public int getMonth(){
		return _month;
	}
	
	public int getDay(){
		return _day;
	}
	
	public int getStart(){
		return _startHr;
	}
	
	public int getEnd(){
		return _endHr;
	}
	
	public int getPriority(){
		return _priority;
	}
	
	public boolean equals(Object o){
	 Event e = (Event) o;
	 return getEvent() == e.getEvent();
 }
 
	public String toString(){
		return "Event: "+_event+" ; "+"Desc: "+_description;
	}
	
	// compareTo override
	// negative is higher priority (i.e day 1 is earlier than day 17)
        /* already in comparator
	public int compareTo(Event rhs){
		if(getMonth() != rhs.getMonth())
			return getMonth() - rhs.getMonth();
		else if(getDay() != rhs.getDay())
			return getDay() - rhs.getDay();
		else if(getPriority() != rhs.getPriority())
			return getPriority() - rhs.getPriority();
		else if(getStart() != rhs.getStart())
			return getStart() - rhs.getStart();
		else if(getEnd() != rhs.getEnd())
			return getEnd() - rhs.getEnd();
		return 0;
	}
	*/

}
