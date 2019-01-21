package thegoldenproof.saomod.blocks.gigasCedar;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialGigasCedar extends Material {
	
	public static final Material GIGAS_CEDAR = new MaterialGigasCedar(MapColor.BLACK).setRequiresTool();
	
	public MaterialGigasCedar(MapColor color) {
		super(color);
	}
	
	

}
