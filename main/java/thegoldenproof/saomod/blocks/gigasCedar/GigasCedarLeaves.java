package thegoldenproof.saomod.blocks.gigasCedar;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thegoldenproof.saomod.SAOM;
import thegoldenproof.saomod.init.ModBlocks;
import thegoldenproof.saomod.util.IMetaName;
import thegoldenproof.saomod.util.handlers.RegistryHandler;

public class GigasCedarLeaves extends BlockLeaves implements IMetaName {
	
	public static final PropertyEnum<GigasCedarLog.EnumType> VARIANT = PropertyEnum.<GigasCedarLog.EnumType>create("variant", GigasCedarLog.EnumType.class, new Predicate<GigasCedarLog.EnumType>() {
		public boolean apply(@Nullable GigasCedarLog.EnumType apply) {
			return apply.getMeta() < 1;
		}
	});
	
	public GigasCedarLeaves(String name, CreativeTabs tab) {
		
		setCreativeTab(tab);
		setRegistryName(name);
		setUnlocalizedName(name);
		setHardness(10);
		setSoundType(SoundType.PLANT);
		setDefaultState(blockState.getBaseState().withProperty(VARIANT, GigasCedarLog.EnumType.DEFAULT).withProperty(CHECK_DECAY, Boolean.valueOf(false)).withProperty(DECAYABLE, Boolean.valueOf(true)));
		setResistance(30);
		RegistryHandler.BLOCKS.add(this);
		RegistryHandler.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	
	
	@Override
	public BlockRenderLayer getBlockLayer() {return Blocks.LEAVES.getBlockLayer();}
	
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {return false;}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {VARIANT,DECAYABLE,CHECK_DECAY});
	}
	
	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).getMeta()));
	}
	
	@Override
	public EnumType getWoodType(int meta) {return null;}
	
	@Override
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {return;}
	
	@Override
	protected int getSaplingDropChance(IBlockState state) {return 2;}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this), 1, ((GigasCedarLog.EnumType)state.getValue(VARIANT)).getMeta());
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return ((GigasCedarLog.EnumType)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		
		return this.getDefaultState().withProperty(VARIANT, GigasCedarLog.EnumType.byMetadata(0));
		
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = ((GigasCedarLog.EnumType)state.getValue(VARIANT)).getMeta();
		
		if (!((Boolean)state.getValue(DECAYABLE)).booleanValue()) {
			i |= 1;
		}
		
		if (!((Boolean)state.getValue(CHECK_DECAY)).booleanValue()) {
			i |= 2;
		}
		
		return i;
	}

	@Override
	public String getSpecialName(ItemStack stack) {return null;}
	
}
