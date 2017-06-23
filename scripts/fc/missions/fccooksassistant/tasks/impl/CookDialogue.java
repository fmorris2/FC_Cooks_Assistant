package scripts.fc.missions.fccooksassistant.tasks.impl;

import org.tribot.api.Timing;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.npcs.dialogue.NpcDialogue;
import scripts.fc.api.interaction.impl.objects.ClickObject;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fccooksassistant.FCCooksAssistant;
import scripts.fc.missions.fccooksassistant.data.QuestSettings;

public class CookDialogue extends Task
{
	private static final long serialVersionUID = -5937909194607020305L;
	
	private QuestSettings setting;
	private boolean cookTookItems;
	
	@Override
	public boolean execute()
	{
		final int START = Inventory.getAll().length;
		
		if(!GetBucket.KITCHEN_AREA.contains(Player.getPosition()))
		{
			if(GetBucket.CELLAR_AREA.contains(Player.getPosition()))
				leaveCellar();
			else
				Travel.webWalkTo(FCCooksAssistant.KITCHEN_TILE);
		}
		else
			new NpcDialogue("Talk-to", "Cook", 15, 0, 0, 3).execute();
		
		if(setting == QuestSettings.TURN_IN_QUEST && START > Inventory.getAll().length)
			cookTookItems = true;
		
		return true;
	}

	@Override
	public boolean shouldExecute()
	{
		setting = null;
		
		if(cookTookItems)
			setting = QuestSettings.TURN_IN_QUEST;
		else if(QuestSettings.START_QUEST.isValid())
			setting = QuestSettings.START_QUEST;
		else if(QuestSettings.TURN_IN_QUEST.isValid())
			setting = QuestSettings.TURN_IN_QUEST;
		
		return setting != null;
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
	
}
