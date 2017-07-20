package scripts.fc.missions.fccooksassistant.prereq_missions.pot_of_flour.tasks;

import org.tribot.api.Timing;
import org.tribot.api2007.Player;

import scripts.fc.api.abc.ABC2Reaction;
import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.EntityInteraction;
import scripts.fc.api.interaction.impl.objects.ClickObject;
import scripts.fc.framework.task.PredictableInteraction;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fccooksassistant.data.QuestSettings;

public class CollectFlour extends Task implements PredictableInteraction
{
	private static final long serialVersionUID = -6447021772045955354L;

	private ABC2Reaction reaction = new ABC2Reaction(true, 2000);
	
	@Override
	public boolean execute()
	{
		int plane = Player.getPosition().getPlane();
		
		if(plane == 2 || plane == 1)
			climbDown(plane);
		else if(plane == 0)
		{
			if(new ClickObject("Empty", "Flour bin", 10).execute())
			{
				reaction.start();
				if(Timing.waitCondition(FCConditions.inventoryContains("Pot of flour"), 5000))
					reaction.react();
			}
		}
		return true;		
	}

	@Override
	public boolean shouldExecute()
	{
		return QuestSettings.COLLECT_FLOUR.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Collect flour";
	}
	
	private void climbDown(int plane)
	{
		if(getInteractable().execute())
			Timing.waitCondition(FCConditions.planeChanged(plane), 4000);
	}

	@Override
	public EntityInteraction getInteractable()
	{
		return new ClickObject("Climb-down", "Ladder", 10);
	}

}
