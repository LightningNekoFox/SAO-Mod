package thegoldenproof.saomod.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;

public class ServerProxy implements IProxy {

	@Override
	public Side getPhysicalSide() {
		return Side.SERVER;
	}

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {}
	
}
