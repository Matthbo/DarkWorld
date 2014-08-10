package matthbo.mods.darkworld.handler;

import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.util.ChatComponentTranslation;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class EventHandler {
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event){
		if(ConfigHandler.dev == true){
			event.player.addChatMessage(new ChatComponentTranslation(Refs.CHAT_LANGKEY_DEVMODE));
		}
	}

}
