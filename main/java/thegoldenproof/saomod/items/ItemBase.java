package thegoldenproof.saomod.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import pilot.simplerpg.capabilities.IRpgPlayer;
import pilot.simplerpg.capabilities.RpgPlayerProvider;
import thegoldenproof.saomod.SAOM;
import thegoldenproof.saomod.util.handlers.RegistryHandler;

public class ItemBase extends Item {
	
	ArrayList<String> tooltip;
	
	public ItemBase(String name, CreativeTabs tab, String[] tooltip) {
		if (tooltip != null) {
			this.tooltip = new ArrayList<String>(Arrays.asList(tooltip));
		} else {
			this.tooltip = new ArrayList<String>();
		}
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		RegistryHandler.ITEMS.add(this);
	}
	
	public ItemBase(String name, CreativeTabs tab) {
		this(name, tab, null);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.addAll(this.tooltip);
	}
	
}
