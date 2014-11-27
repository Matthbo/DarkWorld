package matthbo.mods.darkworld.world;

import matthbo.mods.darkworld.DarkWorld;
import matthbo.mods.darkworld.init.ModBiomes;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenEnd;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderDarkWorld extends WorldProvider {

	public void registerWorldChunkManager() {
		//this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.frozenOcean, 0.6F);
		this.worldChunkMgr = new WorldChunkManagerHell(/*ModBiomes.darkDesert*/ BiomeGenBase.hell, 0.6F);
		//this.dimensionId = DarkWorld.dimensionDarkWordID;
	};
	
	public IChunkProvider createChunkProvider(){
		return new ChunkProviderDarkWorldBeta(this.worldObj, this.worldObj.getSeed(), true);
	}
	
	public boolean isSurfaceWorld()
    {
        return false;
    }
	
	@Override
	public String getDimensionName() {
		return "Dark World";
	}

}
