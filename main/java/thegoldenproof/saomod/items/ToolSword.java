package thegoldenproof.saomod.items;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.common.collect.Multimap;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
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

public class ToolSword extends ItemSword implements IPriority {
	ArrayList<String> tooltip;
	boolean priorityReqd = true;

	public ToolSword(String name, ToolMaterial material, CreativeTabs tab) {
		this(name, material, tab, null);
	}
	
	public ToolSword(String name, ToolMaterial material, CreativeTabs tab, String[] tooltip) {
		super(material);
		
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
	
	public void setPriorityReqd(boolean priorityReqd) {
		this.priorityReqd = priorityReqd;
	}
	
	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		return (
			super.canHarvestBlock(blockIn) ||
			blockIn.getBlock() == ModBlocks.GIGAS_CEDAR_LOG
			);
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
