package matthbo.mods.darkworld.init;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import matthbo.mods.darkworld.item.ItemDarkWorld;
import matthbo.mods.darkworld.item.ItemHardPeculiarDust;
import matthbo.mods.darkworld.item.ItemPeculiarDust;
import matthbo.mods.darkworld.item.ItemPeculiarDustBar;
import matthbo.mods.darkworld.item.ItemPeculiarDustNSteel;
import matthbo.mods.darkworld.item.armor.ItemArmorDarkWorld;
import matthbo.mods.darkworld.item.armor.ItemPeculiarBoots;
import matthbo.mods.darkworld.item.armor.ItemPeculiarChestplate;
import matthbo.mods.darkworld.item.armor.ItemPeculiarHelmet;
import matthbo.mods.darkworld.item.armor.ItemPeculiarLeggings;
import matthbo.mods.darkworld.item.tool.ItemHoeDarkWorld;
import matthbo.mods.darkworld.item.tool.ItemPeculiarAxe;
import matthbo.mods.darkworld.item.tool.ItemPeculiarHoe;
import matthbo.mods.darkworld.item.tool.ItemPeculiarPickaxe;
import matthbo.mods.darkworld.item.tool.ItemPeculiarShovel;
import matthbo.mods.darkworld.item.tool.ItemPeculiarSword;
import matthbo.mods.darkworld.item.tool.ItemSwordDarkWorld;
import matthbo.mods.darkworld.item.tool.ItemToolDarkWorld;
import matthbo.mods.darkworld.reference.Refs;

@ObjectHolder(Refs.MOD_ID)
public class ModItems {	
	
	private static GameRegistry GR;
	
	public static final ItemDarkWorld peculiarDust = new ItemPeculiarDust();
	public static final ItemDarkWorld peculiarDustBar = new ItemPeculiarDustBar();
	public static final ItemDarkWorld hardPeculiarDust = new ItemHardPeculiarDust();
	public static final ItemDarkWorld peculiarDustNSteel = new ItemPeculiarDustNSteel();
	
	//tools
	public static final ItemToolDarkWorld peculiarPickaxe = new ItemPeculiarPickaxe();
	public static final ItemSwordDarkWorld peculiarSword = new ItemPeculiarSword();
	public static final ItemToolDarkWorld peculiarAxe = new ItemPeculiarAxe();
	public static final ItemToolDarkWorld peculiarShovel = new ItemPeculiarShovel();
	public static final ItemHoeDarkWorld peculiarHoe = new ItemPeculiarHoe();
	
	//armor
	public static final ItemArmorDarkWorld peculiarHelmet = new ItemPeculiarHelmet();
	public static final ItemArmorDarkWorld peculiarChestplate = new ItemPeculiarChestplate();
	public static final ItemArmorDarkWorld peculiarLeggings = new ItemPeculiarLeggings();
	public static final ItemArmorDarkWorld peculiarBoots = new ItemPeculiarBoots();
	
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
		
		GR.registerItem(peculiarHelmet, "peculiarHelmet");
		GR.registerItem(peculiarChestplate, "peculiarChestplate");
		GR.registerItem(peculiarLeggings, "peculiarLeggings");
		GR.registerItem(peculiarBoots, "peculiarBoots");
	}

}
