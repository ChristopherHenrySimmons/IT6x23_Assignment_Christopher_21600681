package EpicVotingSystem;

public class admin 
{
	
	private int id;
    private String name;
    private String userName;
    private String passWord;
    
	public admin(int id, String name, String userName, String pasWord) 
	{
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.passWord = pasWord;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasWord() {
		return passWord;
	}
	public void setPasWord(String pasWord) {
		this.passWord = pasWord;
	}
    
}
