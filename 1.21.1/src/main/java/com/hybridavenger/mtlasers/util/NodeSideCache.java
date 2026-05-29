package com.hybridavenger.mtlasers.util;

import com.hybridavenger.mtlasers.common.blockentities.LaserNodeBE;
import com.hybridavenger.mtlasers.common.containers.customhandler.LaserNodeItemHandler;
import it.unimi.dsi.fastutil.bytes.Byte2ByteMap;
import it.unimi.dsi.fastutil.bytes.Byte2ByteOpenHashMap;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NodeSideCache {
    public LaserNodeItemHandler itemHandler;
    public int overClocker;
    public final List<ExtractorCardCache> extractorCardCaches = new CopyOnWriteArrayList<>();
    public LaserNodeBE.LaserEnergyStorage laserEnergyStorage;
    public LaserNodeBE.LaserEnergyStorage energyStorage;
    public Byte2ByteMap myRedstoneFromSensors = new Byte2ByteOpenHashMap();  //Channel,Strength

    public NodeSideCache() {

    }

    public NodeSideCache(LaserNodeItemHandler itemHandler, int overClocker, LaserNodeBE.LaserEnergyStorage energyStorage) {
        this.itemHandler = itemHandler;
        this.overClocker = overClocker;
        this.energyStorage = energyStorage;
        this.laserEnergyStorage = energyStorage;
    }

    public void invalidateEnergy() {
        laserEnergyStorage = energyStorage;
    }


}
