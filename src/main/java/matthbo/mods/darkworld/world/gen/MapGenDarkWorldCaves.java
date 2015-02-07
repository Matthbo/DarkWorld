package matthbo.mods.darkworld.world.gen;

import com.google.common.base.Objects;
import matthbo.mods.darkworld.block.BlockDarkSand;
import matthbo.mods.darkworld.init.ModBiomes;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModFluids;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenCaves;

import java.util.Random;

public class MapGenDarkWorldCaves extends MapGenCaves{

    protected void func_180703_a(long par1, int par2, int par3, ChunkPrimer chunkPrimer, double par5, double par6, double par7)
    {
        this.func_180702_a(par1, par2, par3, chunkPrimer, par5, par6, par7, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
    }

    protected void func_180702_a(long par1, int par2, int par3, ChunkPrimer chunkPrimer, double par5, double par6, double par7, float par8, float par9, float par10, int par11, int par12, double par13)
    {
        double d4 = (double)(par2 * 16 + 8);
        double d5 = (double)(par3 * 16 + 8);
        float f3 = 0.0F;
        float f4 = 0.0F;
        Random random = new Random(par1);

        if (par12 <= 0)
        {
            int j1 = this.range * 16 - 16;
            par12 = j1 - random.nextInt(j1 / 4);
        }

        boolean flag2 = false;

        if (par11 == -1)
        {
            par11 = par12 / 2;
            flag2 = true;
        }

        int k1 = random.nextInt(par12 / 2) + par12 / 4;

        for (boolean flag = random.nextInt(6) == 0; par11 < par12; ++par11)
        {
            double d6 = 1.5D + (double)(MathHelper.sin((float) par11 * (float) Math.PI / (float) par12) * par8 * 1.0F);
            double d7 = d6 * par13;
            float f5 = MathHelper.cos(par10);
            float f6 = MathHelper.sin(par10);
            par5 += (double)(MathHelper.cos(par9) * f5);
            par6 += (double)f6;
            par7 += (double)(MathHelper.sin(par9) * f5);

            if (flag)
            {
                par10 *= 0.92F;
            }
            else
            {
                par10 *= 0.7F;
            }

            par10 += f4 * 0.1F;
            par9 += f3 * 0.1F;
            f4 *= 0.9F;
            f3 *= 0.75F;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

            if (!flag2 && par11 == k1 && par8 > 1.0F && par12 > 0)
            {
                this.func_180702_a(random.nextLong(), par2, par3, chunkPrimer, par5, par6, par7, random.nextFloat() * 0.5F + 0.5F, par9 - ((float)Math.PI / 2F), par10 / 3.0F, par11, par12, 1.0D);
                this.func_180702_a(random.nextLong(), par2, par3, chunkPrimer, par5, par6, par7, random.nextFloat() * 0.5F + 0.5F, par9 + ((float)Math.PI / 2F), par10 / 3.0F, par11, par12, 1.0D);
                return;
            }

            if (flag2 || random.nextInt(4) != 0)
            {
                double d8 = par5 - d4;
                double d9 = par7 - d5;
                double d10 = (double)(par12 - par11);
                double d11 = (double)(par8 + 2.0F + 16.0F);

                if (d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11)
                {
                    return;
                }

                if (par5 >= d4 - 16.0D - d6 * 2.0D && par7 >= d5 - 16.0D - d6 * 2.0D && par5 <= d4 + 16.0D + d6 * 2.0D && par7 <= d5 + 16.0D + d6 * 2.0D)
                {
                    int k3 = MathHelper.floor_double(par5 - d6) - par2 * 16 - 1;
                    int l1 = MathHelper.floor_double(par5 + d6) - par2 * 16 + 1;
                    int l3 = MathHelper.floor_double(par6 - d7) - 1;
                    int i2 = MathHelper.floor_double(par6 + d7) + 1;
                    int i4 = MathHelper.floor_double(par7 - d6) - par3 * 16 - 1;
                    int j2 = MathHelper.floor_double(par7 + d6) - par3 * 16 + 1;

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

                    boolean flag3 = false;
                    int k2;

                    for (k2 = k3; !flag3 && k2 < l1; ++k2)
                    {
                        for (int l2 = i4; !flag3 && l2 < j2; ++l2)
                        {
                            for (int i3 = i2 + 1; !flag3 && i3 >= l3 - 1; --i3)
                            {
                                if (i3 >= 0 && i3 < 256)
                                {
                                    IBlockState iblockstate = chunkPrimer.getBlockState(k2, i3, l2);

                                    if (isOceanBlock(chunkPrimer, k2, i3, l2, par2, par3))
                                    {
                                        flag3 = true;
                                    }

                                    if (i3 != l3 - 1 && k2 != k3 && k2 != l1 - 1 && l2 != i4 && l2 != j2 - 1)
                                    {
                                        i3 = l3;
                                    }
                                }
                            }
                        }
                    }

                    if (!flag3)
                    {
                        for (k2 = k3; k2 < l1; ++k2)
                        {
                            double d14 = ((double)(k2 + par2 * 16) + 0.5D - par5) / d6;

                            for (int j4 = i4; j4 < j2; ++j4)
                            {
                                double d12 = ((double)(j4 + par3 * 16) + 0.5D - par7) / d6;
                                boolean flag1 = false;

                                if (d14 * d14 + d12 * d12 < 1.0D)
                                {
                                    for (int j3 = i2; j3 > l3; --j3)
                                    {
                                        double d13 = ((double)(j3 - 1) + 0.5D - par6) / d7;

                                        if (d13 > -0.7D && d14 * d14 + d13 * d13 + d12 * d12 < 1.0D)
                                        {
                                            IBlockState iblockstate1 = chunkPrimer.getBlockState(k2, j3, j4);
                                            IBlockState iblockstate2 = (IBlockState) Objects.firstNonNull(chunkPrimer.getBlockState(k2, j3 + 1, j4), Blocks.air.getDefaultState());

                                            if (isTopBlock(chunkPrimer, k2, j3, j4, par2, par3))
                                            {
                                                flag1 = true;
                                            }
                                            digBlock(chunkPrimer, k2, j3, j4, par2, par3, flag1, iblockstate1, iblockstate2);
                                        }
                                    }
                                }
                            }
                        }

                        if (flag2)
                        {
                            break;
                        }
                    }
                }
            }
        }
    }

    protected boolean func_175793_a(IBlockState block, IBlockState block1)
    {
        return block.getBlock() == ModBlocks.darkStone ? true : (block.getBlock() == ModBlocks.darkDirt ? true : (block.getBlock() == ModBlocks.darkGrass ? true : (block.getBlock() == Blocks.hardened_clay ? true : (block.getBlock() == Blocks.stained_hardened_clay ? true : (block.getBlock() == ModBlocks.darkSandStone ? true : (block.getBlock() == Blocks.red_sandstone ? true : (block.getBlock() == Blocks.mycelium ? true : (block.getBlock() == Blocks.snow_layer ? true : (block.getBlock() == ModBlocks.darkSand || block.getBlock() == ModBlocks.darkGravel) && block1.getBlock().getMaterial() != Material.water))))))));
    }

    protected void func_180701_a(World world, int par2, int par3, int par4, int par5, ChunkPrimer chunkPrimer)
    {
        int i1 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(15) + 1) + 1);

