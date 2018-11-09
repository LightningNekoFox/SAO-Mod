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
	int priority;
	
	public ItemBase(String name, int priority, CreativeTabs tab, String[] tooltip) {
		this.priority = priority;
		if (tooltip != null) {
			this.tooltip = new ArrayList<String>(Arrays.asList(tooltip));
		} else {
			this.tooltip = new ArrayList<String>();
		}
		this.tooltip.add("Object Priority: "+priority);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		RegistryHandler.ITEMS.add(this);
	}
	
	public ItemBase(String name, int priority, CreativeTabs tab) {
		this(name, priority, tab, null);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.addAll(this.tooltip);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayerMP) {
			EntityPlayerMP p = (EntityPlayerMP)(entityIn);
			IRpgPlayer rpg = (IRpgPlayer) p.getCapability(RpgPlayerProvider.RPG_PLAYER_CAP, (EnumFacing) null);
			int level = rpg.getLevel();
			if (priority > level) {
				p.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("slowness"), 10, (int)(Math.ceil((priority-level)/10))));
				p.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("mining_fatigue"), 10, (int)(Math.ceil((priority-level)/10))));
			}
		}
	}
	
	
	
}
