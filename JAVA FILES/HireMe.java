import java.util.ArrayList;
import java.util.Scanner;


public class HireMe
{
	private ApplicantsManager ApplicantManager;
	private JobManager JobsManager;
	private InterviewersManager InterviewersManager;
	private AppointmentsManager AppointmentsManager;
	public Menu CurrentMenu;
	
	public HireMe()
	{
		ApplicantManager = new ApplicantsManager();
		JobsManager = new JobManager();
		InterviewersManager = new InterviewersManager();
		AppointmentsManager = new AppointmentsManager();
	}
	
	public void DisplayMenu()
	{
		System.out.println("HireMe");
		
		Menu MainMenu = new Menu(1, "Jobs", 0);
		MainMenu.Add("Applicants", 0);
		MainMenu.Add("Interviewers", 0);
		MainMenu.Add("Appointments", 0);
		MainMenu.Add("Exit", 1);
		
		Menu ApplicantsMenu = new Menu(1, "Add Applicant", 2);
		ApplicantsMenu.Add("Delete Applicant", 3);
		ApplicantsMenu.Add("Rate Applicant", 4);
		ApplicantsMenu.Add("View Applicants", 12);
		ApplicantsMenu.Add("Back to the main menu", 0);
		ApplicantsMenu.SetSubMenu(5, MainMenu);
		
		Menu JobsMenu = new Menu(1, "Add Job", 5);
		JobsMenu.Add("Delete Job", 6);
		JobsMenu.Add("Mark Job as Filled", 7);
		JobsMenu.Add("View Jobs", 13);
		JobsMenu.Add("Back to the main menu", 0);
		JobsMenu.SetSubMenu(5, MainMenu);
		
		Menu InterviewersMenu = new Menu(1, "Add Interviewer", 14);
		InterviewersMenu.Add("Delete Interviewer", 8);
		InterviewersMenu.Add("Assign Interviewer to Applicant", 9);
		InterviewersMenu.Add("View Interviewers", 15);
		InterviewersMenu.Add("Back to the main menu", 0);
		InterviewersMenu.SetSubMenu(5, MainMenu);
		
		Menu AppointmentsMenu = new Menu(1, "View Appointments", 10);
		AppointmentsMenu.Add("Delete Appointment", 11);
		AppointmentsMenu.Add("Back to the main menu", 0);
		AppointmentsMenu.SetSubMenu(3, MainMenu);
		
		MainMenu.SetSubMenu(1, JobsMenu);
		MainMenu.SetSubMenu(2, ApplicantsMenu);
		MainMenu.SetSubMenu(3, InterviewersMenu);
		MainMenu.SetSubMenu(4, AppointmentsMenu);
		
		CurrentMenu = MainMenu;
	}
	
	public void ChooseMenu()
	{
		System.out.println();
		CurrentMenu.Display(1);
		Scanner in = new Scanner(System.in);
		int prompt = 0;
		System.out.println("");
		System.out.print("Please make a selection: ");
		prompt = in.nextInt();
		in.nextLine();
		if (CurrentMenu.Choose(prompt) != null)
		{
			CurrentMenu = CurrentMenu.Choose(prompt);
			System.out.println("");
		}
		else 
		{
			System.out.println("");
			doAction(CurrentMenu.getAction(prompt));
		}
	}

	public ApplicantsManager GetApplicantsManager()
	{
		return ApplicantManager;
	}
	
	public JobManager GetJobsManager()
	{
		return JobsManager;
	}
	
	public InterviewersManager GetInterviewersManager()
	{
		return InterviewersManager;
	}
	
	public AppointmentsManager GetAppointmentsManager()
	{
		return AppointmentsManager;
	}

