package thegoldenproof.saomod.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.state.IBlockState;
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
import thegoldenproof.saomod.init.ModBlocks;
import thegoldenproof.saomod.util.IPriority;
import thegoldenproof.saomod.util.handlers.RegistryHandler;

public class ToolAxe extends ItemAxe implements IPriority {
	public int atkSpdChk;
	ArrayList<String> tooltip;
	boolean priorityReqd = true;

	public ToolAxe(String name, ToolMaterial material, CreativeTabs tab) {
		this(name, material, tab, null);
		
	}
	
	public ToolAxe(String name, ToolMaterial material, CreativeTabs tab, String[] tooltip) {
		super(material, material.getAttackDamage(), 0);
		
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
	
	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		return (
				super.canHarvestBlock(blockIn) ||
				blockIn.getBlock() == ModBlocks.GIGAS_CEDAR_LOG
				);
	}
	
	public void setPriorityReqd(boolean priorityReqd) {
		this.priorityReqd = priorityReqd;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.addAll(this.tooltip);
	}

	@Override
	public Item getItem() {
		return this;
	}
	
}
