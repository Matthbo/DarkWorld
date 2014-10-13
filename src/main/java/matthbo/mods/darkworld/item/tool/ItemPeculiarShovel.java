package matthbo.mods.darkworld.item.tool;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;


public class ItemPeculiarShovel extends ItemToolDarkWorld {
	
	public ItemPeculiarShovel(){
		super(1.0F, ToolMaterial.STONE, ItemToolDarkWorld.shovel);
		this.setUnlocalizedName("peculiarshovel");
	}
	
	public boolean func_150897_b(Block p_150897_1_)
    {
        return p_150897_1_ == Blocks.snow_layer ? true : p_150897_1_ == Blocks.snow;
    }

}
