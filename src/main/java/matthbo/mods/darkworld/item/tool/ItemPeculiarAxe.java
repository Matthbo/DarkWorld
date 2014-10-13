package matthbo.mods.darkworld.item.tool;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;


public class ItemPeculiarAxe extends ItemToolDarkWorld {
	
	public ItemPeculiarAxe(){
		super(3.0F, ToolMaterial.STONE, ItemToolDarkWorld.axe);
		this.setUnlocalizedName("peculiaraxe");
	}
	
	public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
    {
        return p_150893_2_.getMaterial() != Material.wood && p_150893_2_.getMaterial() != Material.plants && p_150893_2_.getMaterial() != Material.vine ? super.func_150893_a(p_150893_1_, p_150893_2_) : this.efficiencyOnProperMaterial;
    }

}
