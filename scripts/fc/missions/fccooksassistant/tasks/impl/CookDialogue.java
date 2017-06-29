package scripts.fc.missions.fccooksassistant.tasks.impl;

import org.tribot.api.Timing;
import org.tribot.api2007.Player;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.npcs.dialogue.NpcDialogue;
import scripts.fc.api.interaction.impl.objects.ClickObject;
import scripts.fc.api.items.FCItem;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.ItemsRequiredTask;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fccooksassistant.FCCooksAssistant;
import scripts.fc.missions.fccooksassistant.data.CooksQuestRequirement;
import scripts.fc.missions.fccooksassistant.data.QuestSettings;
import scripts.fc.missions.fccooksassistant.prereq_missions.bucket_of_milk.tasks.GetBucket;

public class CookDialogue extends Task implements ItemsRequiredTask
{
	private static final long serialVersionUID = -5937909194607020305L;
	
	private QuestSettings setting;
	
	@Override
	public boolean execute()
	{
		if(!GetBucket.KITCHEN_AREA.contains(Player.getPosition()))
		{
			if(GetBucket.CELLAR_AREA.contains(Player.getPosition()))
				leaveCellar();
			else
				Travel.webWalkTo(FCCooksAssistant.KITCHEN_TILE);
		}
		else
			new NpcDialogue("Talk-to", "Cook", 15, 0, 0, 3).execute();
		
		return true;
	}

	@Override
	public boolean shouldExecute()
	{
		return true;
	}

	@Override
	public String getStatus()
	{
		return setting == QuestSettings.START_QUEST ? "Start quest" : "Turn in quest";
	}
	
	private void leaveCellar()
	{
		if(new ClickObject("Climb-up", "Ladder", 15).execute())
			Timing.waitCondition(FCConditions.inAreaCondition(GetBucket.KITCHEN_AREA), 7500);
	}

	@Override
	public FCItem[] getRequiredItems()
	{
		return new FCItem[]
		{
			new FCItem(1, false, CooksQuestRequirement.BUCKET_OF_MILK), 
			new FCItem(1, false, CooksQuestRequirement.EGG), 
			new FCItem(1, false, CooksQuestRequirement.POT_OF_FLOUR)
		};
	}
	
}
