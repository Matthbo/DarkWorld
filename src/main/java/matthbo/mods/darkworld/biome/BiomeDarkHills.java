package matthbo.mods.darkworld.biome;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeDarkHills extends DarkBiomeGenBase {

    private WorldGenerator theWorldGenerator;
    private WorldGenTaiga2 genTaiga2;
    private int field_150635_aE;
    private int field_150636_aF;
    private int field_150637_aG;
    private int field_150638_aH;

    public BiomeDarkHills(int id, boolean par2) {
        super(id);
        this.theWorldGenerator = new WorldGenMinable(Blocks.monster_egg, 8);
        this.genTaiga2 = new WorldGenTaiga2(false);
        this.field_150635_aE = 0;
        this.field_150636_aF = 1;
        this.field_150637_aG = 2;
        this.field_150638_aH = this.field_150635_aE;

        if(par2){
            // set trees p chunk to 3
            this.field_150638_aH = this.field_150636_aF;
        }
    }

    public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
    {
        return (WorldGenAbstractTree)(p_150567_1_.nextInt(3) > 0 ? this.genTaiga2 : super.func_150567_a(p_150567_1_));
    }

    public void decorate(World world, Random rand, int par3, int par4)
    {
        super.decorate(world, rand, par3, par4);
        int k = 3 + rand.nextInt(6);
        int l;
        int i1;
        int j1;

        for (l = 0; l < k; ++l)
        {
            i1 = par3 + rand.nextInt(16);
            j1 = rand.nextInt(28) + 4;
            int k1 = par4 + rand.nextInt(16);

            if (world.getBlock(i1, j1, k1).isReplaceableOreGen(world, i1, j1, k1, ModBlocks.darkStone))
            {
                world.setBlock(i1, j1, k1, ModBlocks.darkEmeraldOre, 0, 2);
            }
        }

        for (k = 0; k < 7; ++k)
        {
            l = par3 + rand.nextInt(16);
            i1 = rand.nextInt(64);
            j1 = par4 + rand.nextInt(16);
            this.theWorldGenerator.generate(world, rand, l, i1, j1);
        }
    }

    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        this.topBlock = ModBlocks.darkGrass;
        this.field_150604_aj = 0;
        this.fillerBlock = ModBlocks.darkDirt;

        if ((p_150573_7_ < -1.0D || p_150573_7_ > 2.0D) && this.field_150638_aH == this.field_150637_aG)
        {
            this.topBlock = ModBlocks.darkGravel;
            this.fillerBlock = ModBlocks.darkGravel;
        }
        else if (p_150573_7_ > 1.0D && this.field_150638_aH != this.field_150636_aF)
        {
            this.topBlock = ModBlocks.darkStone;
            this.fillerBlock = ModBlocks.darkStone;
        }

        this.genBiomeTerrainDarkWorld(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }

    /**
     * this creates a mutation specific to Hills biomes
     */
    public BiomeDarkHills mutateHills(BiomeGenBase p_150633_1_)
    {
        this.field_150638_aH = this.field_150637_aG;
        this.func_150557_a(p_150633_1_.color, true);
        this.setBiomeName(p_150633_1_.biomeName + " M");
        this.setHeight(new BiomeGenBase.Height(p_150633_1_.rootHeight, p_150633_1_.heightVariation));
        this.setTemperatureRainfall(p_150633_1_.temperature, p_150633_1_.rainfall);
        return this;
    }

    /**
     * Creates a mutated version of the biome and places it into the biomeList with an index equal to the original plus
     * 128
     */
    public BiomeGenBase createMutation()
    {
        return (new BiomeDarkHills(this.biomeID + 128, false)).mutateHills(this);
    }
}
