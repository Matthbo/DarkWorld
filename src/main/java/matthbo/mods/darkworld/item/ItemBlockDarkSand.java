package matthbo.mods.darkworld.item;

import matthbo.mods.darkworld.reference.MetaNames;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockDarkSand extends ItemBlock {

	public ItemBlockDarkSand(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}
	
	@Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
		int i = itemStack.getItemDamage();
		if(i < 0 || i >= MetaNames.blockDarkSand.length) i = 0;
		
        return String.format("tile.%s.%s", super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1), MetaNames.blockDarkSand[i]);
    }
	
	public int getMetadata(int meta){
		return meta;
	}

}
