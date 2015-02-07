package matthbo.mods.darkworld.world.gen;

import matthbo.mods.darkworld.init.ModBiomes;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModFluids;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenRavine;

import java.util.Random;

public class MapGenDarkWorldRavine extends MapGenRavine {

    private float[] field_75046_d = new float[1024];

    protected void func_180707_a(long par1, int par2, int par3, ChunkPrimer chunkPrimer, double par5, double par6, double par7, float par8, float par9, float par10, int par11, int par12, double par13)
    {
        Random random = new Random(par1);
        double d4 = (double)(par2 * 16 + 8);
        double d5 = (double)(par3 * 16 + 8);
        float f3 = 0.0F;
        float f4 = 0.0F;

        if (par12 <= 0)
        {
            int j1 = this.range * 16 - 16;
            par12 = j1 - random.nextInt(j1 / 4);
        }

        boolean flag1 = false;

        if (par11 == -1)
        {
            par11 = par12 / 2;
            flag1 = true;
        }

        float f5 = 1.0F;

        for (int k1 = 0; k1 < 256; ++k1)
        {
            if (k1 == 0 || random.nextInt(3) == 0)
            {
                f5 = 1.0F + random.nextFloat() * random.nextFloat() * 1.0F;
            }

            this.field_75046_d[k1] = f5 * f5;
        }

        for (; par11 < par12; ++par11)
        {
            double d13 = 1.5D + (double)(MathHelper.sin((float) par11 * (float) Math.PI / (float) par12) * par8 * 1.0F);
            double d6 = d13 * par13;
            d13 *= (double)random.nextFloat() * 0.25D + 0.75D;
            d6 *= (double)random.nextFloat() * 0.25D + 0.75D;
            float f6 = MathHelper.cos(par10);
            float f7 = MathHelper.sin(par10);
            par5 += (double)(MathHelper.cos(par9) * f6);
            par6 += (double)f7;
            par7 += (double)(MathHelper.sin(par9) * f6);
            par10 *= 0.7F;
            par10 += f4 * 0.05F;
            par9 += f3 * 0.05F;
            f4 *= 0.8F;
            f3 *= 0.5F;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

            if (flag1 || random.nextInt(4) != 0)
            {
                double d7 = par5 - d4;
                double d8 = par7 - d5;
                double d9 = (double)(par12 - par11);
                double d10 = (double)(par8 + 2.0F + 16.0F);

                if (d7 * d7 + d8 * d8 - d9 * d9 > d10 * d10)
                {
                    return;
                }

                if (par5 >= d4 - 16.0D - d13 * 2.0D && par7 >= d5 - 16.0D - d13 * 2.0D && par5 <= d4 + 16.0D + d13 * 2.0D && par7 <= d5 + 16.0D + d13 * 2.0D)
                {
                    int k3 = MathHelper.floor_double(par5 - d13) - par2 * 16 - 1;
                    int l1 = MathHelper.floor_double(par5 + d13) - par2 * 16 + 1;
                    int l3 = MathHelper.floor_double(par6 - d6) - 1;
                    int i2 = MathHelper.floor_double(par6 + d6) + 1;
                    int i4 = MathHelper.floor_double(par7 - d13) - par3 * 16 - 1;
                    int j2 = MathHelper.floor_double(par7 + d13) - par3 * 16 + 1;

                    if (k3 < 0)
                    {
                        k3 = 0;
                    }

                    if (l1 > 16)
                    {
                        l1 = 16;
                    }

                    if (l3 < 1)
                    {
                        l3 = 1;
                    }

                    if (i2 > 248)
                    {
                        i2 = 248;
                    }

                    if (i4 < 0)
                    {
                        i4 = 0;
                    }

                    if (j2 > 16)
                    {
                        j2 = 16;
                    }

                    boolean flag2 = false;
                    int k2;

                    for (k2 = k3; !flag2 && k2 < l1; ++k2)
                    {
                        for (int l2 = i4; !flag2 && l2 < j2; ++l2)
                        {
                            for (int i3 = i2 + 1; !flag2 && i3 >= l3 - 1; --i3)
                            {
                                if (i3 >= 0 && i3 < 256)
                                {
                                    IBlockState iblockstate = chunkPrimer.getBlockState(k2, i3, l2);

                                    if (isOceanBlock(chunkPrimer, k2, i2, l2, par2, par3))
                                    {
                                        flag2 = true;
                                    }

                                    if (i3 != l3 - 1 && k2 != k3 && k2 != l1 - 1 && l2 != i4 && l2 != j2 - 1)
                                    {
                                        i3 = l3;
                                    }
                                }
                            }
                        }
                    }

                    if (!flag2)
                    {
                        for (k2 = k3; k2 < l1; ++k2)
                        {
                            double d14 = ((double)(k2 + par2 * 16) + 0.5D - par5) / d13;

                            for (int j4 = i4; j4 < j2; ++j4)
                            {
                                double d11 = ((double)(j4 + par3 * 16) + 0.5D - par7) / d13;
                                boolean flag = false;

                                if (d14 * d14 + d11 * d11 < 1.0D)
                                {
                                    for (int j3 = i2; j3 > l3; --j3)
                                    {
                                        double d12 = ((double)(j3 - 1) + 0.5D - par6) / d6;

                                        if ((d14 * d14 + d11 * d11) * (double)this.field_75046_d[j3 - 1] + d12 * d12 / 6.0D < 1.0D)
                                        {
                                            IBlockState iblockstate1 = chunkPrimer.getBlockState(k2, j3, j4);

                                            if (isTopBlock(chunkPrimer, k2, j3, j4, par2, par3))
                                            {
                                                flag = true;
                                            }

                                            digBlock(chunkPrimer, k2, j3, j4, par2, par3, flag);
                                        }
                                    }
                                }
                            }
                        }

                        if (flag1)
                        {
                            break;
                        }
                    }
                }
            }
        }
    }

