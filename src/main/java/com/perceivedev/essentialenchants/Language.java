package com.perceivedev.essentialenchants;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.configuration.file.YamlConfiguration;

import com.perceivedev.essentialenchants.util.TextUtils;

/**
 * @author Rayzr
 *
 */
public class Language {

    private static final Pattern KEY_PATTERN = Pattern.compile("\\[\\[([a-zA-Z_.-]+)\\]\\]");
    private static final Pattern FORMATTER_OBJECT_PATTERN = Pattern.compile("\\{(\\d+)\\}");

    private Map<String, String> messages = new HashMap<>();

    public void load(YamlConfiguration config) {
        Map<String, String> raw = new LinkedHashMap<>();
        config.getKeys(true).forEach(key -> {
            raw.put(key, config.get(key).toString());
        });
        raw.entrySet().forEach(e -> {
            String msg = e.getValue();
            Matcher matcher = KEY_PATTERN.matcher(msg);
            while (matcher.find()) {
                msg = msg.replace(matcher.group(0), raw.get(matcher.group(1)));
            }
            messages.put(e.getKey(), msg);
        });
    }

    public String tr(String key, Object... formattingObjects) {
        if (!messages.containsKey(key)) {
            return String.format("no translation for [%s]", key);
        }
        String base = messages.get(key);
        Matcher matcher = FORMATTER_OBJECT_PATTERN.matcher(base);
        while (matcher.find()) {
            int pos = Integer.parseInt(matcher.group(1));
            if (pos > formattingObjects.length - 1) {
                continue;
            }
            base = base.replace(matcher.group(0), formattingObjects[pos].toString());
        }
        return TextUtils.colorize(base);
    }

}
