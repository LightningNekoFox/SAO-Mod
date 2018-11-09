package thegoldenproof.saomod.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import pilot.simplerpg.capabilities.IRpgPlayer;
import pilot.simplerpg.capabilities.RpgPlayerProvider;
import thegoldenproof.saomod.SAOM;
import thegoldenproof.saomod.util.IPriority;
import thegoldenproof.saomod.util.handlers.RegistryHandler;

public class ToolAxe extends ItemAxe implements IPriority {
	public int priority;
	public int atkSpdChk;
	ArrayList<String> tooltip;
	boolean priorityReqd = true;

	public ToolAxe(String name, ToolMaterial material, int priority, CreativeTabs tab) {
		this(name, material, priority, tab, null);
		
	}
	
	public ToolAxe(String name, ToolMaterial material, int priority, CreativeTabs tab, String[] tooltip) {
		super(material, material.getAttackDamage(), 0);
		
		this.priority = priority;
		
		if (tooltip != null) {
			this.tooltip = new ArrayList<String>(Arrays.asList(tooltip));
		} else {
			this.tooltip = new ArrayList<String>();
		}
		this.tooltip.add("Object priority: "+priority);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		RegistryHandler.ITEMS.add(this);
	}
	
	public void setPriorityReqd(boolean priorityReqd) {
		this.priorityReqd = priorityReqd;
	}
	
	//displays required priority
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.addAll(this.tooltip);
	}
	
	//Makes tools heavy if you don't have the required level
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayerMP && priorityReqd) {
			EntityPlayerMP p = (EntityPlayerMP)(entityIn);
			IRpgPlayer rpg = (IRpgPlayer) p.getCapability(RpgPlayerProvider.RPG_PLAYER_CAP, (EnumFacing) null);
			int level = rpg.getLevel();
			if (priority > level) {
				p.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("slowness"), 10, (int)(Math.ceil((priority-level)/10))));
				p.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("mining_fatigue"), 10, (int)(Math.ceil((priority-level)/10))));
			} else if (atkSpdChk != level) {
				this.attackSpeed = -(float)(Math.pow(level-priority, 1/2.0));
				atkSpdChk = level;
			}
		}
		
	}

	@Override
	public Item getItem() {
		return this;
	}
	
}
