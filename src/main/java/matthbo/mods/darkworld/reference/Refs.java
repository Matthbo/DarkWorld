package matthbo.mods.darkworld.reference;

public class Refs {
	
	public static final String MOD_ID = "DarkWorld";
	public static final String MOD_NAME = "Dark World";
	public static final String VERSION = "1.7.10-0.2";
	public static final String DEPENDS = "required-after:Forge@[11.14.0.1281,)";

	//classes
	public static final String CLIENT_PROXY_CLASS = "matthbo.mods.darkworld.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "matthbo.mods.darkworld.proxy.ServerProxy";
	public static final String GUI_FACTORY_CLASS = "matthbo.mods.darkworld.client.gui.GuiFactory";
	
	//config
	public static final String CONFIG_LANGKEY = "darkworld.configgui.ctgy.config";
	public static final String CONFIG_LANGKEY_GENERAL = "darkworld.configgui.ctgy.configGeneral";
	public static final String CONFIG_LANGKEY_DEV = "darkworld.configgui.ctgy.configDevelopment";
	
	//chat
	public static final String CHAT_LANGKEY_DEVMODE = "darkworld.chat.command.devmode";
	
	//achievement
	public static final String ACH_PICKUPPCOBBLESTONE = "darkworld.achievement.pickuppcobblestone";
	public static final String ACH_PICKUPPCOBBLESTONE_DESC = "darkworld.achievement.pickuppcobblestone.desc";
	public static final String ACH_SMELTPCOBBLESTONE = "darkworld.achievement.smeltpcobblestone";
	public static final String ACH_SMELTPCOBBLESTONE_DESC = "darkworld.achievement.smeltpcobblestone.desc";
	public static final String ACH_CRAFTPTOOLS = "darkworld.achievement.craftptools";
	public static final String ACH_CRAFTPTOOLS_DESC = "darkworld.achievement.craftptools.desc";

}
