package scripts.fc.missions.fccooksassistant;

import java.util.Arrays;
import java.util.LinkedList;

import org.tribot.api.General;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Login;
import org.tribot.api2007.Login.STATE;
import org.tribot.api2007.types.RSTile;

import scripts.fc.framework.quest.QuestScriptManager;
import scripts.fc.framework.requirement.Requirement;
import scripts.fc.framework.script.FCMissionScript;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fccooksassistant.data.CooksQuestRequirement;
import scripts.fc.missions.fccooksassistant.data.QuestSettings;
import scripts.fc.missions.fccooksassistant.tasks.impl.CookDialogue;

public class FCCooksAssistant extends QuestScriptManager
{
	public static final Positionable KITCHEN_TILE = new RSTile(3207, 3214, 0);
	
	public FCCooksAssistant(FCMissionScript script)
	{
		super(script);
	}
	
	@Override
	public boolean hasReachedEndingCondition()
	{
		return QuestSettings.QUEST_COMPLETE.isValid();
	}

	@Override
	public String getMissionName()
	{
		return "Cook's Assistant";
	}

	@Override
	public String getCurrentTaskName()
	{
		return currentTask == null ? "null" : currentTask.getStatus();
	}

	@Override
	public String getEndingMessage()
	{
		return "Cook's Assistant has been completed.";
	}

	@Override
	public void execute()
	{
		if(Login.getLoginState() != STATE.INGAME)
		{
			General.println("Waiting for login...");
			return;
		}
		
		executeTasks();
	}

	@Override
	public LinkedList<Task> getTaskList()
	{
		return new LinkedList<Task>(Arrays.asList(new CookDialogue()));
	}
	
	public String toString()
	{
		return getMissionName();
	}

	@Override
	public String[] getMissionSpecificPaint()
	{
		return new String[0];
	}

	@Override
	public void resetStatistics()
	{}

	@Override
	public Requirement[] getRequirements()
	{
		return new Requirement[]{new CooksQuestRequirement(missionScript)};
	}

	@Override
	public boolean canStart()
	{
		return true;
	}

	@Override
	public int getQuestPointReward() {
		return 1;
	}
}
