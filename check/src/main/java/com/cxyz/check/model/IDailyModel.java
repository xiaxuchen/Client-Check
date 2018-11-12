package com.cxyz.check.model;

import com.cxyz.check.dto.CommitCheckDto;
import com.cxyz.check.dto.GradeStusDto;
import com.cxyz.commons.IModel.IBaseModel;

import java.util.List;

/**
 * Created by 夏旭晨 on 2018/10/4.
 */

public interface IDailyModel extends IBaseModel{
    /**
     * 通过班级id获取学生们的信息，获取信息后调用回调
     * @param grade 班级编号
     * @param listener 请求成功或失败的回调
     */
    public void getStus(int grade,GetStusListener listener);

    public void commit(CommitCheckDto commitCheckDto, CommitListener listener);

    interface GetStusListener{
        /**
         * 获取数据成功调用
         * @param stus 获取到的学生
         */
        public void onSuccess(List<GradeStusDto> stus);

        /**
         * 获取数据失败调用
         * @param fail 错误信息
         */
        public void onFail(String fail);
    }

    /**
     * 提交触发的回调事件
     */
    interface CommitListener{
        /**
         * 提交成功时触发
         * @param info 完成信息
         */
        void onCompletion(String info);

        /**
         * 提交失败时触发
         * @param error 错误信息
         */
        void onFail(String error);
    }
}
