package com.rjbroker;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.IllegalViewOperationException;

import java.io.IOException;
import java.util.Properties;
import java.util.Map;


import com.rjbroker.Utils;

import io.moquette.BrokerConstants;
import io.moquette.broker.Server;
import io.moquette.broker.config.IConfig;
import io.moquette.broker.config.MemoryConfig;

public class StartBroker extends ReactContextBaseJavaModule {

    public static Server server;
    //Properties props = new Properties();
    //final Properties configProps = new Properties();
    private static final String BrokerIP= Utils.getIPAddress(true);

    public Properties getConfig() {
        Properties props = new Properties();
        props.setProperty(BrokerConstants.PORT_PROPERTY_NAME,  "1883");
        props.setProperty(BrokerConstants.HOST_PROPERTY_NAME, Utils.getIPAddress(true));
        return props;
    }


    public StartBroker(ReactApplicationContext reactContext) {
        super(reactContext); //required by React Native
    }

    @Override
    //getName is required to define the name of the module represented in JavaScript
    public String getName() {
        return "StartBroker";
    }


    @ReactMethod
    public void startBroker(Callback errorCallback, Callback successCallback) {
        try {
            server = ServerInstance.getServerInstance();
            Properties props = getConfig();
            IConfig server_config = new MemoryConfig(props);
            System.out.println("Starting Server at "+ BrokerIP);
            server.startServer(server_config);

            System.out.println("Server : Started..");
            successCallback.invoke("Server : Started");
        } catch (IllegalViewOperationException | IOException e) {
            errorCallback.invoke(e.getMessage());
        }
    }
    @ReactMethod
    public void stopBroker(Callback errorCallback, Callback successCallback){
        System.out.println("Stoping Brokker");
        server.stopServer();
        successCallback.invoke("Server Stopped");
    }
}