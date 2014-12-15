package matthbo.mods.darkworld.reference;

import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.EnumHelper;

public class EnumDarkWorld {
	
	public static final EnumPlantType DarkDesertPlant = EnumHelper.addEnum(EnumPlantType.class, "DarkDesert");
	public static final EnumPlantType DarkPlainsPlant = EnumHelper.addEnum(EnumPlantType.class, "DarkPlains");
	
	public static final Type DarkDesertType = EnumHelper.addEnum(Type.class, "DarkDesert");

}
