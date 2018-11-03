package thegoldenproof.saomod.blocks.gigasCedar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.progwml6.natura.world.worldgen.trees.BaseTreeGenerator;
import com.progwml6.natura.world.worldgen.trees.overworld.RedwoodTreeGenerator;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thegoldenproof.saomod.SAOM;
import thegoldenproof.saomod.init.ModBlocks;
import thegoldenproof.saomod.util.IMetaName;
import thegoldenproof.saomod.util.handlers.RegistryHandler;

public class GigasCedarSapling extends BlockBush implements IMetaName, IGrowable {
	
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
	ArrayList<String> tooltips = new ArrayList<String>(0);
	
	public static final PropertyEnum<GigasCedarLog.EnumType> VARIANT = PropertyEnum.<GigasCedarLog.EnumType>create("variant", GigasCedarLog.EnumType.class, new Predicate<GigasCedarLog.EnumType>() {
		public boolean apply(@Nullable GigasCedarLog.EnumType apply) {
			return apply.getMeta() < 1;
		}
	});
	
	public GigasCedarSapling(String name, CreativeTabs tab) {
		this(name, tab, new String[] {""});
		
	}
	
	public GigasCedarSapling(String name, CreativeTabs tab, String[] tooltip) {
		
		for (String s : tooltip) {
			tooltips.add(s);
		}
		
		setCreativeTab(tab);
		setRegistryName(name);
		setUnlocalizedName(name);
		setSoundType(SoundType.PLANT);
		setHardness(10);
		setDefaultState(blockState.getBaseState().withProperty(VARIANT, GigasCedarLog.EnumType.DEFAULT).withProperty(STAGE, Integer.valueOf(0)));
		setHarvestLevel("sword", 0);
		setResistance(30);
		
		RegistryHandler.BLOCKS.add(this);
		RegistryHandler.ITEMS.add(new ItemBlock(getDefaultState().getBlock()).setRegistryName(this.getRegistryName()));
		
	}
	
	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		if (!tooltips.isEmpty()) {
			for (String s : tooltips) {
				tooltip.add(s);
			}
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {VARIANT, STAGE});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		
		return this.getDefaultState().withProperty(VARIANT, GigasCedarLog.EnumType.byMetadata(0)).withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
		
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i |= ((GigasCedarLog.EnumType)state.getValue(VARIANT)).getMeta();
		i |= ((Integer)state.getValue(STAGE)).intValue() << 3;
		return i;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {return false;}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {return false;}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {return NULL_AABB;}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {return SAPLING_AABB;}

	@Override
	public String getSpecialName(ItemStack stack) {
		return null;
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {return true;}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {return (double)worldIn.rand.nextFloat() < 1D;}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		
		if (((Integer)state.getValue(STAGE)).intValue() == 0) {
			worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
		} else {
			generateTree(worldIn, rand, pos, state);
		}
		
	}
	
	
	
    public void generateTree(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos))
        {
            return;
        }

        BaseTreeGenerator gen = new BaseTreeGenerator();

        IBlockState bark;
        IBlockState heart;
        IBlockState root;
        IBlockState leaves;

        bark = ModBlocks.GIGAS_CEDAR_LOG.getDefaultState();
        heart = ModBlocks.GIGAS_CEDAR_LOG.getDefaultState();
        root = ModBlocks.GIGAS_CEDAR_LOG.getDefaultState();
        leaves = ModBlocks.GIGAS_CEDAR_LEAVES.getDefaultState();

        gen = new RedwoodTreeGenerator(bark, heart, root, leaves);
        
        gen.generateTree(rand, worldIn, pos);

        if (worldIn.isAirBlock(pos))
        {
            worldIn.setBlockState(pos, state, 4);
        }
    }
	
	
	
	
//	public void generateTree(World world, Random rand, BlockPos pos, IBlockState state) {
//		
//		if (TerrainGen.saplingGrowTree(world, rand, pos)) return;
//		WorldGenerator gen = (WorldGenerator)(rand.nextInt(10) == 0 ? new WorldGenBigTree(false) : new WorldGenTrees(false));
//		boolean flag = false;
//		
//		gen = new WorldGenGigasCedar();
//		
//		IBlockState iblockstate = Blocks.AIR.getDefaultState();
//		if (flag) {
//			world.setBlockState(pos.add(1, 0, 0), iblockstate, 4);
//			world.setBlockState(pos.add(0, 0, 0), iblockstate, 4);
//			world.setBlockState(pos.add(0, 0, 1), iblockstate, 4);
//			world.setBlockState(pos.add(1, 0, 1), iblockstate, 4);
//		} else {
//			world.setBlockState(pos, iblockstate, 4);
//		}
//		
//		if (!gen.generate(world, rand, pos)) {
//			if (flag) {
//				world.setBlockState(pos.add(1, 0, 0), iblockstate, 4);
//				world.setBlockState(pos.add(0, 0, 0), iblockstate, 4);
//				world.setBlockState(pos.add(0, 0, 1), iblockstate, 4);
//				world.setBlockState(pos.add(1, 0, 1), iblockstate, 4);
//			} else {
//				world.setBlockState(pos, iblockstate, 4);
//			}
//		}
//		
//	}

	@Override
	protected boolean canSustainBush(IBlockState state) {
        return state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.FARMLAND;
	}
	
	
	
}
