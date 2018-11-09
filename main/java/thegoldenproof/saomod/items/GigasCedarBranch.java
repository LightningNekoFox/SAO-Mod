package thegoldenproof.saomod.items;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.Lists;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import thegoldenproof.saomod.SAOM;
import thegoldenproof.saomod.capabilities.Sharpenedness;
import thegoldenproof.saomod.capabilities.SharpenednessFactory;
import thegoldenproof.saomod.capabilities.SharpenednessProvider;

public class GigasCedarBranch extends ItemBase {
	
	ArrayList<String> tooltip;

	public GigasCedarBranch(String name, int priority, CreativeTabs tab, String[] tooltip) {
		super(name, priority, tab);
		this.tooltip = new ArrayList<String>(Arrays.asList(tooltip));
	}
	
	@Override
	public void readNBTShareTag(ItemStack stack, NBTTagCompound nbt) {
		stack.setTagCompound(nbt);
	}
	
	@Override
	public NBTTagCompound getNBTShareTag(ItemStack stack) {
		return stack.serializeNBT();
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {	
		int prog = stack.getCapability(SharpenednessProvider.SHARPENED, null).getProgress();
		if (prog == 0) {
			tooltip.addAll(this.tooltip);
		}
		tooltip.add("Sharpening progress: "+prog+"/600");
		tooltip.add("Object Priority: "+priority);
	}
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new SharpenednessProvider();
	}
	
}
