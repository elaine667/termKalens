import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;

public class App{

	public static void main(String [] args){
		Scanner sc = new Scanner(System.in);
		//int month = sc.nextInt();
		Comparator<Date> comp = new DateComparator<Date>();
		PriorityQueue<Date> calendar = new PriorityQueue<Date>(1,comp);
		String next = "";
		boolean quit = false;
		OVERALL : while(!next.equals("quit") && !quit){
			System.out.println("What would you like to do? 'add'; 'remove'; 'quit'");
			next = sc.nextLine();
			if(next.equals("add"))
				quit = addEvents(sc, calendar);
			if(next.equals("remove"))
				quit = removeEvents(sc, calendar);
			if(next.equals("quit"))
				quit = true;
		}
	}
	
	public static boolean removeEvents(Scanner sc, PriorityQueue<Date> calendar){
		System.out.println("Would you like to remove an Event or clear a time? 'remove'; 'clear time'");
		System.out.println("Enter cancel to cancel removal");
		String next = sc.nextLine();
		String option = next+"";
		System.out.println("What day is the event/time? mm/dd");
		next = sc.nextLine();
		int j,s;
		j = s = 0;
		DATE: for(;;){
			String a = next.substring(0,2);
			String b = next.substring(3,5);
			if(isInteger(a) && isInteger(b)){
				j = Integer.parseInt(a); // month
				s = Integer.parseInt(b); // day
				break;
			}
			else{
				System.out.println("Please enter a valid mm/dd combination");
				System.out.println();
				continue DATE; // allows us to just skip the rest of the code and start from the beginning
			}
		}
		if(option.equals("remove")){
			System.out.println("What event would you like to remove?");
			next = sc.nextLine();
			Event eventT = new Event(next,null,0,0,0,0,0);
			Date dateT = new Date(j,s,null);
			Object[] dateArr = calendar.toArray();
			System.out.println(dateArr.length);
			for(int i = 0; i < dateArr.length; i++){
				System.out.println("checker");
				Date d = (Date) dateArr[i];
				if(d.equals(dateT)){
					System.out.println(d.getSchedule().remove(eventT));
				}
			}
		}
		return false;
	}
	
	public static boolean addEvents(Scanner sc, PriorityQueue<Date> calendar){
		int j,s,st,en;
		j = s = st = en = 0;
		DATE : for(;;){
			System.out.println("Enter the number corresponding to a date within this year (2016); enter cancel to cancel entry");
			System.out.println("i.e. 06/12 for June 12th, 12/01 for December 1st: ");
			String next = sc.nextLine();
			if(next.equals("cancel")){
				System.out.println("Entry cancelled"); // cancellation
				System.out.println();
				return false;
			}
			if(next.equals("quit"))
				return true;
			if(next.length() == 5){
				String a = next.substring(0,2);
				String b = next.substring(3,5);
				if(isInteger(a) && isInteger(b)){
					j = Integer.parseInt(a); // month
					s = Integer.parseInt(b); // day
					break;
				}
				else{
					System.out.println("Please enter a valid mm/dd combination");
					System.out.println();
					continue DATE; // allows us to just skip the rest of the code and start from the beginning
				}
				// m and n have no leading zeroes
			}
			else{
				System.out.println("Please enter a valid mm/dd combination");
				System.out.println();
				continue DATE;
			}
		}
		System.out.println("Enter the title of your event; enter cancel to cancel entry");
		String title = sc.nextLine();
		if(title.equals("cancel")){
			System.out.println("Entry cancelled"); // cancellation
			System.out.println();
			return false;
		}
		if(title.equals("quit"))
			return true;
		System.out.println("Enter the description of your event; enter cancel to cancel entry");
		String desc = sc.nextLine();
		if(desc.equals("cancel")){
			System.out.println("Entry cancelled"); // cancellation
			System.out.println();
			return false;
		}
		if(desc.equals("quit"))
			return true;
		HOURS : for(;;){
			System.out.println("Enter the start and end hours (24hr time) of your event; enter cancel to cancel entry");
			System.out.println("i.e. 06/12 for 6am to 12pm, 14/18 for 2pm to 6pm: ");
			String times = sc.nextLine();
			if(times.equals("cancel")){
				System.out.println("Entry cancelled"); // cancellation
				System.out.println();
				return false;
			}
			if(times.equals("quit"))
				return true;
			if(times.length() == 5){
				String start = times.substring(0,2);
				String end = times.substring(3,5);
				if(isInteger(start) && isInteger(end)){
					st = Integer.parseInt(start); // start
					en = Integer.parseInt(end); // end
					break;
				}
				else{
					System.out.println("Please enter a valid ss/ee combination");
					System.out.println();
					continue HOURS;
				}
			}
			else{
				System.out.println("Please enter a valid ss/ee combination");
				System.out.println();
				continue HOURS;
			}
		}
		Event event;
		PRIORITY : for(;;){
			System.out.println("What is your event priority? 1-5 5 being the least important");
			String pri = sc.nextLine();
			if(pri.equals("cancel")){
				System.out.println("Entry cancelled"); // cancellation
				System.out.println();
				return false;
			}
			if(pri.equals("quit"))
				return true;
			boolean newEvent = false;
			if(pri.length() == 1 && isInteger(pri)){
				event = new Event(title,desc,j,s,0,0,Integer.parseInt(pri));
				newEvent = true;
				break;
			}
			else{
				System.out.println("Please enter a valid priority");
				System.out.println();
				continue PRIORITY;
			}
		}
		Date temp = new Date(j,s,new Schedule());
		boolean conflict = false;
		for(Date e : calendar){
			if(e.equals(temp)){
				conflict = !e.addEvent(event); // addEvent should return a boolean on whether or not the event can be added
			}
		}
		if(conflict)
			calendar.offer(temp);
		return false;
	}
	
	public static boolean isInteger(String a) {
		try { 
			Integer.parseInt(a); 
		} catch(NumberFormatException e) { 
			return false; 
		} catch(NullPointerException e) {
			return false;
		}
		return true;
	}
	
}
