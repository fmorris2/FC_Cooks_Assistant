package scripts.fc.missions.fccooksassistant.tasks.impl;

import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.grounditems.PickUpGroundItem;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fccooksassistant.data.QuestSettings;

public class GetEgg extends Task
{
	private final Positionable PEN_CENTER = new RSTile(3177, 3296, 0);
	private final RSArea PEN_AREA = new RSArea(PEN_CENTER, 10);
	
	@Override
	public boolean execute()
	{
		if(!PEN_AREA.contains(Player.getPosition()))
		{
			if(Travel.webWalkTo(PEN_CENTER))
				Timing.waitCondition(FCConditions.inAreaCondition(PEN_AREA), 5000);
		}
		else
		{
			if(new PickUpGroundItem("Egg").execute())
				Timing.waitCondition(FCConditions.inventoryContains("Egg"), 10000);
		}
		
		return true;
	}

	@Override
	public boolean shouldExecute()
	{
		return QuestSettings.GET_EGG.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Get egg";
	}

}
