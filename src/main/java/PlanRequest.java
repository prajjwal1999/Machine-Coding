public class PlanRequest {
    public String flightNo;
    public Type type;

    public PlanRequest(String flightNo, Type type, int duration) {
        this.flightNo = flightNo;
        this.type = type;
        this.duration = duration;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int duration;
}
