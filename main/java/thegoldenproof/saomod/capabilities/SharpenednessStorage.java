package thegoldenproof.saomod.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class SharpenednessStorage implements IStorage<Sharpenedness>{

	@Override
	public NBTBase writeNBT(Capability<Sharpenedness> capability, Sharpenedness instance, EnumFacing side) {
		return new NBTTagInt(instance.getProgress());
	}

	@Override
	public void readNBT(Capability<Sharpenedness> capability, Sharpenedness instance, EnumFacing side, NBTBase nbt) {
		instance.setProgress(((NBTTagInt) nbt).getInt());
	}

}
