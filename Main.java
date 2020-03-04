import java.net.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void checkHosts(String subnet) throws IOException {

        InetAddress localhost = InetAddress.getLocalHost();
        // this code assumes IPv4 is used
        byte[] ip = localhost.getAddress();

        for (int i = 1; i <= 254; i++) {
            ip[3] = (byte) i;
            String host = subnet + "." + i;
            InetAddress address = InetAddress.getByAddress(ip);
            String hostName = address.getHostName();
            if (InetAddress.getByName(host).isReachable(200)) {
                System.out.println(hostName + " - " + host);
            }
        }
    }

    public static void main(String[] argv) throws IOException, InterruptedException{
//        Start catching user info
        try {
            InetAddress ownIP = InetAddress.getLocalHost();
            String myIP = ownIP.getHostAddress();
            System.out.println("IP: " + myIP);
            String maskIP = myIP.substring(0, 8);

            InetAddress addr;
            addr = InetAddress.getLocalHost();
            String hostname = addr.getHostName();
            System.out.println("PC name: " + hostname);


            NetworkInterface network = NetworkInterface.getByInetAddress(ownIP);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            System.out.print("MAC: ");
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
            }
            System.out.println(sb.toString());

            System.out.println("Subnet mask: " + maskIP + "\n");
            TimeUnit.SECONDS.sleep(2);

            //        Start catching others info
            checkHosts(maskIP);
            //        End catching others info
        }
        catch(InterruptedException e) {
            System.out.println("Error");
        }
        catch (IOException e) {
            System.out.println("Error");
        }

    }
}
