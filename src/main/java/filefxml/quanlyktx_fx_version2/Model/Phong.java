package filefxml.quanlyktx_fx_version2.Model;

public class Phong {
    public String MaPhong;
    public String Tang;
    public String Day;
    public String LoaiPhong;
    public String GiaPhong;

    public Phong(String maPhong, String tang, String day, String loaiPhong, String giaPhong) {
        MaPhong = maPhong;
        Tang = tang;
        Day = day;
        LoaiPhong = loaiPhong;
        GiaPhong = giaPhong;
    }

    public Phong(String maPhong, String tang, String day) {
        MaPhong = maPhong;
        Tang = tang;
        Day = day;
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

    public String getLoaiPhong() {
        return LoaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        LoaiPhong = loaiPhong;
    }

    public String getGiaPhong() {
        return GiaPhong;
    }

    public void setGiaPhong(String giaPhong) {
        GiaPhong = giaPhong;
    }

    @Override
    public String toString() {
        return "Phong{" +
                "MaPhong='" + MaPhong + '\'' +
                ", Tang='" + Tang + '\'' +
                ", Day='" + Day + '\'' +
                ", LoaiPhong='" + LoaiPhong + '\'' +
                ", GiaPhong='" + GiaPhong + '\'' +
                '}';
    }
}
