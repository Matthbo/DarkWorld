package matthbo.mods.darkworld.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockDarkGrass extends BlockDarkWorld implements IGrowable {

	@SideOnly(Side.CLIENT)
	private IIcon icons;
	@SideOnly(Side.CLIENT)
    private IIcon icons_b;
    @SideOnly(Side.CLIENT)
    private IIcon icons_M;
    //@SideOnly(Side.CLIENT)
    //private IIcon icons_N;
	
	public BlockDarkGrass() {
		super(Material.grass);
		this.setBlockName("darkgrass");
		this.setHardness(0.6F);
		this.setStepSound(soundTypeGrass);
		this.setTickRandomly(true);
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? this.icons_b : (side == 0 ? ModBlocks.darkDirt.getBlockTextureFromSide(side) : this.icons);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote)
        {
            if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2)
            {
                world.setBlock(x, y, z, ModBlocks.darkDirt);
            }
            else if (world.getBlockLightValue(x, y + 1, z) >= 9)
            {
                for (int l = 0; l < 4; ++l)
                {
                    int i1 = x + rand.nextInt(3) - 1;
                    int j1 = y + rand.nextInt(5) - 3;
                    int k1 = z + rand.nextInt(3) - 1;
                    Block block = world.getBlock(i1, j1 + 1, k1);

                    if (world.getBlock(i1, j1, k1) == ModBlocks.darkDirt && world.getBlockMetadata(i1, j1, k1) == 0 && world.getBlockLightValue(i1, j1 + 1, k1) >= 4 && world.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)
                    {
                        world.setBlock(i1, j1, k1, ModBlocks.darkGrass);
                    }
                }
            }
        }
    }

    public Item getItemDropped(int par1, Random rand, int par3)
    {
        return ModBlocks.darkDirt.getItemDropped(par1, rand, par3);
    }

    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
    {
        return true;
    }

    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int par5)
    {
        if (par5 == 1)
        {
            return this.icons_b;
        }
        else if (par5 == 0)
        {
            return ModBlocks.darkDirt.getBlockTextureFromSide(par5);
        }
        else
        {
            Material material = blockAccess.getBlock(x, y + 1, z).getMaterial();
            return material != Material.snow && material != Material.craftedSnow ? this.icons : this.icons_M;
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.icons = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_side");
        this.icons_b = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_top");
        this.icons_M = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_side_snowed");
        //this.icons_N = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_side_overlay");
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable){
        Block plant = plantable.getPlant(world, x, y + 1, z);
        if (plant == ModBlocks.darkTallGrass){return true;}
        return false;
    }

    /*@SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        double d0 = 0.5D;
        double d1 = 1.0D;
        return ColorizerGrass.getGrassColor(d0, d1);
    }*/

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    /*@SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_)
    {
        return this.getBlockColor();
    }*/

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    /*@SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
    {
        int l = 0;
        int i1 = 0;
        int j1 = 0;

        for (int k1 = -1; k1 <= 1; ++k1)
        {
            for (int l1 = -1; l1 <= 1; ++l1)
            {
                int i2 = blockAccess.getBiomeGenForCoords(x + l1, z + k1).getBiomeGrassColor(x + l1, y, z + k1);
                l += (i2 & 16711680) >> 16;
                i1 += (i2 & 65280) >> 8;
                j1 += i2 & 255;
            }
        }

        return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
    }*/

    public void func_149853_b(World world, Random rand, int x, int y, int z)
    {
        int l = 0;

        while (l < 128)
        {
            int i1 = x;
            int j1 = y + 1;
            int k1 = z;
            int l1 = 0;

            while (true)
            {
                if (l1 < l / 16)
                {
                    i1 += rand.nextInt(3) - 1;
                    j1 += (rand.nextInt(3) - 1) * rand.nextInt(3) / 2;
                    k1 += rand.nextInt(3) - 1;

                    if (world.getBlock(i1, j1 - 1, k1) == ModBlocks.darkGrass && !world.getBlock(i1, j1, k1).isNormalCube())
                    {
                        ++l1;
                        continue;
                    }
                }
                else if (world.getBlock(i1, j1, k1).getMaterial() == Material.air)
                {
                    if (rand.nextInt(8) != 0)
                    {
                        if (Blocks.tallgrass.canBlockStay(world, i1, j1, k1))//TODO: something with darkTallGrass...
                        {
                            world.setBlock(i1, j1, k1, Blocks.tallgrass, 1, 3);
                        }
                    }
                    else
                    {
                        world.getBiomeGenForCoords(i1, k1).plantFlower(world, rand, i1, j1, k1);
                    }
                }

                ++l;
                break;
            }
        }
    }
}
