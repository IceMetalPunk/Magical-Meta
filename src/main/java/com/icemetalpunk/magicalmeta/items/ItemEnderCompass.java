package com.icemetalpunk.magicalmeta.items;

import javax.annotation.Nullable;

import com.icemetalpunk.api.item.BasicItem;
import com.icemetalpunk.magicalmeta.MagicalMeta;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEnderCompass extends BasicItem {

	public ItemEnderCompass() {
		super(MagicalMeta.MODID, "ender_compass", MagicalMeta.tab);
		this.setMaxStackSize(1);
		addModelOverrides();
	}

	protected void addModelOverrides() {
		this.addPropertyOverride(new ResourceLocation("angle"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			double rotation;
			@SideOnly(Side.CLIENT)
			double rota;
			@SideOnly(Side.CLIENT)
			long lastUpdateTick;

			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				MagicalMeta.getLogger().info("Applying angle override, maybe?");
				if (entityIn == null && !stack.isOnItemFrame()) {
					return 0.0F;
				} else {
					boolean flag = entityIn != null;
					Entity entity = (Entity) (flag ? entityIn : stack.getItemFrame());

					if (worldIn == null) {
						worldIn = entity.world;
					}

					double d0;

					double d1 = flag ? (double) entity.rotationYaw : this.getFrameRotation((EntityItemFrame) entity);
					d1 = MathHelper.positiveModulo(d1 / 360.0D, 1.0D);
					double d2 = this.getPosToAngle(worldIn, stack, entity) / (Math.PI * 2D);
					d0 = 0.5D - (d1 - 0.25D - d2);

					if (flag) {
						d0 = this.wobble(worldIn, d0);
					}

					return MathHelper.positiveModulo((float) d0, 1.0F);
				}
			}

			@SideOnly(Side.CLIENT)
			private double wobble(World worldIn, double p_185093_2_) {
				if (worldIn.getTotalWorldTime() != this.lastUpdateTick) {
					this.lastUpdateTick = worldIn.getTotalWorldTime();
					double d0 = p_185093_2_ - this.rotation;
					d0 = MathHelper.positiveModulo(d0 + 0.5D, 1.0D) - 0.5D;
					this.rota += d0 * 0.1D;
					this.rota *= 0.8D;
					this.rotation = MathHelper.positiveModulo(this.rotation + this.rota, 1.0D);
				}

				return this.rotation;
			}

			@SideOnly(Side.CLIENT)
			private double getFrameRotation(EntityItemFrame p_185094_1_) {
				return (double) MathHelper.wrapDegrees(180 + p_185094_1_.facingDirection.getHorizontalIndex() * 90);
			}

			@SideOnly(Side.CLIENT)
			private double getPosToAngle(World world, ItemStack stack, Entity player) {
				BlockPos blockpos = world.getSpawnPoint();

				if (stack.hasTagCompound()) {
					NBTTagCompound tag = stack.getTagCompound();
					int[] pos = tag.getIntArray("LinkedTo");
					if (pos.length == 2) {
						blockpos = new BlockPos(pos[0], player.posY, pos[2]);
					}
				}

				return Math.atan2((double) blockpos.getZ() - player.posZ, (double) blockpos.getX() - player.posX);
			}
		});
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);

		if (worldIn.isRemote) {
			return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
		}

		BlockPos pos = playerIn.getPosition();

		NBTTagCompound nbt;
		if (stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
		} else {
			nbt = new NBTTagCompound();
		}

		nbt.setIntArray("LinkedTo", new int[] { pos.getX(), pos.getZ() });
		stack.setTagCompound(nbt);

		String msg = I18n.format("item." + MagicalMeta.MODID + ".ender_compass.set",
				new Object[] { pos.getX(), pos.getZ() });
		Minecraft.getMinecraft().ingameGUI.setOverlayMessage(msg, false);

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
}
