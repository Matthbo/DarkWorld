package matthbo.mods.darkworld.block;

import java.util.Random;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class BlockDarkGrass extends BlockDarkWorld implements IGrowable {

    //public static final PropertyBool SNOWY = PropertyBool.create("snowy");
	
	public BlockDarkGrass() {
		super(Material.grass);
		this.setUnlocalizedName("darkgrass");
		this.setHardness(0.6F);
		this.setStepSound(soundTypeGrass);
		this.setTickRandomly(true);
        //this.setDefaultState(this.blockState.getBaseState().withProperty(SNOWY, Boolean.valueOf(false)));
	}

    /*public IBlockState getActualState(IBlockState state, IBlockAccess block, BlockPos pos){
        Block block1 = block.getBlockState(pos.up()).getBlock();
        return state.withProperty(SNOWY, Boolean.valueOf(block1 == Blocks.snow || block1 == Blocks.snow_layer));
    }*/

    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockState(pos.up()).getBlock().getLightOpacity(world, pos.up()) > 2)
            {
                world.setBlockState(pos, ModBlocks.darkDirt.getDefaultState());
            }
            else
            {
                if (world.getLightFromNeighbors(pos.up()) >= 9)
                {
                    for (int i = 0; i < 4; ++i)
                    {
                        BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                        Block block = world.getBlockState(blockpos1.up()).getBlock();
                        IBlockState iblockstate1 = world.getBlockState(blockpos1);

                        if (iblockstate1.getBlock() == ModBlocks.darkDirt && world.getLightFromNeighbors(blockpos1.up()) >= 4 && block.getLightOpacity(world, blockpos1.up()) <= 2)//TODO: change licht level so that dirt can become grass?
                        {
                            world.setBlockState(blockpos1, ModBlocks.darkGrass.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    public Item getItemDropped(IBlockState par1, Random rand, int fortune)
    {
        return ModBlocks.darkDirt.getItemDropped(ModBlocks.darkDirt.getDefaultState(), rand, fortune);
    }

    public boolean canGrow(World world, BlockPos pos_, IBlockState state, boolean isClient)
    {
        return true;
    }

    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable){
        Block plant = plantable.getPlant(world, pos.up()).getBlock();
        if (plant == ModBlocks.darkTallGrass || plant == ModBlocks.darkSapling){return true;}
        return false;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        BlockPos blockpos1 = pos.up();
        int i = 0;

        while (i < 128)
        {
            BlockPos blockpos2 = blockpos1;
            int j = 0;

            while (true)
            {
                if (j < i / 16)
                {
                    blockpos2 = blockpos2.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                    if (world.getBlockState(blockpos2.down()).getBlock() == ModBlocks.darkGrass && !world.getBlockState(blockpos2).getBlock().isNormalCube())
                    {
                        ++j;
                        continue;
                    }
                }
                else if (world.isAirBlock(blockpos2))
                {
                    if (rand.nextInt(8) == 0)
                    {
                        world.getBiomeGenForCoords(blockpos2).plantFlower(world, rand, blockpos2);
                    }
                    else
                    {
                        IBlockState iblockstate2 = ModBlocks.darkTallGrass.getDefaultState();

                        if (ModBlocks.darkTallGrass.canBlockStay(world, blockpos2, iblockstate2))
                        {
                            world.setBlockState(blockpos2, iblockstate2, 3);
                        }
                    }
                }

                ++i;
                break;
            }
        }
    }

    /*protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {SNOWY});
    }*/

}
