package com.cxyz.commons.domain;

import java.util.Date;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public class TaskInfo {
    public int _id;
    public String _name;
    public User sponser;
    public Student checker;
    public Date startTime;
    public Date time_len;
    public ClassRoom classRoom;
    public String describe;
}
