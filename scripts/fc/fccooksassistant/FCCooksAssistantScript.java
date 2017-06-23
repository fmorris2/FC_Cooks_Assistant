package scripts.fc.fccooksassistant;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Ending;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.interfaces.Starting;

import scripts.fc.framework.mission.Mission;
import scripts.fc.framework.paint.FCPaintable;
import scripts.fc.framework.script.FCMissionScript;
import scripts.fc.missions.fccooksassistant.FCCooksAssistant;

@ScriptManifest(
		authors     = { 
		    "Final Calibur",
		}, 
		category    = "Quests", 
		name        = "FC Cook's Assistant", 
		version     = 0.1, 
		description = "Completes Cook's Assistant for you. Start anywhere.", 
		gameMode    = 1)

public class FCCooksAssistantScript extends FCMissionScript implements FCPaintable, Painting, Starting, Ending
{

	@Override
	protected String[] scriptSpecificPaint()
	{
		return new String[]{};
	}
	
	@Override
	protected Queue<Mission> getMissions()
	{
		return new LinkedList<Mission>(Arrays.asList(new FCCooksAssistant(this)));
	}

}
