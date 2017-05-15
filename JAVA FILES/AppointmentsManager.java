
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class AppointmentsManager 
{
	private ArrayList<Appointment> AppointmentsList;
	
	public AppointmentsManager()
	{
		AppointmentsList = new ArrayList<Appointment>();
	}
	
	public ArrayList<Appointment> get()
	{
		return AppointmentsList;
	}
	
	public boolean AddAppointment(Appointment New)
	{
		if (AppointmentsList.isEmpty() == true)
		{
			New.SetId(1);
		}
		else 
		{
			New.SetId(AppointmentsList.size() + 1);
		}
		AppointmentsList.add(New);
		return true;
	}
	
	public boolean DeleteAppointment(int AppointmentID)
	{
		for(Appointment kk : AppointmentsList)
		{
			if(kk.GetID() == AppointmentID)
			{
				AppointmentsList.remove(kk);
				return true;
			}
		}
		return false;
	}
	
	public Comparator<Appointment> GetComparator()
	{
		class comparator1 implements Comparator<Appointment>
		{
			public int compare(Appointment a, Appointment b) 
			{
				if(a.GetDate().before(b.GetDate()))
				{
					return -1;
				}
				if(a.GetDate().after(b.GetDate()))
				{
					return 1;
				}
				return 0;    
			}
		}
		Comparator<Appointment> AppID = new comparator1();
		return AppID;
	}
	
	public void Sort()
	{
		Collections.sort(AppointmentsList, GetComparator());
	}
	
	public ArrayList<Appointment> GetApplicantAppointments(Applicant Applicant)
	{
		ArrayList<Appointment> kk = new ArrayList<>();
		for(Appointment aa : AppointmentsList)
		{
			if(aa.GetApplicant().equals(Applicant))
			{
				kk.add(aa);
			}
		}
		return kk;
	}
	
	public void Print(ArrayList<Appointment> list)
	{
		for (Appointment a : list)
		{
			String h = Integer.toString(a.GetDate().getHours());
			String m = Integer.toString(a.GetDate().getMinutes());
			if (a.GetDate().getHours() < 10)
			{
				h = "0" + h;
			}
			if (a.GetDate().getMinutes() < 10)
			{
				m = "0" + m;
			}
			System.out.println("ID: " + a.GetID() + " Applicant Name: " + a.GetApplicant().GetFirstName() + " " + a.GetApplicant().GetLastName() + " Location: " + a.GetLocation() + " Date: " + a.GetDate().getMonth()+1 + "/" + a.GetDate().getDate() + " Time: " + h + ":" + m);
		}
	}
	
	public ArrayList<Appointment> Search(String keywords)
	{
		ArrayList<Appointment> found = new ArrayList<Appointment>();
		for (Appointment a : AppointmentsList)
		{
			if (a.GetApplicant().GetLastName().contains(keywords))
			{
				found.add(a);
			}
			else if (a.GetApplicant().GetFirstName().contains(keywords))
			{
				found.add(a);
			}
			else if (a.GetInterviewer().GetName().contains(keywords))
			{
				found.add(a);
			}
			else if (a.GetID() == Integer.parseInt(keywords))
			{
				found.add(a);
			}
		}
		if (found.isEmpty() == true) System.out.println("No appointments found.");
		return found;
	}
}