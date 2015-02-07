package matthbo.mods.darkworld.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHardPeculiarDust extends ItemDarkWorld {

	public ItemHardPeculiarDust(){
		super();
		this.setUnlocalizedName("hardpeculiardust");
		this.setMaxStackSize(16);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List info, boolean useExtraInformation){
		
		info.add(EnumChatFormatting.DARK_GREEN + "It Looks Pretty Stable!");
		
	}
	
}
