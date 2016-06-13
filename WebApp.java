import java.util.PriorityQueue;
import java.util.Comparator;
import static spark.Spark.*;

public class App {
    private static String heading = "<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "<title>MyCalendar</title>" +
            "<link rel='stylesheet' + href='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>" +
            "</head>" +
            "<body><center>" +
            "<h1>My Calendar</h1>";
    private static String ending = "</center></body></html>";

    public static void main(String[] args) {
        UserBase users = new UserBase();

        //WELCOME NEW USER
        get("/welcome", (request, response) -> {
            String html = heading;
            html +=
                "<p>Welcome to MyCalendar. We'll help you keep track of your schedule!  Enter your name to get started.<p>" +
                        "<form action=/logged> Name: <input type='text' name='uname'> <br>" +
                        "Password: <input type = 'text' name='password'><br>" +
                        "<input type='submit' value='Go!'>";
            return html + ending;
            }
        );

	//==================================================================
        //HOMEPAGE FOR LOGGED IN
        get("/logged", (request, response) -> {
            String name = request.queryParams("uname");
            String pword = request.queryParams("password");
            User a = new User(name, pword);
            users.add(a);
            String id = a.getId();
            request.session().attribute("user", id);
            String html = heading + "<h2>You have successfully logged in!</h2>" +
                    "<br><a href = /home/:" + id + ">To Homepage</a>";
            return html + ending;
        });

	//===================================================================
        get("/home/:id", (request, response) -> {
            String id = request.params("id").substring(1);
            String username = users.getUser(id).getName();
            String html = heading;
            html += "<h2>Welcome, " + username + "</h2>" +
                    "<h3>What would you like to do?</h3>" +
                    "<form action=/choice/:" + id + ">" +
                    "<input type ='radio' name = 'choice' value = 'add'>Add an event<br>" +
                    "<input type ='radio' name = 'choice' value = 'remove'>Remove an event<br>" +
                    "<input type ='radio' name = 'choice' value = 'schedule'>See my schedule<br>" +
                    "<input type ='radio' name = 'choice' value = 'friend'>Add a friend<br><br>" +
                    "<input type ='submit' value='Go!'>" +
                    "<br><br><br>" +
                    "<a href = /clear/:"+ id + ">Log Out</a>";
            return html + ending;
            }
        );

	//=====================================================================
        //LOG OUT
        get("/clear/:id", (request, response) -> {
            String id = request.params("id").substring(1);
            users.remove(id);
            return heading + "<h2>You have successfully logged out!</h2>"
                    + "<a href = /welcome>Log In</a>" + ending;
        });

	//=====================================================================
        //USER'S ACTIONS
        get("/choice/:id", (request, response) -> {
            String html = heading;
            String id = request.params("id").substring(1);
            String choice = request.queryParams("choice");

	    //________________________________________________________________
            //ADDING FRIENDS
            if (choice.equals("friend")) {
                if (users.getSize() != 0) {
                    html += "<h2>Available users: </h2><form method = 'post' action = /friend/:" + id + ">";
                    for (int i = 0; i < users.getSize(); i++) {
                        User friend = users.getUser(i);
                        if (!friend.getId().equals(id)) {
                            html += "<input type='radio' name='friend' value='";
                            html += friend.getName();
                            html += "'>";
                            html += friend.getName() + "<br>";
                        }
                    }
                    html += "<input type = 'submit' value = 'Go!'><br><br>";
                }
                else html += "<h2>No available users</h2><br><a href= /home/:" + id + ">Go Back</a><br><br>";
            }

	    //_________________________________________________________________
            //SEEING THE SCHEDULE
            if (choice.equals("schedule")){
                html += "lol";
            }

	    //_________________________________________________________________
	    //REMOVING AN EVENT
	    if (choice.equals("remove")){
		
	    }

	    //_________________________________________________________________
            //ADDING AN EVENT
            if (choice.equals("add")){
                html += "<form method='post' action='/add/:" + id +"'> ";
                html += "Enter the number corresponding to a date within this year " +
                        "i.e. 06/12 for June 12th, 12/01 for December 1st: " +
                        "<input type='text' name='date'><br><br>";
                html += "Enter the title of your event." +
                        "<input type='text' name='title'><br><br>";
                html += "Enter the description of your event." +
                        "<input type='text' name='description'><br><br>";
                html += "Enter the start and end hours (24hr time) of your event. " +
                        "i.e. 06/12 for 6am to 12pm, 14/18 for 2pm to 6pm: " +
                        "<input type='text' name='time'><br><br>";
                html += "What is your event priority? 1-5 5 being the least important<br>" +
                        "<input type='radio' name='priority' value='1'> 1<br>" +
                        "<input type='radio' name='priority' value='2'> 2<br>" +
                        "<input type='radio' name='priority' value='3'> 3<br>" +
                        "<input type='radio' name='priority' value='4'> 4<br>" +
                        "<input type='radio' name='priority' value='5'> 5<br>";
                html += "<input type='submit' value='Go!'><br><br><br>";
            }
            return html +
                    "<a href = /clear/:"+ id + ">Log Out</a><br>" +
                    "<a href = /home/:" + id + ">Home</a>" + ending;
	    }
        });
	//=====================================================================
        //ACTUAL PAGES
	//ADDING FRIENDS
        get("/friend/:id", (request, response) ->{
            String id = request.params("id").substring(1);
            User friend = users.getUser(request.queryParams("friendrequest"));
            users.getUser(id).getFriends().add(friend);
            response.redirect("/home/:" + id);
            return heading +
                    "<h2>You are now friends with " + friend.getName() +"!</h2>" +
                    "<a href = /clear/:"+ id + ">Log Out</a><br>" +
                    "<a href = /home/:" + id + ">Home</a>" + ending;
        });

