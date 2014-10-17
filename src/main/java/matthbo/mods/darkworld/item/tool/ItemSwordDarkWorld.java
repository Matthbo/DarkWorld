package matthbo.mods.darkworld.item.tool;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import matthbo.mods.darkworld.item.ItemDarkWorld;
import matthbo.mods.darkworld.reference.Refs;

public class ItemSwordDarkWorld extends Item{
	
	private float damageOrSomething;
	private final Item.ToolMaterial material;
	
	public ItemSwordDarkWorld(Item.ToolMaterial material){
		this.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
		this.material = material;
		this.maxStackSize = 1;
		this.setMaxDamage(material.getMaxUses());
		this.damageOrSomething = 4.0F + material.getDamageVsEntity();
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
		return String.format("item.%s%s", Refs.MOD_ID.toLowerCase() + ":" + "tools/", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		itemIcon = iconRegister.registerIcon(this.getIconName().substring(this.getIconName().indexOf(".") + 1));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
	
	public float getDamageVsEntity(){
		return this.material.getDamageVsEntity();
	}
	
	public float func_150893_a(ItemStack itemStack, Block block)
    {
        if (block == Blocks.web)
        {
            return 15.0F;
        }
        else
        {
            Material material = block.getMaterial();
            return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.gourd ? 1.0F : 1.5F;
        }
    }
	
	/**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase entity, EntityLivingBase otherEntity)
    {
        itemStack.damageItem(1, otherEntity);
        return true;
    }
    
    public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase entity)
    {
        if ((double)block.getBlockHardness(world, x, y, z) != 0.0D)
        {
            itemStack.damageItem(2, entity);
        }

        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
    
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.block;
    }
    
    public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 72000;
    }
    
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }
    
    public boolean func_150897_b(Block block)
    {
        return block == Blocks.web;
    }
    
    public int getItemEnchantability()
    {
        return this.material.getEnchantability();
    }
    
    public String getToolMaterialName()
    {
        return this.material.toString();
    }
    
    public boolean getIsRepairable(ItemStack itemStack, ItemStack itemStack2)
    {
        return this.material.func_150995_f() == itemStack2.getItem() ? true : super.getIsRepairable(itemStack, itemStack2);
    }
    
    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.damageOrSomething, 0));
        return multimap;
    }

}
