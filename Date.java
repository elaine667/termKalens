public class Date{

	private int _m, _d;
	private Schedule _s;

	public Date(int month, int date, Schedule schedule){
		_m = month;
		_d = date;
		_s = schedule;
	}

	public Date(int month, int date){
		this(_m, _d, null);
	}

	public int getMonth(){
		return _m;
	}

	public int getDate(){
		return _d;
	}

	public Schedule getSchedule(){
		return _schedule;
	}
}