    protected void func_180701_a(World world, int par2, int par3, int par4, int par5, ChunkPrimer chunkPrimer)
    {
        if (this.rand.nextInt(50) == 0)
        {
            double d0 = (double)(par2 * 16 + this.rand.nextInt(16));
            double d1 = (double)(this.rand.nextInt(this.rand.nextInt(40) + 8) + 20);
            double d2 = (double)(par3 * 16 + this.rand.nextInt(16));
            byte b0 = 1;

            for (int i1 = 0; i1 < b0; ++i1)
            {
                float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float f2 = (this.rand.nextFloat() * 2.0F + this.rand.nextFloat()) * 2.0F;
                this.func_180707_a(this.rand.nextLong(), par4, par5, chunkPrimer, d0, d1, d2, f2, f, f1, 0, 0, 3.0D);
            }
        }
    }
    protected boolean isOceanBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ)
    {
        net.minecraft.block.Block block = data.getBlockState(x, y, z).getBlock();
        return block== ModFluids.darkWaterBlock;
    }

    //Exception biomes to make sure we generate like vanilla
    private boolean isExceptionBiome(net.minecraft.world.biome.BiomeGenBase biome)
    {
        if (biome == net.minecraft.world.biome.BiomeGenBase.beach) return true;//Todo make beach biome
        if (biome == ModBiomes.darkDesert) return true;
        //if (biome == net.minecraft.world.biome.BiomeGenBase.mushroomIsland) return true;
        //if (biome == net.minecraft.world.biome.BiomeGenBase.mushroomIslandShore) return true;
        return false;
    }

    //Determine if the block at the specified location is the top block for the biome, we take into account
    //Vanilla bugs to make sure that we generate the map the same way vanilla does.
    private boolean isTopBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ)
    {
        net.minecraft.world.biome.BiomeGenBase biome = worldObj.getBiomeGenForCoords(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
        IBlockState state = data.getBlockState(x, y, z);
        return (isExceptionBiome(biome) ? state.getBlock() == ModBlocks.darkGrass : state.getBlock() == biome.topBlock);
    }

    /**
     * Digs out the current block, default implementation removes stone, filler, and top block
     * Sets the block to lava if y is less then 10, and air other wise.
     * If setting to air, it also checks to see if we've broken the surface and if so
     * tries to make the floor the biome's top block
     *
     * @param data Block data array
     * @param index Pre-calculated index into block data
     * @param x local X position
     * @param y local Y position
     * @param z local Z position
     * @param chunkX Chunk X position
     * @param chunkZ Chunk Y position
     * @param foundTop True if we've encountered the biome's top block. Ideally if we've broken the surface.
     */
    protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop)
    {
        net.minecraft.world.biome.BiomeGenBase biome = worldObj.getBiomeGenForCoords(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
        IBlockState state = data.getBlockState(x, y, z);
        IBlockState top = isExceptionBiome(biome) ? ModBlocks.darkGrass.getDefaultState() : biome.topBlock;
        IBlockState filler = isExceptionBiome(biome) ? ModBlocks.darkDirt.getDefaultState() : biome.fillerBlock;

        if (state.getBlock() == ModBlocks.darkStone || state.getBlock() == top.getBlock() || state.getBlock() == filler.getBlock())
        {
            if (y < 10)
            {
                data.setBlockState(x, y, z, ModFluids.darkLavaBlock.getDefaultState());
            }
            else
            {
                data.setBlockState(x, y, z, Blocks.air.getDefaultState());

                if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == filler.getBlock())
                {
                    data.setBlockState(x, y - 1, z, top.getBlock().getDefaultState());
                }
            }
        }
    }

}
