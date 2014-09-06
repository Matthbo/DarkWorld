package matthbo.mods.darkworld.init;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import matthbo.mods.darkworld.item.ItemDarkWorld;
import matthbo.mods.darkworld.item.ItemHardPeculiarDust;
import matthbo.mods.darkworld.item.ItemPeculiarDust;
import matthbo.mods.darkworld.item.ItemPeculiarDustBar;
import matthbo.mods.darkworld.item.ItemPeculiarDustNSteel;
import matthbo.mods.darkworld.item.ItemToolDarkWorld;
import matthbo.mods.darkworld.item.tool.ItemPeculiarAxe;
import matthbo.mods.darkworld.item.tool.ItemPeculiarHoe;
import matthbo.mods.darkworld.item.tool.ItemPeculiarPickaxe;
import matthbo.mods.darkworld.item.tool.ItemPeculiarShovel;
import matthbo.mods.darkworld.item.tool.ItemPeculiarSword;
import matthbo.mods.darkworld.reference.Refs;

@ObjectHolder(Refs.MOD_ID)
public class ModItems {	
	
	private static GameRegistry GR;
	
	public static final ItemDarkWorld peculiarDust = new ItemPeculiarDust();
	public static final ItemDarkWorld peculiarDustBar = new ItemPeculiarDustBar();
	public static final ItemDarkWorld hardPeculiarDust = new ItemHardPeculiarDust();
	public static final ItemDarkWorld peculiarDustNSteel = new ItemPeculiarDustNSteel();
	
	//tools
	public static final ItemToolDarkWorld peculiarPickaxe = new ItemPeculiarPickaxe();// make it like a real tool
	public static final ItemToolDarkWorld peculiarSword = new ItemPeculiarSword();//make it like a real sword(sword is not a tool??)
	public static final ItemToolDarkWorld peculiarAxe = new ItemPeculiarAxe();//make it like a real tool
	public static final ItemToolDarkWorld peculiarShovel = new ItemPeculiarShovel();
	public static final ItemToolDarkWorld peculiarHoe = new ItemPeculiarHoe();
	
	//armor
	
	public static void init(){
		
		GR.registerItem(peculiarDust, "peculiarDust");
		GR.registerItem(peculiarDustBar, "peculiarDustBar");
		GR.registerItem(hardPeculiarDust, "hardPeculiarDust");
		GR.registerItem(peculiarDustNSteel, "peculiarDustNSteel");
		
		GR.registerItem(peculiarPickaxe, "peculiarPickaxe");
		GR.registerItem(peculiarSword, "peculiarSword");
		GR.registerItem(peculiarAxe, "peculiarAxe");
		GR.registerItem(peculiarShovel, "peculiarShovel");
		GR.registerItem(peculiarHoe, "peculiarHoe");
	}

}
