package matthbo.mods.darkworld.item;

import matthbo.mods.darkworld.reference.MetaNames;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockDarkLog2 extends ItemBlock{

	public ItemBlockDarkLog2(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}
	
	@Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
		int i = itemStack.getItemDamage();
		if(i < 0 || i >= MetaNames.blockDarkLog2.length) i = 0;
		
        return String.format("tile.%s.%s", super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1), MetaNames.blockDarkLog2[i]);
    }
	
	public int getMetadata(int meta){
		return meta;
	}

}
