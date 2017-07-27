package scripts.fc.missions.fccooksassistant.data;

import scripts.fc.framework.requirement.item.ItemRequirement;
import scripts.fc.framework.requirement.item.ReqItem;
import scripts.fc.framework.requirement.item.SingleReqItem;
import scripts.fc.framework.script.FCMissionScript;
import scripts.fc.missions.fccooksassistant.prereq_missions.bucket_of_milk.GetBucketOfMilk;
import scripts.fc.missions.fccooksassistant.prereq_missions.egg.GetEgg;
import scripts.fc.missions.fccooksassistant.prereq_missions.pot_of_flour.GetPotOfFlour;

public class CooksQuestRequirement extends ItemRequirement
{
	public static final int POT_OF_FLOUR = 1933, EGG = 1944, BUCKET_OF_MILK = 1927;
	
	public CooksQuestRequirement(FCMissionScript script)
	{
		super(script);
	}

	@Override
	public ReqItem[] getReqItems()
	{
		return new ReqItem[]
		{
			new SingleReqItem(BUCKET_OF_MILK, 1, false, true, new GetBucketOfMilk(script)),
			new SingleReqItem(EGG, 1, false, true, new GetEgg()),
			new SingleReqItem(POT_OF_FLOUR, 1, false, true, new GetPotOfFlour(script))
		};
	}
}
