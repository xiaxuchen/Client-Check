package com.cxyz.vac.iview;

import com.cxyz.commons.IView.IBaseView;
import com.cxyz.vac.dto.VacateDto;

import java.util.List;

/**
 * Created by Administrator on 2018/12/31.
 */

public interface IMineVacateView extends IBaseView{

    /**
     * 显示请假信息
     * @param vacateDtos
     */
    void showVacates(List<VacateDto> vacateDtos);

    void getVacatesFail(String error);
}
