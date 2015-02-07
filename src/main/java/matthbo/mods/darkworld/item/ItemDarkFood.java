package matthbo.mods.darkworld.item;

import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemDarkFood extends ItemFood {

    /** Number of ticks to run while 'EnumAction'ing until result. */
    /** The amount this food item heals the player. */
    /** Whether wolves like this food (true for raw and cooked porkchop). */
    /** If this field is true, the food can be consumed even if the player don't need to eat. */
    private boolean alwaysEdible;
    /** represents the potion effect that will occurr upon eating this food. Set by setPotionEffect */
    private int potionId;
    /** set by setPotionEffect */
    private int potionDuration;
    /** set by setPotionEffect */
    private int potionAmplifier;
    /** probably of the set potion effect occurring */
    private float potionEffectProbability;
    private static final String __OBFID = "CL_00000036";

    public ItemDarkFood(int healAmount, float saturationMod, boolean isWolfsMeat)
    {
        super(healAmount, saturationMod, isWolfsMeat);
        this.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
    }

    public ItemDarkFood(int healAmount, boolean isWolfsMeat)
    {
        this(healAmount, 0.6F, isWolfsMeat);
    }

    @Override
    public String getUnlocalizedName(){
        return String.format("item.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack){
        return String.format("item.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

}
