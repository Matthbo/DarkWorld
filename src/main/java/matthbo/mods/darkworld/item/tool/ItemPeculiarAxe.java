package matthbo.mods.darkworld.item.tool;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;


public class ItemPeculiarAxe extends ItemToolDarkWorld {
	
	public ItemPeculiarAxe(){
		super(3.0F, ToolMaterial.STONE, ItemToolDarkWorld.axe);
		this.setUnlocalizedName("peculiaraxe");
	}

	public float getStrVsBlock(ItemStack stack, Block block)
	{
		return block.getMaterial() != Material.wood && block.getMaterial() != Material.plants && block.getMaterial() != Material.vine ? super.getStrVsBlock(stack, block) : this.efficiencyOnProperMaterial;
	}

}
