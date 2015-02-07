package matthbo.mods.darkworld.block;

import java.util.Random;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockPeculiarStone extends BlockDarkWorld{
	
	public BlockPeculiarStone(){
		super();
		this.setUnlocalizedName("peculiarstone");
		this.setStepSound(soundTypeStone);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
	}
	
	public Item getItemDropped(IBlockState blockState, Random rand, int fortune){
		return ModBlocks.peculiarCobbleStone.getItemDropped(ModBlocks.peculiarCobbleStone.getDefaultState(), rand, fortune);
	}

}
