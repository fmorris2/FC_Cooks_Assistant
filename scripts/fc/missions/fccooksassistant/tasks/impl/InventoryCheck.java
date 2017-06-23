package scripts.fc.missions.fccooksassistant.tasks.impl;

import org.tribot.api.Timing;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fccooksassistant.data.QuestSettings;

public class InventoryCheck extends Task
{

	@Override
	public boolean execute()
	{
		if(Banking.isInBank())
		{
			if(Banking.isBankScreenOpen())
			{
				final int INV_SIZE = Inventory.getAll().length;
				if(Banking.depositAll() > 0)
					Timing.waitCondition(FCConditions.inventoryChanged(INV_SIZE), 4000);
			}
			else
				if(Banking.openBank())
					Timing.waitCondition(FCConditions.BANK_LOADED_CONDITION, 5000);
		}
		else
			Travel.walkToBank();
		
		return true;
	}

	@Override
	public boolean shouldExecute()
	{
		return !QuestSettings.SPACE_BOOL.validate();
	}

	@Override
	public String getStatus()
	{
		return "Inventory check";
	}

}
