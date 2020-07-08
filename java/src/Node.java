/**
 * 服务器节点
 */
public class Node {
    private String ip;//节点ip地址
    private int num = 100;//对应虚拟节点个数

    public Node(String ip, int num) {
        this.ip = ip;
        this.num = num;
    }

    public Node(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
