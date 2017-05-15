import java.util.ArrayList;

public class Applicant 
{
	private int EmployeeID;
	private String FirstName;
	private String LastName;
	private String EmailAddress;
	private ArrayList<Integer> JobIDs;
	private ArrayList<Rating> Ratings;

	public Applicant(String First, String Last, String Email)
	{
		FirstName = First;
		LastName = Last;
		EmailAddress = Email;
		JobIDs = new ArrayList<Integer>();
		Ratings = new ArrayList<Rating>();
	}

	public void UpdateName(String First, String Last)
	{
		FirstName = First; LastName = Last;
	}
	
	public void UpdateEmail(String New)
	{
		EmailAddress = New;
	}
	
	public void AddJob(int JobID)
	{
		JobIDs.add(JobID);
	}
	
	public int GetId()
	{
		return EmployeeID;
	}
	
	public String GetFirstName()
	{
		return FirstName;
	}
	
	public String GetLastName()
	{
		return LastName;
	}
	
	public String GetEmailAddress()
	{
		return EmailAddress;
	}
	
	public ArrayList<Integer> GetJobs()
	{
		return JobIDs;
	}
	
	public ArrayList<Rating> GetRatings()
	{
		return Ratings;
	}
	
	public boolean AddRating(Rating New)
	{
		if (Ratings.isEmpty() == true)
		{
			New.SetId(1);
		}
		else 
		{
			New.SetId(Ratings.size() + 1);
		}
		Ratings.add(New);
		return true; 
	}
	
	public boolean DeleteRating(int RatingID)
	{
		for(Rating kk : Ratings)
		{
			if(kk.GetRatingId() == RatingID)
			{
				Ratings.remove(kk);
				return true;
			}
		}
		return false;
	}
	
	public void SetId(int New)
	{
		EmployeeID = New;
	}
}
