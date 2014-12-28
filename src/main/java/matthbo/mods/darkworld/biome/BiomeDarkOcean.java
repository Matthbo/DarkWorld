package matthbo.mods.darkworld.biome;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

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

    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        super.genTerrainBlocks(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }
}
