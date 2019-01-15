package com.la.server.monitor.gui.infoPanel;

public class InfoFormat {
    public Accelerometer accelerometer = new Accelerometer();
    public Magnetometer magnetometer = new Magnetometer();


    public class Accelerometer {
        public float x = (float)0;
        public float y = (float)0;
        public float z = (float)0;
    }

    public class Magnetometer {
        public float x = (float)0;
        public float y = (float)0;
        public float z = (float)0;
    }

    public String out() {
        return "Accelerometer\n" +
                String.format("x-axis:%-10fy-axis:%-10fz-axis:%-10f\n",
                        this.accelerometer.x,
                        this.accelerometer.y,
                        this.accelerometer.z) +
                "Magnetometer\n" +
                String.format("x-axis:%-10fy-axis:%-10fz-axis:%-10f\n",
                        this.magnetometer.x,
                        this.magnetometer.y,
                        this.magnetometer.z);
    }
}

