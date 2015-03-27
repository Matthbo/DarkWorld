package matthbo.mods.darkworld.proxy;

import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy{

    @Override
    public boolean fancyGraphics() {
        return Minecraft.getMinecraft().gameSettings.fancyGraphics;
    }

    //this is very inefficient
    public void textureFix(){
        fix("peculiarStone");
        fix("peculiarCobbleStone");
        fix("peculiarDustBlock");
        fix("compressedPeculiarDust");
        fix("darkworldPortal");
        fix("darkGrass");
        fix("darkDirt");
        fix("darkStone");
        fix("darkCobbleStone");
        fix("darkPlanks");
        fix("darkSapling");
        fix("darkSand");
        fix("darkGravel");
        fix("darkTallGrass");
        fix("darkGoldOre");
        fix("darkIronOre");
        fix("darkCoalOre");
        fix("darkLog");
        fix("darkLog2");
        fix("darkLeaves");
        fix("darkSponge");
        fix("darkSandStone");
        fix("darkLapisOre");
        fix("darkBrick");
        fix("darkMossyCobble");
        fix("darkObsidian");
        fix("darkDiamondOre");
        fix("darkRedstoneOre");
        fix("darkLitRedstoneOre");
        fix("darkCactus");
        fix("darkEmeraldOre");
        fix("darkFire");

        fix("peculiarDust");
        fix("peculiarDustBar");
        fix("hardPeculiarDust");
        fix("peculiarDustNSteel");
        fix("darkWheat");
        fix("darkApple");
        fix("peculiarPickaxe");
        fix("peculiarSword");
        fix("peculiarAxe");
        fix("peculiarShovel");
        fix("peculiarHoe");
        fix("peculiarHelmet");
        fix("peculiarChestplate");
        fix("peculiarLeggings");
        fix("peculiarBoots");
    }

    private void fix(String object){
        final int DEFAULT_ITEM_SUBTYPE = 0;

        Item item = GameRegistry.findItem(Refs.MOD_ID.toLowerCase(), object);
        ModelResourceLocation modelItem = new ModelResourceLocation(Refs.MOD_ID.toLowerCase()+":"+object, "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, DEFAULT_ITEM_SUBTYPE, modelItem);
    }

}
