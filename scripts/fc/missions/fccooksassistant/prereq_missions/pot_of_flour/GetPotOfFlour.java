package scripts.fc.missions.fccooksassistant.prereq_missions.pot_of_flour;

import java.util.Arrays;
import java.util.LinkedList;

import org.tribot.api2007.Inventory;

import scripts.fc.framework.mission.MissionManager;
import scripts.fc.framework.script.FCMissionScript;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fccooksassistant.tasks.CATask;

public class GetPotOfFlour extends MissionManager
{
	public GetPotOfFlour(FCMissionScript script)
	{
		super(script);
	}
	
	@Override
	public boolean hasReachedEndingCondition()
	{
		return Inventory.getCount("Pot of flour") > 0;
	}

	@Override
	public String getMissionName()
	{
		return "Get Pot of Flour";
	}

	@Override
	public String getCurrentTaskName()
	{
		return currentTask == null ? "null" : currentTask.getStatus();
	}

	@Override
	public String getEndingMessage()
	{
		return "Get Pot of Flour has ended";
	}

	@Override
	public String[] getMissionSpecificPaint()
	{
		return new String[]{};
	}

	@Override
	public void execute()
	{
		executeTasks();
	}

	@Override
	public void resetStatistics()
	{
	}

	@Override
	public LinkedList<Task> getTaskList()
	{
		return new LinkedList<>(Arrays.asList(CATask.GET_POT.TASK, CATask.PICK_WHEAT.TASK, CATask.MILL_WHEAT.TASK, CATask.COLLECT_FLOUR.TASK));
	}

}
