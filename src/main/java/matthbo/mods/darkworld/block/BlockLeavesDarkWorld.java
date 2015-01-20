package matthbo.mods.darkworld.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import java.util.ArrayList;
import java.util.Random;

public abstract class BlockLeavesDarkWorld extends BlockLeaves implements IShearable{

	int[] field1;
	//@SideOnly(Side.CLIENT)
	protected IIcon[] field_150129_M = new IIcon[2];

	public BlockLeavesDarkWorld(){
		super();
		this.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
	}

	@Override
	public String getUnlocalizedName()
	{
		return String.format("tile.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

	@Override
	public boolean isOpaqueCube(){return false;}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess block, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	{
		Block block1 = block.getBlock(p_149646_2_, p_149646_3_, p_149646_4_);
		return !Minecraft.getMinecraft().gameSettings.fancyGraphics && block1 == this ? false : shouldSideBeRenderedOriginal(block, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRenderedOriginal(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	{
		return p_149646_5_ == 0 && this.minY > 0.0D ? true : (p_149646_5_ == 1 && this.maxY < 1.0D ? true : (p_149646_5_ == 2 && this.minZ > 0.0D ? true : (p_149646_5_ == 3 && this.maxZ < 1.0D ? true : (p_149646_5_ == 4 && this.minX > 0.0D ? true : (p_149646_5_ == 5 && this.maxX < 1.0D ? true : !p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_).isOpaqueCube())))));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor(){
		//return 16777215;
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int par1){
		//return 16777215;
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess Block, int x, int y, int z){
		return 16777215;
	}

	public void breakBlock(World world, int x, int y, int z, Block block, int par6)
	{
		byte b0 = 1;
		int i1 = b0 + 1;

		if (world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1))
		{
			for (int j1 = -b0; j1 <= b0; ++j1)
			{
				for (int k1 = -b0; k1 <= b0; ++k1)
				{
					for (int l1 = -b0; l1 <= b0; ++l1)
					{
						Block block1 = world.getBlock(x + j1, y + k1, z + l1);
						if (block1.isLeaves(world, x + j1, y + k1, z + l1))
						{
							block1.beginLeavesDecay(world, x + j1, y + k1, z + l1);
						}
					}
				}
			}
		}
	}

	//TODO: check if this works or wtf this actually is...
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (!world.isRemote)
		{
			int l = world.getBlockMetadata(x, y, z);

			if ((l & 8) != 0 && (l & 4) == 0)
			{
				byte b0 = 4;
				int i1 = b0 + 1;
				byte b1 = 32;
				int j1 = b1 * b1;
				int k1 = b1 / 2;

				if (this.field1 == null)
				{
					this.field1 = new int[b1 * b1 * b1];
				}

				int l1;

				if (world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1))
				{
					int i2;
					int j2;

					for (l1 = -b0; l1 <= b0; ++l1)
					{
						for (i2 = -b0; i2 <= b0; ++i2)
						{
							for (j2 = -b0; j2 <= b0; ++j2)
							{
								Block block = world.getBlock(x + l1, y + i2, z + j2);

								if (!block.canSustainLeaves(world, x + l1, y + i2, z + j2))
								{
									if (block.isLeaves(world, x + l1, y + i2, z + j2))
									{
										this.field1[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
									}
									else
									{
										this.field1[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
									}
								}
								else
								{
									this.field1[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
								}
							}
						}
					}

					for (l1 = 1; l1 <= 4; ++l1)
					{
						for (i2 = -b0; i2 <= b0; ++i2)
						{
							for (j2 = -b0; j2 <= b0; ++j2)
							{
								for (int k2 = -b0; k2 <= b0; ++k2)
								{
									if (this.field1[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1)
									{
										if (this.field1[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
										{
											this.field1[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
										}

										if (this.field1[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
										{
											this.field1[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
										}

										if (this.field1[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2)
										{
											this.field1[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;
										}

										if (this.field1[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2)
										{
											this.field1[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
										}

										if (this.field1[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] == -2)
										{
											this.field1[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] = l1;
										}

										if (this.field1[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2)
										{
											this.field1[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
										}
									}
								}
							}
						}
					}
				}

				l1 = this.field1[k1 * j1 + k1 * b1 + k1];

				if (l1 >= 0)
				{
					world.setBlockMetadataWithNotify(x, y, z, l & -9, 4);
				}
				else
				{
					this.removeLeaves(world, x, y, z);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		if (world.canLightningStrikeAt(x, y + 1, z) && !World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && rand.nextInt(15) == 1)
		{
			double d0 = (double)((float)x + rand.nextFloat());
			double d1 = (double)y - 0.05D;
			double d2 = (double)((float)z + rand.nextFloat());
			world.spawnParticle("dripWater", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	private void removeLeaves(World world, int x, int y, int z)
	{
		this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		world.setBlockToAir(x, y, z);
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random rand)
	{
		return rand.nextInt(20) == 0 ? 1 : 0;
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return Item.getItemFromBlock(Blocks.sapling);
	}

	protected void func_150124_c(World p_150124_1_, int p_150124_2_, int p_150124_3_, int p_150124_4_, int p_150124_5_, int p_150124_6_) {}

	protected int func_150123_b(int p_150123_1_)
	{
		return 20;
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
	public int damageDropped(int par1)
	{
		//return par1 & 3;
		return 0;
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@SideOnly(Side.CLIENT)
	public abstract IIcon getIcon(int p_149691_1_, int p_149691_2_);

	/**
	 * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
	 * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
	 */
	protected ItemStack createStackedBlock(int p_149644_1_)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, p_149644_1_ & 3);
	}

	public abstract String[] func_150125_e();


	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 3));
		return ret;
	}

	@Override
	public void beginLeavesDecay(World world, int x, int y, int z)
	{

		int i2 = world.getBlockMetadata(x, y, z);

		if ((i2 & 8) == 0)
		{
			world.setBlockMetadataWithNotify(x, y, z, i2 | 8, 4);
		}
		world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 4);
	}

	@Override
	public boolean isLeaves(IBlockAccess world, int x, int y, int z)
	{
		return true;
	}


	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		int chance = this.func_150123_b(metadata);

		if (fortune > 0)
		{
			chance -= 2 << fortune;
			if (chance < 10) chance = 10;
		}

		if (world.rand.nextInt(chance) == 0)
			ret.add(new ItemStack(this.getItemDropped(metadata, world.rand, fortune), 1, this.damageDropped(metadata)));

		chance = 200;
		if (fortune > 0)
		{
			chance -= 10 << fortune;
			if (chance < 40) chance = 40;
		}

		this.captureDrops(true);
		this.func_150124_c(world, x, y, z, metadata, chance); // Dammet mojang
		ret.addAll(this.captureDrops(false));
		return ret;
	}

}
