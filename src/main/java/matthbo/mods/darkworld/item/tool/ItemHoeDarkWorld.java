package matthbo.mods.darkworld.item.tool;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHoeDarkWorld extends Item{
	
	protected Item.ToolMaterial toolMaterial;

    public ItemHoeDarkWorld(Item.ToolMaterial material){
        this.toolMaterial = material;
        this.maxStackSize = 1;
        this.setMaxDamage(material.getMaxUses());
        this.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
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

    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!playerIn.canPlayerEdit(pos.offset(side), side, stack))
        {
            return false;
        }
        else
        {
            int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(stack, playerIn, worldIn, pos);
            if (hook != 0) return hook > 0;

            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (side != EnumFacing.DOWN && worldIn.isAirBlock(pos.up()))
            {
                if (block == ModBlocks.darkGrass)
                {
                    return this.useHoe(stack, playerIn, worldIn, pos, Blocks.farmland.getDefaultState());
                }

                if (block == ModBlocks.darkDirt)
                {
                    this.useHoe(stack, playerIn, worldIn, pos, ModBlocks.darkDirt.getDefaultState());
                }
            }

            return false;
        }
    }

    protected boolean useHoe(ItemStack stack, EntityPlayer player, World worldIn, BlockPos target, IBlockState newState)
    {
        worldIn.playSoundEffect((double)((float)target.getX() + 0.5F), (double)((float)target.getY() + 0.5F), (double)((float)target.getZ() + 0.5F), newState.getBlock().stepSound.getStepSound(), (newState.getBlock().stepSound.getVolume() + 1.0F) / 2.0F, newState.getBlock().stepSound.getFrequency() * 0.8F);

        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            worldIn.setBlockState(target, newState);
            stack.damageItem(1, player);
            return true;
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    public String getToolMaterialName()
    {
        return this.toolMaterial.toString();
    }

}
