package thegoldenproof.saomod.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import thegoldenproof.saomod.capabilities.Sharpenedness;
import thegoldenproof.saomod.capabilities.SharpenednessProvider;
import thegoldenproof.saomod.init.ModItems;

public class MarbleBlock extends BlockBase {
	
	public MarbleBlock(String name, Material material, CreativeTabs tab, String[] tooltip) {
		super(name, material, tab, tooltip);
	}
	
	public MarbleBlock(String name, Material material, CreativeTabs tab) {
		super(name, material, tab);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		BlockPos up = pos.up();
		int tickPhase = 0;
		if (getBlockFromItem(playerIn.inventory.getCurrentItem().getItem()) == Blocks.OBSIDIAN && worldIn.isAirBlock(up) && tickPhase == 0) {
			AxisAlignedBB above = new AxisAlignedBB(up);
			boolean hasBranch = false;
			EntityItem branch = null;
			EntityItem obsidian = null;
			List<EntityItem> entities = worldIn.getEntitiesWithinAABB(EntityItem.class, above);
			
			for(EntityItem ei : entities) {
				if (ei.getItem().getItem().equals(ModItems.GIGAS_CEDAR_BRANCH)) {
					hasBranch = true;
					branch = ei;
				}
			}
			
			if (!hasBranch) {
				playerIn.sendMessage(new TextComponentString("Missing Gigas Cedar Branch!"));
			} else if (hasBranch) {
				Sharpenedness capability = branch.getItem().getCapability(SharpenednessProvider.SHARPENED, null);
				
				capability.addProgress(1);
				
				if (capability.getProgress() % 200 == 0 && capability.getProgress() != 0) {
					int count = playerIn.inventory.getCurrentItem().getCount();
					//if (count > 1) {
						int index = playerIn.inventory.currentItem;
						playerIn.inventory.removeStackFromSlot(index);
						playerIn.inventory.setInventorySlotContents(index, new ItemStack(Blocks.OBSIDIAN, count - 1));
					//}
				}
				
				if(capability.getProgress() >= 600) {
					worldIn.removeEntity(branch);
					playerIn.addItemStackToInventory(new ItemStack(ModItems.BLACK_ONE));
				}
			}
			tickPhase++;
		} else {
			tickPhase = 0;
		}
		return true;
	}
	
}
