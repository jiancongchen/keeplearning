package datastruct.RBTree;

/**
 * 红黑树
 * @author jiancongchen on 2020/10/18
 */
public class RBTree {

    private RBTreeNode root;

    /**
     * 遍历红黑树的节点
     * @param node
     */
    public void list(RBTreeNode node){
        if (node == null){
            return;
        }
        // 递归终止条件
        if (node.getLeft() == null && node.getRight() == null){
            System.out.println(node);
            return;
        }
        System.out.println(node);
        list(node.getLeft());
        list(node.getRight());
    }

    /**
     * 插入节点
     * @param key
     */
    public void insert(int key){
        RBTreeNode node = new RBTreeNode(key);
        // 1.  插入根节点
        if(root == null){
            node.setBlack(true);
            root = node;
            return;
        }

        RBTreeNode parent = root;
        RBTreeNode son = null;

        if (key <= parent.getKey()){
            son = parent.getLeft();
        } else {
            son = parent.getRight();
        }
        // 一直找到适合新增节点的位置的叶子节点
        while (son != null){
            parent = son;
            if (key <= parent.getKey()){
                son = parent.getLeft();
            } else {
                son = parent.getRight();
            }
        }
        // 此时 son 已经是空，那么新增的节点应该在这个 son 的父节点
        if (key <= parent.getKey()){
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }
        node.setParent(parent);

        // 自平衡
        balanceInsert(node);
    }

    /**
     * 自平衡
     * @param node
     */
    private void balanceInsert(RBTreeNode node) {
        RBTreeNode father, gFather;
        // 父节点是红色
        while ( (father = node.getParent()) != null && father.isBlack() == false){
            gFather = father.getParent();
            // 父节点在祖父节点的左边
            if(gFather.getLeft() == father){
                RBTreeNode uncle = gFather.getRight();
                if (uncle != null && uncle.isBlack() == false){
                    // 颜色反转
                    setBlack(father);
                    setBlack(uncle);
                    setRed(gFather);
                    // gFather的颜色变成红色，相当于在gFather的father节点下新增了一个红色节点
                    node = gFather;
                    continue;
                }
                // 代码执行到这，说明父节点是红色，且uncle节点不存在或者为黑色
                // 需要左旋
                if (node == father.getRight()){
                    leftRota(father);
                    RBTreeNode temp = node;
                    node = father;
                    father = temp;
                }
                setBlack(father);
                setRed(gFather);
                rightRota(gFather);
            } else {
                // 父节点在祖父节点的右边
                RBTreeNode uncle = gFather.getLeft();
                if (uncle != null && uncle.isBlack() == false){
                    // 颜色反转
                    setBlack(father);
                    setBlack(uncle);
                    setRed(gFather);
                    // gFather的颜色变成红色，相当于在gFather的father节点下新增了一个红色节点
                    node = gFather;
                    continue;
                }
                // 代码执行到这，说明父节点是红色，且uncle节点不存在或者为黑色
                // 需要左旋
                if (node == father.getLeft()){
                    rightRota(father);
                    RBTreeNode temp = node;
                    node = father;
                    father = temp;
                }
                setBlack(father);
                setRed(gFather);
                leftRota(gFather);
            }
        }
        setBlack(root);
    }

    /**
     * 左旋转
     * @param node
     */
    private void leftRota(RBTreeNode node) {
        RBTreeNode right = node.getRight();
        RBTreeNode parent = node.getParent();

        // node没有父节点，说明node是根节点
        if(parent == null){
            root = right;
            right.setParent(null);
        } else {
            if (parent.getLeft() != null && parent.getLeft() == node){
                parent.setLeft(right);
            } else {
                parent.setRight(right);
            }
            right.setParent(parent);
        }
        node.setParent(right);
        node.setRight(right.getLeft());

        if (right.getLeft() != null){
            right.getLeft().setParent(node);
        }
        right.setLeft(node);
    }

    /**
     * 右旋转
     * @param node
     */
    private void rightRota(RBTreeNode node) {
        RBTreeNode left = node.getLeft();
        RBTreeNode parent = node.getParent();

        // node没有父节点，说明node是根节点
        if(parent == null){
            root = left;
            left.setParent(null);
        } else {
            if (parent.getLeft() != null && parent.getLeft() == node){
                parent.setLeft(left);
            } else {
                parent.setRight(left);
            }
            left.setParent(parent);
        }
        node.setParent(left);
        node.setLeft(left.getRight());

        if (left.getRight() != null){
            left.getRight().setParent(node);
        }
        left.setRight(node);
    }

    /**
     * 节点设置为黑色
     * @param node
     */
    private void setBlack(RBTreeNode node){
        node.setBlack(true);
    }

    /**
     * 节点设置为红色
     * @param node
     */
    private void setRed(RBTreeNode node){
        node.setBlack(false);
    }

    public static void main(String[] args) {
        RBTree rb=new RBTree();
        //根节点
        rb.insert(10);
        rb.insert(5);
        rb.insert(9);
        rb.insert(3);
        rb.insert(6);
        rb.insert(7);
        rb.insert(19);
        rb.insert(32);
        rb.insert(24);
        rb.insert(17);
        rb.list(rb.root);
    }
}
