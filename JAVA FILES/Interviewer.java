
public class Interviewer 
{
	private int InterviewerID;
	private String Name;
	
	public Interviewer(String name)
	{
		Name = name;
	}
	
	public int GetId()
	{
		return InterviewerID;
	}
	
	public String GetName()
	{
		return Name;
	}
	
	public void SetId(int New)
	{
		InterviewerID = New;
	}
	
	public void UpdateName(String New)
	{
		Name = New;
	}
}
