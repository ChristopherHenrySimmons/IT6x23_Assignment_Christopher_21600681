package EpicVotingSystem;

import java.util.Iterator;

/**
 * File Name : EpicVotingSystem
 * author : 21600681 Chris 
 * Date : 14.09.2017
 * Description : 
 */
public class Staff
{
    private int id;
    private String name;
  //staff voted
    private int voted; 
    //timestamp
    private String timeStampString;
    private int passWord;

    public Staff(int id, String name, int voted, String date, int passWord)//, String date , String passWord
    {
            this.id = id;
            this.name = name;
            this.voted = voted;
            
            this.timeStampString = date;
            this.passWord = passWord;
    }

    public void setId(int id)
    {
       this.id = id;
    }

    public void setName(String name)
    {
            this.name = name;
    }

    public void setVoted()
    {
            this.voted = 1;
    }

    public int getId()
    {
       return id;
    }

    public String getName()
    {
            return name;
    }

    public int hasVoted()
    {
            return voted;
    }

	public void setTimeStampString(String timeStampString) {
		
		this.timeStampString= timeStampString;
		
	}

	public String getTimeStamp() {
		
		return timeStampString;
	}

	public static Iterator<Staff> iterator() {
		
		return null;
	}

	public int getPasWord() {
		return passWord;
	}
	public void setPasWord(int pasWord) {
		this.passWord = pasWord;
	}

}

