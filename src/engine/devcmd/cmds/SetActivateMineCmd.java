// • ▌ ▄ ·.  ▄▄▄·  ▄▄ • ▪   ▄▄· ▄▄▄▄·  ▄▄▄·  ▐▄▄▄  ▄▄▄ .
// ·██ ▐███▪▐█ ▀█ ▐█ ▀ ▪██ ▐█ ▌▪▐█ ▀█▪▐█ ▀█ •█▌ ▐█▐▌·
// ▐█ ▌▐▌▐█·▄█▀▀█ ▄█ ▀█▄▐█·██ ▄▄▐█▀▀█▄▄█▀▀█ ▐█▐ ▐▌▐▀▀▀
// ██ ██▌▐█▌▐█ ▪▐▌▐█▄▪▐█▐█▌▐███▌██▄▪▐█▐█ ▪▐▌██▐ █▌▐█▄▄▌
// ▀▀  █▪▀▀▀ ▀  ▀ ·▀▀▀▀ ▀▀▀·▀▀▀ ·▀▀▀▀  ▀  ▀ ▀▀  █▪ ▀▀▀
//      Magicbane Emulator Project © 2013 - 2022
//                www.magicbane.com


package engine.devcmd.cmds;


import engine.devcmd.AbstractDevCmd;
import engine.gameManager.BuildingManager;
import engine.objects.AbstractGameObject;
import engine.objects.Building;
import engine.objects.Mine;
import engine.objects.PlayerCharacter;

/**
 *
 */
public class SetActivateMineCmd extends AbstractDevCmd {

	public SetActivateMineCmd() {
        super("mineactive");
    }

	@Override
	protected void _doCmd(PlayerCharacter pcSender, String[] args,
			AbstractGameObject target) {
		Building mineBuilding = BuildingManager.getBuilding(target.getObjectUUID());
		if (mineBuilding == null)
			return;

		Mine mine = Mine.getMineFromTower(mineBuilding.getObjectUUID());
		if (mine == null)
			return;

		String trigger = args[0];
		switch (trigger){
		case "true":
			mine.handleStartMineWindow();
			Mine.setLastChange(System.currentTimeMillis());
			break;
		case "false":
			mine.handleEndMineWindow();
			Mine.setLastChange(System.currentTimeMillis());
			break;
		default:
			this.sendUsage(pcSender);
			break;

		}
	}

	@Override
	protected String _getUsageString() {
        return "' /mineactive true|false";
	}

	@Override
	protected String _getHelpString() {
        return "Temporarily add visual effects to Character";
	}

}
