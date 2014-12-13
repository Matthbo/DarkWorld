package matthbo.mods.darkworld.item;

import java.awt.Color;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPeculiarDust extends ItemDarkWorld {
	
	public ItemPeculiarDust(){
		super();
		this.setUnlocalizedName("peculiardust");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List info, boolean useExtraInformation){
		
		info.add("Crumbled Peculiar Stone");
		info.add(EnumChatFormatting.DARK_RED + "It Looks Very Unstable!");
		
	}

}
