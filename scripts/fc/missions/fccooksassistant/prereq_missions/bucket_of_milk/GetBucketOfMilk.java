package scripts.fc.missions.fccooksassistant.prereq_missions.bucket_of_milk;

import java.util.Arrays;
import java.util.LinkedList;

import org.tribot.api2007.Inventory;

import scripts.fc.framework.mission.MissionManager;
import scripts.fc.framework.script.FCMissionScript;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fccooksassistant.tasks.CATask;

public class GetBucketOfMilk extends MissionManager
{
	public GetBucketOfMilk(FCMissionScript script)
	{
		super(script);
	}
	
	@Override
	public boolean hasReachedEndingCondition()
	{
		return Inventory.getCount("Bucket of milk") > 0;
	}

	@Override
	public String getMissionName()
	{
		return "Get Bucket of Milk";
	}

	@Override
	public String getCurrentTaskName()
	{
		return currentTask == null ? "null" : currentTask.getStatus();
	}

	@Override
	public String getEndingMessage()
	{
		return "Get Bucket of Milk has ended";
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
	{}

	@Override
	public LinkedList<Task> getTaskList()
	{
		return new LinkedList<>(Arrays.asList(CATask.GET_BUCKET.TASK, CATask.MILK_COW.TASK));
	}

}
