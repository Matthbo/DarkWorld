package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.handler.ConfigHandler;
import matthbo.mods.darkworld.init.ModBlocks;

public class BlockDarkLapisOre extends BlockDarkWorld {
	
	public BlockDarkLapisOre(){
		super();
		this.setBlockName("darklapisore");
		this.setHardness(3.0F).setResistance(5.0F);
		this.setStepSound(soundTypePiston);
	}

}
