package matthbo.mods.darkworld.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.reference.EnumDarkWorld;
import matthbo.mods.darkworld.block.BlockDarkWorld;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockDarkCactus extends BlockDarkWorld implements IPlantable {

	@SideOnly(Side.CLIENT)
    private IIcon icons;
    @SideOnly(Side.CLIENT)
    private IIcon icons2;
	
	public BlockDarkCactus() {
		super(Material.cactus);
		
		this.setBlockName("darkcactus");
		this.setHardness(0.4F);
		this.setStepSound(soundTypeCloth);
		this.setTickRandomly(true);
	}
	
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        if (p_149674_1_.isAirBlock(p_149674_2_, p_149674_3_ + 1, p_149674_4_))
        {
            int l;

            for (l = 1; p_149674_1_.getBlock(p_149674_2_, p_149674_3_ - l, p_149674_4_) == this; ++l)
            {
                ;
            }

            if (l < 3)
            {
                int i1 = p_149674_1_.getBlockMetadata(p_149674_2_, p_149674_3_, p_149674_4_);

                if (i1 == 15)
                {
                    p_149674_1_.setBlock(p_149674_2_, p_149674_3_ + 1, p_149674_4_, this);
                    p_149674_1_.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_, p_149674_4_, 0, 4);
                    this.onNeighborBlockChange(p_149674_1_, p_149674_2_, p_149674_3_ + 1, p_149674_4_, this);
                }
                else
                {
                    p_149674_1_.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_, p_149674_4_, i1 + 1, 4);
                }
            }
        }
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox((double)((float)p_149668_2_ + f), (double)p_149668_3_, (double)((float)p_149668_4_ + f), (double)((float)(p_149668_2_ + 1) - f), (double)((float)(p_149668_3_ + 1) - f), (double)((float)(p_149668_4_ + 1) - f));
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_)
    {
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox((double)((float)p_149633_2_ + f), (double)p_149633_3_, (double)((float)p_149633_4_ + f), (double)((float)(p_149633_2_ + 1) - f), (double)(p_149633_3_ + 1), (double)((float)(p_149633_4_ + 1) - f));
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_1_ == 1 ? this.icons : (p_149691_1_ == 0 ? this.icons2 : this.blockIcon);
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 13;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return !super.canPlaceBlockAt(world, x, y, z) ? false : this.canBlockStay(world, x, y, z);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        if (!this.canBlockStay(world, x, y, z))
        {
            world.func_147480_a(x, y, z, true);
        }
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World world, int x, int y, int z)
    {
    	LogHelper.info("Checking!!!!! <-----------");
    	if (world.getBlock(x - 1, y, z).getMaterial().isSolid()){
            return false;
        }
        else if (world.getBlock(x + 1, y, z).getMaterial().isSolid()){
            return false;
        }
        else if (world.getBlock(x, y, z - 1).getMaterial().isSolid()){
            return false;
        }
        else if (world.getBlock(x, y, z + 1).getMaterial().isSolid()){
            return false;
        }
    	else{
    		Block block = world.getBlock(x, y -1, z);
    		Block soil = ModBlocks.darkSand;
    		return (block != Blocks.air && (block == soil || block == this) && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this));
    	}
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        entity.attackEntityFrom(DamageSource.cactus, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_side");
        this.icons = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_top");
        this.icons2 = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_bottom");
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        //return EnumPlantType.Desert;
    	return EnumDarkWorld.DarkDesertPlant;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z)
    {
        return this;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
    {
        return -1;
    }

}
