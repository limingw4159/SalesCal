package config;

import entities.Bundle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BundleConfig {

    Map<String, List<Bundle>> existBundles;

    public Map<String, List<Bundle>> initialBundles() {

        existBundles = new HashMap<>();

        List<Bundle> imgBundle = new ArrayList<>() {{
            add(new Bundle(5, 450, 0));
            add(new Bundle(10, 800, 5));
        }};

        List<Bundle> flacBundle = new ArrayList<>() {{
            add(new Bundle(3, 427.50, 0));
            add(new Bundle(6, 810, 3));
            add(new Bundle(9, 1147.50, 6));
        }};

        List<Bundle> vidBundle = new ArrayList<>() {{
            add(new Bundle(3, 570, 0));
            add(new Bundle(5, 900, 3));
            add(new Bundle(9, 1530, 5));
        }};

        existBundles.put("IMG", imgBundle);
        existBundles.put("FLAC", flacBundle);
        existBundles.put("VID", vidBundle);

        return existBundles;
    }
}
