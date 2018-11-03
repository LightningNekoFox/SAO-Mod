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
	
	ArrayList<String> tooltips = new ArrayList<String>(0);
	
	public BlockBase(String name, Material material, CreativeTabs tab, String[] tooltip) {
		super(material);
		
		for (String s : tooltip) {
			tooltips.add(s);
		}
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		RegistryHandler.BLOCKS.add(this);
		RegistryHandler.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
	}
	
	public BlockBase(String name, Material material, CreativeTabs tab) {
		this(name, material, tab, new String[] {""});
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!tooltips.isEmpty()) {
			for (String s : tooltips) {
				tooltip.add(s);
			}
		}
	}
	
}
