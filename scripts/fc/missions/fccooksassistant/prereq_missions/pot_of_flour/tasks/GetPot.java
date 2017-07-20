package scripts.fc.missions.fccooksassistant.prereq_missions.pot_of_flour.tasks;

import org.tribot.api.Timing;
import org.tribot.api2007.GroundItems;
import org.tribot.api2007.Player;

import scripts.fc.api.abc.ABC2Reaction;
import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.grounditems.PickUpGroundItem;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.SpaceRequiredTask;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fccooksassistant.FCCooksAssistant;
import scripts.fc.missions.fccooksassistant.data.QuestSettings;
import scripts.fc.missions.fccooksassistant.prereq_missions.bucket_of_milk.tasks.GetBucket;

public class GetPot extends Task implements SpaceRequiredTask
{
	private static final long serialVersionUID = 749928296636648342L;
	
	private ABC2Reaction reaction = new ABC2Reaction(false, 20000);

	@Override
	public boolean execute()
	{
		if(!GetBucket.KITCHEN_AREA.contains(Player.getPosition()))
			Travel.webWalkTo(FCCooksAssistant.KITCHEN_TILE);
		else
		{
			if(GroundItems.find("Pot").length ==0)
				reaction.start();
			else
			{
				reaction.react();
				if(new PickUpGroundItem("Pot").execute())
					return Timing.waitCondition(FCConditions.inventoryContains("Pot"), 7500);
			}
		}
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

	@Override
	public int getSpaceRequired()
	{
		return 1;
	}

}
