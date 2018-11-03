package thegoldenproof.saomod.items;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import pilot.simplerpg.capabilities.IRpgPlayer;
import pilot.simplerpg.capabilities.RpgPlayerProvider;
import scala.actors.threadpool.Arrays;
import thegoldenproof.saomod.SAOM;
import thegoldenproof.saomod.init.ModBlocks;
import thegoldenproof.saomod.util.handlers.RegistryHandler;

public class ToolSword extends ItemSword {
	int priority;
	List<String> tooltip;

	public ToolSword(String name, ToolMaterial material, int priority, CreativeTabs tab) {
		this(name, material, priority, tab, new String[] {""});
	}
	
	public ToolSword(String name, ToolMaterial material, int priority, CreativeTabs tab, String[] tooltip) {
		super(material);
		
		this.priority = priority;
		this.tooltip = Arrays.asList(tooltip);
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
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.addAll(this.tooltip);
		tooltip.add("Object priority: "+priority);
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
