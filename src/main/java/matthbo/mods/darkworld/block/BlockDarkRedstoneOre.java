package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.handler.ConfigHandler;
import matthbo.mods.darkworld.init.ModBlocks;

public class BlockDarkRedstoneOre extends BlockDarkWorld {

	public BlockDarkRedstoneOre(){
		super();
		this.setBlockName("darkredstoneore");
		this.setHardness(3.0F).setResistance(5.0F);
		this.setStepSound(soundTypePiston);
	}
	
}
