package matthbo.mods.darkworld.world.gen.feature;

import matthbo.mods.darkworld.block.BlockDarkSapling;
import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

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

    private boolean func_150536_b(World world, Random rand, int par3, int par4, int par5, int par6)
    {
        boolean flag = true;

        if (par4 >= 1 && par4 + par6 + 1 <= 256)
        {
            for (int i1 = par4; i1 <= par4 + 1 + par6; ++i1)
            {
                byte b0 = 2;

                if (i1 == par4)
                {
                    b0 = 1;
                }

                if (i1 >= par4 + 1 + par6 - 2)
                {
                    b0 = 2;
                }

                for (int j1 = par3 - b0; j1 <= par3 + b0 && flag; ++j1)
                {
                    for (int k1 = par5 - b0; k1 <= par5 + b0 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            Block block = world.getBlock(j1, i1, k1);

                            if (!this.isReplaceable(world, j1, i1, k1))
                            {
                                flag = false;
                            }
                        }
                        else
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

    private boolean func_150532_c(World world, Random p_150532_2_, int x, int y, int z)
    {
        Block block = world.getBlock(x, y - 1, z);

        boolean isSoil = block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (BlockDarkSapling) ModBlocks.darkSapling);
        if (isSoil && y >= 2)
        {
            onPlantGrow(world, x,     y - 1, z,     x, y, z);
            onPlantGrow(world, x + 1, y - 1, z,     x, y, z);
            onPlantGrow(world, x,     y - 1, z + 1, x, y, z);
            onPlantGrow(world, x + 1, y - 1, z + 1, x, y, z);
            return true;
        }
        else
        {
            return false;
        }
    }

    protected boolean func_150537_a(World world, Random rand, int x, int y, int z, int par6)
    {
        return this.func_150536_b(world, rand, x, y, z, par6) && this.func_150532_c(world, rand, x, y, z);
    }

    protected void func_150535_a(World world, int x, int y, int z, int par5, Random rand)
    {
        int i1 = par5 * par5;

        for (int j1 = x - par5; j1 <= x + par5 + 1; ++j1)
        {
            int k1 = j1 - x;

            for (int l1 = z - par5; l1 <= z + par5 + 1; ++l1)
            {
                int i2 = l1 - z;
                int j2 = k1 - 1;
                int k2 = i2 - 1;

                if (k1 * k1 + i2 * i2 <= i1 || j2 * j2 + k2 * k2 <= i1 || k1 * k1 + k2 * k2 <= i1 || j2 * j2 + i2 * i2 <= i1)
                {
                    Block block = world.getBlock(j1, y, l1);

                    if (block.isAir(world, j1, y, l1) || block.isLeaves(world, j1, y, l1))
                    {
                        this.setBlockAndNotifyAdequately(world, j1, y, l1, ModBlocks.darkLeaves, 0);
                    }
                }
            }
        }
    }

    protected void func_150534_b(World world, int x, int y, int z, int par6, Random rand)
    {
        int i1 = par6 * par6;

        for (int j1 = x - par6; j1 <= x + par6; ++j1)
        {
            int k1 = j1 - x;

            for (int l1 = z - par6; l1 <= z + par6; ++l1)
            {
                int i2 = l1 - z;

                if (k1 * k1 + i2 * i2 <= i1)
                {
                    Block block = world.getBlock(j1, y, l1);

                    if (block.isAir(world, j1, y, l1) || block.isLeaves(world, j1, y, l1))
                    {
                        this.setBlockAndNotifyAdequately(world, j1, y, l1, ModBlocks.darkLeaves, 0);
                    }
                }
            }
        }
    }

    //Just a helper macro ye fuck!
    private void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
    {
        world.getBlock(x, y, z).onPlantGrow(world, x, y, z, sourceX, sourceY, sourceZ);
    }

}
