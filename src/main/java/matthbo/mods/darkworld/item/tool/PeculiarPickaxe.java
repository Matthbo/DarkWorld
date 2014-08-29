package matthbo.mods.darkworld.item.tool;

import net.minecraft.item.Item;
import matthbo.mods.darkworld.item.ItemDarkWorld;
import matthbo.mods.darkworld.item.ItemToolDarkWorld;

public class PeculiarPickaxe extends ItemToolDarkWorld {
	
	public PeculiarPickaxe(){
		super(); //cause Item.ToolMaterial.IRON is OP
		this.setUnlocalizedName("peculiarpickaxe");
	}

}
