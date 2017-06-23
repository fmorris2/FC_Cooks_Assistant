package scripts.fc.missions.fccooksassistant.data;

import org.tribot.api2007.Game;

import scripts.fc.framework.quest.Order;
import scripts.fc.framework.quest.PlaneBool;
import scripts.fc.framework.quest.QuestState;
import scripts.fc.framework.quest.SettingBool;
import scripts.fc.missions.fccooksassistant.data.bools.BucketBool;
import scripts.fc.missions.fccooksassistant.data.bools.EggBool;
import scripts.fc.missions.fccooksassistant.data.bools.FlourBool;
import scripts.fc.missions.fccooksassistant.data.bools.MilkBool;
import scripts.fc.missions.fccooksassistant.data.bools.PotBool;
import scripts.fc.missions.fccooksassistant.data.bools.SpaceBool;
import scripts.fc.missions.fccooksassistant.data.bools.WheatBool;

public enum QuestSettings
{
	GET_POT(new QuestState(new SettingBool(29, 0, true, Order.AFTER_EQUALS), new PotBool(false), new FlourBool(false))),
	
	GET_BUCKET(new QuestState(new SettingBool(29, 0, true, Order.AFTER_EQUALS), new BucketBool(false), new PotBool(true), new MilkBool(false))),
	
	START_QUEST(new QuestState(new SettingBool(29, 0, true, Order.EQUALS), new PotBool(true), new BucketBool(true))),
	
	MILK_COW(new QuestState(new SettingBool(29, 1, true, Order.EQUALS), new PotBool(true), new BucketBool(true), new MilkBool(false))),
	
	GET_EGG(new QuestState(new SettingBool(29, 1, true, Order.EQUALS), new PotBool(true), new MilkBool(true), new EggBool(false))),
	
	PICK_WHEAT(new QuestState(new SettingBool(29, 1, true, Order.EQUALS), new SettingBool(695, 0, true, Order.EQUALS), new PotBool(true), new MilkBool(true), 
					new EggBool(true), new WheatBool(false), new FlourBool(false), new PlaneBool(true, 0, Order.EQUALS))),
					
	MILL_WHEAT(new QuestState(new SettingBool(29, 1, true, Order.EQUALS), new SettingBool(695, 0, true, Order.EQUALS), new PotBool(true), new MilkBool(true), 
					new EggBool(true), new WheatBool(true))),
					
	COLLECT_FLOUR(new QuestState(new SettingBool(29, 1, true, Order.EQUALS), new SettingBool(695, 1, true, Order.EQUALS), new PotBool(true), new MilkBool(true),
					new EggBool(true), new FlourBool(false))),
					
	TURN_IN_QUEST(new QuestState(new SettingBool(29, 1, true, Order.EQUALS), new SettingBool(695, 0, true, Order.EQUALS), new MilkBool(true), new EggBool(true), 
					new FlourBool(true))),
					
	QUEST_COMPLETE(new QuestState(new SettingBool(29, 2, true, Order.EQUALS)));
	
	private QuestState[] states;
	public final static SpaceBool SPACE_BOOL = new SpaceBool(true);
	
	QuestSettings(QuestState... states)
	{
		this.states = states;
	}
	
	public boolean isValid()
	{
		if(Game.getSetting(29) != 2 && !SPACE_BOOL.validate())
			return false;
		
		for(QuestState state : states)
			if(state.validate())
				return true;
		
		return false;
	}
}
