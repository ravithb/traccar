package org.traccar.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.BaseDataHandler;
import org.traccar.database.DeviceManager;
import org.traccar.database.FirebaseRTDBManager;
import org.traccar.model.Device;
import org.traccar.model.Position;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.channel.ChannelHandler;

@ChannelHandler.Sharable
public class FirebaseDataHandler extends BaseDataHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseDataHandler.class);

    private final DeviceManager deviceManager;

    private final FirebaseRTDBManager firebaseDataManager;

    private final Map<Long, Device> deviceLookup = new HashMap<Long, Device>();

    public FirebaseDataHandler(DeviceManager deviceManager, FirebaseRTDBManager firebaseDataManager) {
        this.deviceManager = deviceManager;
        this.firebaseDataManager = firebaseDataManager;
        refreshLookup();
    }

    private synchronized void refreshLookup() {
        deviceLookup.clear();
        for (Device d : this.deviceManager.getAllDevices()) {
            deviceLookup.put(d.getId(), d);
        }
    }

    @Override
    protected Position handlePosition(Position position) {
        ObjectMapper objMapper = new ObjectMapper();
        try {
            Device dev = deviceLookup.get(position.getDeviceId());
            if (position.getAttributes().containsKey("type")) {
                switch (position.getInteger("type")) {
                case 0x06:
                    this.firebaseDataManager.addCommandResponse(dev, position, true);
                    break;
                case 0x11:
                    this.firebaseDataManager.addCommandResponse(dev, null, false);
                    break;
                default:
                    break;
                }
                LOGGER.info("Command Response : " + objMapper.writeValueAsString(position));
            } else {
//            LOGGER.info("Device : " + dev.getName());
                String jsonStr = objMapper.writeValueAsString(position);
//            LOGGER.info("POSITION IN FIREBASE " + jsonStr);
                this.firebaseDataManager.addEvent(dev, position);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return position;
    }

}
