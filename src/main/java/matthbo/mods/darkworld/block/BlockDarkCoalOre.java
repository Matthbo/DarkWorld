package matthbo.mods.darkworld.block;

import java.util.Random;

import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockDarkCoalOre extends BlockDarkWorld {
	
	public BlockDarkCoalOre(){
		super();
		this.setBlockName("darkcoalore");
		this.setHardness(3.0F).setResistance(5.0F);
		this.setStepSound(soundTypePiston);
	}
	
	public Item getItemDropped(int par1, Random rand, int par3)
    {
		return Items.coal;
    }

}
