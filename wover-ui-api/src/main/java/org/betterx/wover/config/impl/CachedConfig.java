package org.betterx.wover.config.impl;

import de.ambertation.wunderlib.configs.ConfigFile;
import org.betterx.wover.config.api.Configs;
import org.betterx.wover.config.api.MainConfig;
import org.betterx.wover.entrypoint.LibWoverUi;
import org.betterx.wover.ui.api.VersionChecker;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

public class CachedConfig extends ConfigFile {
    public final StringValue lastCheckDate = new StringValue(
            VERSION_CATEGORY,
            "last_check_date",
            "never"
    ).setGroup(MainConfig.GENERAL_GROUP)
     .hideInUI();

    public final StringValue lastJson = new StringValue(
            VERSION_CATEGORY,
            "last_json",
            "{}"
    ).setGroup(MainConfig.GENERAL_GROUP)
     .hideInUI();

    public final static String VERSION_CATEGORY = "version";
    public static final CachedConfig INSTANCE = Configs.register(CachedConfig::new);

    public CachedConfig() {
        super(LibWoverUi.C, "cached");
    }

    public String lastVersionJson() {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(lastJson.get());
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    public void setLastVersionJson(String json) {
        lastJson.set(Base64.getUrlEncoder().encodeToString(json.getBytes(StandardCharsets.UTF_8)));
    }

    public Instant lastCheckDate() {
        String d = lastCheckDate.get();
        if (d.trim().toLowerCase().equals("never")) {
            return Instant.now().minus(VersionChecker.WAIT_FOR_DAYS + 1, ChronoUnit.DAYS);
        }
        return Instant.parse(d);
    }

    public void setLastCheckDate() {
        lastCheckDate.set(Instant.now().toString());
    }

    public static void ensureStaticallyLoaded() {
        //NO-OP
    }
}
