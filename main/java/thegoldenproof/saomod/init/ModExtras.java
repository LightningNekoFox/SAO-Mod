package thegoldenproof.saomod.init;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModExtras {
	
	//Block-Related
		
		
		
	//Item-Related/ItemBlock-Related
		//Tooltips
			public static final String[] GIGAS_BRANCH_TOOLTIP = {
					"To get, throw a Dragon Bone Axe on top of a Gigas Cedar Log that is above y=200 and right click the log with an empty hand",
					"\nTo sharpen, throw on a marble block right-click the marble with a piece of obsidian\n"
				};
			public static final String[] GIGAS_SAPLING_TOOLTIP = {"Very processing intensive. Please only use bone meal once, and wait until the tree grows to do anything else, otherwise whatever you do will be rolled back to the time of the sapling growing."};
			public static final String[] BLACK_ONE_TOOLTIP = {"Read Gigas Cedar Branch tooltip for info on how to make"};
			public static final String[] ELUCIDATOR_TOOLTIP = {"A 20% drop chance from a player killing the wither boss in The End"};
			public static final String[] DRAGON_BONE_AXE_TOOLTIP = {"1/5 spawn chance in village blacksmith chests"};
			public static final String[] BLUE_ROSE_SWORD_TOOLTIP = {"1/5 spawn chance in igloos"};
			public static final String[] FRAGRANT_OLIVE_SWORD_TOOLTIP = {"Currently unattainable"};
			public static final String[] EXCALIBUR_TOOLTIP = {"Currently unattainable, except by System Commands"};
			public static final String[] CALIBUR_TOOLTIP = {"Currently unattainable, except by System Commands"};
		
		//Object Priorities
			public static final int P_ELUCIDATOR = 41;
			public static final int P_DARK_REPULSER = 40;
			public static final int P_EXCALIBUR = 65;
			public static final int P_CALIBUR = 40;
			public static final int P_BLACK_ONE = 46;
			public static final int P_BLUE_ROSE_SWORD = 45;
			public static final int P_FRAGRANT_OLIVE_SWORD = 60;
			public static final int P_DRAGON_BONE_AXE = 10;
			public static final int P_GIGAS_CEDAR_BRANCH = 25;
			public static final int P_DRAGON_ICE = 5;
		
		//Materials
			public static final ToolMaterial M_ELUCIDATOR = EnumHelper.addToolMaterial("m_elucidator", P_ELUCIDATOR, P_ELUCIDATOR*60, P_ELUCIDATOR/4.0f, P_ELUCIDATOR/1.5f, 15);
			public static final ToolMaterial M_DARK_REPULSER = EnumHelper.addToolMaterial("m_dark_repulser", P_DARK_REPULSER, P_DARK_REPULSER*60, P_DARK_REPULSER/4.0f, P_DARK_REPULSER/1.5f, 15);
			public static final ToolMaterial M_EXCALIBUR = EnumHelper.addToolMaterial("m_excalibur", P_EXCALIBUR, P_EXCALIBUR*60, P_EXCALIBUR/4.0f, P_EXCALIBUR/1.5f, 15);
			public static final ToolMaterial M_CALIBUR = EnumHelper.addToolMaterial("m_calibur", P_CALIBUR, P_CALIBUR*60, P_CALIBUR/4.0f, P_CALIBUR/1.5f, 15);
			public static final ToolMaterial M_GIGAS_CEDAR = EnumHelper.addToolMaterial("m_gigas_cedar", P_BLACK_ONE, P_BLACK_ONE*60, P_BLACK_ONE/4.0f, P_BLACK_ONE/1.5f, 15);
			public static final ToolMaterial M_BLUE_ROSE = EnumHelper.addToolMaterial("m_blue_rose", P_BLUE_ROSE_SWORD, P_BLUE_ROSE_SWORD*60, P_BLUE_ROSE_SWORD/4.0f, P_BLUE_ROSE_SWORD/1.5f, 15);
			public static final ToolMaterial M_FRAGRANT_OLIVE = EnumHelper.addToolMaterial("m_fragrant_olive", P_FRAGRANT_OLIVE_SWORD, P_FRAGRANT_OLIVE_SWORD*60, P_FRAGRANT_OLIVE_SWORD/4.0f, P_FRAGRANT_OLIVE_SWORD/1.5f, 15);
			
			public static final ToolMaterial M_DRAGON_BONE = EnumHelper.addToolMaterial("m_dragon_bone", P_DRAGON_BONE_AXE, P_DRAGON_BONE_AXE*60, P_DRAGON_BONE_AXE/4.0f, P_DRAGON_BONE_AXE/1.5f, 10);
		
		
	
}
