package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;

public class BlockDarkDirt extends BlockDarkWorld {
	
	public BlockDarkDirt(){
		super(Material.ground);
		this.setUnlocalizedName("darkdirt");
		this.setStepSound(soundTypeGravel);
		this.setHardness(0.5F);
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable){
		Block plant = plantable.getPlant(world, pos.up()).getBlock();
		if (plant == ModBlocks.darkSapling){return true;}
		return false;
	}

}
