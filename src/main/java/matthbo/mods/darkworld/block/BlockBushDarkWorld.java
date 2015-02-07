package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class BlockBushDarkWorld extends BlockBush implements IPlantable{
	
	public BlockBushDarkWorld(Material material){
        super(material);
        this.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
    }

    public BlockBushDarkWorld(){
        super();
        this.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        return super.canPlaceBlockAt(world, pos) && this.canBlockStay(world, pos);
    }

    /**
     * is the block grass, dirt or farmland
     */
    protected boolean canPlaceBlockOn(Block block)
    {
        return block == ModBlocks.darkGrass || block == ModBlocks.darkDirt;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block)
    {
        super.onNeighborBlockChange(world, pos, state, block);
        this.checkAndDropBlock(world, pos, state);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, BlockPos pos, IBlockState state ,Random rand)
    {
        this.checkAndDropBlock(world, pos, state);
    }

    /**
     * checks if the block can stay, if not drop as item
     */
    protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(world, pos, state))
        {
            this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
            world.setBlockState(pos, Blocks.air.getDefaultState(), 3);
        }
    }

    public boolean canBlockStay(World world, BlockPos pos)
    {
        return  this.canSustainPlant(world, pos.down(), EnumFacing.UP, this);
    }

    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable){
        Block block = world.getBlockState(pos).getBlock();
        if(block == ModBlocks.darkGrass || block == ModBlocks.darkDirt){
            return true;
        }
        return false;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {
        return null;
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
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean isFullCube()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 1;
    }

    /*@Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        if (this == Blocks.wheat)          return Crop;
        if (this == Blocks.carrots)        return Crop;
        if (this == Blocks.potatoes)       return Crop;
        if (this == Blocks.melon_stem)     return Crop;
        if (this == Blocks.pumpkin_stem)   return Crop;
        if (this == Blocks.deadbush)       return Desert;
        if (this == Blocks.waterlily)      return Water;
        if (this == Blocks.red_mushroom)   return Cave;
        if (this == Blocks.brown_mushroom) return Cave;
        if (this == Blocks.nether_wart)    return Nether;
        if (this == Blocks.sapling)        return Plains;
        if (this == Blocks.tallgrass)      return Plains;
        if (this == Blocks.double_plant)   return Plains;
        if (this == Blocks.red_flower)     return Plains;
        if (this == Blocks.yellow_flower)  return Plains;
        return Plains;
    }*/

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        if(state.getBlock() != this) return getDefaultState();
        return state;
    }
}
