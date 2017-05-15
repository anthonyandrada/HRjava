import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Scanner;

/* list of jobs and manages the jobs 
 * @authors kareem khattab, anthony andrada , atif khan , mohammed gaggutir 
 */
public class JobManager 
{
	private ArrayList<Job> JobList;


	public JobManager() //initialize 
	{
		JobList = new ArrayList<Job>(); 
	}
	
	public boolean AddJob(Job New)
	{
		if (JobList.isEmpty() == true)
		{
			New.SetId(1);
		}
		else 
		{
			New.SetId(JobList.size() + 1);
		}
		JobList.add(New);
		return true; 
	}

	public boolean DeleteJob(int JobId)
	{
		Scanner in = new Scanner(System.in);
		char prompt = 'Y';
		while (prompt == 'Y' || prompt == 'y')
		{
			for(Job m : JobList)
			{
				if(m.GetJobId() == JobId  && m.GetStatus()== false)//false means job is empty 
				{
					char prompt2;
					System.out.println("Job ID: " + m.GetJobId());
					System.out.println("Name: " + m.GetName());
					System.out.println("Description: " + m.GetDescription());
					System.out.print("Delete job? (Y/N): ");
					prompt2 = in.next().charAt(0);
					if (prompt2 == 'Y' || prompt2 == 'y')
					{
					JobList.remove(m);
					return true;
					}
					else
					{
						return false;
					}
				}
				else if (m.GetJobId() == JobId  && m.GetStatus() == true)
				{
					System.out.println("Job has been filled. Delete is not allowed.");
					return false;
				}
			}
			System.out.println("Job ID not found. Do you want to enter another job ID? (Y/N)");
			prompt = in.next().charAt(0);
			System.out.print("Enter new job ID: ");
			JobId = in.nextInt();
		}
		in.close();
		return false;
	}
	// The UML and UseCases did not specify how they wanted to have the jobs sorted we will sort by unfilled/filled.
	public Comparator<Job> GetComparator()
	{
		class comparator1 implements Comparator<Job>
		{
			public int compare(Job a, Job b) 
			{
				if(a.GetStatus() == true && b.GetStatus() == false)
				{
					return 1;
				}
				if(a.GetStatus() == false && b.GetStatus() == true)
				{
					return -1;
				}
				return 0;    
			}
		}
		Comparator<Job> JobName = new comparator1();
		return JobName;
	}

	public void Sort()
	{
		Collections.sort(JobList, GetComparator());
	}

	public ArrayList<Job> GetJob()
	{
		return JobList; 
	}
	
	public Job get(int jobID)
	{
		for (Job j : JobList)
		{
			if (j.GetJobId() == jobID)
			{
				return j;
			}
		}
		return null;
	}
	
	public void Print(ArrayList<Job> list)
	{
		for (Job j : list)
		{
			System.out.println("ID: " + j.GetJobId() + " Name: " + j.GetName() + " Department: " + j.GetDepartment() + " Filled: " + j.GetStatus() + " Description: " + j.GetDescription());
		}
	}
	
	public ArrayList<Job> Search(int keywords)
	{
		ArrayList<Job> found = new ArrayList<Job>();
		for (Job j : JobList)
		{
			if (j.GetJobId() == keywords)
			{
				found.add(j);
			}
		}
		return found;
	}
}
