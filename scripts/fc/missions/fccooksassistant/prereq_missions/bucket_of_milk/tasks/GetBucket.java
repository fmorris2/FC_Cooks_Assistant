package scripts.fc.missions.fccooksassistant.prereq_missions.bucket_of_milk.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.grounditems.PickUpGroundItem;
import scripts.fc.api.interaction.impl.objects.ClickObject;
import scripts.fc.api.travel.Travel;
import scripts.fc.api.viewport.FCCameraUtils;
import scripts.fc.framework.task.SpaceRequiredTask;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fccooksassistant.FCCooksAssistant;

public class GetBucket extends Task implements SpaceRequiredTask
{	
	private static final long serialVersionUID = 8306748680279766036L;
	
	public static final RSArea CELLAR_AREA = new RSArea(new RSTile(3213, 9620, 0), 7);
	public static final RSArea KITCHEN_AREA = new RSArea(new RSTile(3205, 3217, 0), new RSTile(3212, 3212, 0));
	private final Positionable CELLAR_TILE = new RSTile(3214, 9620, 0);
	private final int CELLAR_THRESHOLD = 2;
	
	@Override
	public boolean execute()
	{
		if(!CELLAR_AREA.contains(Player.getPosition()))
			goToCellar();
		else
			pickUpBucket();
		
		return true;
	}

	@Override
	public boolean shouldExecute()
	{
		return Inventory.getCount("Bucket") == 0 && Inventory.getCount("Bucket of milk") == 0;
	}

	@Override
	public String getStatus()
	{
		return "Get bucket";
	}
	
	private void goToCellar()
	{
		if(!KITCHEN_AREA.contains(Player.getPosition()))
		{
			if(Travel.webWalkTo(FCCooksAssistant.KITCHEN_TILE))
				Timing.waitCondition(FCConditions.inAreaCondition(KITCHEN_AREA), 4000);
		}
		else
		{
			if(new ClickObject("Climb-down", "Trapdoor", 15).execute())
				Timing.waitCondition(FCConditions.inAreaCondition(CELLAR_AREA), 7500);
			else
				Camera.setCameraAngle(General.random(80, 100));
		}
	}
	
	private void pickUpBucket()
	{
		if(Player.getPosition().distanceTo(CELLAR_TILE) > CELLAR_THRESHOLD)
		{
			if(!CELLAR_TILE.getPosition().isOnScreen())
				Camera.turnToTile(CELLAR_TILE);
			
			if(Walking.walkScreenPath(Walking.generateStraightScreenPath(CELLAR_TILE)))
				Timing.waitCondition(FCConditions.inAreaCondition(new RSArea(CELLAR_TILE, 2)), 5000);
		}
		else
		{
			if(new PickUpGroundItem("Bucket").execute())
				Timing.waitCondition(FCConditions.inventoryContains("Bucket"), 5000);
			else
				FCCameraUtils.adjustCameraRandomly();
		}
	}

	@Override
	public int getSpaceRequired()
	{
		return 1;
	}

}
