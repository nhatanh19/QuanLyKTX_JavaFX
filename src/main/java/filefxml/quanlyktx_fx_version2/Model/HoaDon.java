package filefxml.quanlyktx_fx_version2.Model;

public class HoaDon {
    public String MaHoaDon;
    public String TongTienThanhToan;
    public String TenHoaDon;
    public String TrangThaiThanhToan;

    public HoaDon(String maHoaDon, String tongTienThanhToan, String tenHoaDon, String trangThaiThanhToan) {
        MaHoaDon = maHoaDon;
        TongTienThanhToan = tongTienThanhToan;
        TenHoaDon = tenHoaDon;
        TrangThaiThanhToan = trangThaiThanhToan;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public String getTongTienThanhToan() {
        return TongTienThanhToan;
    }

    public void setTongTienThanhToan(String tongTienThanhToan) {
        TongTienThanhToan = tongTienThanhToan;
    }

    public String getTenHoaDon() {
        return TenHoaDon;
    }

    public void setTenHoaDon(String tenHoaDon) {
        TenHoaDon = tenHoaDon;
    }

    public String getTrangThaiThanhToan() {
        return TrangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(String trangThaiThanhToan) {
        TrangThaiThanhToan = trangThaiThanhToan;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "MaHoaDon='" + MaHoaDon + '\'' +
                ", TongTienThanhToan='" + TongTienThanhToan + '\'' +
                ", TenHoaDon='" + TenHoaDon + '\'' +
                ", TrangThaiThanhToan='" + TrangThaiThanhToan + '\'' +
                '}';
    }
}
