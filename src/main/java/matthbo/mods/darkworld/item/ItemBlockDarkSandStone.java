package matthbo.mods.darkworld.item;

import matthbo.mods.darkworld.reference.MetaNames;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockDarkSandStone extends ItemBlock {

	public ItemBlockDarkSandStone(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}
	
	@Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
		int i = itemStack.getItemDamage();
		if(i < 0 || i >= MetaNames.blockDarkSandStone_b.length) i = 0;
		
        return String.format("tile.%s.%s", super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1), MetaNames.blockDarkSandStone_b[i]);
    }
	
	public int getMetadata(int meta){
		return meta;
	}

}
