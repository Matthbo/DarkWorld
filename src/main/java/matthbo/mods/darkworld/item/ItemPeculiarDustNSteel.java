package matthbo.mods.darkworld.item;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPeculiarDustNSteel extends ItemDarkWorld {
	
	public ItemPeculiarDustNSteel(){
		super();
		this.setUnlocalizedName("peculiardustnsteel");
		this.setMaxStackSize(1);
		this.setMaxDamage(35);
	}
	
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10){
		switch(side){
		case 0: y--;
		case 1: y++;
		case 2: z--;
		case 3: z++;
		case 4: x--;
		case 5: x++;
		}
		
		if(!player.canPlayerEdit(x, y, z, side, itemStack)){
			return false;
		}else{
			if(world.isAirBlock(x, y, z)){
				world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "fire.ignite", 1F, itemRand.nextFloat()*0.4F + 0.8F);
				world.setBlock(x, y, z, ModBlocks.darkFire);
			}
			
			itemStack.damageItem(1, player);
			return true;
		}
	}

}
