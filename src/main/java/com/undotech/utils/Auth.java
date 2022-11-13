/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.utils;

import com.undotech.entity.NhanVien;

/**
 *
 * @author blackundo
 */
public class Auth {
    public static NhanVien user = null;
    public static void clear(){
        Auth.user = null;
    }
    public static boolean isLogin(){
        return Auth.user != null;
    }
    public static String role(){
        String role = null;
        if (Auth.isLogin()) {
            role = user.getVaiTro();
        }
        return role;
    }
}
