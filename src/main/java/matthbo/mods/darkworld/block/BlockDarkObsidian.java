package matthbo.mods.darkworld.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class BlockDarkObsidian extends BlockDarkWorld {
	
	public BlockDarkObsidian(){
		super();
		this.setBlockName("darkobsidian");
		this.setHardness(50.0F).setResistance(2000.0F);
		this.setStepSound(soundTypePiston);
	}
	
	public MapColor getMapColor(int p_149728_1_)
    {
        return MapColor.obsidianColor;
    }
	
	@Override
	public boolean isToolEffective(String type, int metadata) {
		if ("pickaxe".equals(type)) return false;
        if (getHarvestTool(metadata) == null) return false;
        return getHarvestTool(metadata).equals(type);
	}

}
