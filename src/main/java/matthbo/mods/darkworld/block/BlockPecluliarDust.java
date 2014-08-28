package matthbo.mods.darkworld.block;

import net.minecraft.block.material.Material;


public class BlockPecluliarDust extends BlockFallingMod {
	
	public BlockPecluliarDust(){
		super(Material.sand);
		this.setBlockName("peculiardustblock");
		this.setStepSound(soundTypeSand);
		this.setHardness(0.5F);
	}

}
