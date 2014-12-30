package matthbo.mods.darkworld.biome;/*parse("File Header.java")*/

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class BiomeDarkTaiga extends DarkBiomeGenBase{

    private static final WorldGenTaiga1 field_150639_aC = new WorldGenTaiga1();
    private static final WorldGenTaiga2 field_150640_aD = new WorldGenTaiga2(false);
    private static final WorldGenMegaPineTree field_150641_aE = new WorldGenMegaPineTree(false, false);
    private static final WorldGenMegaPineTree field_150642_aF = new WorldGenMegaPineTree(false, true);
    private static final WorldGenBlockBlob field_150643_aG = new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0);
    private int field_150644_aH;

    public BiomeDarkTaiga(int id, int par2) {
        super(id);
        this.field_150644_aH = par2;
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 8, 4, 4));
        //this.theBiomeDecorator.treesPerChunk = 10;

        if(par2 != 1 && par2 != 2){
            //this.theBiomeDecorator.grassPerChunk = 1;
            //this.theBiomeDecorator.mushroomsPerChunk = 1;
        }
        else{
            //this.theBiomeDecorator.grassPerChunk = 7;
            //this.theBiomeDecorator.deadBushPerChunk = 1;
            //this.theBiomeDecorator.mushroomsPerChunk = 3;
        }
    }

    public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
    {
        return (WorldGenAbstractTree)((this.field_150644_aH == 1 || this.field_150644_aH == 2) && p_150567_1_.nextInt(3) == 0 ? (this.field_150644_aH != 2 && p_150567_1_.nextInt(13) != 0 ? field_150641_aE : field_150642_aF) : (p_150567_1_.nextInt(3) == 0 ? field_150639_aC : field_150640_aD));
    }

    //TODO: something with flowers
    public void decorate(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
    {
        int k;
        int l;
        int i1;
        int j1;

        if (this.field_150644_aH == 1 || this.field_150644_aH == 2)
        {
            k = p_76728_2_.nextInt(3);

            for (l = 0; l < k; ++l)
            {
                i1 = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
                j1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
                int k1 = p_76728_1_.getHeightValue(i1, j1);
                field_150643_aG.generate(p_76728_1_, p_76728_2_, i1, k1, j1);
            }
        }

        genTallFlowers.func_150548_a(3);

        for (k = 0; k < 7; ++k)
        {
            l = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
            i1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
            j1 = p_76728_2_.nextInt(p_76728_1_.getHeightValue(l, i1) + 32);
            genTallFlowers.generate(p_76728_1_, p_76728_2_, l, j1, i1);
        }

        super.decorate(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
    }

    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        if (this.field_150644_aH == 1 || this.field_150644_aH == 2)
        {
            this.topBlock = ModBlocks.darkGrass;
            this.field_150604_aj = 0;
            this.fillerBlock = ModBlocks.darkDirt;

            if (p_150573_7_ > 1.75D)
            {
                this.topBlock = ModBlocks.darkDirt;
                this.field_150604_aj = 1;
            }
            else if (p_150573_7_ > -0.95D)
            {
                this.topBlock = ModBlocks.darkDirt;
                this.field_150604_aj = 2;
            }
        }

        this.genBiomeTerrainDarkWorld(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }

    //TODO: check this when all biomes are finished
    /*public BiomeGenBase createMutation()
    {
        return this.biomeID == BiomeGenBase.megaTaiga.biomeID ? (new BiomeGenTaiga(this.biomeID + 128, 2)).func_150557_a(5858897, true).setBiomeName("Mega Spruce Taiga").func_76733_a(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(new BiomeGenBase.Height(this.rootHeight, this.heightVariation)) : super.createMutation();
    }*/
}
