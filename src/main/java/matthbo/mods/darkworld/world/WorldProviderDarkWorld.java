package matthbo.mods.darkworld.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.DarkWorld;
import matthbo.mods.darkworld.init.ModBiomes;
import matthbo.mods.darkworld.init.ModDimensions;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenEnd;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraftforge.common.DimensionManager;

public class WorldProviderDarkWorld extends WorldProvider {

	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(ModBiomes.darkPlains, 0.6F);
		//this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.desert, 0.6F);
		//this.worldChunkMgr = new WorldChunkManagerDarkWorld(worldObj.getSeed(), terrainType);
		this.dimensionId = ModDimensions.dimensionIDDarkWorld;
	};
	
	public IChunkProvider createChunkGenerator(){
		return new ChunkProviderDarkWorldBeta(this.worldObj, this.worldObj.getSeed(), true);
		//return new ChunkProviderHell(ModDimensions.dimensionIDDarkWorld, 0.6);
	}
	
	public static WorldProvider getProviderForDimension(int id)
	{
		return DimensionManager.createProviderFor(ModDimensions.dimensionIDDarkWorld);
	}
	
	public boolean isSurfaceWorld()
    {
        return false;
    }
	
	/**
	 * Doesn't really work, mojang needs to fix this
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public String getWelcomeMessage()
	{
		return "Entering the Dark World";
	}

	/**
	 * Doesn't really work, mojang needs to fix this
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public String getDepartMessage()
	{
		return "Leaving the Dark World";
	}
	
	public boolean canRespawnHere()
    {
        return false;
    }
	
	/**
	 * doesn't work either
	 */
	@SideOnly(Side.CLIENT)
	public boolean renderStars() {
		return true;
	}
	
	@Override
	public String getDimensionName() {
		return "Dark World";
	}

}
