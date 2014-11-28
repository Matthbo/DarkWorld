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
		//this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.frozenOcean, 0.6F);
		this.worldChunkMgr = new WorldChunkManagerHell(ModBiomes.darkDesert, 0.6F);
		this.dimensionId = ModDimensions.dimensionIDDarkWorld;
	};
	
	public IChunkProvider createChunkGenerator(){
		return new ChunkProviderDarkWorldBeta(this.worldObj, this.worldObj.getSeed(), true);
		//return new ChunkProviderHell(worldObj, this.worldObj.getSeed());
	}
	
	public static WorldProvider getProviderForDimension(int id)
	{
		return DimensionManager.createProviderFor(ModDimensions.dimensionIDDarkWorld);
	}
	
	public boolean isSurfaceWorld()
    {
        return false;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getWelcomeMessage()
	{
		return "Entering the Dark World";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getDepartMessage()
	{
		return "Leaving the Dark World";
	}
	
	@Override
	public String getDimensionName() {
		return "Dark World";
	}

}
