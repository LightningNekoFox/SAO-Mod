package thegoldenproof.saomod.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	
	public static void init() {
		
		GameRegistry.addSmelting(ModItems.DRAGON_ICE_CRYSTAL, new ItemStack(ModItems.DRAGON_ICE_INGOT, 1), 7.5f);
		
	}
	
}
