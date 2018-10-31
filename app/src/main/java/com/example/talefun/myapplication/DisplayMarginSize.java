package com.example.talefun.myapplication;

public class DisplayMarginSize {
    private int top = 0;
    private int left = 0;
    private int bootom = 0;
    private int right = 0;
    private int notchStatus = 1; //1 开启安全保护,0未开启安全保护  ,在Android 8.0上面的vivo和oppo有效果


    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getBootom() {
        return bootom;
    }

    public void setBootom(int bootom) {
        this.bootom = bootom;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getNotchStatus() {
        return notchStatus;
    }

    public void setNotchStatus(int notchStatus) {
        this.notchStatus = notchStatus;
    }

    @Override
    public String toString() {
        return "DisplayMarginSize{" +
                "top=" + top +
                ", left=" + left +
                ", bootom=" + bootom +
                ", right=" + right +
                ", notchStatus=" + notchStatus +
                '}';
    }
}
