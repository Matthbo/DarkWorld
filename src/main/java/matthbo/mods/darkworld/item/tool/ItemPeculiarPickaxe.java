package matthbo.mods.darkworld.item.tool;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import matthbo.mods.darkworld.init.ModBlocks;

public class ItemPeculiarPickaxe extends ItemToolDarkWorld {
	
	public ItemPeculiarPickaxe(){
		super(2.0F, ToolMaterial.STONE, ItemToolDarkWorld.pickaxe); //cause Item.ToolMaterial.IRON is OP
		this.setUnlocalizedName("peculiarpickaxe");
	}
	
	public boolean canHarvestBlock(Block p_150897_1_)
    {
        return p_150897_1_ == ModBlocks.darkObsidian ? this.toolMaterial.getHarvestLevel() == 3 : (p_150897_1_ == Blocks.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (p_150897_1_ != Blocks.diamond_block && p_150897_1_ != Blocks.diamond_ore ? (p_150897_1_ != Blocks.emerald_ore && p_150897_1_ != Blocks.emerald_block ? (p_150897_1_ != Blocks.gold_block && p_150897_1_ != Blocks.gold_ore ? (p_150897_1_ != Blocks.iron_block && p_150897_1_ != Blocks.iron_ore ? (p_150897_1_ != Blocks.lapis_block && p_150897_1_ != Blocks.lapis_ore ? (p_150897_1_ != Blocks.redstone_ore && p_150897_1_ != Blocks.lit_redstone_ore ? (p_150897_1_.getMaterial() == Material.rock ? true : (p_150897_1_.getMaterial() == Material.iron ? true : p_150897_1_.getMaterial() == Material.anvil)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2));
    }

    public float getStrVsBlock(ItemStack stack, Block block)
    {
        return block.getMaterial() != Material.iron && block.getMaterial() != Material.anvil && block.getMaterial() != Material.rock ? super.getStrVsBlock(stack, block) : this.efficiencyOnProperMaterial;
    }

}