        if (this.rand.nextInt(7) != 0)
        {
            i1 = 0;
        }

        for (int j1 = 0; j1 < i1; ++j1)
        {
            double d0 = (double)(par2 * 16 + this.rand.nextInt(16));
            double d1 = (double)this.rand.nextInt(this.rand.nextInt(120) + 8);
            double d2 = (double)(par3 * 16 + this.rand.nextInt(16));
            int k1 = 1;

            if (this.rand.nextInt(4) == 0)
            {
                this.func_180703_a(this.rand.nextLong(), par4, par5, chunkPrimer, d0, d1, d2);
                k1 += this.rand.nextInt(4);
            }

            for (int l1 = 0; l1 < k1; ++l1)
            {
                float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float f2 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

                if (this.rand.nextInt(10) == 0)
                {
                    f2 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
                }

                this.func_180702_a(this.rand.nextLong(), par4, par5, chunkPrimer, d0, d1, d2, f2, f, f1, 0, 0, 1.0D);
            }
        }
    }

    protected boolean isOceanBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ)
    {
        net.minecraft.block.Block block = data.getBlockState(x, y, z).getBlock();
        return block == ModFluids.darkWaterBlock;
    }

    //Exception biomes to make sure we generate like vanilla
    private boolean isExceptionBiome(net.minecraft.world.biome.BiomeGenBase biome)
    {
        if (biome == net.minecraft.world.biome.BiomeGenBase.beach) return true;// TODO: make beach biome
        if (biome == ModBiomes.darkDesert) return true;
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
    protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop, IBlockState state, IBlockState up)
    {
        net.minecraft.world.biome.BiomeGenBase biome = worldObj.getBiomeGenForCoords(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
        IBlockState top = biome.topBlock;
        IBlockState filler = biome.fillerBlock;

        if (this.func_175793_a(state, up) || state.getBlock() == top.getBlock() || state.getBlock() == filler.getBlock())
        {
            if (y < 10)
            {
                data.setBlockState(x, y, z, ModFluids.darkLavaBlock.getDefaultState());
            }
            else
            {
                data.setBlockState(x, y, z, Blocks.air.getDefaultState());

                if (up.getBlock() == ModBlocks.darkSand)
                {
                    data.setBlockState(x, y + 1, z, up.getValue(BlockDarkSand.VARIANT) == BlockDarkSand.EnumType.DARKRED_SAND ? ModBlocks.darkSandStone.getDefaultState() : ModBlocks.darkSandStone.getDefaultState());//TODO: make dark red sandstone
                }

                if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == filler.getBlock())
                {
                    data.setBlockState(x, y - 1, z, top.getBlock().getDefaultState());
                }
            }
        }
    }
	
}
