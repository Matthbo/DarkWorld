package matthbo.mods.darkworld.item;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemPeculiarDustNSteel extends ItemDarkWorld {
	
	public ItemPeculiarDustNSteel(){
		super();
		this.setUnlocalizedName("peculiardustnsteel");
		this.setMaxStackSize(1);
		this.setMaxDamage(35);
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		pos = pos.offset(side);

		if (!player.canPlayerEdit(pos, side, stack))
		{
			return false;
		}
		else
		{
			if (world.isAirBlock(pos))
			{
				world.playSoundEffect((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
				world.setBlockState(pos, ModBlocks.darkFire.getDefaultState());
			}

			stack.damageItem(1, player);
			return true;
		}
	}

}
