package thegoldenproof.saomod.blocks.gigasCedar;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thegoldenproof.saomod.SAOM;
import thegoldenproof.saomod.init.ModBlocks;
import thegoldenproof.saomod.init.ModItems;
import thegoldenproof.saomod.util.IMetaName;
import thegoldenproof.saomod.util.handlers.RegistryHandler;

public class GigasCedarLog extends BlockLog implements IMetaName {
	
	public static final PropertyEnum<GigasCedarLog.EnumType> VARIANT = PropertyEnum.<GigasCedarLog.EnumType>create("variant", GigasCedarLog.EnumType.class, new Predicate<GigasCedarLog.EnumType>() {
		public boolean apply(@Nullable GigasCedarLog.EnumType apply) {
			return apply.getMeta() < 1;
		}
	});

	public GigasCedarLog(String name, CreativeTabs tab) {
		
		setCreativeTab(tab);
		setRegistryName(name);
		setUnlocalizedName(name);
		setHardness(90);
		setSoundType(SoundType.METAL);
		setDefaultState(blockState.getBaseState().withProperty(VARIANT, GigasCedarLog.EnumType.DEFAULT).withProperty(LOG_AXIS, EnumAxis.Y));
		setHarvestLevel("sword", 4);
		setResistance(10000);
		RegistryHandler.BLOCKS.add(this);
		RegistryHandler.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		AxisAlignedBB above = new AxisAlignedBB(pos.up());
		boolean hasAxe = false;
		List<EntityItem> entities = worldIn.getEntitiesWithinAABB(EntityItem.class, above);
		
		for(EntityItem ei : entities) {
			if (ei.getItem().getItem().equals(ModItems.DRAGON_BONE_AXE)) {
				hasAxe = true;
			}
		}
		
		if (pos.getY() > 200 && playerIn.inventory.getCurrentItem().isEmpty() && hasAxe) {
			playerIn.addItemStackToInventory(new ItemStack(ModItems.GIGAS_CEDAR_BRANCH, 1));
			worldIn.destroyBlock(pos, false);
		}
		return false;
	}
	
	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		
		return super.canHarvestBlock(world, pos, player);
		
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return ((GigasCedarLog.EnumType)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState().withProperty(VARIANT, GigasCedarLog.EnumType.byMetadata(0));
		
		switch (meta & 6) {
		case 0:
			state = state.withProperty(LOG_AXIS, EnumAxis.Y); break;
		case 1:
			state = state.withProperty(LOG_AXIS, EnumAxis.X); break;
		case 2:
			state = state.withProperty(LOG_AXIS, EnumAxis.Z); break;
		default:
			state = state.withProperty(LOG_AXIS, EnumAxis.NONE); break;
		}
		
		return state;
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((GigasCedarLog.EnumType)state.getValue(VARIANT)).getMeta();
		
		switch ((BlockLog.EnumAxis)state.getValue(LOG_AXIS)) {
		case X:
			i |= 1; break;
		case Y:
			i |= 2; break;
		case Z:
			i |= 3; break;
		}
		
		return i;
		
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {VARIANT, LOG_AXIS});
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this), 1, ((GigasCedarLog.EnumType)state.getValue(VARIANT)).getMeta());
	}
	
	
	
	
	
	public static enum EnumType implements IStringSerializable {
		DEFAULT(0, "default");
		
		public static final GigasCedarLog.EnumType[] META_LOOKUP = new GigasCedarLog.EnumType[values().length];
		private final int meta;
		private final String name, unlocalizedName;
		
		private EnumType(int meta, String name) {
			this(meta, name, name);
		}
		
		private EnumType(int meta, String name, String unlocalizedName) {
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}

		@Override
		public String getName() {
			return this.name;
		}
		
		public int getMeta() {
			return this.meta;
		}
		
		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}
		
		@Override
		public String toString() {
			return this.name();
		}
		
		public static GigasCedarLog.EnumType byMetadata(int meta) {
			return META_LOOKUP[meta];
		}
		
		static
		{
			for (GigasCedarLog.EnumType gigascedarlog$enumtype : values()) {
				META_LOOKUP[gigascedarlog$enumtype.getMeta()] = gigascedarlog$enumtype;
			}
		}
		
	}



	@Override
	public String getSpecialName(ItemStack stack) {
		return GigasCedarLog.EnumType.values()[stack.getItemDamage()].getName();
	}

}
