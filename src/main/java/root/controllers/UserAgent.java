package root.controllers;

import net.sf.uadetector.*;
public class UserAgent {
    private String os;
    private String browser;
    private String deviceType;

    public UserAgent( ) {

    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void printUa(ReadableUserAgent agent){
        setBrowser(agent.getName());
        setOs(agent.getOperatingSystem().getName());
        setDeviceType(agent.getDeviceCategory().getName());
    }

}
