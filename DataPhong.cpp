#include <bits/stdc++.h>
using namespace std;

int main (){
	int loaiPhong = 0, giaPhong = 0;
	for(int day = 1; day <= 3; day++){
		for(int tang = 1; tang <= 5; tang++){
			loaiPhong = 4;
			giaPhong = 240;
			for(int phong = 1; phong <= 20; phong++){
				if(phong >= 11 && phong <= 15){
					loaiPhong = 6;
					giaPhong = 160;
				}else if(phong >= 16 && phong <= 20){
					loaiPhong = 8;
					giaPhong = 120;
				}
				int maPhong = tang*100 + phong; 
				cout << "('"<< maPhong <<"','"<< day <<"','"<< tang <<"','"<< loaiPhong <<"','"<< giaPhong <<"','true','0'),\n";
			}
		}
	}
	
	return 0;
}

//INSERT INTO `phong`(`MaSoPhong`, `Day`, `Tang`, `LoaiPhong`, `GiaPhong`, `TinhTrangSuDung`, `SoNguoiTrongPhong`) VALUES ('[value-1]','[value-2]','[value-3]','[value-4]','[value-5]','[value-6]','[value-7]')
