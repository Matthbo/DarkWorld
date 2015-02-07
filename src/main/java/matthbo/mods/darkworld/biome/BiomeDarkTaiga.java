package matthbo.mods.darkworld.biome;/*parse("File Header.java")*/

import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenAbstractTree;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenMegaPineTree;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenTaiga1;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenTaiga2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class BiomeDarkTaiga extends DarkBiomeGenBase{

    private static final DarkWorldGenTaiga1 field_150639_aC = new DarkWorldGenTaiga1();
    private static final DarkWorldGenTaiga2 field_150640_aD = new DarkWorldGenTaiga2(false);
    private static final DarkWorldGenMegaPineTree field_150641_aE = new DarkWorldGenMegaPineTree(false, false);
    private static final DarkWorldGenMegaPineTree field_150642_aF = new DarkWorldGenMegaPineTree(false, true);
    private static final WorldGenBlockBlob field_150643_aG = new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0); //TODO: WTF... make DarkWorldGenBlockBlob if needed, wtf would ye....
    private int field_150644_aH;

    public BiomeDarkTaiga(int id, int par2) {
        super(id);
        this.field_150644_aH = par2;
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 8, 4, 4));
        this.theDecorationHandler.treesPerChunk = 10;

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

    public DarkWorldGenAbstractTree genBigTreeChance(Random p_150567_1_)
    {
        return (DarkWorldGenAbstractTree)((this.field_150644_aH == 1 || this.field_150644_aH == 2) && p_150567_1_.nextInt(3) == 0 ? (this.field_150644_aH != 2 && p_150567_1_.nextInt(13) != 0 ? field_150641_aE : field_150642_aF) : (p_150567_1_.nextInt(3) == 0 ? field_150639_aC : field_150640_aD));
    }

    //TODO: something with flowers
    public void decorate(World worldIn, Random p_180624_2_, BlockPos p_180624_3_)
    {
        int i;
        int j;
        int k;
        int l;

        if (this.field_150644_aH == 1 || this.field_150644_aH == 2)
        {
            i = p_180624_2_.nextInt(3);

            for (j = 0; j < i; ++j)
            {
                k = p_180624_2_.nextInt(16) + 8;
                l = p_180624_2_.nextInt(16) + 8;
                BlockPos blockpos1 = worldIn.getHorizon(p_180624_3_.add(k, 0, l));
                field_150643_aG.generate(worldIn, p_180624_2_, blockpos1);
            }
        }

        DOUBLE_PLANT_GENERATOR.func_180710_a(BlockDoublePlant.EnumPlantType.FERN);

        for (i = 0; i < 7; ++i)
        {
            j = p_180624_2_.nextInt(16) + 8;
            k = p_180624_2_.nextInt(16) + 8;
            l = p_180624_2_.nextInt(worldIn.getHorizon(p_180624_3_.add(j, 0, k)).getY() + 32);
            DOUBLE_PLANT_GENERATOR.generate(worldIn, p_180624_2_, p_180624_3_.add(j, l, k));
        }

        super.decorate(worldIn, p_180624_2_, p_180624_3_);
    }

    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int x, int z, double par6)
    {
        if (this.field_150644_aH == 1 || this.field_150644_aH == 2)
        {
            this.topBlock = ModBlocks.darkGrass.getDefaultState();
            this.fillerBlock = ModBlocks.darkDirt.getDefaultState();

            if (par6 > 1.75D)
            {
                this.topBlock = ModBlocks.darkDirt.getDefaultState();
            }
            else if (par6 > -0.95D)
            {
                this.topBlock = ModBlocks.darkDirt.getDefaultState();
            }
        }

        this.genBiomeTerrainDarkWorld(world, rand, chunkPrimer, x, z, par6);
    }

    //TODO: check this when all biomes are finished
    /*public BiomeGenBase createMutation()
    {
        return this.biomeID == BiomeGenBase.megaTaiga.biomeID ? (new BiomeGenTaiga(this.biomeID + 128, 2)).func_150557_a(5858897, true).setBiomeName("Mega Spruce Taiga").func_76733_a(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(new BiomeGenBase.Height(this.rootHeight, this.heightVariation)) : super.createMutation();
    }*/
}
