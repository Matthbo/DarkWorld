package matthbo.mods.darkworld.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockDarkGravel extends BlockFallingDarkWorld {
	
	public BlockDarkGravel(){
		super(Material.sand);
		this.setUnlocalizedName("darkgravel");
		this.setHardness(0.6F);
		this.setStepSound(soundTypeGravel);
	}

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (fortune > 3)
        {
            fortune = 3;
        }

        return rand.nextInt(10 - fortune * 3) == 0 ? Items.flint : Item.getItemFromBlock(this);
    }

}
