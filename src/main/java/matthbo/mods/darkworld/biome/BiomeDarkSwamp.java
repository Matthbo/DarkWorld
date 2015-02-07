package matthbo.mods.darkworld.biome;

import matthbo.mods.darkworld.init.ModFluids;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenAbstractTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeDarkSwamp extends DarkBiomeGenBase {

    public BiomeDarkSwamp(int id) {
        super(id);
        this.setColor(522674);
        this.setBiomeName("Swampland");
        this.setFillerBlockMetadata(9154376);
        this.setHeight(height_PartiallySubmerged);
        this.setTemperatureRainfall(0.8F, 0.9F);

        this.theDecorationHandler.treesPerChunk = 2;
        //this.theBiomeDecorator.flowersPerChunk = 1;
        //this.theBiomeDecorator.deadBushPerChunk = 1;
        //this.theBiomeDecorator.mushroomsPerChunk = 8;
        //this.theBiomeDecorator.reedsPerChunk = 10;
        //this.theBiomeDecorator.clayPerChunk = 1;
        //this.theBiomeDecorator.waterlilyPerChunk = 4;
        //this.theBiomeDecorator.sandPerChunk2 = 0;
        //this.theBiomeDecorator.sandPerChunk = 0;
        //this.theBiomeDecorator.grassPerChunk = 5;
        //this.waterColorMultiplier = 14745518;
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 1, 1, 1));
    }

    public DarkWorldGenAbstractTree genBigTreeChance(Random p_150567_1_)
    {
        return this.darkWorldGenSwamp;
    }

    //TODO: flower stuff
    public BlockFlower.EnumFlowerType pickRandomFlower(Random p_180623_1_, BlockPos p_180623_2_)
    {
        return BlockFlower.EnumFlowerType.BLUE_ORCHID;
    }

    //TODO: check plant stuff
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int x, int z, double par6)
    {
        double d1 = field_180281_af.func_151601_a((double)x * 0.25D, (double)z * 0.25D);

        if (d1 > 0.0D)
        {
            int k = x & 15;
            int l = z & 15;

            for (int i1 = 255; i1 >= 0; --i1)
            {
                if (chunkPrimer.getBlockState(l, i1, k).getBlock().getMaterial() != Material.air)
                {
                    if (i1 == 62 && chunkPrimer.getBlockState(l, i1, k).getBlock() != ModFluids.darkWaterBlock)
                    {
                        chunkPrimer.setBlockState(l, i1, k, ModFluids.darkWaterBlock.getDefaultState());

                        if (d1 < 0.12D)
                        {
                            chunkPrimer.setBlockState(l, i1 + 1, k, Blocks.waterlily.getDefaultState());
                        }
                    }

                    break;
                }
            }
        }

        this.genBiomeTerrainDarkWorld(world, rand, chunkPrimer, x, z, par6);
    }
}
