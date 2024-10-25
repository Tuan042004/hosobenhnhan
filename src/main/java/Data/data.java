//CREATE DATABASE QuanLyBenhNhan;
//USE QuanLyBenhNhan;
//
//CREATE TABLE Users (
//    UserID INT PRIMARY KEY IDENTITY(1,1),
//    Username NVARCHAR(50) NOT NULL UNIQUE,
//    Password NVARCHAR(255) NOT NULL
//);
//
//-- Bảng BenhNhan
//CREATE TABLE BenhNhan (
//    MaBenhNhan INT PRIMARY KEY,
//    HoTen NVARCHAR(100),
//    NgaySinh DATE,
//    GioiTinh NVARCHAR(10),
//    DiaChi NVARCHAR(200),
//    SDT VARCHAR(15)
//);
//
//-- Bảng HoSoNhapVien
//CREATE TABLE HoSoNhapVien (
//    MaHoSoNhapVien INT PRIMARY KEY,
//    MaBenhNhan INT FOREIGN KEY REFERENCES BenhNhan(MaBenhNhan),
//    NgayNhapVien DATE,
//    ChanDoan NVARCHAR(200),
//    KhoaDieuTri NVARCHAR(100)
//);
//
//-- Bảng QuaTrinhDieuTri
//CREATE TABLE QuaTrinhDieuTri (
//    MaDieuTri INT PRIMARY KEY,
//    MaBenhNhan INT FOREIGN KEY REFERENCES BenhNhan(MaBenhNhan),
//    NgayDieuTri DATE,
//    ChanDoanDieuTri NVARCHAR(200),
//    PhuongPhapDieuTri NVARCHAR(200),
//    BacSiDieuTri NVARCHAR(100)
//);
//
//-- Bảng DonThuoc
//CREATE TABLE DonThuoc (
//    MaDonThuoc INT PRIMARY KEY,
//    MaBenhNhan INT FOREIGN KEY REFERENCES BenhNhan(MaBenhNhan),
//    NgayKeDon DATE,
//    Thuoc NVARCHAR(200),
//    LieuLuong NVARCHAR(100),
//    BacSiKeDon NVARCHAR(100)
//);
//
//-- Bảng ChanDoan
//CREATE TABLE ChanDoan (
//    MaChanDoan INT PRIMARY KEY,
//    MaBenhNhan INT FOREIGN KEY REFERENCES BenhNhan(MaBenhNhan),
//    NgayChanDoan DATE,
//    ChanDoanChiTiet NVARCHAR(200),
//    BacSiChanDoan NVARCHAR(100)
//);
//
//-- Bảng PhongBenh
//CREATE TABLE PhongBenh (
//    MaPhong INT PRIMARY KEY,
//    TenPhong NVARCHAR(100),
//    LoaiPhong NVARCHAR(50),
//    SoGiuong INT
//);
//
//-- Bảng GiuongBenh
//CREATE TABLE GiuongBenh (
//    MaGiuong INT PRIMARY KEY,
//    MaPhong INT FOREIGN KEY REFERENCES PhongBenh(MaPhong),
//    TrangThai NVARCHAR(50)
//);
//
//-- Bảng NhanVienYTe
//CREATE TABLE NhanVienYTe (
//    MaNhanVien INT PRIMARY KEY,
//    HoTen NVARCHAR(100),
//    ChucVu NVARCHAR(100),
//    Khoa NVARCHAR(100),
//    SDT VARCHAR(15)
//);
//
//-- Bảng LichSuXuatVien
//CREATE TABLE LichSuXuatVien (
//    MaXuatVien INT PRIMARY KEY,
//    MaBenhNhan INT FOREIGN KEY REFERENCES BenhNhan(MaBenhNhan),
//    NgayXuatVien DATE,
//    KetQuaDieuTri NVARCHAR(200),
//    GhiChu NVARCHAR(200)
//);
//
//-- Bảng Khoa
//CREATE TABLE Khoa (
//    MaKhoa INT PRIMARY KEY,
//    TenKhoa NVARCHAR(100),
//    TruongKhoa NVARCHAR(100)
//);
//
//-- Bảng LichLamViec
//CREATE TABLE LichLamViec (
//    MaLichLamViec INT PRIMARY KEY,
//    MaNhanVien INT FOREIGN KEY REFERENCES NhanVienYTe(MaNhanVien),
//    NgayLamViec DATE,
//    CaLamViec NVARCHAR(50)
//);
//
//-- Bảng LichSuDieuTri
//CREATE TABLE LichSuDieuTri (
//    MaLichSu INT PRIMARY KEY,
//    MaBenhNhan INT FOREIGN KEY REFERENCES BenhNhan(MaBenhNhan),
//    NgayDieuTri DATE,
//    KetQuaDieuTri NVARCHAR(200),
//    BacSiDieuTri NVARCHAR(100)
//);
//
//-- Bảng BaoCaoBenhNhanDangDieuTri
//CREATE TABLE BaoCaoBenhNhanDangDieuTri (
//    MaBaoCao INT PRIMARY KEY,
//    MaBenhNhan INT FOREIGN KEY REFERENCES BenhNhan(MaBenhNhan),
//    MaPhong INT FOREIGN KEY REFERENCES PhongBenh(MaPhong),
//    MaKhoa INT FOREIGN KEY REFERENCES Khoa(MaKhoa),
//    NgayDieuTri DATE
//);
//-- Bảng BaoCaoBenhNhanDaXuatVien
//CREATE TABLE BaoCaoBenhNhanDaXuatVien (
//    MaBaoCao INT PRIMARY KEY,
//    MaBenhNhan INT FOREIGN KEY REFERENCES BenhNhan(MaBenhNhan),
//    NgayXuatVien DATE,
//    KetQuaDieuTri NVARCHAR(200)
//);
//
//-- Bảng TaiLieuYTe
//CREATE TABLE TaiLieuYTe (
//    MaTaiLieu INT PRIMARY KEY,
//    MaBenhNhan INT FOREIGN KEY REFERENCES BenhNhan(MaBenhNhan),
//    TenTaiLieu NVARCHAR(100),
//    NoiDungTaiLieu NVARCHAR(MAX)
//);