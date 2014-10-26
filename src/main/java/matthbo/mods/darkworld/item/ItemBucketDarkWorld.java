package matthbo.mods.darkworld.item;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import matthbo.mods.darkworld.init.ModFluids;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class ItemBucketDarkWorld extends ItemBucket {
	
	private Block isFull;

	public ItemBucketDarkWorld(Block block) {
		super(block);
		this.isFull = block;
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
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
	
	//---------------------[Shit starts to get fucked!]---------------------
	
	 	

	    /**
	     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	     */
	    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entity)
	    {
	        boolean flag = this.isFull == Blocks.air;
	        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, entity, flag);

	        if (movingobjectposition == null)
	        {
	            return itemStack;
	        }
	        else
	        {
	            FillBucketEvent event = new FillBucketEvent(entity, itemStack, world, movingobjectposition);
	            if (MinecraftForge.EVENT_BUS.post(event))
	            {
	                return itemStack;
	            }

	            if (event.getResult() == Event.Result.ALLOW)
	            {
	                if (entity.capabilities.isCreativeMode)
	                {
	                    return itemStack;
	                }

	                if (--itemStack.stackSize <= 0)
	                {
	                    return event.result;
	                }

	                if (!entity.inventory.addItemStackToInventory(event.result))
	                {
	                    entity.dropPlayerItemWithRandomChoice(event.result, false);
	                }

	                return itemStack;
	            }
	            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
	            {
	                int i = movingobjectposition.blockX;
	                int j = movingobjectposition.blockY;
	                int k = movingobjectposition.blockZ;

	                if (!world.canMineBlock(entity, i, j, k))
	                {
	                    return itemStack;
	                }

	                if (flag)
	                {
	                    if (!entity.canPlayerEdit(i, j, k, movingobjectposition.sideHit, itemStack))
	                    {
	                        return itemStack;
	                    }

	                    Material material = world.getBlock(i, j, k).getMaterial();
	                    int l = world.getBlockMetadata(i, j, k);

	                    if (material == Material.water && l == 0)
	                    {
	                        world.setBlockToAir(i, j, k);
	                        return this.func_150910_a(itemStack, entity, ModFluids.darkWaterBucket);
	                    }

	                    if (material == Material.lava && l == 0)
	                    {
	                        world.setBlockToAir(i, j, k);
	                        return this.func_150910_a(itemStack, entity, ModFluids.darkLavaBucket);
	                    }
	                }
	                else
	                {
	                    if (this.isFull == Blocks.air)
	                    {
	                        return new ItemStack(Items.bucket);
	                    }

	                    if (movingobjectposition.sideHit == 0)
	                    {
	                        --j;
	                    }

	                    if (movingobjectposition.sideHit == 1)
	                    {
	                        ++j;
	                    }

	                    if (movingobjectposition.sideHit == 2)
	                    {
	                        --k;
	                    }

	                    if (movingobjectposition.sideHit == 3)
	                    {
	                        ++k;
	                    }

	                    if (movingobjectposition.sideHit == 4)
	                    {
	                        --i;
	                    }

	                    if (movingobjectposition.sideHit == 5)
	                    {
	                        ++i;
	                    }

	                    if (!entity.canPlayerEdit(i, j, k, movingobjectposition.sideHit, itemStack))
	                    {
	                        return itemStack;
	                    }

	                    if (this.tryPlaceContainedLiquid(world, i, j, k) && !entity.capabilities.isCreativeMode)
	                    {
	                        return new ItemStack(Items.bucket);
	                    }
	                }
	            }

	            return itemStack;
	        }
	    }

	    private ItemStack func_150910_a(ItemStack itemStack, EntityPlayer entity, Item item)
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
	    public boolean tryPlaceContainedLiquid(World world, int x, int y, int z)
	    {
	        if (this.isFull == Blocks.air)
	        {
	            return false;
	        }
	        else
	        {
	            Material material = world.getBlock(x, y, z).getMaterial();
	            boolean flag = !material.isSolid();

	            if (!world.isAirBlock(x, y, z) && !flag)
	            {
	                return false;
	            }
	            else
	            {
	                if (world.provider.isHellWorld && this.isFull == Blocks.flowing_water)
	                {
	                    world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

	                    for (int l = 0; l < 8; ++l)
	                    {
	                        world.spawnParticle("largesmoke", (double)x + Math.random(), (double)y + Math.random(), (double)z + Math.random(), 0.0D, 0.0D, 0.0D);
	                    }
	                }
	                else
	                {
	                    if (!world.isRemote && flag && !material.isLiquid())
	                    {
	                        world.func_147480_a(x, y, z, true);
	                    }

	                    world.setBlock(x, y, z, this.isFull, 0, 3);
	                }

	                return true;
	            }
	        }
	    }

}