        //ADDING AN EVENT
        get("/add/:id", (request, response) -> {
            String html = heading;
            String id = request.params("id").substring(1);
            PriorityQueue<Date> calendar = users.getUser(id).getCalendar();
            String date = request.queryParams("date");
            String title = request.queryParams("title");
            String description = request.queryParams("description");
            String times = request.queryParams("time");
            int j,s,st,en;
            j = s = st = en = 0;
            int priority = Integer.parseInt(request.queryParams("priority"));
	    int slash;
	    if (date.indexOf("/")!=-1)
		slash = date.indexOf("/");
	    else comboError("date");
            String a = date.substring(0,2);
            String b = date.substring(3,5);
            if (isInteger(a) && isInteger(b)){
                j = Integer.parseInt(a); // month
                s = Integer.parseInt(b); // day
            }
            if (j < 0 || j > 12 || s < 0 || s > 31){
                comboError("date");        
            }
            if (times.indexOf("/")!=-1)
		slash = times.indexOf("/");
	    else comboError("date");
            a = times.substring(0,slash);
            b = times.substring(slash+1);
            if (isInteger(a) && isInteger(b)){
                st = Integer.parseInt(a); 
                en = Integer.parseInt(b); 
            }
            if (st < 0 || en > 24 || st < 0 || en > 24 || st>en){
                comboError("time");        
            }
            Event event = new Event(title,description,j,s,0,0,priority);
            Date temp = new Date(j,s,new Schedule());
            boolean conflict = false;
            for(Date e : calendar){
                if(e.equals(temp)){
                    conflict = !e.addEvent(event); // addEvent should return a boolean on whether or not the event can be added
                }
            }
            if(conflict)
                calendar.offer(temp);
                html += "Event successfully added! Go back to do something else.";
            return html +
                    "<a href = /clear/:"+ id + ">Log Out</a><br>" +
                    "<a href = /home/:" + id + ">Home</a>" + ending;
        });


    }


    //===================================================================
    // HELPER FUNCTIONS
    //===================================================================
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
    public static void comboError(String type){
        if (type.equals("date"))
	    html += "Please go back and enter a valid mm/dd combination";
	if(type.equals("time"))
            html += "Please enter a valid start/end combination";
    }
}
