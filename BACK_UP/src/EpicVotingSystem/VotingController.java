
package EpicVotingSystem;

import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * File Name : EpicVotingSystem
 * author : 21600681 Chris 
 * Date : 14.09.2017
 * Description : 
 */

public class VotingController
{
    private ArrayList<Staff> staffs = new ArrayList<Staff>();
    private ArrayList<Candidate> candidates = new ArrayList<Candidate>();
    private ArrayList<admin> admins = new ArrayList<admin>();

    private Staff theStaff;
    private Candidate theCandidate;
    private admin theAdmin;
    
    private VotingInterface vi;
    public Date today = new Date();
    // start date
    //ERROR Does not read right format
    public Date startDate;
    // end date
    //Its changing the time not Date why? FIXEDish
    //Now i get the option to type in Staff id 
    //Now its just Jan Upper Case M is mouth and lower case m is min nice to know
    public Date endDate;
    
    //++++++++New Method for Date Range++++++++
    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    public boolean dateChecker() throws ParseException{
    	
    	
	    // simple date formatter
	    
	    // todays date
	    today = new Date();
	    // start date
	    //ERROR Does not read right format
	    startDate = sdf.parse("01/01/2017");
	    // end date
	    //Its changing the time not Date why? FIXEDish
	    //Now i get the option to type in Staff id 
	    //Now its just Jan Upper Case M is mouth and lower case m is min nice to know
	    endDate = sdf.parse("31/10/2017");
	    
	    if(!today.before(startDate) && !today.after(endDate)) {
	    
	    	System.out.println("");
	    	System.out.println("");
	    	System.out.println("Today is the Day Bud.");
	    	System.out.println("");
	    	System.out.println("");
	    	System.out.printf("Start: %s\nEnd: %s\nToday: %s\n",startDate.toString(), endDate.toString(), today.toString());
	    	System.out.println("");
	    	System.out.println("");
	    	//ERROR Lets User pass, even within wrong Date range
	    	
//	    	vi.commenceVoting();
	    	return true;
	    	    	
	    }else {
	    	
//	    	vi.manageVote();
//	    	return true;
	    	System.out.println("");
	    	System.out.println("");
	    	System.out.println("Today is not the Day Bud!!!");
	    	System.out.println("");
	    	System.out.println("");
	    	return false;
	    }
     
	    
    }
    public boolean ManageDateRange(){
		boolean dateQuit = false;
		while (!dateQuit){
			String input2 = getInput();
			Scanner input = new Scanner(System.in);
			System.out.println("\n\t\t===================== Manage Date =====================\n\n");
			System.out.println("\"SD\"  :Type in New Start Date");
			System.out.println("\"ED\"  :Type in New End Date");
//			Date startDate = sdf.parse("01/01/2017");
//			Date endDate = sdf.parse("31/10/2017");
			
//			if (input.equalsIgnoreCase("A")){
//				
//				startDate = input.next();
//				
//			}
			dateQuit = true;
		}
    	
    	
    	return false;
    }
    private BufferedReader in = new BufferedReader( new InputStreamReader( System.in ));
    
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
    
    
    
//	System.out.println("Time is up!!!\n\n");
//	vi.commenceVoting();
	//pass = true;
    // need a method to check if todays date is within the range
    // do it by using date.before && date.after
    
//    boolean isWithinRange(Date testDate) {
// 	   return !(testDate.before(startDate) || testDate.after(endDate));
// 		}
    
    //++++++++++++++++++++++++
  //Constructor calls "loadStaffData" "loadCandidateData" to load staff and candidate data from a text file.
    public VotingController()
    {
        loadStaffData();
        loadCandidateData();
        loadAdminData();
    }
    

    
    
    public void loadAdminData()
    {
    	try
        {
             String fileName = "admin.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));

             String adminData;

             while((adminData = reader.readLine())!= null)
             {
                 String[] adminDetails = adminData.split(",");
                 
                 int code = Integer.parseInt(adminDetails[0]);
                 String Name = adminDetails[1];
                 String adminUserName = adminDetails[2];
                 String adminPassWord = adminDetails[3];
                 
                 theAdmin = new admin(code, Name,adminUserName, adminPassWord);
                 admins.add(theAdmin);
             }
             reader.close();
         }
         catch(IOException e)
         {
             System.out.println("Error! There was a problem with loading candidate names from file");
         }
    }
//  loads candidates data from text file "candidates.txt"Each candidate from file is loaded as "theCandidate" and then added to "candidates" Arraylist.
    
