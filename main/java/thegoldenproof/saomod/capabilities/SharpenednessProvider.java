package thegoldenproof.saomod.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class SharpenednessProvider implements ICapabilitySerializable<NBTBase> {
	
	@CapabilityInject(value = Sharpenedness.class)
	public static final Capability<Sharpenedness> SHARPENED = null;
	
	private Sharpenedness instance = SHARPENED.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return (capability == SHARPENED);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == SHARPENED ? SHARPENED.<T> cast(instance) : null;
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		SHARPENED.getStorage().readNBT(SHARPENED, instance, null, nbt);
		
	}

	@Override
	public NBTBase serializeNBT() {
		return SHARPENED.getStorage().writeNBT(SHARPENED, instance, null);
	}

}
