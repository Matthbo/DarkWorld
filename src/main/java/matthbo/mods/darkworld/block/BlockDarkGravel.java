package matthbo.mods.darkworld.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockDarkGravel extends BlockFallingDarkWorld {
	
	public BlockDarkGravel(){
		super(Material.sand);
		this.setBlockName("darkgravel");
		this.setHardness(0.6F);
		this.setStepSound(soundTypeGravel);
	}
	
	public Item getItemDropped(int par1, Random rand, int par3)
    {
        if (par3 > 3)
        {
            par3 = 3;
        }

        return rand.nextInt(10 - par3 * 3) == 0 ? Items.flint : Item.getItemFromBlock(this);
    }

}