    public void loadCandidateData()
    {
        try
        {
             String fileName = "candidates.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));

             String candidateData;

             while((candidateData = reader.readLine())!= null)
             {
                 String[] candidateDetails = candidateData.split(",");
                 int code = Integer.parseInt(candidateDetails[0]);
                 int votes = Integer.parseInt(candidateDetails[2]);
                 theCandidate = new Candidate(code, candidateDetails[1], votes, candidateDetails[3]);
                 candidates.add(theCandidate);
             }
             reader.close();
         }
         catch(IOException e)
         {
             System.out.println("Error! There was a problem with loading candidate names from file");
         }
    }

//  loads staff data from text file "staff.txt".
    //view Staff method?========================
    public void loadStaffData()
    {
         try
         {
             String fileName = "staff.txt";


             File theFile = new File(fileName);

             BufferedReader reader = new BufferedReader(new  FileReader(theFile));

             String staffData;
             String[] staffDetails;

             while((staffData = reader.readLine()) != null)
             {
                 staffDetails = staffData.split(",");
                 int id = Integer.parseInt(staffDetails[0]);
                 int voted = Integer.parseInt(staffDetails[2]);
                 int pass = Integer.parseInt(staffDetails[4]);
                 theStaff = new Staff(id, staffDetails[1], voted, staffDetails[3], pass);//, staffDetails[3]
                 //add date???
                 staffs.add(theStaff);
             }
             reader.close();
         }
         catch(IOException e)
         {
              System.out.println("Error! There was a problem with loading staff names from file");
         }
         catch(Exception e)
         {
             System.out.println("Error! Unlown problem accoured during loading the staff names from file.");
         }
    }

  //return id found in the staffs ArrayList
    //+++++++++++UserName++++++++++++++++
    public Staff getStaff(int id)
    {
        Iterator<Staff> it = staffs.iterator();
        while(it.hasNext())
        {
            theStaff = (Staff) it.next();
            if(theStaff.getId()== id)
            {
                return theStaff;
            }
        }
        return null;
    }
    //++++++++++++++++++++Password++++++++++++++
    public Staff getStaffPass(int pass) {
    	
    	Iterator<Staff> it = staffs.iterator();
        while(it.hasNext())
        {
            theStaff = (Staff) it.next();
            if(theStaff.getPasWord()== pass)
            {
		return theStaff;
            }
        }
		return null;
    }

    //return staff password in the staffs ArrayList
    
    //  return a Candidate found in the staffs ArrayList
    public Candidate getCandidate(int candidateCode)
    {
        Iterator<Candidate> it = candidates.iterator();
        while(it.hasNext())
		{
            theCandidate = (Candidate) it.next();
            if(theCandidate.getCandidateCode() == candidateCode){
                return theCandidate;
            }
        }
        return null;
    }


    public ArrayList<Candidate> getCandidates()
    {
        return candidates;
    }
    public ArrayList<Staff> getStaffs()
    {
        return staffs;
    }
    public ArrayList<admin> getAdmins()
    {
        return admins;
    }
    
   
    public admin getAdminUsername(String adminUserName)
    {
        Iterator<admin> it = admins.iterator();
        while(it.hasNext())
		{
            theAdmin = (admin) it.next();
            if((theAdmin.getUserName()).equals(adminUserName))
            {
                return theAdmin;
            }
        }
        return null;
    }
    
    
    
    // return a collection Admin found in the Admins ArrayList
    public ArrayList<admin> getadmins()
    {
        return admins;
    }

 // used to return total number of staff
    public int getTotalVoters()
    {
        return staffs.size();
    }
    
      
  //update number of votes received and save every staff vote to textfile now also timestamp
//    Date today = Calendar.getInstance().getTime();
//    String deadline = "13-12-17";
    
    
    
    public void recordVote()
    {
    	//new timeStamp
    	java.util.Date date = new java.util.Date();
    	Timestamp theTimeStamp = new Timestamp(date.getTime());
    	
    	
    	DateFormat theDateFormat = new SimpleDateFormat("dd-mm-yy");
    	String timeStampString = theDateFormat.format(theTimeStamp);
    	
        theStaff.setVoted();
        theStaff.setTimeStampString(timeStampString);
        theCandidate.addVote();
        //save to textfile
        saveStaffData();
        //save to textfile
        saveCandidateData();
        //new
        
    }
    //18/10/2017 Date Check
//    public void checkdate(){
//   	 
//   	 if(theDateFormat.compareTo(deadline) > 0){
//   		 theStaff.setExpireVote();
//   		 saveStaffDate();
//   	 }
//   	 else
//   	 {
//   		 
//   	 }
//   	 
//    }
    
    
  //This method writes staffs data back to textfile
    //+++++++++++++++++++++++++++++Can be used for add staff or admin?++++++++++++++++++++++++++++++++
    public void saveStaffData()
    {
        try
        {
        	BufferedWriter writer = new  BufferedWriter(new FileWriter("staff.txt"));
        	Iterator<Staff> it = staffs.iterator();
            String staffDetails;
            while(it.hasNext())
            {
                theStaff = (Staff) it.next();
                staffDetails = theStaff.getId() + "," +theStaff.getName() + "," + theStaff.hasVoted() + "," + theStaff.getTimeStamp() + "\n";
                writer.write(staffDetails);
            }
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

  //writes candidates data to textfile
    public void saveCandidateData()
    {
        try
        {
            BufferedWriter writer = new  BufferedWriter(new FileWriter("candidates.txt"));
            Iterator<Candidate> it = candidates.iterator();
            String candidateDetails;
            while(it.hasNext())
			{
                theCandidate = (Candidate) it.next();
                candidateDetails = theCandidate.getCandidateCode() + "," +theCandidate.getName() + "," + theCandidate.getVotes() +"\n";
                writer.write(candidateDetails);
            }
            writer.close();
        }
        catch(IOException e)
		{
            System.out.println(e);
        }
    }
    
    
}
