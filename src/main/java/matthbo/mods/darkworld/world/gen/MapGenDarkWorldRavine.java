package matthbo.mods.darkworld.world.gen;

import matthbo.mods.darkworld.init.ModBiomes;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModFluids;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenRavine;

public class MapGenDarkWorldRavine extends MapGenRavine {
	
	protected boolean isOceanBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ)
    {
        return data[index] == ModFluids.darkWaterBlock;
    }

    //Exception biomes to make sure we generate like vanilla
    private boolean isExceptionBiome(BiomeGenBase biome)
    {
        //if (biome == BiomeGenBase.mushroomIsland) return true;
        //if (biome == BiomeGenBase.beach) return true;
        if (biome == ModBiomes.darkDesert) return true;
        return false;
    }

    //Determine if the block at the specified location is the top block for the biome, we take into account
    //Vanilla bugs to make sure that we generate the map the same way vanilla does.
    private boolean isTopBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ)
    {
        BiomeGenBase biome = worldObj.getBiomeGenForCoords(x + chunkX * 16, z + chunkZ * 16);
        return (isExceptionBiome(biome) ? data[index] == ModBlocks.darkGrass : data[index] == biome.topBlock);
    }
    
    protected void digBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop)
    {
        BiomeGenBase biome = worldObj.getBiomeGenForCoords(x + chunkX * 16, z + chunkZ * 16);
        Block top    = (isExceptionBiome(biome) ? ModBlocks.darkGrass : biome.topBlock);
        Block filler = (isExceptionBiome(biome) ? ModBlocks.darkDirt  : biome.fillerBlock);
        Block block  = data[index];

        if (block == ModBlocks.darkStone || block == filler || block == top)
        {
            if (y < 10)
            {
                data[index] = ModFluids.darkLavaBlock;
            }
            else
            {
                data[index] = null;

                if (foundTop && data[index - 1] == filler)
                {
                    data[index - 1] = top;
                }
            }
        }
    }

}
