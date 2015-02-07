package matthbo.mods.darkworld.proxy;

import net.minecraft.client.Minecraft;

public class ClientProxy extends CommonProxy{

    @Override
    public boolean fancyGraphics() {
        return Minecraft.getMinecraft().gameSettings.fancyGraphics;
    }

}
