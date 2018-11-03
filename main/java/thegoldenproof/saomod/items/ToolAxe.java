package thegoldenproof.saomod.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import pilot.simplerpg.capabilities.IRpgPlayer;
import pilot.simplerpg.capabilities.RpgPlayerProvider;
import thegoldenproof.saomod.SAOM;
import thegoldenproof.saomod.util.handlers.RegistryHandler;

public class ToolAxe extends ItemAxe {
	public int priority;
	public int atkSpdChk;
	int level = 0;

	public ToolAxe(String name, ToolMaterial material, int priority, CreativeTabs tab) {
		super(material, material.getAttackDamage(), 0);
		
		this.priority = priority;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		RegistryHandler.ITEMS.add(this);
		
	}
	
	//displays required priority
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Object priority: "+priority);
	}
	
	//Makes tools heavy if you don't have the required level
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayerMP) {
			EntityPlayerMP p = (EntityPlayerMP)(entityIn);
			IRpgPlayer rpg = (IRpgPlayer) p.getCapability(RpgPlayerProvider.RPG_PLAYER_CAP, (EnumFacing) null);
			level = rpg.getLevel();
			if (priority > level) {
				p.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("slowness"), 10, (int)(Math.ceil((priority-level)/10))));
				p.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("mining_fatigue"), 10, (int)(Math.ceil((priority-level)/10))));
			} else if (atkSpdChk != level) {
				this.attackSpeed = -(float)(Math.pow(level-priority, 1/2.0));
				atkSpdChk = level;
			}
		}
		
	}
	
}
