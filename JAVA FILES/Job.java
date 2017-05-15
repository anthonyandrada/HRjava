/* @Authors Kareem Khattab, Anthony Andrada, Atif Khan , Mohammed Gaggutir 
 * 
 */
public class Job 
{
	private int Id; // Job Id
	private String Name; // Job Name 
	private String Department; // Job Department 
	private String Description; // Description of Job
	private boolean Status; // Whether job is filled or empty 

	public Job(String NewName, String NewDepartment, String NewDescription)//constructor 
	{
		Name = NewName; 
		Description = NewDescription; 
		Department = NewDepartment;
		Status = false;
	}
/* SO THEY WANTED THE UPDATE TO BE BOOLEAN, THAT WASNT GNNA FLY, WE DECIDED TO MAKE THEM VOID BECAUSE IT DOES NOT MAKE SENSE TO CHECK THE UPDATE METHODS
 * IF THEY WERE TRUE OR NOT. THEY ALSO DID NOT HAVE GETS IN THERE UML AND USE CASES SO WE ARE ADDING THOSE IN. Please read report for further explanation. 
 */
	//updates the Description
	public void UpdateDescription(String NewDescription)
	{	
		Description = NewDescription; 
	}

	//updates the name, and then checks if it works 
	public void UpdateName(String NewName) 
	{
		Name = NewName; 
	}

	//update the department and then checks if it works 
	public void UpdateDepartment(String NewDepartment)
	{
		Department = NewDepartment; 
	}
	
	//updates the department and then checks if it works 
	public void UpdateStatus(boolean NewStatus)
	{
		Status = NewStatus; 
	}
	
	public void SetId(int New)
	{
		Id = New;
	}

	public int GetJobId()
	{
		return Id; 
	}

	public boolean GetStatus()
	{
		return Status; 
	}

	public String GetName()
	{
		return Name; 
	}

	public String GetDescription()
	{
		return Description; 
	}

	public String GetDepartment()
	{
		return Department; 
	}
}
