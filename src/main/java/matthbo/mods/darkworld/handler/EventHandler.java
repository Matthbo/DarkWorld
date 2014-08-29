package matthbo.mods.darkworld.handler;

import matthbo.mods.darkworld.init.ModAchievements;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModItems;
import matthbo.mods.darkworld.reference.Refs;
import matthbo.mods.darkworld.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class EventHandler {
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event){
		if(ConfigHandler.dev == true){
			event.player.addChatMessage(new ChatComponentTranslation(Refs.CHAT_LANGKEY_DEVMODE));
			event.player.addChatMessage(new ChatComponentTranslation("TODO: more achievements? | make peculiar armor | add more peculiar tools"));
		}
	}
	
	@SubscribeEvent
	public void onItemPickup(ItemPickupEvent event){
		LogHelper.info(Block.getBlockFromItem(event.pickedUp.getEntityItem().getItem()));
		if(Block.getBlockFromItem(event.pickedUp.getEntityItem().getItem()).equals(ModBlocks.peculiarCobbleStone)){
			
			event.player.addStat(ModAchievements.achPickupPCobbleStone, 1);
			
		}
	}
	
	@SubscribeEvent
	public void onSmelted(ItemSmeltedEvent event){
		if(event.smelting.getItem().equals(ModItems.peculiarDust)){
			
			event.player.addStat(ModAchievements.achSmeltPCobbleStone, 1);
			
		}
	}

}
