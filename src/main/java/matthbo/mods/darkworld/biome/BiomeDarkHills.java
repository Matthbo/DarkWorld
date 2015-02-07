package matthbo.mods.darkworld.biome;

import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenAbstractTree;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenTaiga2;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeDarkHills extends DarkBiomeGenBase {

    private WorldGenerator theWorldGenerator;
    private DarkWorldGenTaiga2 genTaiga2;
    private int field_150635_aE;
    private int field_150636_aF;
    private int field_150637_aG;
    private int field_150638_aH;

    public BiomeDarkHills(int id, boolean par2) {
        super(id);
        this.theWorldGenerator = new WorldGenMinable(Blocks.monster_egg.getDefaultState(), 8);
        this.genTaiga2 = new DarkWorldGenTaiga2(false);
        this.field_150635_aE = 0;
        this.field_150636_aF = 1;
        this.field_150637_aG = 2;
        this.field_150638_aH = this.field_150635_aE;

        if(par2){
            this.theDecorationHandler.treesPerChunk = 3;
            this.field_150638_aH = this.field_150636_aF;
        }
    }

    public DarkWorldGenAbstractTree genBigTreeChance(Random rand)
    {
        return (DarkWorldGenAbstractTree)(rand.nextInt(3) > 0 ? this.genTaiga2 : super.genBigTreeChance(rand));
    }

    public void decorate(World world, Random rand, BlockPos pos)
    {
        super.decorate(world, rand, pos);
        int i = 3 + rand.nextInt(6);
        int j;
        int k;
        int l;

        for (j = 0; j < i; ++j)
        {
            k = rand.nextInt(16);
            l = rand.nextInt(28) + 4;
            int i1 = rand.nextInt(16);
            BlockPos blockpos1 = pos.add(k, l, i1);

            if (world.getBlockState(blockpos1).getBlock().isReplaceableOreGen(world, blockpos1, net.minecraft.block.state.pattern.BlockHelper.forBlock(ModBlocks.darkStone)))
            {
                world.setBlockState(blockpos1, ModBlocks.darkEmeraldOre.getDefaultState(), 2);
            }
        }

        for (i = 0; i < 7; ++i)
        {
            j = rand.nextInt(16);
            k = rand.nextInt(64);
            l = rand.nextInt(16);
            this.theWorldGenerator.generate(world, rand, pos.add(j, k, l));
        }
    }

    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int x, int z, double par6)
    {
        this.topBlock = ModBlocks.darkGrass.getDefaultState();
        this.fillerBlock = ModBlocks.darkDirt.getDefaultState();

        if ((par6 < -1.0D || par6 > 2.0D) && this.field_150638_aH == this.field_150637_aG)
        {
            this.topBlock = ModBlocks.darkGravel.getDefaultState();
            this.fillerBlock = ModBlocks.darkGravel.getDefaultState();
        }
        else if (par6 > 1.0D && this.field_150638_aH != this.field_150636_aF)
        {
            this.topBlock = ModBlocks.darkStone.getDefaultState();
            this.fillerBlock = ModBlocks.darkStone.getDefaultState();
        }

        this.genBiomeTerrainDarkWorld(world, rand, chunkPrimer, x, z, par6);
    }

    /**
     * this creates a mutation specific to Hills biomes
     */
    public BiomeDarkHills mutateHills(BiomeGenBase p_150633_1_)
    {
        this.field_150638_aH = this.field_150637_aG;
        this.func_150557_a(p_150633_1_.color, true);
        this.setBiomeName(p_150633_1_.biomeName + " M");
        this.setHeight(new BiomeGenBase.Height(p_150633_1_.minHeight, p_150633_1_.maxHeight));
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
