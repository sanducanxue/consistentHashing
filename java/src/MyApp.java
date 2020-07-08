import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MyApp {
    private static Map<String,Integer> map = new HashMap<>();
    private static int  times = 1000000;//重复次数
    private static int  nodeNum = 10;//节点个数
    private static int  tempNodeNum = 10000;//节点个数

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        NodeManager nodeManager = new NodeManager();
        for (int i = 0; i < nodeNum; i++) {
            nodeManager.addPort(new Node("192.168.22." + i,tempNodeNum));
        }

        Random random = new Random();
        for (int i = 0; i < times; i++) {
            String balanceIp = nodeManager.getBalanceIp(String.valueOf(random.nextInt()));
            add(balanceIp);
        }
        print();
        long end = System.currentTimeMillis();
        System.out.println("总耗时 ：" +(end -start) + "毫秒");

    }

    public static void add(String ip){
        if (map.containsKey(ip)){
            Integer num = map.get(ip);
            map.put(ip,num + 1);
        }else {
            map.put(ip,1);
        }
    }

    /**
     * 输出标准差
     */
    public static void print(){
        int average = times/nodeNum;

        int totalPow = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            double pow = Math.pow(average - value, 2);
            totalPow += pow;
        }
        double sqrt = Math.sqrt(totalPow / nodeNum);
        System.out.println("虚拟节点数： " + tempNodeNum + "  " +"节点数 :" +nodeNum);
        /*for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("ip :" + entry.getKey() + "  " + "命中次数 ：" + entry.getValue());
        }*/
        System.out.println("标准差： " + sqrt);
    }
}
