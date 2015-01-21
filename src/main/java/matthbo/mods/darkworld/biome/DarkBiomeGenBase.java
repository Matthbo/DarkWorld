package matthbo.mods.darkworld.biome;

import java.awt.Color;
import java.util.Random;

import matthbo.mods.darkworld.DarkWorld;
import matthbo.mods.darkworld.handler.BiomeDecoratorHandler;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModFluids;
import matthbo.mods.darkworld.world.DarkWorldGenerator;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenAbstractTree;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenBigTree;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenSwamp;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenTrees;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class DarkBiomeGenBase extends BiomeGenBase{


    public static final DarkBiomeGenBase.Height height_MidHills = BiomeGenBase.height_MidHills;
    public static final DarkBiomeGenBase.Height height_MidPlains = BiomeGenBase.height_MidPlains;

    protected DarkWorldGenTrees darkWorldGenTrees;
    protected DarkWorldGenBigTree darkWorldGenBigTree;
    protected DarkWorldGenSwamp darkWorldGenSwamp;

    public BiomeDecoratorHandler theDecorationHandler;

	public DarkBiomeGenBase(int id) {
		super(id);
		
		this.topBlock = ModBlocks.darkGrass;
		this.fillerBlock = ModBlocks.darkDirt;

        this.theDecorationHandler = new BiomeDecoratorHandler();

        this.darkWorldGenTrees = new DarkWorldGenTrees(false);
        this.darkWorldGenBigTree = new DarkWorldGenBigTree(false);
        this.darkWorldGenSwamp = new DarkWorldGenSwamp();
	}
	
	@Override
	public int getSkyColorByTemp(float p_76731_1_) {
		return Color.BLACK.getRGB();
	}
	
	public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        this.genBiomeTerrainDarkWorld(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }

    public DarkWorldGenAbstractTree func_150567_a(Random p_150567_1_)
    {
        return (DarkWorldGenAbstractTree)(p_150567_1_.nextInt(10) == 0 ? this.darkWorldGenBigTree : this.darkWorldGenTrees);
    }

	public void genBiomeTerrainDarkWorld(World p_150560_1_, Random p_150560_2_, Block[] p_150560_3_, byte[] p_150560_4_, int p_150560_5_, int p_150560_6_, double p_150560_7_)
    {
        boolean flag = true;
        Block block = this.topBlock;
        byte b0 = (byte)(this.field_150604_aj & 255);
        Block block1 = this.fillerBlock;
        int k = -1;
        int l = (int)(p_150560_7_ / 3.0D + 3.0D + p_150560_2_.nextDouble() * 0.25D);
        int i1 = p_150560_5_ & 15;
        int j1 = p_150560_6_ & 15;
        int k1 = p_150560_3_.length / 256;

        for (int l1 = 255; l1 >= 0; --l1)
        {
            int i2 = (j1 * 16 + i1) * k1 + l1;

            if (l1 <= 0 + p_150560_2_.nextInt(5))
            {
                p_150560_3_[i2] = Blocks.bedrock;
            }
            else
            {
                Block block2 = p_150560_3_[i2];

                if (block2 != null && block2.getMaterial() != Material.air)
                {
                    if (block2 == ModBlocks.darkStone)
                    {
                        if (k == -1)
                        {
                            if (l <= 0)
                            {
                                block = null;
                                b0 = 0;
                                block1 = ModBlocks.darkStone;
                            }
                            else if (l1 >= 59 && l1 <= 64)
                            {
                                block = this.topBlock;
                                b0 = (byte)(this.field_150604_aj & 255);
                                block1 = this.fillerBlock;
                            }

                            if (l1 < 63 && (block == null || block.getMaterial() == Material.air))
                            {
                                if (this.getFloatTemperature(p_150560_5_, l1, p_150560_6_) < 0.15F)
                                {
                                    block = Blocks.ice;
                                    b0 = 0;
                                }
                                else
                                {
                                    block = ModFluids.darkWaterBlock;
                                    b0 = 0;
                                }
                            }

                            k = l;

                            if (l1 >= 62)
                            {
                                p_150560_3_[i2] = block;
                                p_150560_4_[i2] = b0;
                            }
                            else if (l1 < 56 - l)
                            {
                                block = null;
                                block1 = ModBlocks.darkStone;
                                p_150560_3_[i2] = ModBlocks.darkGravel;
                            }
                            else
                            {
                                p_150560_3_[i2] = block1;
                            }
                        }
                        else if (k > 0)
                        {
                            --k;
                            p_150560_3_[i2] = block1;

                            if (k == 0 && block1 == ModBlocks.darkSand)
                            {
                                k = p_150560_2_.nextInt(4) + Math.max(0, l1 - 63);
                                block1 = ModBlocks.darkSandStone;
                            }
                        }
                    }
                }
                else
                {
                    k = -1;
                }
            }
        }
    }

}
