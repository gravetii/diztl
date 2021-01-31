package io.github.gravetii.node;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Logger;

public class DiztlUtils {

  private static final Logger logger = Logger.getLogger(DiztlUtils.class.getCanonicalName());

  public static String getMyIP() throws Exception {
    Socket socket = new Socket();
    socket.connect(new InetSocketAddress("google.com", 80));
    String ip = socket.getLocalAddress().getHostAddress();
    logger.info("Local IP - " + ip);
    return ip;
  }

  public static void main(String[] args) throws Exception {
    String ip = getMyIP();
  }
}
