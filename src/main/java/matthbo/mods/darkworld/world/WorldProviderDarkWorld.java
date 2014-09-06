package matthbo.mods.darkworld.world;

import matthbo.mods.darkworld.DarkWorld;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenEnd;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderDarkWorld extends WorldProvider {

	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.sky, 0.6F);
		this.dimensionId = DarkWorld.dimensionDarkWordID;
	};
	
	public IChunkProvider createChunkProvider(){
		return new ChunkProviderDarkWord(this.worldObj, this.worldObj.getSeed(), true);
	}
	
	@Override
	public String getDimensionName() {
		return "Dark World";
	}

}
