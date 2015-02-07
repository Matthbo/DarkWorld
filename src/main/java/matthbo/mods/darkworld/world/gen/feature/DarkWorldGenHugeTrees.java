package matthbo.mods.darkworld.world.gen.feature;

import matthbo.mods.darkworld.block.BlockDarkSapling;
import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public abstract class DarkWorldGenHugeTrees extends DarkWorldGenAbstractTree {

    /** The base height of the tree */
    protected final int baseHeight;
    /** Sets the metadata for the wood blocks used */
    protected final int woodMetadata;
    /** Sets the metadata for the leaves used in huge trees */
    protected final int leavesMetadata;
    protected int field_150538_d;

    public DarkWorldGenHugeTrees(boolean par1, int height, int par2, int metaWood, int metaLeaves)
    {
        super(par1);
        this.baseHeight = height;
        this.field_150538_d = par2;
        this.woodMetadata = metaWood;
        this.leavesMetadata = metaLeaves;
    }

    protected int func_150533_a(Random rand)
    {
        int i = rand.nextInt(3) + this.baseHeight;

        if (this.field_150538_d > 1)
        {
            i += rand.nextInt(this.field_150538_d);
        }

        return i;
    }

    private boolean func_175926_c(World worldIn, BlockPos pos, int par3)
    {
        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + par3 + 1 <= 256)
        {
            for (int j = 0; j <= 1 + par3; ++j)
            {
                byte b0 = 2;

                if (j == 0)
                {
                    b0 = 1;
                }
                else if (j >= 1 + par3 - 2)
                {
                    b0 = 2;
                }

                for (int k = -b0; k <= b0 && flag; ++k)
                {
                    for (int l = -b0; l <= b0 && flag; ++l)
                    {
                        if (pos.getY() + j < 0 || pos.getY() + j >= 256 || !this.isReplaceable(worldIn, pos.add(k, j, l)))
                        {
                            flag = false;
                        }
                    }
                }
            }

            return flag;
        }
        else
        {
            return false;
        }
    }

    private boolean func_175927_a(BlockPos pos, World world)
    {
        BlockPos blockpos1 = pos.down();
        Block block = world.getBlockState(blockpos1).getBlock();
        boolean isSoil = block.canSustainPlant(world, blockpos1, net.minecraft.util.EnumFacing.UP, ((BlockDarkSapling)ModBlocks.darkSapling));

        if (isSoil && pos.getY() >= 2)
        {
            this.onPlantGrow(world, blockpos1, pos);
            this.onPlantGrow(world, blockpos1.east(), pos);
            this.onPlantGrow(world, blockpos1.south(), pos);
            this.onPlantGrow(world, blockpos1.south().east(), pos);
            return true;
        }
        else
        {
            return false;
        }
    }

    protected boolean func_175929_a(World worldIn, Random rand, BlockPos pos, int par4)
    {
        return this.func_175926_c(worldIn, pos, par4) && this.func_175927_a(pos, worldIn);
    }

    protected void func_175925_a(World worldIn, BlockPos pos, int par3)
    {
        int j = par3 * par3;

        for (int k = -par3; k <= par3 + 1; ++k)
        {
            for (int l = -par3; l <= par3 + 1; ++l)
            {
                int i1 = k - 1;
                int j1 = l - 1;

                if (k * k + l * l <= j || i1 * i1 + j1 * j1 <= j || k * k + j1 * j1 <= j || i1 * i1 + l * l <= j)
                {
                    BlockPos blockpos1 = pos.add(k, 0, l);
                    net.minecraft.block.state.IBlockState state = worldIn.getBlockState(blockpos1);

                    if (state.getBlock().isAir(worldIn, blockpos1) || state.getBlock().isLeaves(worldIn, blockpos1))
                    {
                        this.func_175905_a(worldIn, blockpos1, ModBlocks.darkLeaves, 0);
                    }
                }
            }
        }
    }

    protected void func_175928_b(World worldIn, BlockPos pos, int par3)
    {
        int j = par3 * par3;

        for (int k = -par3; k <= par3; ++k)
        {
            for (int l = -par3; l <= par3; ++l)
            {
                if (k * k + l * l <= j)
                {
                    BlockPos blockpos1 = pos.add(k, 0, l);
                    Block block = worldIn.getBlockState(blockpos1).getBlock();

                    if (block.isAir(worldIn, blockpos1) || block.isLeaves(worldIn, blockpos1))
                    {
                        this.func_175905_a(worldIn, blockpos1, ModBlocks.darkLeaves, 0);
                    }
                }
            }
        }
    }

    //Just a helper macro
    private void onPlantGrow(World world, BlockPos pos, BlockPos source)
    {
        world.getBlockState(pos).getBlock().onPlantGrow(world, pos, source);
    }

}
