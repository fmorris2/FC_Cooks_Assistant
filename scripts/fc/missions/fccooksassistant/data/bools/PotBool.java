package scripts.fc.missions.fccooksassistant.data.bools;

import org.tribot.api2007.Inventory;

import scripts.fc.framework.quest.QuestBool;

public class PotBool extends QuestBool
{
	public PotBool(boolean normal)
	{
		super(normal);
	}

	@Override
	public boolean value()
	{
		return Inventory.getCount("Pot") > 0;
	}

}
