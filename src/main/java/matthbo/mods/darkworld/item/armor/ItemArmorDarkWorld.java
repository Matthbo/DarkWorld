package matthbo.mods.darkworld.item.armor;

import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ItemArmorDarkWorld extends ItemArmor{
	
	protected static ArmorMaterial peculiarArmor = EnumHelper.addArmorMaterial("PECULIAR", "peculiar",15, new int[]{2, 5, 4, 1}, 12);

	private String armorClass;
	
	public ItemArmorDarkWorld(ArmorMaterial material, int renderIndex, int armorType) {
		super(material, renderIndex, armorType);
		this.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
		
		if(this instanceof ItemPeculiarHelmet){
			armorClass = "peculiarHelmet";
		}else if(this instanceof ItemPeculiarChestplate){
			armorClass = "peculiarChestplate";
		}else if(this instanceof ItemPeculiarLeggings){
			armorClass = "peculiarLeggings";
		}else if(this instanceof ItemPeculiarBoots){
			armorClass = "peculiarBoots";
		}
		
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer){
		if(armorClass.equals("peculiarHelmet") || armorClass.equals("peculiarChestplate") || armorClass.equals("peculiarBoots")){
			return "darkworld:textures/models/armor/peculiar_1.png";
		}
		
		if(armorClass.equals("peculiarLeggings")){
			return "darkworld:textures/models/armor/peculiar_2.png";
		}
		
		else return null;
	}
	

	@Override
	public String getUnlocalizedName(){
		return String.format("item.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack){
		return String.format("item.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	public String getIconName(){
		return String.format("item.%s%s", Refs.MOD_ID.toLowerCase() + ":" + "armor/", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
	
	
	
}
