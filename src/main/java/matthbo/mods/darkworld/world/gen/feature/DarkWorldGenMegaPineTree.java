package matthbo.mods.darkworld.world.gen.feature;

import matthbo.mods.darkworld.block.BlockDarkSapling;
import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class DarkWorldGenMegaPineTree extends DarkWorldGenHugeTrees {

    private boolean field_150542_e;
    private static final String __OBFID = "CL_00000421";

    public DarkWorldGenMegaPineTree(boolean p_i45457_1_, boolean p_i45457_2_)
    {
        super(p_i45457_1_, 13, 15, 1, 1);
        this.field_150542_e = p_i45457_2_;
    }

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        int l = this.func_150533_a(rand);

        if (!this.func_150537_a(world, rand, x, y, z, l))
        {
            return false;
        }
        else
        {
            this.func_150541_c(world, x, z, y + l, 0, rand);

            for (int i1 = 0; i1 < l; ++i1)
            {
                Block block = world.getBlock(x, y + i1, z);

                if (block.isAir(world, x, y + i1, z) || block.isLeaves(world, x, y + i1, z))
                {
                    this.setBlockAndNotifyAdequately(world, x, y + i1, z, ModBlocks.darkLog, this.woodMetadata);
                }

                if (i1 < l - 1)
                {
                    block = world.getBlock(x + 1, y + i1, z);

                    if (block.isAir(world, x + 1, y + i1, z) || block.isLeaves(world, x + 1, y + i1, z))
                    {
                        this.setBlockAndNotifyAdequately(world, x + 1, y + i1, z, ModBlocks.darkLog, this.woodMetadata);
                    }

                    block = world.getBlock(x + 1, y + i1, z + 1);

                    if (block.isAir(world, x + 1, y + i1, z + 1) || block.isLeaves(world, x + 1, y + i1, z + 1))
                    {
                        this.setBlockAndNotifyAdequately(world, x + 1, y + i1, z + 1, ModBlocks.darkLog, this.woodMetadata);
                    }

                    block = world.getBlock(x, y + i1, z + 1);

                    if (block.isAir(world, x, y + i1, z + 1) || block.isLeaves(world, x, y + i1, z + 1))
                    {
                        this.setBlockAndNotifyAdequately(world, x, y + i1, z + 1, ModBlocks.darkLog, this.woodMetadata);
                    }
                }
            }

            return true;
        }
    }

    private void func_150541_c(World world, int x, int y, int z, int par5, Random rand)
    {
        int i1 = rand.nextInt(5);

        if (this.field_150542_e)
        {
            i1 += this.baseHeight;
        }
        else
        {
            i1 += 3;
        }

        int j1 = 0;

        for (int k1 = z - i1; k1 <= z; ++k1)
        {
            int l1 = z - k1;
            int i2 = par5 + MathHelper.floor_float((float) l1 / (float) i1 * 3.5F);
            this.func_150535_a(world, x, k1, y, i2 + (l1 > 0 && i2 == j1 && (k1 & 1) == 0 ? 1 : 0), rand);
            j1 = i2;
        }
    }

    public void func_150524_b(World world, Random rand, int x, int y, int z)
    {
        this.func_150539_c(world, rand, x - 1, y, z - 1);
        this.func_150539_c(world, rand, x + 2, y, z - 1);
        this.func_150539_c(world, rand, x - 1, y, z + 2);
        this.func_150539_c(world, rand, x + 2, y, z + 2);

        for (int l = 0; l < 5; ++l)
        {
            int i1 = rand.nextInt(64);
            int j1 = i1 % 8;
            int k1 = i1 / 8;

            if (j1 == 0 || j1 == 7 || k1 == 0 || k1 == 7)
            {
                this.func_150539_c(world, rand, x - 3 + j1, y, z - 3 + k1);
            }
        }
    }

    private void func_150539_c(World world, Random rand, int x, int y, int z)
    {
        for (int l = -2; l <= 2; ++l)
        {
            for (int i1 = -2; i1 <= 2; ++i1)
            {
                if (Math.abs(l) != 2 || Math.abs(i1) != 2)
                {
                    this.func_150540_a(world, x + l, y, z + i1);
                }
            }
        }
    }

    private void func_150540_a(World world, int x, int y, int z)
    {
        for (int l = y + 2; l >= y - 3; --l)
        {
            Block block = world.getBlock(x, l, z);

            if (block.canSustainPlant(world, x, l, z, ForgeDirection.UP, (BlockDarkSapling)ModBlocks.darkSapling))
            {
                this.setBlockAndNotifyAdequately(world, x, l, z, Blocks.dirt, 2);
                break;
            }

            if (block.isAir(world, x, l, z) && l < y)
            {
                break;
            }
        }
    }

}
