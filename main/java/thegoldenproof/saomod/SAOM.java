package thegoldenproof.saomod;

import java.lang.reflect.Field;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import thegoldenproof.saomod.capabilities.Sharpenedness;
import thegoldenproof.saomod.capabilities.SharpenednessFactory;
import thegoldenproof.saomod.capabilities.SharpenednessStorage;
import thegoldenproof.saomod.command.SystemCommandCmd;
import thegoldenproof.saomod.init.ModRecipes;
import thegoldenproof.saomod.proxy.IProxy;
import thegoldenproof.saomod.tabs.SaoModTab;
import thegoldenproof.saomod.util.Reference;
import thegoldenproof.saomod.util.handlers.RegistryHandler;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, useMetadata = true)
public class SAOM {
	
	@Instance
	public static SAOM instance;
	
	private static Logger logger;
	public static final CreativeTabs saoModTab = new SaoModTab("saomodtab");
	
	@SidedProxy(clientSide = "thegoldenproof.saomod.proxy.ClientProxy", serverSide = "thegoldenproof.saomod.proxy.ServerProxy")
	public static IProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.logPhysicalSide();
		registerStuff();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		ModRecipes.init();
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
		
	}
	
	@EventHandler
	public static void serverStarting(FMLServerStartingEvent event) {
		//event.registerServerCommand(new SystemCommandCmd());
	}
	
	private static void registerStuff() {
		CapabilityManager.INSTANCE.register(Sharpenedness.class, new SharpenednessStorage(), new SharpenednessFactory());
		LootTableList.register(new ResourceLocation("saom:village_blacksmith"));
	}
	
	private static Logger getLogger() {
		if (logger == null) {
			final Logger temp_logger = LogManager.getLogger();
			temp_logger.error("[" + SAOM.class.getSimpleName() + "]: getLogger called before logger has been initalised! Providing default logger");
			return temp_logger;
		}
		return logger;
	}
	
	
	
	//Logging
	
	public static void debug(final Object... messages) {
		for (final Object msg : messages) {
			getLogger().debug(msg);
		}
	}
	
	public static void info(final Object... messages) {
		for (final Object msg : messages) {
			getLogger().info(msg);
		}
	}
	
	public static void warn(final Object... messages) {
		for (final Object msg : messages) {
			getLogger().warn(msg);
		}
	}
	
	public static void error(final Object... messages) {
		for (final Object msg : messages) {
			getLogger().error(msg);
		}
	}
	
	public static void fatal(final Object... messages) {
		for (final Object msg : messages) {
			getLogger().fatal(msg);
		}
	}
	
	public static void dump(final Object... objects) {
		for (final Object obj : objects) {
			final Field[] fields = obj.getClass().getDeclaredFields();
			info("Dump of " + obj + ":");
			for (int i = 0; i < fields.length; i++) {
				try {
					fields[i].setAccessible(true);
					info(fields[i].getName() + " - " + fields[i].get(obj));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					info("Error getting field " + fields[i].getName());
					info(e.getLocalizedMessage());
				}
			}
		}
	}
	
}
