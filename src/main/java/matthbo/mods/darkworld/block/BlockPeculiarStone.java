package matthbo.mods.darkworld.block;

import java.util.Random;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.item.Item;

public class BlockPeculiarStone extends BlockDarkWorld{
	
	public BlockPeculiarStone(){
		super();
		this.setBlockName("peculiarstone");
		this.setStepSound(soundTypeStone);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
	}
	
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_){
		return Item.getItemFromBlock(ModBlocks.peculiarCobbleStone);
	}

}