package ferture_z.doMain;

/**
 * Created by 鱼塘主 on 2018/9/25.
 */

public class Clazz {
    private String name;
    private String teacher;
    private String room;
    private String start_time;
    private String end_time;

    public Clazz(String name, String teacher, String room, String start_time, String end_time) {
        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
