package scripts.fc.missions.fccooksassistant.tasks;

import scripts.fc.framework.task.Task;
import scripts.fc.missions.fccooksassistant.tasks.impl.CollectFlour;
import scripts.fc.missions.fccooksassistant.tasks.impl.CookDialogue;
import scripts.fc.missions.fccooksassistant.tasks.impl.GetBucket;
import scripts.fc.missions.fccooksassistant.tasks.impl.GetEgg;
import scripts.fc.missions.fccooksassistant.tasks.impl.GetPot;
import scripts.fc.missions.fccooksassistant.tasks.impl.InventoryCheck;
import scripts.fc.missions.fccooksassistant.tasks.impl.MilkCow;
import scripts.fc.missions.fccooksassistant.tasks.impl.MillWheat;
import scripts.fc.missions.fccooksassistant.tasks.impl.PickWheat;

public enum CATask
{
	COLLECT_FLOUR(new CollectFlour()),
	COOK_DIALOGUE(new CookDialogue()),
	GET_BUCKET(new GetBucket()),
	GET_EGG(new GetEgg()),
	GET_POT(new GetPot()),
	INVENTORY_CHECK(new InventoryCheck()),
	MILK_COW(new MilkCow()),
	MILL_WHEAT(new MillWheat()),
	PICK_WHEAT(new PickWheat());
	
	public final Task TASK;
	CATask(Task t)
	{
		TASK = t;
	}
	
	public static Task[] getTasks()
	{
		Task[] tasks = new Task[values().length];
		for(int i = 0; i < values().length; i++)
			tasks[i] = values()[i].TASK;
		
		return tasks;
	}
}
