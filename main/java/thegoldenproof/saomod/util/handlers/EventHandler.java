package thegoldenproof.saomod.util.handlers;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thegoldenproof.saomod.capabilities.Sharpenedness;
import thegoldenproof.saomod.capabilities.SharpenednessProvider;
import thegoldenproof.saomod.items.GigasCedarBranch;

public class EventHandler {
	
	@SubscribeEvent
	public void attachItemStackCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
		event.addCapability(new ResourceLocation("saom", "SharpenednessProvider"), new SharpenednessProvider());
	}
	
}
