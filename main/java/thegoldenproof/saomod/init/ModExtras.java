package thegoldenproof.saomod.init;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModExtras {
	
	//Block-Related
		
		
		
	//Item-Related
		//Tooltips
		public static final String[] GIGAS_BRANCH_TOOLTIP = {
				"To get, throw a Dragon Bone Axe on top of a Gigas Cedar Log that is above y=200 and right click the log with an empty hand",
				"\nTo sharpen, throw on a marble block with a piece of obsidian and right-click the marble with an empty hand 20 times\n"
			};
		public static final String[] GIGAS_SAPLING_TOOLTIP = {"Very processing intensive. Please only use bone meal once, and wait until the tree grows to do anything else, otherwise whatever you do will be rolled back to the time of the sapling growing."};
		public static final String[] BLACK_ONE_TOOLTIP = {"Read Gigas Cedar Branch tooltip for info on how to make"};
		
		//Object Priorities
		public static final int P_ELUCIDATOR = 41;
		public static final int P_DARK_REPULSER = 40;
		public static final int P_BLACK_ONE = 46;
		public static final int P_BLUE_ROSE_SWORD = 45;
		public static final int P_DRAGON_BONE_AXE = 10;
		public static final int P_GIGAS_CEDAR_BRANCH = 25;
		public static final int P_DRAGON_ICE = 5;
		
		//Materials
		public static final ToolMaterial M_ELUCIDATOR = EnumHelper.addToolMaterial("m_elucidator", P_ELUCIDATOR, P_ELUCIDATOR*60, P_ELUCIDATOR/4.0f, P_ELUCIDATOR/2.0f, 15);
		public static final ToolMaterial M_DARK_REPULSER = EnumHelper.addToolMaterial("m_dark_repulser", P_DARK_REPULSER, P_DARK_REPULSER*60, P_DARK_REPULSER/46.0f, P_DARK_REPULSER/2.0f, 15);
		public static final ToolMaterial M_GIGAS_CEDAR = EnumHelper.addToolMaterial("m_gigas_cedar", P_BLACK_ONE, P_BLACK_ONE*60, P_BLACK_ONE/4.0f, P_BLACK_ONE/2.0f, 15);
		public static final ToolMaterial M_BLUE_ROSE = EnumHelper.addToolMaterial("m_blue_rose", P_BLUE_ROSE_SWORD, P_BLUE_ROSE_SWORD*60, P_BLUE_ROSE_SWORD/4.0f, P_BLUE_ROSE_SWORD/2.0f, 15);
		public static final ToolMaterial M_DRAGON_BONE = EnumHelper.addToolMaterial("m_dragon_bone", P_DRAGON_BONE_AXE, P_DRAGON_BONE_AXE*60, P_DRAGON_BONE_AXE/4.0f, P_DRAGON_BONE_AXE/2.0f, 10);
		
		
	
}
