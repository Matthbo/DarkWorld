package matthbo.mods.darkworld.handler;

import java.util.HashMap;
import java.util.Map;

import matthbo.mods.darkworld.init.ModAchievements;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModItems;
import matthbo.mods.darkworld.reference.Refs;
import matthbo.mods.darkworld.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;


public class EventHandler {
	
	public static EventHandler INSTANCE = new EventHandler();
	
	public EventHandler(){}
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event){
		if(ConfigHandler.dev == true){
			event.player.addChatMessage(new ChatComponentTranslation(Refs.CHAT_LANGKEY_DEVMODE));
			event.player.addChatMessage(new ChatComponentTranslation("TODO: biomes, blocks"));
		}
	}
	
	@SubscribeEvent
	public void onItemPickup(ItemPickupEvent event){
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
	
	@SubscribeEvent
	public void onCrafted(ItemCraftedEvent event){
		Item ei = event.crafting.getItem();
		if(ei.equals(ModItems.peculiarPickaxe) || ei.equals(ModItems.peculiarSword) || ei.equals(ModItems.peculiarAxe) || ei.equals(ModItems.peculiarShovel) || ei.equals(ModItems.peculiarHoe)){
			event.player.addStat(ModAchievements.achCraftPTools, 1);
		}else if(ei.equals(ModItems.peculiarHelmet) || ei.equals(ModItems.peculiarChestplate) || ei.equals(ModItems.peculiarLeggings) || ei.equals(ModItems.peculiarBoots)){
			event.player.addStat(ModAchievements.achCraftPArmor, 1);
		}
	}

}
