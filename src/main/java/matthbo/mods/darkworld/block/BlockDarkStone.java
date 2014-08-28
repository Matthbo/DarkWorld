package matthbo.mods.darkworld.block;

import net.minecraft.block.material.Material;

public class BlockDarkStone extends BlockDarkWorld {
	
	public BlockDarkStone(){
		super();
		this.setBlockName("darkstone");
		this.setStepSound(soundTypeStone);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
	}

}
