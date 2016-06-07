public class Event{

	private String _n, _d, _start, _end;
	private int _k;

	public Event(String name, String description, String startTime, String endTime, int key){
		_n = name;
		_d = description;
		_start = startTime;
		_end = endTime;
		_k = key;
	}

	public Event(String name, String startTime, String endTime, int key){
		this(name, null, startTime, endTime, key);
	}

	public String getName(){
		return _n;
	}

	public String getStartTime(){
		return _start;
	}

	public String getEndTime(){
		return _end;
	}

	public int getKey(){
		return _k;
	}

	public String toString(){
		return getName();
	}
}