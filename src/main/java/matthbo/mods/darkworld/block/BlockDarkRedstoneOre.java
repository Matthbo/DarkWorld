package matthbo.mods.darkworld.block;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.init.ModBlocks;

public class BlockDarkRedstoneOre extends BlockDarkWorld {

	private boolean lit;
	
	public BlockDarkRedstoneOre(boolean lit){
		super();
		
		this.setUnlocalizedName("darkredstoneore");
		this.setCreativeTab(null);
		this.setHardness(3.0F).setResistance(5.0F);
		this.setStepSound(soundTypePiston);
		
		if(lit){
			this.setTickRandomly(true);
		}
		this.lit = lit;
	}
	
	public int tickRate(World world)
    {
        return 30;
    }

    /**
     * Called when a player hits the block. Args: world, x, y, z, player
     */
    public void onBlockClicked(World world, BlockPos pos, EntityPlayer player)
    {
        this.changeBlock(world, pos);
        super.onBlockClicked(world, pos, player);
    }

    /**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
    {
        this.changeBlock(world, pos);
        super.onEntityCollidedWithBlock(world, pos, entity);
    }

    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        this.changeBlock(world, pos);
        return super.onBlockActivated(world, pos, state, playerIn, side, hitX, hitY, hitZ);
    }

    private void changeBlock(World world, BlockPos pos)
    {
        this.addParticles(world, pos);

        if (this == ModBlocks.darkRedstoneOre)
        {
            world.setBlockState(pos, ModBlocks.darkLitRedstoneOre.getDefaultState());
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (this == ModBlocks.darkLitRedstoneOre)
        {
            world.setBlockState(pos, ModBlocks.darkRedstoneOre.getDefaultState());
        }
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.redstone;
    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int par1, Random rand)
    {
        return this.quantityDropped(rand) + rand.nextInt(par1 + 1);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random rand)
    {
        return 4 + rand.nextInt(2);
    }

    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }

    /*private Random rand = new Random();
    @Override // World, meta, fortune
    public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_)
    {
        if (this.getItemDropped(p_149690_5_, rand, p_149690_7_) != Item.getItemFromBlock(this))
        {
            return 1 + rand.nextInt(5);
        }
        return 0;
    }*/

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (this.lit)
        {
            this.addParticles(world, pos);
        }
    }

    private void addParticles(World worldIn, BlockPos pos)
    {
        Random random = worldIn.rand;
        double d0 = 0.0625D;

        for (int i = 0; i < 6; ++i)
        {
            double d1 = (double)((float)pos.getX() + random.nextFloat());
            double d2 = (double)((float)pos.getY() + random.nextFloat());
            double d3 = (double)((float)pos.getZ() + random.nextFloat());

            if (i == 0 && !worldIn.getBlockState(pos.up()).getBlock().isOpaqueCube())
            {
                d2 = (double)pos.getY() + d0 + 1.0D;
            }

            if (i == 1 && !worldIn.getBlockState(pos.down()).getBlock().isOpaqueCube())
            {
                d2 = (double)pos.getY() - d0;
            }

            if (i == 2 && !worldIn.getBlockState(pos.south()).getBlock().isOpaqueCube())
            {
                d3 = (double)pos.getZ() + d0 + 1.0D;
            }

            if (i == 3 && !worldIn.getBlockState(pos.north()).getBlock().isOpaqueCube())
            {
                d3 = (double)pos.getZ() - d0;
            }

            if (i == 4 && !worldIn.getBlockState(pos.east()).getBlock().isOpaqueCube())
            {
                d1 = (double)pos.getX() + d0 + 1.0D;
            }

            if (i == 5 && !worldIn.getBlockState(pos.west()).getBlock().isOpaqueCube())
            {
                d1 = (double)pos.getX() - d0;
            }

            if (d1 < (double)pos.getX() || d1 > (double)(pos.getX() + 1) || d2 < 0.0D || d2 > (double)(pos.getY() + 1) || d3 < (double)pos.getZ() || d3 > (double)(pos.getZ() + 1))
            {
                worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d1, d2, d3, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(IBlockState state)
    {
        return new ItemStack(ModBlocks.darkRedstoneOre);
    }
	
}
