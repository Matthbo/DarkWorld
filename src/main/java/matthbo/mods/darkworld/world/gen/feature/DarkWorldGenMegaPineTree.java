package matthbo.mods.darkworld.world.gen.feature;

import matthbo.mods.darkworld.block.BlockDarkPlanks;
import matthbo.mods.darkworld.block.BlockDarkSapling;
import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class DarkWorldGenMegaPineTree extends DarkWorldGenHugeTrees {

    private boolean field_150542_e;
    private static final String __OBFID = "CL_00000421";

    public DarkWorldGenMegaPineTree(boolean p_i45457_1_, boolean p_i45457_2_)
    {
        super(p_i45457_1_, 13, 15, BlockDarkPlanks.EnumType.DARKSPRUCE.getMetadata(), BlockDarkPlanks.EnumType.DARKSPRUCE.getMetadata());
        this.field_150542_e = p_i45457_2_;
    }

    public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
        int i = this.func_150533_a(rand);

        if (!this.func_175929_a(worldIn, rand, pos, i))
        {
            return false;
        }
        else
        {
            this.func_150541_c(worldIn, pos.getX(), pos.getZ(), pos.getY() + i, 0, rand);

            for (int j = 0; j < i; ++j)
            {
                if (this.isAirLeaves(worldIn, pos.up(j)))
                {
                    this.func_175905_a(worldIn, pos.up(j), ModBlocks.darkLog, this.woodMetadata);
                }

                if (j < i - 1)
                {
                    if (this.isAirLeaves(worldIn, pos.add(1, j, 0)))
                    {
                        this.func_175905_a(worldIn, pos.add(1, j, 0), ModBlocks.darkLog, this.woodMetadata);
                    }

                    if (this.isAirLeaves(worldIn, pos.add(1, j, 1)))
                    {
                        this.func_175905_a(worldIn, pos.add(1, j, 1), ModBlocks.darkLog, this.woodMetadata);
                    }

                    if (this.isAirLeaves(worldIn, pos.add(0, j, 1)))
                    {
                        this.func_175905_a(worldIn, pos.add(0, j, 1), ModBlocks.darkLog, this.woodMetadata);
                    }
                }
            }

            return true;
        }
    }

    private void func_150541_c(World worldIn, int x, int z, int par4, int par5, Random rand)
    {
        int i1 = rand.nextInt(5) + (this.field_150542_e ? this.baseHeight : 3);
        int j1 = 0;

        for (int k1 = par4 - i1; k1 <= par4; ++k1)
        {
            int l1 = par4 - k1;
            int i2 = par5 + MathHelper.floor_float((float)l1 / (float)i1 * 3.5F);
            this.func_175925_a(worldIn, new BlockPos(x, k1, z), i2 + (l1 > 0 && i2 == j1 && (k1 & 1) == 0 ? 1 : 0));
            j1 = i2;
        }
    }

    public void func_180711_a(World worldIn, Random rand, BlockPos pos)
    {
        this.func_175933_b(worldIn, pos.west().north());
        this.func_175933_b(worldIn, pos.east(2).north());
        this.func_175933_b(worldIn, pos.west().south(2));
        this.func_175933_b(worldIn, pos.east(2).south(2));

        for (int i = 0; i < 5; ++i)
        {
            int j = rand.nextInt(64);
            int k = j % 8;
            int l = j / 8;

            if (k == 0 || k == 7 || l == 0 || l == 7)
            {
                this.func_175933_b(worldIn, pos.add(-3 + k, 0, -3 + l));
            }
        }
    }

    private void func_175933_b(World worldIn, BlockPos pos)
    {
        for (int i = -2; i <= 2; ++i)
        {
            for (int j = -2; j <= 2; ++j)
            {
                if (Math.abs(i) != 2 || Math.abs(j) != 2)
                {
                    this.func_175934_c(worldIn, pos.add(i, 0, j));
                }
            }
        }
    }

    private void func_175934_c(World worldIn, BlockPos pos)
    {
        for (int i = 2; i >= -3; --i)
        {
            BlockPos blockpos1 = pos.up(i);
            Block block = worldIn.getBlockState(blockpos1).getBlock();

            if (block.canSustainPlant(worldIn, blockpos1, net.minecraft.util.EnumFacing.UP, ((BlockDarkSapling)ModBlocks.darkSapling)))
            {
                this.func_175905_a(worldIn, blockpos1, ModBlocks.darkDirt, 0);
                break;
            }

            if (!block.isAir(worldIn, blockpos1) && i < 0)
            {
                break;
            }
        }
    }

    //Helper macro
    private boolean isAirLeaves(World world, BlockPos pos)
    {
        net.minecraft.block.Block block = world.getBlockState(pos).getBlock();
        return block.isAir(world, pos) || block.isLeaves(world, pos);
    }

}
