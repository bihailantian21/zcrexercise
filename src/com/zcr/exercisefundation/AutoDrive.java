package com.zcr.exercisefundation;

/**
 * @author zcr
 * @date 2019/5/6-11:48
 */
public abstract class AutoDrive {

    private String tool;

    public AutoDrive(String tool) {
        this.tool = tool;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public abstract void drive();

    @Override
    public String toString() {
        return super.toString();
    }
}
