package matthbo.mods.darkworld.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class BlockDarkObsidian extends BlockDarkWorld {
	
	public BlockDarkObsidian(){
		super();
		this.setUnlocalizedName("darkobsidian");
		this.setHardness(50.0F).setResistance(2000.0F);
		this.setStepSound(soundTypePiston);
	}
	
	public MapColor getMapColor(IBlockState state)
    {
        return MapColor.obsidianColor;
    }
	
	@Override
	public boolean isToolEffective(String type, IBlockState state) {
		if ("pickaxe".equals(type)) return false;
        if (getHarvestTool(state) == null) return false;
        return getHarvestTool(state).equals(type);
	}

}
