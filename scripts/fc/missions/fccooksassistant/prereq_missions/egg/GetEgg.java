package scripts.fc.missions.fccooksassistant.prereq_missions.egg;

import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.GroundItems;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.abc.ABC2Reaction;
import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.grounditems.PickUpGroundItem;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;

public class GetEgg extends Task
{
	private static final long serialVersionUID = -2982150960407960159L;
	
	private final Positionable PEN_CENTER = new RSTile(3177, 3296, 0);
	private final RSArea PEN_AREA = new RSArea(PEN_CENTER, 7);
	
	private ABC2Reaction reaction = new ABC2Reaction(false, 30000);
	
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
			if(GroundItems.find("Egg").length == 0) //WAITING FOR EGG SPAWN
				reaction.start();
			else
			{
				reaction.react();
				if(new PickUpGroundItem("Egg").execute())
					return Timing.waitCondition(FCConditions.inventoryContains("Egg"), 10000);
			}
				
		}
		
		return true;
	}

	@Override
	public boolean shouldExecute()
	{
		return Inventory.getCount("Egg") == 0;
	}

	@Override
	public String getStatus()
	{
		return "Get egg";
	}

}
