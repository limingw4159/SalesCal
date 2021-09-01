package config;

import entities.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BundleConfig {

    public Map<String, List<Bundle>> initialBundles() {
        Map<String, List<Bundle>> bundles = new HashMap<>();
        Bundle bundle = new Bundle(5, 450,0);
        Bundle bundle1 = new Bundle(10, 800,5);
        Bundle bundle2 = new Bundle(3, 427.50,0);
        Bundle bundle3 = new Bundle(6, 810,3);
        Bundle bundle4 = new Bundle(9, 1147.50,6);
        Bundle bundle5 = new Bundle(3, 570,0);
        Bundle bundle6 = new Bundle(5, 900,3);
        Bundle bundle7 = new Bundle(9, 1530,6);
        List<Bundle> lBundle = new ArrayList<>() {{
            add(bundle);
            add(bundle1);
        }};
        List<Bundle> lBundle1 = new ArrayList<>() {{
            add(bundle2);
            add(bundle3);
            add(bundle4);
        }};
        List<Bundle> lBundle2 = new ArrayList<>() {{
            add(bundle5);
            add(bundle6);
            add(bundle7);
        }};
        bundles.put("IMG", lBundle);
        bundles.put("FLAC", lBundle1);
        bundles.put("VID", lBundle2);
        return bundles;
    }
}
