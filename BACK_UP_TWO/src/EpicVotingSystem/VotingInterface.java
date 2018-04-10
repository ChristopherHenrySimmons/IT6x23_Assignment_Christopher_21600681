package EpicVotingSystem;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import EpicVotingSystem.Staff;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * File Name : EpicVotingSystem
 * author : 21600681 Chris 
 * Date : 14.09.2017
 * Description : 
 */


public class VotingInterface
{
	
    private VotingController vc;
    private Staff theStaff;
    private Candidate theCandidate;
    private Manage m = new Manage(); 
    
    private admin theAdmin;
    
    private final String USERNAME = "admin";
    private final String PASSWORD ="admin123";
    
    
    
    private int numberOfCandidates = 0;
    private BufferedReader in = new BufferedReader( new InputStreamReader( System.in ));
    Scanner scanner = new Scanner(System.in);

    //Set method for num of Candididates
    public int getNumberOfCandidates() 
    {
		return numberOfCandidates;
	}
    
    //Get method for num of Candidates
	public void setNumberOfCandidates(int numberOfCandidates) 
	{
		this.numberOfCandidates = numberOfCandidates;
	}
	
	
	
	//Start
    public static void main(String[] args)
    {
        VotingInterface vi = new VotingInterface();
        vi.start();
    }

    public void start()
    {
        vc = new VotingController();
        commenceVoting();
    }

  //-----------------------------NEW------------------------------
    
