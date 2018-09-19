package com.liulu.pojo;

/**
 * @Description:
 * @Copyright (c) by HomeFax.
 * @All right reserved.
 * @Create Date: 2018/7/16 10:52
 * @Create Author: nevermore
 * @File Name: Customer
 * @Last version: 2.1.0
 */
public class Customer {


    static class customerBuild{
        private String name;
        private String age;
        private String pasword;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getPasword() {
            return pasword;
        }

        public void setPasword(String pasword) {
            this.pasword = pasword;
        }
    }

}
