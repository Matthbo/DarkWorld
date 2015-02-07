package matthbo.mods.darkworld.item;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import matthbo.mods.darkworld.init.ModFluids;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemBucketDarkWorld extends ItemBucket {
	
	private Block isFull;

	public ItemBucketDarkWorld(Block block) {
		super(block);
		this.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
		this.setContainerItem(Items.bucket);
		
	}
	
	@Override
	public String getUnlocalizedName(){
		return String.format("item.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack){
		return String.format("item.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
	
	//---------------------[Shit starts to get fucked!]---------------------
	
	 	

	    /**
	     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	     */
	    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
	    {
	    	if(this instanceof ItemDarkWaterBucket) this.isFull = ModFluids.darkWaterBlock;
			if(this instanceof ItemDarkLavaBucket) this.isFull = ModFluids.darkLavaBlock;
			boolean flag = this.isFull == Blocks.air;
			MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(worldIn, playerIn, flag);

			if (movingobjectposition == null)
			{
				return itemStackIn;
			}
			else
			{
				ItemStack ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, itemStackIn, movingobjectposition);
				if (ret != null) return ret;

				if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
				{
					BlockPos blockpos = movingobjectposition.getBlockPos();

					if (!worldIn.isBlockModifiable(playerIn, blockpos))
					{
						return itemStackIn;
					}

					if (flag)
					{
						if (!playerIn.canPlayerEdit(blockpos.offset(movingobjectposition.sideHit), movingobjectposition.sideHit, itemStackIn))
						{
							return itemStackIn;
						}

						IBlockState iblockstate = worldIn.getBlockState(blockpos);
						Material material = iblockstate.getBlock().getMaterial();

						if (material == Material.water && ((Integer)iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0)
						{
							worldIn.setBlockToAir(blockpos);
							playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
							return this.fillBucket(itemStackIn, playerIn, ModFluids.darkWaterBucket);
						}

						if (material == Material.lava && ((Integer)iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0)
						{
							worldIn.setBlockToAir(blockpos);
							playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
							return this.fillBucket(itemStackIn, playerIn, ModFluids.darkLavaBucket);
						}
					}
					else
					{
						if (this.isFull == Blocks.air)
						{
							return new ItemStack(Items.bucket);
						}

						BlockPos blockpos1 = blockpos.offset(movingobjectposition.sideHit);

						if (!playerIn.canPlayerEdit(blockpos1, movingobjectposition.sideHit, itemStackIn))
						{
							return itemStackIn;
						}

						if (this.tryPlaceContainedLiquid(worldIn, blockpos1) && !playerIn.capabilities.isCreativeMode)
						{
							playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
							return new ItemStack(Items.bucket);
						}
					}
				}

				return itemStackIn;
			}
	    }

	private ItemStack fillBucket(ItemStack itemStack, EntityPlayer entity, Item item)
	{
	    if (entity.capabilities.isCreativeMode)
		{
	           return itemStack;
	        }
	        else if (--itemStack.stackSize <= 0)
	        {
	            return new ItemStack(item);
	        }
	        else
	        {
	            if (!entity.inventory.addItemStackToInventory(new ItemStack(item)))
	            {
	                entity.dropPlayerItemWithRandomChoice(new ItemStack(item, 1, 0), false);
	            }

	            return itemStack;
	        }
	    }

	    /**
	     * Attempts to place the liquid contained inside the bucket.
	     */
		public boolean tryPlaceContainedLiquid(World worldIn, BlockPos pos)
		{
			if (this.isFull == Blocks.air)
			{
				return false;
			}
			else
			{
				Material material = worldIn.getBlockState(pos).getBlock().getMaterial();
				boolean flag = !material.isSolid();

				if (!worldIn.isAirBlock(pos) && !flag)
				{
					return false;
				}
				else
				{
					if (worldIn.provider.doesWaterVaporize() && this.isFull == ModFluids.darkWaterBlock)
					{
						int i = pos.getX();
						int j = pos.getY();
						int k = pos.getZ();
						worldIn.playSoundEffect((double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), "random.fizz", 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

						for (int l = 0; l < 8; ++l)
						{
							worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
						}
					}
					else
					{
						if (!worldIn.isRemote && flag && !material.isLiquid())
						{
							worldIn.destroyBlock(pos, true);
						}

						worldIn.setBlockState(pos, this.isFull.getDefaultState(), 3);
					}

					return true;
				}
			}
		}

}
