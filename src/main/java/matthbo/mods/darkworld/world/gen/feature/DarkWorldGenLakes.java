package matthbo.mods.darkworld.world.gen.feature;

import java.util.Random;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLakes;

public class DarkWorldGenLakes extends WorldGenLakes {

	private Block field_150556_a;
    private static final String __OBFID = "CL_00000418";

    public DarkWorldGenLakes(Block block)
    {
    	super(block);
        this.field_150556_a = block;
    }

    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (pos = pos.add(-8, 0, -8); pos.getY() > 5 && world.isAirBlock(pos); pos = pos.down())
        {
            ;
        }

        if (pos.getY() <= 4)
        {
            return false;
        }
        else
        {
            pos = pos.down(4);
            boolean[] aboolean = new boolean[2048];
            int i = rand.nextInt(4) + 4;
            int j;

            for (j = 0; j < i; ++j)
            {
                double d0 = rand.nextDouble() * 6.0D + 3.0D;
                double d1 = rand.nextDouble() * 4.0D + 2.0D;
                double d2 = rand.nextDouble() * 6.0D + 3.0D;
                double d3 = rand.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = rand.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = rand.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for (int l = 1; l < 15; ++l)
                {
                    for (int i1 = 1; i1 < 15; ++i1)
                    {
                        for (int j1 = 1; j1 < 7; ++j1)
                        {
                            double d6 = ((double)l - d3) / (d0 / 2.0D);
                            double d7 = ((double)j1 - d4) / (d1 / 2.0D);
                            double d8 = ((double)i1 - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;

                            if (d9 < 1.0D)
                            {
                                aboolean[(l * 16 + i1) * 8 + j1] = true;
                            }
                        }
                    }
                }
            }

            int k;
            int k1;
            boolean flag;

            for (j = 0; j < 16; ++j)
            {
                for (k1 = 0; k1 < 16; ++k1)
                {
                    for (k = 0; k < 8; ++k)
                    {
                        flag = !aboolean[(j * 16 + k1) * 8 + k] && (j < 15 && aboolean[((j + 1) * 16 + k1) * 8 + k] || j > 0 && aboolean[((j - 1) * 16 + k1) * 8 + k] || k1 < 15 && aboolean[(j * 16 + k1 + 1) * 8 + k] || k1 > 0 && aboolean[(j * 16 + (k1 - 1)) * 8 + k] || k < 7 && aboolean[(j * 16 + k1) * 8 + k + 1] || k > 0 && aboolean[(j * 16 + k1) * 8 + (k - 1)]);

                        if (flag)
                        {
                            Material material = world.getBlockState(pos.add(j, k, k1)).getBlock().getMaterial();

                            if (k >= 4 && material.isLiquid())
                            {
                                return false;
                            }

                            if (k < 4 && !material.isSolid() && world.getBlockState(pos.add(j, k, k1)).getBlock() != this.field_150556_a)
                            {
                                return false;
                            }
                        }
                    }
                }
            }

            for (j = 0; j < 16; ++j)
            {
                for (k1 = 0; k1 < 16; ++k1)
                {
                    for (k = 0; k < 8; ++k)
                    {
                        if (aboolean[(j * 16 + k1) * 8 + k])
                        {
                            world.setBlockState(pos.add(j, k, k1), k >= 4 ? Blocks.air.getDefaultState() : this.field_150556_a.getDefaultState(), 2);
                        }
                    }
                }
            }

            for (j = 0; j < 16; ++j)
            {
                for (k1 = 0; k1 < 16; ++k1)
                {
                    for (k = 4; k < 8; ++k)
                    {
                        if (aboolean[(j * 16 + k1) * 8 + k])
                        {
                            BlockPos blockpos1 = pos.add(j, k - 1, k1);

                            if (world.getBlockState(blockpos1).getBlock() == ModBlocks.darkDirt && world.getLightFor(EnumSkyBlock.SKY, pos.add(j, k, k1)) > 0)
                            {
                                BiomeGenBase biomegenbase = world.getBiomeGenForCoords(blockpos1);

                                if (biomegenbase.topBlock.getBlock() == Blocks.mycelium)
                                {
                                    world.setBlockState(blockpos1, Blocks.mycelium.getDefaultState(), 2);
                                }
                                else
                                {
                                    world.setBlockState(blockpos1, ModBlocks.darkGrass.getDefaultState(), 2);
                                }
                            }
                        }
                    }
                }
            }

            if (this.field_150556_a.getMaterial() == Material.lava)
            {
                for (j = 0; j < 16; ++j)
                {
                    for (k1 = 0; k1 < 16; ++k1)
                    {
                        for (k = 0; k < 8; ++k)
                        {
                            flag = !aboolean[(j * 16 + k1) * 8 + k] && (j < 15 && aboolean[((j + 1) * 16 + k1) * 8 + k] || j > 0 && aboolean[((j - 1) * 16 + k1) * 8 + k] || k1 < 15 && aboolean[(j * 16 + k1 + 1) * 8 + k] || k1 > 0 && aboolean[(j * 16 + (k1 - 1)) * 8 + k] || k < 7 && aboolean[(j * 16 + k1) * 8 + k + 1] || k > 0 && aboolean[(j * 16 + k1) * 8 + (k - 1)]);

                            if (flag && (k < 4 || rand.nextInt(2) != 0) && world.getBlockState(pos.add(j, k, k1)).getBlock().getMaterial().isSolid())
                            {
                                world.setBlockState(pos.add(j, k, k1), ModBlocks.darkStone.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }

            if (this.field_150556_a.getMaterial() == Material.water)
            {
                for (j = 0; j < 16; ++j)
                {
                    for (k1 = 0; k1 < 16; ++k1)
                    {
                        byte b0 = 4;

                        if (world.func_175675_v(pos.add(j, b0, k1)))
                        {
                            world.setBlockState(pos.add(j, b0, k1), Blocks.ice.getDefaultState(), 2);
                        }
                    }
                }
            }

            return true;
        }
    }
	
}
