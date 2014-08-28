package matthbo.mods.darkworld.block;

import net.minecraft.block.material.Material;

public class BlockDarkDirt extends BlockDarkWorld {
	
	public BlockDarkDirt(){
		super(Material.ground);
		this.setBlockName("darkdirt");
		this.setStepSound(soundTypeGravel);
		this.setHardness(0.5F);
	}

}
