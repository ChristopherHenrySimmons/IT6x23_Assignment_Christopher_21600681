package EpicVotingSystem;



public class Candidate
{
	//999 is not an eligible candidate
    int candidateCode = 999;
    String name = null;
  //total votes
    int votes = 0; 
    String department;

    //constructor
    public Candidate(int candidateCode, String name, int votes, String department)
    {
        this.candidateCode = candidateCode;
        this.name = name;
        this.votes = votes;
        this.department = department;
    }

    public int getCandidateCode ()
    {
        return candidateCode;
    }

    public String getName()
    {
        return  name;
    }

    public int getVotes()
    {
        return  votes;
    }

    public void addVote()
    {
        votes++;
    }
    
    public String getDepartment() {
		return department;
	}


}