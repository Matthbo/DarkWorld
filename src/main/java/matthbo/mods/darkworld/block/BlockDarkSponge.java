package matthbo.mods.darkworld.block;

import net.minecraft.block.material.Material;

public class BlockDarkSponge extends BlockDarkWorld {
	
	public BlockDarkSponge(){
		super(Material.sponge);
		this.setBlockName("darksponge");
		this.setHardness(0.6F);
		this.setStepSound(soundTypeGrass);
	}

}
