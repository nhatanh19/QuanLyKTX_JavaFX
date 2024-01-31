package filefxml.quanlyktx_fx_version2.Model;

import javafx.scene.control.DatePicker;

public class HoatDongThuePhong {
    public String MaHoaDon;
    public String MaPhong;
    public String Tang;
    public String Day;
    public String NgayNhanPhong;
    public String NgayTraPhong;
    public String TrangThai;

    public HoatDongThuePhong() {

    }

    public HoatDongThuePhong(String maHoaDon, String maPhong, String tang, String day) {
        MaHoaDon = maHoaDon;
        MaPhong = maPhong;
        Tang = tang;
        Day = day;
    }

    public HoatDongThuePhong(String maHoaDon, String maPhong, String tang, String day, String ngayNhanPhong, String ngayTraPhong, String trangThai) {
        MaHoaDon = maHoaDon;
        MaPhong = maPhong;
        Tang = tang;
        Day = day;
        NgayNhanPhong = ngayNhanPhong;
        NgayTraPhong = ngayTraPhong;
        TrangThai = trangThai;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String maPhong) {
        MaPhong = maPhong;
    }

    public String getTang() {
        return Tang;
    }

    public void setTang(String tang) {
        Tang = tang;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getNgayNhanPhong() {
        return NgayNhanPhong;
    }

    public void setNgayNhanPhong(String ngayNhanPhong) {
        NgayNhanPhong = ngayNhanPhong;
    }

    public String getNgayTraPhong() {
        return NgayTraPhong;
    }

    public void setNgayTraPhong(String ngayTraPhong) {
        NgayTraPhong = ngayTraPhong;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    @Override
    public String toString() {
        return "HoatDongThuePhong{" +
                "MaHoaDon='" + MaHoaDon + '\'' +
                ", MaPhong='" + MaPhong + '\'' +
                ", Tang='" + Tang + '\'' +
                ", Day='" + Day + '\'' +
                ", NgayNhanPhong='" + NgayNhanPhong + '\'' +
                ", NgayTraPhong='" + NgayTraPhong + '\'' +
                ", TrangThai='" + TrangThai + '\'' +
                '}';
    }
}