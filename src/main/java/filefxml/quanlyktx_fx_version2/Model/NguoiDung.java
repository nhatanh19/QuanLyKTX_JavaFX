package filefxml.quanlyktx_fx_version2.Model;


import java.time.LocalDate;

public class NguoiDung{
    private String HoVaTen;
    private String Email;
    private String SDT;
    private String MaDinhDanh;
    private String QueQuan;
    private LocalDate NgaySinh;
    private String GioiTinh;

    public NguoiDung(String hoVaTen, String email, String SDT, String maDinhDanh, String queQuan,  LocalDate ngaySinh, String gioiTinh) {
        this.HoVaTen = hoVaTen;
        this.Email = email;
        this.SDT = SDT;
        this.MaDinhDanh = maDinhDanh;
        this.QueQuan = queQuan;
        this.NgaySinh = ngaySinh;
        this.GioiTinh = gioiTinh;
    }

    public NguoiDung(String hoVaTen, String maDinhDanh , String SDT , String email, LocalDate ngaySinh) {
        this.HoVaTen = hoVaTen;
        this.Email = email;
        this.SDT = SDT;
        this.MaDinhDanh = maDinhDanh;
        this.NgaySinh = ngaySinh;
    }

    public NguoiDung() {
    }

    public String getHoVaTen() {
        return HoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        HoVaTen = hoVaTen;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMaDinhDanh() {
        return MaDinhDanh;
    }

    public void setMaDinhDanh(String maDinhDanh) {
        MaDinhDanh = maDinhDanh;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String queQuan) {
        QueQuan = queQuan;
    }


    public LocalDate getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }
}
