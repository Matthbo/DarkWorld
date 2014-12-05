package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.handler.ConfigHandler;
import matthbo.mods.darkworld.init.ModBlocks;

public class BlockDarkDiamondOre extends BlockDarkWorld {
	
	public BlockDarkDiamondOre(){
		super();
		this.setBlockName("darkdiamondore");
		this.setHardness(3.0F).setResistance(5.0F);
		this.setStepSound(soundTypePiston);
	}

}
