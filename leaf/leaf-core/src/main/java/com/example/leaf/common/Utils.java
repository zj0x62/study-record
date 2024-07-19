package com.example.leaf.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

/**
 * @Author: zhoujing
 * @Date: 2024/7/18 9:28
 * @Description:
 */
public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    public static String getIP() {
        String ip;
        try {
            List<String> ipList = getHostAddress(null);
            ip = (!ipList.isEmpty()) ? ipList.get(0) : "";
        } catch (Exception e) {
            ip = "";
            logger.warn("Utils get IP warn", e);
        }

        return ip;
    }

    public static String getIP(String interfaceName) {
        String ip;
        interfaceName = interfaceName.trim();

        try {
            List<String> ipList = getHostAddress(interfaceName);
            ip = (!ipList.isEmpty()) ? ipList.get(0) : "";
        } catch (Exception e) {
            ip = "";
            logger.warn("Utils get IP warn", e);
        }

        return ip;
    }

    /**
     * 获取已激活网卡的ip地址
     * @param interfaceName 可指定网卡名称，null则获取全部
     * @return List<String>
     */
    private static List<String> getHostAddress(String interfaceName) throws SocketException {
        List<String> ipList = new ArrayList<>(5);
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            Enumeration<InetAddress> allAddress = ni.getInetAddresses();
            while (allAddress.hasMoreElements()) {
                InetAddress address = allAddress.nextElement();
                if (address.isLoopbackAddress()) {
                    // skip the loopback addr
                    continue;
                }
                if (address instanceof Inet6Address) {
                    // skip the IPv6 addr
                    continue;
                }
                String hostAddress = address.getHostAddress();
                if (interfaceName == null) {
                    ipList.add(hostAddress);
                } else if (Objects.equals(interfaceName, ni.getDisplayName())) {
                    ipList.add(hostAddress);
                }
            }
        }
        return ipList;
    }
}
