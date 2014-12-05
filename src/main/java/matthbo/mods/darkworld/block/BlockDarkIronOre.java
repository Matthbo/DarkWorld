package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.handler.ConfigHandler;

public class BlockDarkIronOre extends BlockDarkWorld {
	
	public BlockDarkIronOre(){
		super();
		this.setBlockName("darkironore");
		this.setHardness(3.0F).setResistance(5.0F);
		this.setStepSound(soundTypePiston);
	}

}