    public void commenceVoting()
    {
        boolean systemQuit = false;
        while (!systemQuit)
        {
            String input = null;
            System.out.println("\n\t\t============== Main Menu =====================\n\n");
            System.out.print("Enter \"v\" to Vote as staff \nOR \"a\" to login in as system adminirstaror \nOR \"h\" help options : ");
            input = getInput();

            if (input.equalsIgnoreCase("V"))
            {
            	//New Staff Login
//            	validatestaff();
            	try {
            		if(vc.dateChecker()){
            			manageVote();
            		}

					
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
            	
            	//Doesn't go to managePass FIXEDish
//            	managePass();
            }
            else if (input.equalsIgnoreCase("A"))
            {
                if(validateAdmin()){
                manageAdmin();
                }
            }
            else if (input.equalsIgnoreCase("H"))
            {
                help();
                systemQuit = false;
            }
            else
            {
                System.out.println("Your input was not recognised");
            }
        }
    }

    	//Help Similar to getStaffVote
    	public void help() {
		
    		boolean retry = false;
    		
//    		displayVotingScreen();
		
        while (!retry)
        {
            String input = null;
//            System.out.println("\n\n");
            System.out.println("\n\t\t===================== Help =====================\n\n");
            System.out.print("Enter \"vh\" Vote help \nOR \"ah\" adminirstaror help \nOR \"Q\" to quit : ");
            
           try {
            input = getInput();

            if (input.equalsIgnoreCase("VH"))
            {
                System.out.println("\nTo vote goto main eVoting System menu,\npress \"v\" then type in your staff id and password");
                retry = false;
            }
            
            else if (input.equalsIgnoreCase("AH"))
            {
                System.out.println("\nTo administrate goto main eVoting System menu,\npress \"a\" then type in your adminirstaror username and password");
                retry = false;
            }
            else if (input.equalsIgnoreCase("Q")) 
            {
            	
            	retry = true;
            }
            else
            {
                System.out.println("Your input was not recognised");
            }
        }
        
        catch(NumberFormatException e)
        {
             System.out.println("Enter a number");
        }
         catch(NullPointerException e)
         {
              System.out.println("Error! Staff ID not found.\nPress ENTER to try again or  \"q\" to QUIT :  ");
                 if ("q".equalsIgnoreCase(getInput()))
                 {
                     System.out.println("Good bye!");
                     retry = true;
                 }
         }
        }
		
        System.out.print("going back to voting screen");
	}
    	//-----------------------------NEW------------------------------

     //Input Reader
     private String getInput()
     {
        String theInput = "";

        try
        {
            theInput = in.readLine().trim();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return theInput;
     }

    public void manageVote()
    {
        boolean moveOn = false;
        int counter = 0;
        //loop vote
        while (moveOn == false || counter ==3)
        {
            System.out.print("Please enter your staff ID :");
            try
            {
                theStaff = vc.getStaff(Integer.parseInt(getInput()));
                
                System.out.print("Please enter your staff password :");
                theStaff = vc.getStaffPass(Integer.parseInt(getInput()));
                
                
                
//                password here
//                theStaff = vc.getStaffPass(getInput());

                if(theStaff.hasVoted() == 1)
                {
                    System.out.println("\nYou have voted and cannot vote again\nGood bye...!");
                    moveOn = true;
                }
                else if (theStaff.hasVoted() == 0)
                {
                    getStaffVote();
                    moveOn = true;
                }
                else if(counter ==3)
                {
                	moveOn = true;
                }
                else
                {
                    System.out.println("There seems to be a problem.  Contact your administrator");
                }
                //3 times and then go back to main menu need a counter? loop?
                
                
            }
            catch(NumberFormatException e)
            {
                 System.out.println("Invalid entry - you must enter a number\nPlease try again");
                 ++counter;
            }
             catch(NullPointerException e)
             {
                  System.out.println("Error! Staff ID not found.\nPress ENTER to try again or  \"q\" to QUIT :  ");
                  ++counter;
                  //counter here?
                     if ("q".equalsIgnoreCase(getInput()))
                     {
                         System.out.println("Good bye!");
                         moveOn = true;
                     }
             }
         }
         System.out.print("going back to voting screen...");
     }
    
    


     public void displayVotingScreen()
     {

        System.out.println("\nWelcome "+ theStaff.getName()+"!\n");
        setNumberOfCandidates(0);

        ArrayList<?> candidates = vc.getCandidates();

        Iterator<?> it = candidates.iterator();
        System.out.println("\tCode\tCandidate Name");
        System.out.println("\t====\t==============\n");
        while(it.hasNext())
        {   theCandidate = (Candidate)it.next();
            System.out.println("\t" + theCandidate.getCandidateCode() + "\t" + theCandidate.getName());
            setNumberOfCandidates(getNumberOfCandidates() + 1);
        }
     }

     //choice of vote
     private void getStaffVote()
     {
        int candidateCode;
        boolean retry = true;

        displayVotingScreen();
        //Date Log

        while (retry)
        {
            System.out.print("\n\nEnter your candidate's code OR enter Q to quit voting : ");
            try{
                String input = getInput();

                if (input.equalsIgnoreCase("Q"))
                {
                    retry = false;
                }
                else
                {
                    candidateCode = Integer.parseInt(input);
                    theCandidate = vc.getCandidate(candidateCode);
                    System.out.print("\nYou have selected " + theCandidate.getName()+ ". \n\nEnter  Y to confirm or any other key to Cancel, then press ENTER : ");

                    if (getInput().equalsIgnoreCase("y"))
                    {
                        vc.recordVote();
                        System.out.println("\n\nThanks for voting " + theStaff.getName() + ". Bye!!!");
                        retry = false;
                    }
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("That was not a number you entered\nPlease try again");
            }
            catch(NullPointerException e)
            {
                System.out.println("This candidate code does not exit\nPlease try again");
            }
            catch(Exception e){
                System.out.println("We have a problem, please contact your administrator");
            }
        }
     }

     private boolean validateAdmin()
     {
        boolean adminQuit = false;

        while (!adminQuit)
        {
            System.out.print("\nYou have entered Administration space. \nEnter username or \"Q\" to quit : " );
            String input = getInput();
            
           
            for (int i = 0; i <vc.getadmins().size() ;i++)
            {         	
            	theAdmin = vc.getAdminUsername(input);
            	if (theAdmin != null)
            	{
            		break;
            	}
            }            
            
            try
            {
//            	System.out.print(theAdmin.getPasWord());
            	 if (input.equalsIgnoreCase("q"))
                 { 
                     adminQuit = false;
                     commenceVoting();
                 }
            	 else
                 {
                     String password = null;
                     
                     System.out.print("\nPlease enter password : ");
                     password = getInput().trim();

                     if((theAdmin.getPasWord()).equals(password))
                     { 
                         
                         adminQuit = true;
                     }
                     else
                     {
                         System.out.println("Incorrect username.\"" + password+"\" does not match password stored for this admin");
                     }
                 }
            }
            catch(Exception e)
            {
            	System.out.println("Incorrect username. Username \"" + input + "\" does not exit!");
            }
        }
		return adminQuit;
     }

     

   //-----------------------------NEW------------------------------
   //new add view delete staff goes into this method
     private boolean manageAdmin()
     {
        boolean adminQuit = false;
        boolean systemQuit = false;

        
        
        while (!adminQuit){
        	System.out.println("\n\t\t===================== Manage =====================\n\n");
            
            System.out.println("\"C\"    :To continue voting enter");
	        System.out.println("\"Stop\" :To end voting enter");
	        System.out.println("\"VR\"   :To View Voting Result");
	        System.out.println("\"HV\"   :To View Who Voted");
	        
	        System.out.println("\"US\"   :To Update Staff");
	        
	        System.out.println("\"UA\"   :To Update Admin");
	        System.out.println("\"UC\"   :To Update Candidate");
	        System.out.println("\"UD\"   :To Update Date Range");
	        System.out.println("\"Q \"   :To Quit Back To Main Menu");
	    
	        System.out.print("\n\n");
	        System.out.println("Enter your choice: ");
            String input = getInput();
            if (input.equalsIgnoreCase("C"))
            {
                adminQuit = true;
            }
            else if(input.equalsIgnoreCase("Stop"))
            {
                //stop system
                adminQuit = true;
                systemQuit = true;
                System.out.println("Voting System Closed");
            }
            else if(input.equalsIgnoreCase("US"))
            {
            	adminQuit = true;
            	//ERROR Loops Choice
            	//FIXED 11/10/2017
            	
					m.ManageStaff();
				
            	
            }
            else if(input.equalsIgnoreCase("UA"))
            {
            	adminQuit = true;
            	//ERROR - goes back to main screen
            	//FIXED 11/10/2017
            	m.Manageadmin();
            	
            }
            else if(input.equalsIgnoreCase("UC"))
            {
            	adminQuit = true;
            	m.Managecandidate();
            	
            }
            else if(input.equalsIgnoreCase("UD"))
            {
            	adminQuit = true;
            	System.out.println("Note this method is not working right now");
            	m.Managedate();
            	
            }
            else if(input.equalsIgnoreCase("VR"))
            {
            	adminQuit = false;
            	printVoteResults();
            }
            else if(input.equalsIgnoreCase("HV"))
            {
            	
            	try {
					m.ViewAllRecord();
				} catch (IOException e) {
					
					e.printStackTrace();
					adminQuit = false;
				}
            }
            else
            {
            	System.out.println("Good Bye... : \n\n");
                adminQuit = true;
            }
        }
        return systemQuit;
     }
     //-------------NEW------------
     
     

	//prints out the voting results
     public void printVoteResults()
     {
        ArrayList<?> candidates = vc.getCandidates();
        int totalVoters = vc.getTotalVoters();
        double totalVoted = 0;
        int candidateVotes = 0;

        //formatting display Voting stats
        DecimalFormat df = new DecimalFormat("###.##");

        Iterator<?> it = candidates.iterator();
        System.out.println("\n\t\t VOTING STATS");
        System.out.println("\t\t=========================\n");
        System.out.println("Code\tName\t\tDepartment\tVotes\t(Vote%)");
        System.out.println("____\t____\t\t__________\t_____\t______\n");


        //calculate total voted
        while(it.hasNext()) 
        {
             theCandidate = (Candidate) it.next();
          // count total votes for this candidate
             totalVoted += theCandidate.getVotes();
        }

        it = candidates.iterator();
        while(it.hasNext()) 
        {
             theCandidate = (Candidate) it.next();
             candidateVotes = theCandidate.getVotes();
             System.out.println(theCandidate.getCandidateCode() + "\t" + theCandidate.getName() + "\t" + theCandidate.getDepartment() + "\t\t" +//Department here
             candidateVotes +"\t(" + df.format((candidateVotes/totalVoted)*100) +"%)");
        }
        System.out.println("\nNumbers on voting list: " + totalVoters);
        System.out.println("Numbers voted: " + totalVoted + "(" + df.format((totalVoted/totalVoters)*100)  + "%)");
        System.out.println();

   }

     //validates admin Username & Password
     public boolean validateAdmin(String username, String password)
     {
        if(username.equalsIgnoreCase(USERNAME)&&(password.equals(PASSWORD)))
        {
            return true;
        }
        else
        {
            return false;
        }
     }
     
     
     
     

	
}
