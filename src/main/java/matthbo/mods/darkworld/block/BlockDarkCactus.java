package matthbo.mods.darkworld.block;

import java.util.Iterator;
import java.util.Random;

import matthbo.mods.darkworld.reference.EnumDarkWorld;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDarkCactus extends BlockDarkWorld implements IPlantable {

    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
	
	public BlockDarkCactus() {
		super(Material.cactus);
		
		this.setUnlocalizedName("darkcactus");
		this.setHardness(0.4F);
		this.setStepSound(soundTypeCloth);
		this.setTickRandomly(true);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
	}

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        BlockPos blockpos1 = pos.up();

        if (worldIn.isAirBlock(blockpos1))
        {
            int i;

            for (i = 1; worldIn.getBlockState(pos.down(i)).getBlock() == this; ++i)
            {
                ;
            }

            if (i < 3)
            {
                int j = ((Integer)state.getValue(AGE)).intValue();

                if (j == 15)
                {
                    worldIn.setBlockState(blockpos1, this.getDefaultState());
                    IBlockState iblockstate1 = state.withProperty(AGE, Integer.valueOf(0));
                    worldIn.setBlockState(pos, iblockstate1, 4);
                    this.onNeighborBlockChange(worldIn, blockpos1, iblockstate1, this);
                }
                else
                {
                    worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(j + 1)), 4);
                }
            }
        }
    }

    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        float f = 0.0625F;
        return new AxisAlignedBB((double)((float)pos.getX() + f), (double)pos.getY(), (double)((float)pos.getZ() + f), (double)((float)(pos.getX() + 1) - f), (double)((float)(pos.getY() + 1) - f), (double)((float)(pos.getZ() + 1) - f));
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos)
    {
        float f = 0.0625F;
        return new AxisAlignedBB((double)((float)pos.getX() + f), (double)pos.getY(), (double)((float)pos.getZ() + f), (double)((float)(pos.getX() + 1) - f), (double)(pos.getY() + 1), (double)((float)(pos.getZ() + 1) - f));
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean isFullCube()
    {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        return super.canPlaceBlockAt(world, pos) ? false : this.canBlockStay(world, pos);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block)
    {
        if (!this.canBlockStay(world,pos))
        {
            world.destroyBlock(pos, true);
        }
    }

    public boolean canBlockStay(World worldIn, BlockPos pos)
    {
        Iterator iterator = EnumFacing.Plane.HORIZONTAL.iterator();

        while (iterator.hasNext())
        {
            EnumFacing enumfacing = (EnumFacing)iterator.next();

            if (worldIn.getBlockState(pos.offset(enumfacing)).getBlock().getMaterial().isSolid())
            {
                return false;
            }
        }

        Block block = worldIn.getBlockState(pos.down()).getBlock();
        return block.canSustainPlant(worldIn, pos.down(), EnumFacing.UP, this);
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        entity.attackEntityFrom(DamageSource.cactus, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(AGE)).intValue();
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {AGE});
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
        //return EnumPlantType.Desert;
    	return EnumDarkWorld.DarkDesertPlant;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos)
    {
        return getDefaultState();
    }

}
