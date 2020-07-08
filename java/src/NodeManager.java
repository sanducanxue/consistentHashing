import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class NodeManager {
    private TreeMap<Integer,String> consistentHashingMap = new TreeMap<>();//一致性hash环

    public void addPort(Node node){
        Random random = new Random();
        for (int i = 0; i < node.getNum(); i++) {
            String tempIp = node.getIp() + "_" +random.nextInt();
            consistentHashingMap.put(tempIp.hashCode(),tempIp);
        }
    }
    /**
     * 根据客户端ip获取均衡IP
     * @param clientIp
     * @return
     */
    public String getBalanceIp(String clientIp){
        Map.Entry<Integer, String> higherEntry = consistentHashingMap.higherEntry(clientIp.hashCode());
        if (higherEntry == null){
            //超过现有最大值，接到最小值形成环
            higherEntry = consistentHashingMap.firstEntry();
        }
        String balanceIP = higherEntry.getValue().split("_")[0];
        return balanceIP;
    }
}
