package matthbo.mods.darkworld.biome;

import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenAbstractTree;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenCanopyTree;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenForest;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenMutated;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;

import java.util.Random;

public class BiomeDarkForest extends DarkBiomeGenBase{

    private int field_150632_aF;
    protected static final DarkWorldGenForest field_150629_aC = new DarkWorldGenForest(false, true);
    protected static final DarkWorldGenForest field_150630_aD = new DarkWorldGenForest(false, false);
    protected static final DarkWorldGenCanopyTree field_150631_aE = new DarkWorldGenCanopyTree(false);

    public BiomeDarkForest(int id, int par2) {
        super(id);
        this.field_150632_aF = par2;
        this.theDecorationHandler.treesPerChunk = 10;
        //this.theBiomeDecorator.grassPerChunk = 2;

        if(this.field_150632_aF == 1){
            this.theDecorationHandler.treesPerChunk = 6;
            //this.theBiomeDecorator.flowersPerChunk = 100;
            //this.theBiomeDecorator.grassPerChunk = 1;
        }

        this.setFillerBlockMetadata(5159473);
        this.setTemperatureRainfall(0.7F, 0.8F);

        if(this.field_150632_aF == 2){
            this.field_150609_ah = 353825;
            this.color = 3175492;
            this.setTemperatureRainfall(0.6F, 0.6F);
        }

        if(this.field_150632_aF == 0){
            this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 5, 4, 4));
        }

        if(this.field_150632_aF == 3){
            this.theDecorationHandler.treesPerChunk = -999;
        }

        if(this.field_150632_aF == 1){
            this.flowers.clear();
            /*for (int x = 0; x < BlockFlower.field_149859_a.length; x++)
            {
                this.addFlower(Blocks.red_flower, x == 1 ? 0 : x, 10);
            }*/
        }
    }

    public BiomeGenBase func_150557_a(int p_150557_1_, boolean p_150557_2_)
    {
        if (this.field_150632_aF == 2)
        {
            this.field_150609_ah = 353825;
            this.color = p_150557_1_;

            if (p_150557_2_)
            {
                this.field_150609_ah = (this.field_150609_ah & 16711422) >> 1;
            }

            return this;
        }
        else
        {
            return super.func_150557_a(p_150557_1_, p_150557_2_);
        }
    }

    public DarkWorldGenAbstractTree genBigTreeChance(Random rand)
    {
        return (DarkWorldGenAbstractTree)(this.field_150632_aF == 3 && rand.nextInt(3) > 0 ? field_150631_aE : (this.field_150632_aF != 2 && rand.nextInt(5) != 0 ? this.darkWorldGenTrees : field_150630_aD));
    }

    //TODO: check what this is
    public BlockFlower.EnumFlowerType pickRandomFlower(Random p_180623_1_, BlockPos p_180623_2_)
    {
        if (this.field_150632_aF == 1)
        {
            double d0 = MathHelper.clamp_double((1.0D + field_180281_af.func_151601_a((double)p_180623_2_.getX() / 48.0D, (double)p_180623_2_.getZ() / 48.0D)) / 2.0D, 0.0D, 0.9999D);
            BlockFlower.EnumFlowerType enumflowertype = BlockFlower.EnumFlowerType.values()[(int)(d0 * (double)BlockFlower.EnumFlowerType.values().length)];
            return enumflowertype == BlockFlower.EnumFlowerType.BLUE_ORCHID ? BlockFlower.EnumFlowerType.POPPY : enumflowertype;
        }
        else
        {
            return super.pickRandomFlower(p_180623_1_, p_180623_2_);
        }
    }

    // TODO: something with dark big mushrooms & flowers
    public void decorate(World worldIn, Random p_180624_2_, BlockPos p_180624_3_)
    {
        int i;
        int j;
        int k;
        int l;

        if (this.field_150632_aF == 3)
        {
            for (i = 0; i < 4; ++i)
            {
                for (j = 0; j < 4; ++j)
                {
                    k = i * 4 + 1 + 8 + p_180624_2_.nextInt(3);
                    l = j * 4 + 1 + 8 + p_180624_2_.nextInt(3);
                    BlockPos blockpos1 = worldIn.getHorizon(p_180624_3_.add(k, 0, l));

                    if (p_180624_2_.nextInt(20) == 0)
                    {
                        WorldGenBigMushroom worldgenbigmushroom = new WorldGenBigMushroom();
                        worldgenbigmushroom.generate(worldIn, p_180624_2_, blockpos1);
                    }
                    else
                    {
                        WorldGenAbstractTree worldgenabstracttree = this.genBigTreeChance(p_180624_2_);
                        worldgenabstracttree.func_175904_e();

                        if (worldgenabstracttree.generate(worldIn, p_180624_2_, blockpos1))
                        {
                            worldgenabstracttree.func_180711_a(worldIn, p_180624_2_, blockpos1);
                        }
                    }
                }
            }
        }

        i = p_180624_2_.nextInt(5) - 3;

        if (this.field_150632_aF == 1)
        {
            i += 2;
        }

        j = 0;

        while (j < i)
        {
            k = p_180624_2_.nextInt(3);

            if (k == 0)
            {
                DOUBLE_PLANT_GENERATOR.func_180710_a(BlockDoublePlant.EnumPlantType.SYRINGA);
            }
            else if (k == 1)
            {
                DOUBLE_PLANT_GENERATOR.func_180710_a(BlockDoublePlant.EnumPlantType.ROSE);
            }
            else if (k == 2)
            {
                DOUBLE_PLANT_GENERATOR.func_180710_a(BlockDoublePlant.EnumPlantType.PAEONIA);
            }

            l = 0;

            while (true)
            {
                if (l < 5)
                {
                    int j1 = p_180624_2_.nextInt(16) + 8;
                    int k1 = p_180624_2_.nextInt(16) + 8;
                    int i1 = p_180624_2_.nextInt(worldIn.getHorizon(p_180624_3_.add(j1, 0, k1)).getY() + 32);

                    if (!DOUBLE_PLANT_GENERATOR.generate(worldIn, p_180624_2_, new BlockPos(p_180624_3_.getX() + j1, i1, p_180624_3_.getZ() + k1)))
                    {
                        ++l;
                        continue;
                    }
                }

                ++j;
                break;
            }
        }

        super.decorate(worldIn, p_180624_2_, p_180624_3_);
    }

    /**
     * Creates a mutated version of the biome and places it into the biomeList with an index equal to the original plus
     * 128
     */
    public BiomeGenBase createMutatedBiome(final int p_180277_1_)
    {
        if (this.biomeID == BiomeGenBase.forest.biomeID)
        {
            BiomeGenForest biomegenforest = new BiomeGenForest(p_180277_1_, 1);
            biomegenforest.setHeight(new BiomeGenBase.Height(this.minHeight, this.maxHeight + 0.2F));
            biomegenforest.setBiomeName("Flower Forest");
            biomegenforest.func_150557_a(6976549, true);
            biomegenforest.setFillerBlockMetadata(8233509);
            return biomegenforest;
        }
        else
        {
            return this.biomeID != BiomeGenBase.birchForest.biomeID && this.biomeID != BiomeGenBase.birchForestHills.biomeID ? new BiomeGenMutated(p_180277_1_, this)
            {
                public void decorate(World worldIn, Random p_180624_2_, BlockPos p_180624_3_)
                {
                    this.baseBiome.decorate(worldIn, p_180624_2_, p_180624_3_);
                }
            }: new BiomeGenMutated(p_180277_1_, this)
            {
                public WorldGenAbstractTree genBigTreeChance(Random p_150567_1_)
                {
                    return p_150567_1_.nextBoolean() ? BiomeDarkForest.field_150629_aC : BiomeDarkForest.field_150630_aD;
                }
            };
        }
    }
}
