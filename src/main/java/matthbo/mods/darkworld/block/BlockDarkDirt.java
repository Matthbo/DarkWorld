package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockDarkDirt extends BlockDarkWorld {
	
	public BlockDarkDirt(){
		super(Material.ground);
		this.setBlockName("darkdirt");
		this.setStepSound(soundTypeGravel);
		this.setHardness(0.5F);
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable){
		Block plant = plantable.getPlant(world, x, y + 1, z);
		if (plant == ModBlocks.darkSapling){return true;}
		return false;
	}

}
