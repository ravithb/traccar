package org.traccar.database;

import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.config.Config;
import org.traccar.config.Keys;
import org.traccar.model.Device;
import org.traccar.model.EventSummary;
import org.traccar.model.Position;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRTDBManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseRTDBManager.class);
    private Config config;
    private FirebaseDatabase db;

    public FirebaseRTDBManager(Config cfg) {
        this.config = cfg;
        initDB();
    }

    public void initDB() {
        try {
            FileInputStream serviceAccount = new FileInputStream(
                    config.getString(Keys.FIREBASE_SERVICE_ACCOUNT_KEY_FILE));

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(config.getString(Keys.FIREBASE_URL)).build();

            FirebaseApp.initializeApp(options);
            db = FirebaseDatabase.getInstance();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public ApiFuture<Void> addEvent(Device dev, Position position) {
        if (db.getReference() == null) {
            LOGGER.error("DB reference is null");
            return null;
        }
        return db.getReference().child("events").child(dev.getUniqueId()).setValueAsync(EventSummary.create(position));
    }

    public void addCommandResponse(Device dev, Position position, boolean success) {
        if (db.getReference() == null) {
            LOGGER.error("DB reference is null");
            return;
        }
        db.getReference().child("commandResponses").child(dev.getUniqueId())
        .child("success").setValueAsync(success);
        db.getReference().child("commandResponses").child(dev.getUniqueId())
        .child("response").setValueAsync(EventSummary.create(position));
    }
}
