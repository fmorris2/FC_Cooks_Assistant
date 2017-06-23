package scripts.fc.missions.fccooksassistant.data.bools;

import org.tribot.api2007.Inventory;

import scripts.fc.framework.quest.QuestBool;

public class BucketBool extends QuestBool
{
	public BucketBool(boolean normal)
	{
		super(normal);
	}

	@Override
	public boolean value()
	{
		return Inventory.getCount("Bucket") > 0;
	}

}
