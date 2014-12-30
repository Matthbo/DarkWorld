package matthbo.mods.darkworld.biome;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeDarkSwamp extends DarkBiomeGenBase {

    public BiomeDarkSwamp(int id) {
        super(id);
        this.setColor(522674);
        this.setBiomeName("Swampland");
        this.func_76733_a(9154376);
        this.setHeight(height_PartiallySubmerged);
        this.setTemperatureRainfall(0.8F, 0.9F);

        //this.theBiomeDecorator.treesPerChunk = 2;
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
        //this.flowers.clear();
        //this.addFlower(Blocks.red_flower, 1, 10);
    }

    public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
    {
        return this.worldGeneratorSwamp;
    }

    //TODO: flower stuff
    public String func_150572_a(Random p_150572_1_, int p_150572_2_, int p_150572_3_, int p_150572_4_)
    {
        return BlockFlower.field_149859_a[1];
    }

    //TODO: check plant stuff
    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        double d1 = plantNoise.func_151601_a((double)p_150573_5_ * 0.25D, (double)p_150573_6_ * 0.25D);

        if (d1 > 0.0D)
        {
            int k = p_150573_5_ & 15;
            int l = p_150573_6_ & 15;
            int i1 = p_150573_3_.length / 256;

            for (int j1 = 255; j1 >= 0; --j1)
            {
                int k1 = (l * 16 + k) * i1 + j1;

                if (p_150573_3_[k1] == null || p_150573_3_[k1].getMaterial() != Material.air)
                {
                    if (j1 == 62 && p_150573_3_[k1] != Blocks.water)
                    {
                        p_150573_3_[k1] = Blocks.water;

                        if (d1 < 0.12D)
                        {
                            p_150573_3_[k1 + 1] = Blocks.waterlily;
                        }
                    }

                    break;
                }
            }
        }

        this.genBiomeTerrainDarkWorld(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }
}
