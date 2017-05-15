

/**
 * This class embodies a menu, as well
 * as a submenu. The menu that includes
 * this item is held in the Next linked 
 * list. If this item is selected by the
 * user, then the user goes to the linked
 * list held by SubMenu.
 * 
 * Think of the menu across the top of this
 * window. It says File, Edit, Source, and 
 * so on. That is one linked list. If you
 * click File, then it shows New, Open File,
 * Close, and so on. That is another linked
 * list, which would be held in SubMenu in this 
 * case.
 * 
 * You can view SubMenu as a linked list
 * within a linked list.
 * 
 * Here's how this class might look in 
 * action.
 * 
 * 1. this
 * 2. this.Next
 * 3. this.Next.Next
 * User> 1
 * 
 * 1. SubMenu
 * 2. SubMenu.Next
 * 3. SubMenu.Next.Next
 * ...etc
 * 
 * See the testers for example menus.
 * 
 * HINT: There is very little code in
 * this class, and every method except one
 * can be recursive. Take time to understand 
 * the structure here. There is not a lot 
 * of code to write.
 */
public class Menu
{
    public int OptionNumber;
	public String Prompt;

	// the menu containing this prompt (a linked list)
	public Menu Next;
	public int Action;

	// the submenu that is displayed when this option is selected (also a linked list)
	public Menu SubMenu;

	public Menu(int NewOptionNumber, String NewPrompt, int newAction)
	{
		OptionNumber = NewOptionNumber;
		Prompt = NewPrompt;
		Action = newAction;
	}

	/**
	 * Add an option to the END of this menu.
	 * This menu is held in the Next linked list.
	 * Everything in the Next linked list 
	 * should be displayed when this item
	 * is displayed. The new Menu item should
	 * have OptionNumber = OptionNumber + 1 and
	 * Prompt = NewPrompt.
	 * 
	 * @param NewPrompt the prompt for the item to add to the Next list
	 */
	public void Add(String NewPrompt, int newAction)
	{
		// YOUR CODE HERE
		if (this.OptionNumber == 0)
		{
			Prompt = NewPrompt;
			OptionNumber = 1;
		}
		else if (this.Next == null)
		{
			this.Next = new Menu(2, NewPrompt, newAction);
		}
		else
		{
			Menu current = this;
			Menu last = null;
			int option = this.OptionNumber;
			while (current != null)
			{
				option++;
				last = current;
				current = current.Next;
			}
			last.Next = new Menu(option, NewPrompt, newAction);
		}
	}

	
	/**
	 * This method simply sets a new submenu for
	 * the menu item with the given option number.
	 * @param NewSubMenu the submenu to be displayed if MenuOptionNumber is chosen
	 */
	public void SetSubMenu(int MenuOptionNumber, Menu NewSubMenu)
	{
		// YOUR CODE HERE
		Menu current = this;
		while (current.Next != null && current.OptionNumber != MenuOptionNumber)
		{
			current = current.Next;
		}
		current.SubMenu = NewSubMenu;
	}

	/**
	 * A recursive routine that displays the entire
	 * menu held by this menu (which is the linked
	 * list in Next). This method should show
	 * the number passed in, then a period, then
	 * a space, then Prompt, then a newline. So if OptionNumber is
	 * 2 and Prompt is "Exit" then this method
	 * should print "2. Exit\n". Then it should
	 * call the Display method on the rest of
	 * this menu.
	 */
	public void Display(int OptionNumber)
	{
		//YOUR CODE HERE
			Menu current = this;
			while (current != null)
			{
				System.out.print(current.OptionNumber + ". " + current.Prompt + "\n");
				current = current.Next;
			}
	}

	/**
	 * This method is called when the user makes
	 * a choice. If this option number has been
	 * selected, then return this SubMenu, otherwise
	 * recurse to the Menu in Next.
	 * 
	 * @param SelectedOptionNumber the option selected by the user
	 * @return the SubMenu corresponding to that option number or null of there is no such option
	 */
	public Menu Choose(int SelectedOptionNumber)
	{
		// YOUR CODE HERE
		Menu current = this;
		while (current.OptionNumber < SelectedOptionNumber)
		{
			current = current.Next;
		}
		return current.SubMenu;
	}
	
	public int getAction(int OptionNumber)
	{
		Menu current = this;
		while (current.OptionNumber < OptionNumber)
		{
			current = current.Next;
		}
		return current.Action;
	}
}
