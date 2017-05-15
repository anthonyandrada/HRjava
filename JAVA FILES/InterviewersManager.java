import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class InterviewersManager 
{
	private ArrayList<Interviewer> InterviewersList;
	
	public InterviewersManager()
	{
		InterviewersList = new ArrayList<Interviewer>();
	}
	
	public boolean AddInterviewer(Interviewer New)
	{
		if (InterviewersList.isEmpty() == true)
		{
			New.SetId(1);
		}
		else 
		{
			New.SetId(InterviewersList.size() + 1);
		}
		InterviewersList.add(New);
		return true;
	}
	
	public boolean DeleteInterviewer(int InterviewerID)
	{
		for(Interviewer kk : InterviewersList)
		{
			if(kk.GetId() == InterviewerID)
			{
				InterviewersList.remove(kk);
				return true;
			}
		}
		return false;
	}
	
	public void Sort()
	{
		Collections.sort(InterviewersList, GetComparator());
	}
	
	public Comparator<Interviewer> GetComparator()
	{
		class comparator1 implements Comparator<Interviewer>
		{
			public int compare(Interviewer a, Interviewer b) 
			{
				return a.GetName().compareTo(b.GetName());  
			}
		}
		Comparator<Interviewer> InterviewerName = new comparator1();
		return InterviewerName;
	}
	
	public ArrayList<Interviewer> GetInterviewers()
	{
		return InterviewersList;
	}
	
	public ArrayList<Interviewer> Search(String keywords)
	{
		ArrayList<Interviewer> found = new ArrayList<Interviewer>();
		keywords = keywords.toLowerCase();
		for (Interviewer i : InterviewersList)
		{
			if (i.GetName().toLowerCase().contains(keywords) == true)
			{
				found.add(i);
			}
		}
		return found;
	}
	
	public void Print(ArrayList<Interviewer> list)
	{
		for (Interviewer i : list)
		{
			System.out.println("ID: " + i.GetId() + " Name: " + i.GetName());
		}
	}
	
	public Interviewer get(int id)
	{
		for (Interviewer i : InterviewersList)
		{
			if (i.GetId() == id)
			{
				return i;
			}
		}
		return null;
	}
}
