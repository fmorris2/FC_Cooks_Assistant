package scripts.fc.missions.fccooksassistant.prereq_missions.pot_of_flour.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Login;
import org.tribot.api2007.Login.STATE;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.objects.ClickObject;
import scripts.fc.api.interaction.impl.objects.ItemOnObject;
import scripts.fc.api.travel.Travel;
import scripts.fc.api.wrappers.FCTiming;
import scripts.fc.framework.task.AnticipativeTask;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fccooksassistant.data.QuestSettings;
import scripts.fc.missions.fccooksassistant.tasks.CATask;

public class MillWheat extends AnticipativeTask
{
	private static final long serialVersionUID = -398207071500798021L;
	
	private final Positionable GROUND_FLOOR_TILE = new RSTile(3165, 3306, 0);
	private final int DISTANCE_THRESHOLD = 3;
	private final int TRY_THRESHOLD = 5;
	private final int LOADING_ANIM = 3572;
	
	private int tries = 0;
	
	@Override
	public boolean execute()
	{
		int plane = Player.getPosition().getPlane();
		
		if(plane == 0)
			handleGroundFloor(plane);
		else if(plane == 1)
			climbUp(plane);
		else if(plane == 2)
			return mill();
		
		return false;
	}

	@Override
	public boolean shouldExecute()
	{
		return QuestSettings.MILL_WHEAT.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Mill wheat";
	}
	
	private void climbUp(int plane)
	{
		if(new ClickObject("Climb-up", "Ladder", 10).execute())
			Timing.waitCondition(FCConditions.planeChanged(plane), 4000);
	}
	
	private void handleGroundFloor(int plane)
	{
		if(Player.getPosition().distanceTo(GROUND_FLOOR_TILE) > DISTANCE_THRESHOLD)
			Travel.webWalkTo(GROUND_FLOOR_TILE);
		else
			climbUp(plane);
	}
	
	private boolean mill()
	{
		final int INV_SPACE = Inventory.getAll().length;
		
		if(tries >= TRY_THRESHOLD || (Inventory.getCount("Grain") == 0 || (new ItemOnObject("Use", "Hopper", "Grain", 10).execute() 
				&& Timing.waitCondition(FCConditions.animationChanged(-1), 4000)
				&& Timing.waitCondition(FCConditions.inventoryChanged(INV_SPACE), 5000)
				&& Timing.waitCondition(FCConditions.animationChanged(LOADING_ANIM), 4000))))
		{
			while(Login.getLoginState() == STATE.INGAME && !QuestSettings.COLLECT_FLOUR.isValid())
			{
				if(new ClickObject("Operate", "Hopper controls", 10).execute() && FCTiming.waitCondition(() -> Player.getAnimation() != -1, 5000))
					return true;
				
				General.sleep(1000);
			}
		}
		else
			tries++;
		
		return false;
	}

	@Override
	public Task getNext()
	{
		return CATask.COLLECT_FLOUR.TASK;
	}

	@Override
	public void waitForTaskComplete()
	{
		FCTiming.waitCondition(() -> Player.getAnimation() == -1, 3500);
	}
}
