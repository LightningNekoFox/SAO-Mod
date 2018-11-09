package thegoldenproof.saomod.blocks;

import java.util.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thegoldenproof.saomod.SAOM;
import thegoldenproof.saomod.init.ModBlocks;
import thegoldenproof.saomod.init.ModItems;
import thegoldenproof.saomod.util.handlers.RegistryHandler;

public class BlockBase extends Block {
	
	ArrayList<String> tooltip;
	
	public BlockBase(String name, Material material, CreativeTabs tab, String[] tooltip) {
		super(material);
		if (tooltip != null) {
			this.tooltip = new ArrayList<String>(Arrays.asList(tooltip));
		} else {
			this.tooltip = new ArrayList<String>();
		}
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		RegistryHandler.BLOCKS.add(this);
		RegistryHandler.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
	}
	
	public BlockBase(String name, Material material, CreativeTabs tab) {
		this(name, material, tab, null);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.addAll(this.tooltip);
	}
	
}
