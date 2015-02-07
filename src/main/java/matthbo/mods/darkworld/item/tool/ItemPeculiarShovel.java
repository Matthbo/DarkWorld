package matthbo.mods.darkworld.item.tool;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;


public class ItemPeculiarShovel extends ItemToolDarkWorld {
	
	public ItemPeculiarShovel(){
		super(1.0F, ToolMaterial.STONE, ItemToolDarkWorld.shovel);
		this.setUnlocalizedName("peculiarshovel");
	}

	public boolean canHarvestBlock(Block blockIn)
	{
		return blockIn == Blocks.snow_layer ? true : blockIn == Blocks.snow;
	}

}
