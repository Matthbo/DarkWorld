package matthbo.mods.darkworld.block;

import java.util.Random;

import matthbo.mods.darkworld.handler.ConfigHandler;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockDarkCoalOre extends BlockDarkWorld {
	
	public BlockDarkCoalOre(){
		super();
		this.setUnlocalizedName("darkcoalore");
		this.setHardness(3.0F).setResistance(5.0F);
		this.setStepSound(soundTypePiston);
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		return Items.coal;
    }

}
