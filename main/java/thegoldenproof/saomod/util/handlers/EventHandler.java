package thegoldenproof.saomod.util.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thegoldenproof.saomod.SAOM;
import thegoldenproof.saomod.capabilities.Sharpenedness;
import thegoldenproof.saomod.capabilities.SharpenednessProvider;
import thegoldenproof.saomod.init.ModItems;
import thegoldenproof.saomod.items.GigasCedarBranch;
import thegoldenproof.saomod.util.Reference;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EventHandler {
	
	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent event) {
		if (event.getEntityLiving() instanceof EntityWither && Math.random() <= 0.2 && !event.getEntityLiving().getEntityWorld().isRemote && event.getSource().getTrueSource() instanceof EntityPlayer) {
			event.getEntityLiving().dropItem(ModItems.ELUCIDATOR, 1);
		}
	}
	
	@SubscribeEvent
	public static void loadLootTables(LootTableLoadEvent event) {
		if (event.getName().toString().equals("minecraft:chests/village_blacksmith")) {
			LootEntry entry = new LootEntryTable(new ResourceLocation("saom:village_blacksmith"), 1, 5, new LootCondition[] {}, "main");
			LootPool pool = new LootPool(new LootEntry[] {entry}, new LootCondition[] {}, new RandomValueRange(1, 1), new RandomValueRange(0), "saomDragonBoneAxe");
			event.getTable().addPool(pool);
		}
		
		if (event.getName().toString().equals("minecraft:chests/igloo_chest")) {
			LootEntry entry = new LootEntryTable(new ResourceLocation("saom:igloo_chest"), 1, 5, new LootCondition[] {}, "main");
			LootPool pool = new LootPool(new LootEntry[] {entry}, new LootCondition[] {}, new RandomValueRange(1, 1), new RandomValueRange(0), "saomBlueRoseSword");
			event.getTable().addPool(pool);
		}
	}
	
}
