package scripts.fc.missions.fccooksassistant.tasks.impl;

import org.tribot.api.Timing;
import org.tribot.api2007.Player;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.grounditems.PickUpGroundItem;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fccooksassistant.FCCooksAssistant;
import scripts.fc.missions.fccooksassistant.data.QuestSettings;

public class GetPot extends Task
{
	private static final long serialVersionUID = 749928296636648342L;

	@Override
	public boolean execute()
	{
		if(!GetBucket.KITCHEN_AREA.contains(Player.getPosition()))
			Travel.webWalkTo(FCCooksAssistant.KITCHEN_TILE);
		else
			if(new PickUpGroundItem("Pot").execute())
				Timing.waitCondition(FCConditions.inventoryContains("Pot"), 7500);
		
		return true;
	}

	@Override
	public boolean shouldExecute()
	{
		return QuestSettings.GET_POT.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Get pot";
	}

}
