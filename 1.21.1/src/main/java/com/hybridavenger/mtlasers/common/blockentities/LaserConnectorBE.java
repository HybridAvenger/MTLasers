package com.hybridavenger.mtlasers.common.blockentities;

import com.hybridavenger.mtlasers.common.blockentities.basebe.BaseLaserBE;
import com.hybridavenger.mtlasers.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class LaserConnectorBE extends BaseLaserBE {
    public LaserConnectorBE(BlockPos pos, BlockState state) {
        super(Registration.LaserConnector_BE.get(), pos, state);
    }
}
