package root.URLShortener;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;


public class IpDevice   {

    private String ip;
    private String device;

    public IpDevice(String ip, String device) {
        this.ip = ip;
        this.device = device;
    }

    public IpDevice() {

    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public List<IpDevice> createIpDeviceList(List<Visit> visits){
        List<IpDevice> list = new ArrayList<>();
        if (visits!=null){
            for (int i = 0;i<visits.size();i++){
                    list.add((new IpDevice(visits.get(i).getIp(),visits.get(i).getDevice())));
            }
        }
        return list;
    }

    public List<IpDevice> createIpDeviceListTemp(List<TempVisits> visits){
        List<IpDevice> list = new ArrayList<>();
        if (visits!=null){
            for (int i = 0;i<visits.size();i++){
                list.add((new IpDevice(visits.get(i).getIp(),visits.get(i).getDevice())));
            }
        }
        return list;
    }
}
