import java.util.Date;


public class Rating 
{
	private int RatingID;
	private int Experience;
	private int Attitude;
	private int DesiredSalary;
	private Date LastUpdated;
	
	public Rating(int Exp, int Att, int Salary)
	{
		Experience = Exp;
		Attitude = Att;
		DesiredSalary = Salary;
		LastUpdated = new Date();
	}
	
	public int GetExperience()
	{
		return Experience;
	}
	
	public int GetAttitude()
	{
		return Attitude;
	}
	
	public int GetDesiredSalary()
	{
		return DesiredSalary;
	}
	
	public Date GetLastUpdated()
	{
		return LastUpdated;
	}
	
	public int GetRatingId()
	{
		return RatingID;
	}
	
	public void UpdateExperience(int New)
	{
		Experience = New;
		LastUpdated = new Date();
	}
	
	public void UpdateAttitude(int New)
	{
		Attitude = New;
		LastUpdated = new Date();
	}
	
	public void UpdateDesiredSalary(int New)
	{
		DesiredSalary = New;
		LastUpdated = new Date();
	}
	
	public void SetId(int New)
	{
		RatingID = New;
	}
}
