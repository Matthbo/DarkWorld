package matthbo.mods.darkworld.biome;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

public class BiomeDarkOcean extends DarkBiomeGenBase {
    public BiomeDarkOcean(int id) {
        super(id);
        this.spawnableCreatureList.clear();
        this.setColor(112);
        this.setBiomeName("Dark Ocean");
        this.setHeight(height_Oceans);
    }

    public BiomeGenBase.TempCategory getTempCategory()
    {
        return BiomeGenBase.TempCategory.OCEAN;
    }

    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int x, int z, double par6)
    {
        super.genTerrainBlocks(world, rand, chunkPrimer, x, z, par6);
    }
}
