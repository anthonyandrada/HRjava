import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ApplicantsManager 
{
	private ArrayList<Applicant> ApplicantsList;
	
	public ApplicantsManager()
	{
		ApplicantsList = new ArrayList<Applicant>();
	}
	
	public boolean AddApplicant(Applicant New)
	{
		if (ApplicantsList.isEmpty() == true)
		{
			New.SetId(1);
		}
		else 
		{
			New.SetId(ApplicantsList.size() + 1);
		}
		ApplicantsList.add(New);
		return true; 
	}
	
	public boolean DeleteApplicant(int ApplicantID)
	{
		for(Applicant kk : ApplicantsList)
		{
			if(kk.GetId() == ApplicantID)
			{
				ApplicantsList.remove(kk);
				return true;
			}
		}
		return false;
	}
	
	public Comparator<Applicant> GetComparator()
	{
		class comparator1 implements Comparator<Applicant>
		{
			public int compare(Applicant a, Applicant b) 
			{
				if(a.GetLastName().compareTo(b.GetLastName()) != 0)
				{
					return a.GetLastName().compareTo(b.GetLastName());
				}
				else
				{
					return a.GetFirstName().compareTo(b.GetFirstName());
				} 
			}
		}
		Comparator<Applicant> AppID = new comparator1();
		return AppID;
	}
	
	public void Sort()
	{
		Collections.sort(ApplicantsList, GetComparator());
	}
	
	public ArrayList<Applicant> Search(String keywords)
	{
		ArrayList<Applicant> found = new ArrayList<Applicant>();
		for (Applicant i : ApplicantsList)
		{
			if (i.GetFirstName().equalsIgnoreCase(keywords))
			{
				found.add(i);
			}
			else if (i.GetLastName().equalsIgnoreCase(keywords))
			{
				found.add(i);
			}
		}
		return found;
	}
	
	public Applicant getApplicant(int appId)
	{
		for (Applicant a: ApplicantsList)
		{
			if (a.GetId() == appId)
			{
				return a;
			}
		}
		return null;
	}
	
	public ArrayList<Applicant> GetApplicants()
	{
		return ApplicantsList;
	}
	
	public void Print(ArrayList<Applicant> list)
	{
		for (Applicant a: list)
		{
			System.out.println("ID: " + a.GetId() + " Name: " + a.GetFirstName() + " " + a.GetLastName() + " Email: " + a.GetEmailAddress());
			if (a.GetRatings() != null && a.GetRatings().isEmpty() == false)
			for (Rating r : a.GetRatings())
			{
				System.out.println("Rating ID: " + r.GetRatingId() + " Experience: " + r.GetExperience() + " Attitude: " + r.GetAttitude() + " Salary: " + r.GetDesiredSalary());
			}
		}
	}
}
