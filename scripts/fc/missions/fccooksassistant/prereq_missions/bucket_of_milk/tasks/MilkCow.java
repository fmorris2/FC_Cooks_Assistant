package scripts.fc.missions.fccooksassistant.prereq_missions.bucket_of_milk.tasks;

import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.npcs.ClickNpc;
import scripts.fc.api.travel.Travel;
import scripts.fc.api.viewport.FCCameraUtils;
import scripts.fc.framework.task.Task;

public class MilkCow extends Task
{
	private static final long serialVersionUID = -3047782114384963082L;
	
	private final Positionable COW_TILE = new RSTile(3257, 3274, 0);
	private final RSArea COW_AREA = new RSArea(COW_TILE, 8);
	private final int DAIRY_COW_ID = 2691; //Won't find by name, Dairy cow seems to be special for some reason
	private final int MILKING_ANIMATION = 2305;
	
	@Override
	public boolean execute()
	{
		if(!COW_AREA.contains(Player.getPosition()))
		{
			if(Travel.webWalkTo(COW_TILE, FCConditions.inAreaCondition(COW_AREA)))
				Timing.waitCondition(FCConditions.inAreaCondition(COW_AREA), 5000);
		}
		else
		{
			if(new ClickNpc("Milk", DAIRY_COW_ID, 15).execute())
			{
				if(Timing.waitCondition(FCConditions.inventoryContains("Bucket of milk"), 7500))
					Timing.waitCondition(FCConditions.animationChanged(MILKING_ANIMATION), 5000);
			}
			else
				FCCameraUtils.adjustCameraRandomly();
		}
		
		return false;
	}

	@Override
	public boolean shouldExecute()
	{
		return Inventory.getCount("Bucket") > 0 && Inventory.getCount("Bucket of milk") == 0;
	}

	@Override
	public String getStatus()
	{
		return "Milk cow";
	}

}
