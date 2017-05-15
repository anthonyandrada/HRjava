import java.util.Date;


public class Appointment 
{
	private int AppointmentID;
	private Applicant Applicant;
	private Interviewer Interviewer;
	private Date Time;
	private String Location;
	
	public Appointment(Applicant applicant, Interviewer interviewer, String location, int month, int day, int hour, int minute)
	{
		Applicant = applicant;
		Interviewer = interviewer;
		Time = new Date(2014, month, day, hour, minute);
		Location = location;
	}
	
	public boolean UpdateApplicant(Applicant New)
	{
		Applicant = New;
		return true;
	}
	
	public boolean UpdateInterviewer(Interviewer New)
	{
		Interviewer = New;
		return true;
	}
	
	public boolean UpdateTime(Date New)
	{
		Time = New;
		return true;
	}
	
	public boolean UpdateLocation(String New)
	{
		Location = New;
		return true;
	}
	
	public int GetID()
	{
		return AppointmentID;
	}
	
	public Applicant GetApplicant()
	{
		return Applicant;
	}
	
	public Interviewer GetInterviewer()
	{
		return Interviewer;
	}
	
	public Date GetDate()
	{
		return Time;
	}
	
	public String GetLocation()
	{
		return Location;
	}
	
	public void SetId(int New)
	{
		AppointmentID = New;
	}
}
