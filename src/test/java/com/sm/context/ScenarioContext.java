package com.sm.context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private static Map<String, Object> scenarioContext = new HashMap<>();

    public ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    public static void setContext(Context key, Object value) {
        scenarioContext.put(key.toString(), value);
    }

    public static Object getContext(Context key){
        return scenarioContext.get(key.toString());
    }

    public static Boolean isContains(Context key){
        return scenarioContext.containsKey(key.toString());
    }

}
