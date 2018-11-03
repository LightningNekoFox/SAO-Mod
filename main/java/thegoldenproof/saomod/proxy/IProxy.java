package thegoldenproof.saomod.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import thegoldenproof.saomod.SAOM;

public interface IProxy {
	
	public void registerItemRenderer(Item item, int meta, String id);
	
	default void logPhysicalSide() {
		SAOM.info("Physical Side: " + getPhysicalSide());
	}

	Side getPhysicalSide();
	
}
