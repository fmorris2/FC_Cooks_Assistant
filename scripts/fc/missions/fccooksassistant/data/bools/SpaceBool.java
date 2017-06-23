package scripts.fc.missions.fccooksassistant.data.bools;

import org.tribot.api2007.Inventory;

import scripts.fc.framework.quest.QuestBool;

public class SpaceBool extends QuestBool
{
	private final int MIN_INVENTORY_SPACE = 4;
	private final int MAX_INVENTORY_SPACE = 28;
	
	public SpaceBool(boolean normal)
	{
		super(normal);
	}

	@Override
	public boolean value()
	{
		return MAX_INVENTORY_SPACE - Inventory.getAll().length >= MIN_INVENTORY_SPACE;
	}

}
