package matthbo.mods.darkworld.item.tool;

import net.minecraft.item.Item;
import matthbo.mods.darkworld.item.ItemDarkWorld;
import matthbo.mods.darkworld.item.ItemToolDarkWorld;

public class ItemPeculiarPickaxe extends ItemToolDarkWorld {
	
	public ItemPeculiarPickaxe(){
		super(); //cause Item.ToolMaterial.IRON is OP
		this.setUnlocalizedName("peculiarpickaxe");
	}

}
