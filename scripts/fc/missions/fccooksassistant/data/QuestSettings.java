package scripts.fc.missions.fccooksassistant.data;

import java.util.Arrays;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.fc.framework.quest.AreaBool;
import scripts.fc.framework.quest.Order;
import scripts.fc.framework.quest.QuestState;
import scripts.fc.framework.quest.SettingBool;
import scripts.fc.missions.fccooksassistant.data.bools.BucketBool;
import scripts.fc.missions.fccooksassistant.data.bools.EggBool;
import scripts.fc.missions.fccooksassistant.data.bools.FlourBool;
import scripts.fc.missions.fccooksassistant.data.bools.MilkBool;
import scripts.fc.missions.fccooksassistant.data.bools.PotBool;
import scripts.fc.missions.fccooksassistant.data.bools.WheatBool;

public enum QuestSettings
{
	GET_POT(new QuestState(new PotBool(false), new FlourBool(false))),
	
	START_QUEST(new QuestState(new SettingBool(29, 0, true, Order.EQUALS), new PotBool(true), new BucketBool(true))),
	
	PICK_WHEAT(new QuestState(new PotBool(true), new WheatBool(false), new FlourBool(false),
				new AreaBool(false, new RSArea(new RSTile(3160, 3312, 0), new RSTile(3175, 3300, 0))),
				new AreaBool(false, new RSArea(new RSTile(3160, 3312, 1), new RSTile(3175, 3300, 1))),
				new AreaBool(false, new RSArea(new RSTile(3160, 3312, 2), new RSTile(3175, 3300, 2))))),
					
	MILL_WHEAT(new QuestState(new PotBool(true), new WheatBool(true))),
					
	COLLECT_FLOUR(new QuestState(new PotBool(true), new FlourBool(false))),
					
	TURN_IN_QUEST(new QuestState(new SettingBool(29, 1, true, Order.EQUALS), new SettingBool(695, 0, true, Order.EQUALS), new MilkBool(true), new EggBool(true), 
					new FlourBool(true))),
					
	QUEST_COMPLETE(new QuestState(new SettingBool(29, 2, true, Order.EQUALS)));
	
	private QuestState[] states;
	
	QuestSettings(QuestState... states)
	{
		this.states = states;
	}
	
	public boolean isValid()
	{
		return Arrays.stream(states).anyMatch(s -> s.validate());
	}
}
