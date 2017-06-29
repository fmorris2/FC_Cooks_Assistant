package scripts.fc.missions.fccooksassistant.tasks;

import scripts.fc.framework.task.Task;
import scripts.fc.missions.fccooksassistant.prereq_missions.bucket_of_milk.tasks.GetBucket;
import scripts.fc.missions.fccooksassistant.prereq_missions.bucket_of_milk.tasks.MilkCow;
import scripts.fc.missions.fccooksassistant.prereq_missions.pot_of_flour.tasks.CollectFlour;
import scripts.fc.missions.fccooksassistant.prereq_missions.pot_of_flour.tasks.GetPot;
import scripts.fc.missions.fccooksassistant.prereq_missions.pot_of_flour.tasks.MillWheat;
import scripts.fc.missions.fccooksassistant.prereq_missions.pot_of_flour.tasks.PickWheat;
import scripts.fc.missions.fccooksassistant.tasks.impl.CookDialogue;

public enum CATask
{
	COOK_DIALOGUE(new CookDialogue()),
	
	//BUCKET OF MILK
	GET_BUCKET(new GetBucket()),
	MILK_COW(new MilkCow()),
	
	//POT OF FLOUR
	GET_POT(new GetPot()),
	PICK_WHEAT(new PickWheat()),
	MILL_WHEAT(new MillWheat()),
	COLLECT_FLOUR(new CollectFlour());
	
	
	public final Task TASK;
	
	CATask(Task t)
	{
		TASK = t;
	}
}
