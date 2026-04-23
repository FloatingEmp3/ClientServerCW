package com.mycompany.clientservercw.storage;

import com.mycompany.clientservercw.model.Room;
import com.mycompany.clientservercw.model.Sensor;
import java.util.HashMap;
import java.util.Map;

//Eduardo Lamasanu w2078922

public class DataStore {

    public static Map<String, Room> rooms = new HashMap<>();
    public static Map<String, Sensor> sensors = new HashMap<>();

}