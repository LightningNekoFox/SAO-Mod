package thegoldenproof.saomod.util.handlers;

import java.awt.Dialog.ModalExclusionType;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import thegoldenproof.saomod.SAOM;
import thegoldenproof.saomod.blocks.BlockBase;
import thegoldenproof.saomod.blocks.MarbleBlock;
import thegoldenproof.saomod.blocks.gigasCedar.GigasCedarLeaves;
import thegoldenproof.saomod.blocks.gigasCedar.GigasCedarLog;
import thegoldenproof.saomod.blocks.gigasCedar.GigasCedarSapling;
import thegoldenproof.saomod.init.ModBlocks;
import thegoldenproof.saomod.init.ModItems;
import thegoldenproof.saomod.items.GigasCedarBranch;
import thegoldenproof.saomod.items.ItemBase;
import thegoldenproof.saomod.items.ToolAxe;
import thegoldenproof.saomod.items.ToolSword;
import thegoldenproof.saomod.util.Reference;
import thegoldenproof.saomod.init.*;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class RegistryHandler {
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		final IForgeRegistry<Block> registry = event.getRegistry();
		
		SAOM.info("Registering Blocks...");
		
		registry.register(new MarbleBlock("marble_block", Material.ROCK, SAOM.saoModTab));
		
		registry.register(new GigasCedarLog("gigas_cedar_log", SAOM.saoModTab));
		registry.register(new GigasCedarLeaves("gigas_cedar_leaves", SAOM.saoModTab));
		registry.register(new GigasCedarSapling("gigas_cedar_sapling", SAOM.saoModTab, ModExtras.GIGAS_SAPLING_TOOLTIP));
		
		SAOM.info("Registering Blocks Complete");
		
	}
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		//ItemBlocks
		SAOM.info("Registering Items: ItemBlocks");
		
		registry.register(new ItemBlock(ModBlocks.MARBLE_BLOCK).setRegistryName(ModBlocks.MARBLE_BLOCK.getRegistryName()));
		
		registry.register(new ItemBlock(ModBlocks.GIGAS_CEDAR_LOG).setRegistryName(ModBlocks.GIGAS_CEDAR_LOG.getRegistryName()));
		registry.register(new ItemBlock(ModBlocks.GIGAS_CEDAR_LEAVES).setRegistryName(ModBlocks.GIGAS_CEDAR_LEAVES.getRegistryName()));
		registry.register(new ItemBlock(ModBlocks.GIGAS_CEDAR_SAPLING).setRegistryName(ModBlocks.GIGAS_CEDAR_SAPLING.getRegistryName()));
		
		//Tools
		SAOM.info("Registering Items: Tools");
		
		registry.register(new ToolSword("elucidator", ModExtras.M_ELUCIDATOR, ModExtras.P_ELUCIDATOR, SAOM.saoModTab, ModExtras.ELUCIDATOR_TOOLTIP));
		registry.register(new ToolSword("dark_repulser", ModExtras.M_DARK_REPULSER, ModExtras.P_DARK_REPULSER, SAOM.saoModTab));
		registry.register(new ToolSword("excalibur", ModExtras.M_EXCALIBUR, ModExtras.P_EXCALIBUR, SAOM.saoModTab, ModExtras.EXCALIBUR_TOOLTIP));
		registry.register(new ToolSword("calibur", ModExtras.M_CALIBUR, ModExtras.P_CALIBUR, SAOM.saoModTab, ModExtras.CALIBUR_TOOLTIP));
		registry.register(new ToolSword("black_one", ModExtras.M_GIGAS_CEDAR, ModExtras.P_BLACK_ONE, SAOM.saoModTab, ModExtras.BLACK_ONE_TOOLTIP));
		registry.register(new ToolSword("blue_rose_sword", ModExtras.M_BLUE_ROSE, ModExtras.P_BLUE_ROSE_SWORD, SAOM.saoModTab, ModExtras.BLUE_ROSE_SWORD_TOOLTIP));
		registry.register(new ToolSword("fragrant_olive_sword", ModExtras.M_FRAGRANT_OLIVE, ModExtras.P_FRAGRANT_OLIVE_SWORD, SAOM.saoModTab, ModExtras.FRAGRANT_OLIVE_SWORD_TOOLTIP));
		registry.register(new ToolAxe("dragon_bone_axe", ModExtras.M_DRAGON_BONE, ModExtras.P_DRAGON_BONE_AXE, SAOM.saoModTab, ModExtras.DRAGON_BONE_AXE_TOOLTIP));
		
		//Items
		SAOM.info("Registering Items: Items");
		registry.register(new ItemBase("dragon_ice_crystal", ModExtras.P_DRAGON_ICE, SAOM.saoModTab));
		registry.register(new ItemBase("dragon_ice_ingot", ModExtras.P_DRAGON_ICE, SAOM.saoModTab));
		
		registry.register(new GigasCedarBranch("gigas_cedar_branch", ModExtras.P_GIGAS_CEDAR_BRANCH, SAOM.saoModTab, ModExtras.GIGAS_BRANCH_TOOLTIP));
		
		SAOM.info("Registering Items Complete");
		
		registerOres();
		
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for (Item item : ITEMS) {
			SAOM.proxy.registerItemRenderer(item, 0, "inventory");
		}
		
		for (Block block : BLOCKS) {
			SAOM.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, "inventory");
		}
	}
	
	public static void registerOres() {
		SAOM.info("Registering OreDictionary Entries");
		
		OreDictionary.registerOre("stone", ModBlocks.MARBLE_BLOCK);
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.GIGAS_CEDAR_LOG, 1, OreDictionary.WILDCARD_VALUE));
		
		SAOM.info("Registering OreDictionary Entries Complete");
	}
}
