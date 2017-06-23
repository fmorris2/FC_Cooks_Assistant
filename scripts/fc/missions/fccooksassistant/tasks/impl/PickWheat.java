package scripts.fc.missions.fccooksassistant.tasks.impl;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.objects.ClickObject;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fccooksassistant.data.QuestSettings;

public class PickWheat extends Task
{
	private static final long serialVersionUID = -2104950617276013467L;
	private final Positionable WHEAT_TILE = new RSTile(3162, 3292, 0);
	private final int DISTANCE_THRESHOLD = 2;
	
	@Override
	public boolean execute()
	{
		if(Player.getPosition().distanceTo(WHEAT_TILE) > DISTANCE_THRESHOLD)
			Travel.webWalkTo(WHEAT_TILE);
		else
			if(new ClickObject("Pick", "Wheat", 10).execute())
				Timing.waitCondition(FCConditions.inventoryContains("Grain"), 5000);
			else
				Camera.setCameraAngle(General.random(0, 100));
		
		return false;
	}

	@Override
	public boolean shouldExecute()
	{
		return QuestSettings.PICK_WHEAT.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Pick wheat";
	}

}
