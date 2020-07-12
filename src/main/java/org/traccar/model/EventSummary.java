package org.traccar.model;

public final class EventSummary {

    /** accuracy */
    private double ac;
    /** altitude */
    private double al;
    /** course */
    private double cs;
    /** device id */
    private double di;
    /** device time */
    private long dt;
    /** fixed time */
    private long ft;
    /** server time */
    private long st;
    /** id */
    private long id;
    /** latitude */
    private double la;
    /** longitude */
    private double lo;
    /** protocol */
    private String pc;
    /** speed */
    private double sp;
    /** axis x */
    private double ax;
    /** axis y */
    private double ay;
    /** axis z */
    private double az;
    /** battery */
    private double ba;
    /** distance */
    private double ds;
    /** gps status */
    private int gs;
    /** ignition */
    private boolean ig;
    /** odometer */
    private long od;
    /** power */
    private double pw;
    /** total distance */
    private double td;
    /** motion */
    private boolean mt;
    /** event */
    private int ev;
    /** hours */
    private long hr;
    /** result */
    private String rs;
    /** type */
    private int ty;

    public long getHr() {
        return hr;
    }
    public void setHr(long hr) {
        this.hr = hr;
    }
    public String getRs() {
        return rs;
    }
    public void setRs(String rs) {
        this.rs = rs;
    }
    public int getTy() {
        return ty;
    }
    public void setTy(int ty) {
        this.ty = ty;
    }
    public double getAc() {
        return ac;
    }
    public void setAc(double ac) {
        this.ac = ac;
    }
    public double getAl() {
        return al;
    }
    public void setAl(double al) {
        this.al = al;
    }
    public double getCs() {
        return cs;
    }
    public void setCs(double cs) {
        this.cs = cs;
    }
    public double getDi() {
        return di;
    }
    public void setDi(double di) {
        this.di = di;
    }
    public long getDt() {
        return dt;
    }
    public void setDt(long dt) {
        this.dt = dt;
    }
    public long getFt() {
        return ft;
    }
    public void setFt(long ft) {
        this.ft = ft;
    }
    public long getSt() {
        return st;
    }
    public void setSt(long st) {
        this.st = st;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public double getLa() {
        return la;
    }
    public void setLa(double la) {
        this.la = la;
    }
    public double getLo() {
        return lo;
    }
    public void setLo(double lo) {
        this.lo = lo;
    }
    public String getPc() {
        return pc;
    }
    public void setPc(String pc) {
        this.pc = pc;
    }
    public double getSp() {
        return sp;
    }
    public void setSp(double sp) {
        this.sp = sp;
    }
    public double getAx() {
        return ax;
    }
    public void setAx(double ax) {
        this.ax = ax;
    }
    public double getAy() {
        return ay;
    }
    public void setAy(double ay) {
        this.ay = ay;
    }
    public double getAz() {
        return az;
    }
    public void setAz(double az) {
        this.az = az;
    }
    public double getBa() {
        return ba;
    }
    public void setBa(double ba) {
        this.ba = ba;
    }
    public double getDs() {
        return ds;
    }
    public void setDs(double ds) {
        this.ds = ds;
    }
    public int getGs() {
        return gs;
    }
    public void setGs(int gs) {
        this.gs = gs;
    }
    public boolean isIg() {
        return ig;
    }
    public void setIg(boolean ig) {
        this.ig = ig;
    }
    public long getOd() {
        return od;
    }
    public void setOd(long od) {
        this.od = od;
    }
    public double getPw() {
        return pw;
    }
    public void setPw(double pw) {
        this.pw = pw;
    }
    public double getTd() {
        return td;
    }
    public void setTd(double td) {
        this.td = td;
    }
    public boolean isMt() {
        return mt;
    }
    public void setMt(boolean mt) {
        this.mt = mt;
    }
    public int getEv() {
        return ev;
    }
    public void setEv(int ev) {
        this.ev = ev;
    }

    private EventSummary() {
        super();
    }

    public static EventSummary create(Position p) {
        EventSummary sum = new EventSummary();
        sum.ac = p.getAccuracy();
        sum.al = p.getAltitude();
        sum.cs = p.getCourse();
        sum.di = p.getDeviceId();
        sum.dt = p.getDeviceTime() != null ? p.getDeviceTime().getTime() : 0L;
        sum.ft = p.getFixTime() != null ? p.getFixTime().getTime() : 0L;
        sum.st = p.getServerTime() != null ? p.getServerTime().getTime() : 0L;
        sum.id = p.getId();
        sum.la = p.getLatitude();
        sum.lo = p.getLongitude();
        sum.pc = p.getProtocol();
        sum.sp = p.getSpeed();
        if (p.getAttributes() != null && p.getAttributes().size() > 0) {
            sum.ax = p.getDouble("axisX");
            sum.ay = p.getDouble("axisY");
            sum.az = p.getDouble("axisZ");
            sum.ba = p.getDouble("battery");
            sum.ds = p.getDouble("distance");
            sum.gs = p.getInteger("gpsStatus");
            sum.ig = p.getBoolean("ignition");
            sum.od = p.getLong("odometer");
            sum.pw = p.getDouble("power");
            sum.td = p.getDouble("totalDistance");
            sum.mt = p.getBoolean("motion");
            sum.ev = p.getInteger("event");
            sum.rs = p.getString("result");
            sum.hr = p.getLong("hours");
            sum.ty = p.getInteger("type");
        }
        return sum;
    }
}
