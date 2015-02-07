package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;

public class BlockFallingDarkWorld extends BlockFalling {
	
	public BlockFallingDarkWorld(Material material){
		super(material);
		this.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
	}
	
	public BlockFallingDarkWorld(){
		this(Material.rock);
		this.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
	}
	
	@Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
    
    /*@Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
    	Block plant = plantable.getPlant(world, x, y + 1, z);
        EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);

        

        /*if (plant == Blocks.reeds && this == Blocks.reeds)
        {
            return true;
        }*/

        /*if (plantable instanceof BlockBush && canPlaceBlockOn(this))
        {
            return true;
        }*/
        
        //if(plantType == EnumDarkWorld.DarkDesertPlant) return this == ModBlocks.darkSand;
        //if(plantType == EnumDarkWorld.DarkPlainsPlant) return this == ModBlocks.darkGrass || this == ModBlocks.darkDirt;// || this == Blocks.farmland;

        /*switch (plantType)
        {
            case Desert: return this == ModBlocks.darkSand;
            case Nether: return this == Blocks.soul_sand;
            case Crop:   return this == Blocks.farmland;
            case Cave:   return isSideSolid(world, x, y, z, UP);
            case Plains: return this == ModBlocks.darkGrass || this == ModBlocks.darkDirt;// || this == Blocks.farmland;
            case Water:  return world.getBlock(x, y, z).getMaterial() == Material.water && world.getBlockMetadata(x, y, z) == 0;
            case Beach:
                boolean isBeach = this == ModBlocks.darkGrass || this == Blocks.dirt || this == ModBlocks.darkSand;
                boolean hasWater = (world.getBlock(x - 1, y, z    ).getMaterial() == Material.water ||
                                    world.getBlock(x + 1, y, z    ).getMaterial() == Material.water ||
                                    world.getBlock(x,     y, z - 1).getMaterial() == Material.water ||
                                    world.getBlock(x,     y, z + 1).getMaterial() == Material.water);
                return isBeach && hasWater;
        }*/

        //return false;
    //}
    
    protected boolean canPlaceBlockOn(Block p_149854_1_)
    {
        return p_149854_1_ == ModBlocks.darkGrass || p_149854_1_ == ModBlocks.darkDirt;// || p_149854_1_ == Blocks.farmland;
    }

}
