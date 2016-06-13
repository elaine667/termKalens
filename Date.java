public class Date{

 private int _m, _d;
 private Schedule _s;

 public Date(int month, int date, Schedule schedule){
  _m = month;
  _d = date;
  _s = schedule;
 }

 public Date(int month, int date){
  this(month, date, null);
 }

 public int getMonth(){
  return _m;
 }

 public int getDate(){
  return _d;
 }

 public Schedule getSchedule(){
  return _s;
 }
 
 public boolean addEvent(Event e){
	 return _s.add(e);
 }
 
 public boolean equals(Object o){
	 Date d = (Date) o;
	 return getMonth() == d.getMonth() && getDate() == d.getDate();
 }
 
 public String toString(){
	 return getMonth()+"/"+getDate();
 }
 
}