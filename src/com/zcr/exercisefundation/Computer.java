package com.zcr.exercisefundation;

class Cpu {
    public void run() {
        System.out.println("quickly.........");
    }
}
class MainBoard {
    public void connect() {
        System.out.println("connect...........");
    }
}
class Memory {
    public void store() {
        System.out.println("store........");
    }
}
public class Computer {
    Cpu cpu;
    Memory memory;
    MainBoard mainBoard;

    public void work() {
        cpu.run();
        memory.store();
        mainBoard.connect();
    }

    public static void main(String[] args) {
        //组合模式，对象的组合
        /*组合模式是将对象组合成树形结构以表示“部分-整体”的层次结构。
        组合模式使得用户对单个对象和组合对象的使用具有一致性。*/

        Computer computer = new Computer();
        computer.cpu = new Cpu();
        computer.mainBoard = new MainBoard();
        computer.memory = new Memory();
        computer.work();
    }
}


