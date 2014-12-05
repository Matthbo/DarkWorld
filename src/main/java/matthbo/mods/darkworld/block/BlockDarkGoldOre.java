package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.handler.ConfigHandler;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.utility.LogHelper;
import net.minecraft.block.material.Material;

public class BlockDarkGoldOre extends BlockDarkWorld {
	

	
	public BlockDarkGoldOre(){
		super();
		this.setBlockName("darkgoldore");
		this.setHardness(3.0F).setResistance(5.0F);
		this.setStepSound(soundTypePiston);
		
	}

}
