package scripts.fc.missions.fccooksassistant.data.bools;

import org.tribot.api2007.Inventory;

import scripts.fc.framework.quest.QuestBool;

public class FlourBool extends QuestBool
{
	public FlourBool(boolean normal)
	{
		super(normal);
	}

	@Override
	public boolean value()
	{
		return Inventory.getCount("Pot of flour") > 0;
	}

}
