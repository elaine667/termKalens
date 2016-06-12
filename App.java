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
		OVERALL : while(!next.equals("quit")){
			System.out.println("Enter the number corresponding to a date within this year (2016).");
			System.out.println("i.e. 06/12 for June 12th, 12/01 for December 1st: ");
			next = sc.nextLine();
			if(next.equals("quit"))
				break;
			if(next.length() == 5){
				String a = next.substring(0,2);
				String b = next.substring(3,5);
				int j = 0;
				int s = 0;
				if(isInteger(a) && isInteger(b)){
					j = Integer.parseInt(a); // month
					s = Integer.parseInt(b); // day
				}
				else{
					System.out.println("Please enter a valid mm/dd combination");
					System.out.println();
					continue OVERALL; // allows us to just skip the rest of the code and start from the beginning
				}
				// m and n have no leading zeroes
				System.out.println("Enter the title of your event; enter cancel to cancel entry");
				String title = sc.nextLine();
				if(title.equals("cancel")){
					System.out.println("Entry cancelled"); // cancellation
					System.out.println();
					continue OVERALL;
				}
				System.out.println("Enter the description of your event; enter cancel to cancel entry");
				String desc = sc.nextLine();
				if(desc.equals("cancel")){
					System.out.println("Entry cancelled"); // cancellation
					System.out.println();
					continue OVERALL;
				}
				System.out.println("Enter the start and end hours (24hr time) of your event; enter cancel to cancel entry");
				System.out.println("i.e. 06/12 for 6am to 12pm, 14/18 for 2pm to 6pm: ");
				String times = sc.nextLine();
				if(next.length() == 5){
					String s = next.substring(0,2);
					String e = next.substring(3,5);
					int st = 0;
					int en = 0;
					if(isInteger(s) && isInteger(e)){
						st = Integer.parseInt(a); // start
						en = Integer.parseInt(b); // end
					}
					else{
						System.out.println("Please enter a valid ss/ee combination");
						System.out.println();
						continue OVERALL; // allows us to just skip the rest of the code and start from the beginning
					}
				}
				else{
					System.out.println("Please enter a valid ss/ee combination");
					System.out.println();
				}
				if(times.equals("cancel")){
					System.out.println("Entry cancelled"); // cancellation
					System.out.println();
					continue OVERALL;
				}
				System.out.println("What is your event priority? 1-5 5 being the least important");
				String pri = sc.nextLine();
				if(pri.length() == 1 && isInteger(pri))
					Event event = new Event(title,desc,j,s,)
				Date temp = new Date(j,s,new Schedule());
				boolean conflict = false;
				for(Date e : calendar){
					if(e.equals(temp)){
						conflict = !e.addEvent(event); // addEvent should return a boolean on whether or not the event can be added
					}
				}
				if(conflict)
					calendar.offer(temp);
			}
			else{
				System.out.println("Please enter a valid mm/dd combination");
				System.out.println();
			}
		}
	}
	
	public void addEvents(){
		
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
