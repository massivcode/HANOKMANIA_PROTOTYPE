package com.raychoe.httpexample.HanokRepairAdvice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.raychoe.httpexample.Hanok;

/**
 * Created by massivCode on 2015-10-17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)     // 매칭되는 필드가 없을 때 무시
public class HanokRepairAdvice implements Hanok {

    //    등록번호
    public String HANOKNUM;
    //    차수별
    public String SN;
    //    주소
    public String ADDR;
    //    안건
    public String ITEM;
    //    공사종별
    public String CONSTRUCTION;
    //    신청내용
    public String REQUEST;
    //    심의내용
    public String REVIEW;
    //    심의결과
    public String RESULT;
    //    비용지원결정
    public String LOANDEC;
    //    비고
    public String NOTE;



}