	public void doAction(int action)
	{
		Scanner in = new Scanner(System.in);
		switch (action)
		{
			case 1: 
				System.exit(0);
				break;
			case 2:
				boolean run = true;
				while (run == true)
				{
					System.out.print("Enter a job ID: ");
					int job = in.nextInt();
					in.nextLine();
					if (JobsManager.Search(job).isEmpty() == true)
					{
						System.out.print("Job ID not found. Do you want to enter another job ID? (Y/N): ");
						String prompt = in.next();
						if (prompt.charAt(0) == 'N' || prompt.charAt(0) == 'n')
						{
							run = false;
							break;
						}
					}
					else
					{
						System.out.print("Enter applicant first name: ");
						String first = in.next();
						System.out.print("Enter applicant last name: ");
						String last = in.next();
						System.out.print("Enter applicant email adress: ");
						String email = in.next();
						Applicant newApp = new Applicant(first, last, email);
						if (ApplicantManager.AddApplicant(newApp) == true)
						{
							ApplicantManager.getApplicant(newApp.GetId()).AddJob(job);
							System.out.println();
							System.out.println("Applicant added succesfully.");
						}
						else
						{
							System.out.println();
							System.out.println("Failed to add applicant");
						}
						run = false;
						break;
					}
				}
				break;
				
			case 3:
				System.out.print("Enter applicant first or last name: ");
				String name = in.nextLine();
				System.out.println();
				ApplicantManager.Print(ApplicantManager.Search(name));
				System.out.println();
				System.out.print("Enter an applicant ID to delete: ");
				int delete = in.nextInt();
				in.nextLine();
				if (ApplicantManager.DeleteApplicant(delete) == true)
				{
					System.out.println();
					System.out.println("Applicant successfully deleted.");
				}
				else
				{
					System.out.println();
					System.out.println("Failed to delete applicant.");
				}
				break;
			case 4:
				System.out.print("Enter applicant first or last name: ");
				String appname = in.nextLine();
				System.out.println();
				ApplicantManager.Print(ApplicantManager.Search(appname));
				System.out.println();
				if (ApplicantManager.Search(appname).isEmpty() == true)
				{
					System.out.println();
					System.out.println("No applicants found.");
				}
				else
				{
					System.out.print("Enter an applicant ID to rate: ");
					int appid = in.nextInt();
					in.nextLine();
					if (ApplicantManager.getApplicant(appid) == null)
					{
						System.out.println();
						System.out.println("No applicants found.");
					}
					else
					{
						ArrayList<Appointment> app = AppointmentsManager.GetApplicantAppointments(ApplicantManager.getApplicant(appid));
						System.out.print("Enter interviewer's name: ");
						String interviewerName = in.nextLine();
						if (InterviewersManager.Search(interviewerName).isEmpty() == true)
						{
							System.out.println("No interviewers found.");
						}
						else
						{
							for (Appointment a : app)
							{
								String intName = interviewerName.toLowerCase();
								String applicName = a.GetInterviewer().GetName().toLowerCase();
								if (applicName.contains(intName) == true && a.GetApplicant().equals(ApplicantManager.getApplicant(appid)))
								{
									System.out.print("Enter applicant experience rating: ");
									int xp = in.nextInt();
									in.nextLine();
									System.out.print("Enter applicant attitude rating: ");
									int att = in.nextInt();
									in.nextLine();
									System.out.print("Enter applicant salary rating: ");
									int sal = in.nextInt();
									in.nextLine();
									Rating rate = new Rating(xp, att, sal);
									ApplicantManager.getApplicant(appid).AddRating(rate);
								}
							}
						}
					}				
				}
				break;
			case 5:
				System.out.print("Enter job name: ");
				String jobName = in.nextLine();
				System.out.print("Enter job department: ");
				String jobDepartment = in.nextLine();
				System.out.print("Enter job description: ");
				String jobDescription = in.nextLine();
				if (JobsManager.AddJob(new Job(jobName, jobDepartment, jobDescription)) == true)
				{
					System.out.println();
					System.out.println("Job added succesfully.");
				}
				else
				{
					System.out.println();
					System.out.println("Failed to add job.");
				}
				break;
			case 6:
				System.out.print("Enter job ID: ");
				int ID = in.nextInt();
				in.nextLine();
				if (JobsManager.Search(ID).isEmpty() == true)
				{
					System.out.println();
					System.out.println("No jobs found.");
				}
				else
				{
					if (JobsManager.DeleteJob(ID) == true)
					{
						System.out.println();
						System.out.println("Job succesfully deleted.");
					}
					else
					{
						System.out.println();
						System.out.println("Failed to delete job.");
					}
				}
				break;
			case 7:
				System.out.print("Enter job ID: ");
				int jobID = in.nextInt();
				in.nextLine();
				if (JobsManager.Search(jobID).isEmpty() == true)
				{
					System.out.println();
					System.out.println("No jobs found.");
				}
				else
				{
					JobsManager.Print(JobsManager.Search(jobID));
					JobsManager.get(jobID).UpdateStatus(true);
					System.out.println();
					System.out.println("Job status set to filled.");
				}
				break;
			case 8:
				System.out.print("Enter interviewer name: ");
				String iName = in.nextLine();
				if (InterviewersManager.Search(iName).isEmpty() == true)
				{
					System.out.println("No interviewers found.");
				}
				else
				{
					InterviewersManager.Print(InterviewersManager.Search(iName));
					System.out.print("Enter Interviewer ID to delete: ");
					int iID = in.nextInt();
					in.nextLine();
					if (InterviewersManager.DeleteInterviewer(iID) == true)
					{
						System.out.println();
						System.out.println("Interviewer deleted successfully.");
					}
					else
					{
						System.out.println();
						System.out.println("Failed to delete Interviewer.");
					}
				}
				break;
			case 9:
				System.out.print("Enter an applicant first or last name: ");
				String appName = in.next();
				if (ApplicantManager.Search(appName).isEmpty() == true)
				{
					System.out.println();
					System.out.println("No applicants found.");
				}
				else
				{
					ApplicantManager.Print(ApplicantManager.Search(appName));
					System.out.println();
					System.out.print("Enter applicant ID: ");
					int appID = in.nextInt();
					in.nextLine();
					System.out.print("Enter Interviewer Name: ");
					String intName = in.nextLine();
					if (InterviewersManager.Search(intName).isEmpty() == true)
					{
						System.out.println();
						System.out.println("No interviewers found.");
					}
					else
					{
						System.out.print("Enter appointment location: ");
						String location = in.nextLine();
						System.out.print("Enter appointment month (for ex. 1 for January): ");
						int month = in.nextInt() - 1;
						in.nextLine();
						System.out.print("Enter appointment day (from 1 to 31): ");
						int day = in.nextInt();
						in.nextLine();
						System.out.print("Enter appointment hour (from 0 to 23): ");
						int hour = in.nextInt();
						in.nextLine();
						System.out.print("Enter appointment minute (from 0 to 59): ");
						int minute = in.nextInt();
						in.nextLine();
						if (AppointmentsManager.AddAppointment(new Appointment(ApplicantManager.getApplicant(appID), new Interviewer((InterviewersManager.get(InterviewersManager.Search(intName).get(0).GetId())).GetName()), location, month, day, hour, minute)) == true)
						{
							System.out.println();
							System.out.println("Appointment created succesfully.");
						}
						else
						{
							System.out.println();
							System.out.println("Failed to create appointment.");
						}
					}
				}
				break;
			case 10:
				AppointmentsManager.Print(AppointmentsManager.get());
				break;
			case 11:
				AppointmentsManager.Print(AppointmentsManager.get());
				System.out.println();
				System.out.print("Enter appointment ID: ");
				int apptID = in.nextInt();
				in.nextLine();
				if (AppointmentsManager.DeleteAppointment(apptID) == true)
				{
					System.out.println();
					System.out.println("Appointment deleted succesfully.");
				}
				else
				{
					System.out.println();
					System.out.println("Failed to delete appointment.");
				}
				break;
			case 12:
				ApplicantManager.Print(ApplicantManager.GetApplicants());
				break;
			case 13:
				JobsManager.Print(JobsManager.GetJob());
				break;
			case 14:
				System.out.print("Enter interviewer first and last name: ");
				String interviewer = in.nextLine();
				if (InterviewersManager.Search(interviewer).isEmpty() == false)
				{
					System.out.println();
					System.out.print("An interviewer with this name already exists. Add anyway? (Y/N): ");
					String prompt = in.next();
					if (prompt.charAt(0) == 'Y' || prompt.charAt(0) == 'y')
					{
						InterviewersManager.AddInterviewer(new Interviewer(interviewer));
					}
				}
				else
				{
					if (InterviewersManager.AddInterviewer(new Interviewer(interviewer)) == true)
					{
						System.out.println();
						System.out.println("Successfully added interviewer.");
					}
					else
					{
						System.out.println();
						System.out.println("Failed to add interviewer.");
					}
				}
				break;
			case 15:
				InterviewersManager.Print(InterviewersManager.GetInterviewers());
		}
	}
}
