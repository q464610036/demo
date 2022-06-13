package com.lambda;

/**
 * @author 陈孟飞
 * @date 2021/2/19
 */
public class FilterAge implements MyPredicate<PeopleVO> {

    @Override
    public boolean test(PeopleVO peopleVO) {
        return peopleVO.getAge() > 20;
    }
}
