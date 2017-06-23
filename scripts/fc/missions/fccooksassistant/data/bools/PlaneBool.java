package scripts.fc.missions.fccooksassistant.data.bools;

import org.tribot.api2007.Player;

import scripts.fc.framework.quest.QuestBool;

public class PlaneBool extends QuestBool
{
	private int plane;
	
	public PlaneBool(boolean normal, int plane)
	{
		super(normal);
		this.plane = plane;
	}

	@Override
	public boolean value()
	{
		return Player.getPosition().getPlane() <= plane;
	}

}
