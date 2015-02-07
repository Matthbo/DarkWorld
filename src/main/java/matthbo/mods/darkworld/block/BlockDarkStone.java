package matthbo.mods.darkworld.block;

import java.util.Random;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockDarkStone extends BlockDarkWorld {
	
	public BlockDarkStone(){
		super();
		this.setUnlocalizedName("darkstone");
		this.setStepSound(soundTypeStone);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
	}
	
	public Item getItemDropped(IBlockState block, Random rand, int fortune){
		return ModBlocks.darkCobbleStone.getItemDropped(ModBlocks.darkCobbleStone.getDefaultState(), rand, fortune);
	}

}
