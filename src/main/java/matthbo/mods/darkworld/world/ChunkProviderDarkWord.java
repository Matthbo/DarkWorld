package matthbo.mods.darkworld.world;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderDarkWord implements IChunkProvider {
	
	private Random rand;
	
	private World worldObj;
	
	private final boolean mapFeaturesEnabled;

	private double[] noiseArray;
	private double[] stoneNoise = new double[256];
	private MapGenBase caveGen = new MapGenBase();
	private MapGenStronghold strongholdGen = new MapGenStronghold();
	private MapGenVillage villageGen = new MapGenVillage();
	private MapGenMineshaft mineshaftGen = new MapGenMineshaft();
	private MapGenScatteredFeature scatterFeatureGen = new MapGenScatteredFeature();
	private MapGenBase ravineGen = new MapGenRavine();
	
	private BiomeGenBase[] biomesForGen;
	
	private InitMapGenEvent.EventType mapGenEvent;
	private PopulateChunkEvent.Populate.EventType popEvent;
	
	{
		caveGen = TerrainGen.getModdedMapGen(caveGen, mapGenEvent.CAVE);
		strongholdGen = (MapGenStronghold) TerrainGen.getModdedMapGen(strongholdGen, mapGenEvent.STRONGHOLD);
		villageGen = (MapGenVillage) TerrainGen.getModdedMapGen(villageGen, mapGenEvent.VILLAGE);
		mineshaftGen = (MapGenMineshaft) TerrainGen.getModdedMapGen(mineshaftGen, mapGenEvent.MINESHAFT);
		scatterFeatureGen = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(scatterFeatureGen, mapGenEvent.SCATTERED_FEATURE);
		ravineGen = TerrainGen.getModdedMapGen(ravineGen, mapGenEvent.RAVINE);
	}

	public ChunkProviderDarkWord(World worldObj, long seed, boolean features) {
		this.worldObj = worldObj;
		this.mapFeaturesEnabled = features;
		this.rand = new Random(seed);
	}

	@Override
	public boolean chunkExists(int i, int j) {
		return false;
	}

	@Override
	public Chunk provideChunk(int i, int j) {
		return null;
	}

	@Override
	public Chunk loadChunk(int i, int j) {
		return null;
	}

	@Override
	public void populate(IChunkProvider ichunkprovider, int i, int j) {
		BlockSand.fallInstantly = true;
		
		int k = i * 16;
		int l = j * 16;
		
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(k + 16, l + 16);
		
		this.rand.setSeed(this.worldObj.getSeed());
		
		long i1 = this.rand.nextLong() / 2L * 2L + 1L;
		long j1 = this.rand.nextLong() / 2L * 2L + 1L;
		
		this.rand.setSeed((long)i * i1 + (long)j * j1 ^ this.worldObj.getSeed());
		
		boolean flag = false;
		
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(ichunkprovider, worldObj, rand, i, j, flag));
		
		if(mapFeaturesEnabled){
			this.mineshaftGen.generateStructuresInChunk(worldObj, rand, i, j);
			flag = this.villageGen.generateStructuresInChunk(worldObj, rand, i, j);
			this.strongholdGen.generateStructuresInChunk(worldObj, rand, i, j);
			this.scatterFeatureGen.generateStructuresInChunk(worldObj, rand, i, j);
		}
		
		int k1;
		int l1;
		int i2;
		
		if(biome != BiomeGenBase.desert && biome != BiomeGenBase.desertHills && !flag && this.rand.nextInt(4) == 0 && TerrainGen.populate(ichunkprovider, worldObj, rand, i, j, flag, popEvent.LAKE)){
			k1 = k + this.rand.nextInt(16) + 8;
			l1 = this.rand.nextInt(128);
			i2 = l + this.rand.nextInt(16) + 8;
			
			(new WorldGenLakes(Blocks.water)).generate(worldObj, rand, k1, l1, i2);
		}
		
		if(TerrainGen.populate(ichunkprovider, worldObj, rand, i, j, flag, popEvent.LAVA) && !flag && this.rand.nextInt(8) == 0){
			k1 = k + this.rand.nextInt(16) + 8;
			l1 = this.rand.nextInt(this.rand.nextInt(120) + 8);
			i2 = l + this.rand.nextInt(16) + 8;
			
			if(l1 < 63 || this.rand.nextInt(10) == 0){
				(new WorldGenLakes(Blocks.lava)).generate(worldObj, rand, k1, l1, i2);
			}
		}
		
		boolean doGen = TerrainGen.populate(ichunkprovider, worldObj, rand, i, j, flag, popEvent.DUNGEON);
		
		for(k1 = 0; doGen && k1 < 8; k1++){
			l1 = k + this.rand.nextInt(16) + 8;
			i2 = l + this.rand.nextInt(16) + 8;//yPos
			int j2 = l +this.rand.nextInt(16) + 8;
			
			(new WorldGenDungeons()).generate(worldObj, rand, l1, i2, j2);
		}
		
		biome.decorate(worldObj, rand, k, l);
		SpawnerAnimals.performWorldGenSpawning(worldObj, biome, k+ 8, l+8, 16, 16, rand);
		
		k+=8;
		l+=8;

		doGen = TerrainGen.populate(ichunkprovider, worldObj, rand, i, j, flag, popEvent.ICE);
		
		for(k1 = 0; doGen && k1 < 16; k1++){
			for(l1 = 0; l1 < 16; l1++){
				i2 = this.worldObj.getPrecipitationHeight(k + k1, l + l1);
				
				if(this.worldObj.isBlockFreezable(k + k1, i2 -1, l + l1)){
					this.worldObj.setBlock(k + k1, i2-1, l + l1, Blocks.ice, 0, 2);
				}
				
				if(this.worldObj.func_147478_e(k + k1, i2 -1, l + l1, true)){
					this.worldObj.setBlock(k + k1, i2, l + l1, Blocks.ice, 0, 2);
				}
			}
		}
		
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(ichunkprovider, worldObj, rand, i, j, flag));
		
		BlockSand.fallInstantly = false;
	}

	@Override
	public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
		return true;
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return "RandomLevelSource";
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i, int j, int k) {
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k);
		return biome == null ? null : (biome == BiomeGenBase.swampland && enumcreaturetype == EnumCreatureType.monster && this.scatterFeatureGen.hasStructureAt(i, j, k) ? this.scatterFeatureGen.getScatteredFeatureSpawnList() : biome.getSpawnableList(enumcreaturetype));
	}

	@Override
	public ChunkPosition func_147416_a(World world, String s, int i, int j, int k) {
		return "Stronghold".equals(s) && this.strongholdGen != null ? this.strongholdGen.func_151545_a(world, i, j, k) : null;
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void recreateStructures(int i, int j) {
		
	}

	@Override
	public void saveExtraData() {
		
	}

}
