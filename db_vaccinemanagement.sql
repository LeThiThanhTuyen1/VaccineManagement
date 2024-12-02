CREATE DATABASE vaccine_management;
USE vaccine_management;
CREATE TABLE `vaccine_management`.`users` (
  `id` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(225) NOT NULL,
  `enabled` BOOLEAN NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE citizen (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15),
    email VARCHAR(255),
    citizen_group VARCHAR(50), -- Nhóm đối tượng: Trẻ em, Người già, Phụ nữ mang thai
    date_of_birth DATE, -- Ngày sinh
    gender VARCHAR(10), -- Giới tính
    address VARCHAR(255), -- Địa chỉ
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE vaccine (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    vaccine_name VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(255) NOT NULL, -- Nhà sản xuất
    doses_required INT NOT NULL, -- Số liều yêu cầu
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE vaccination_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    citizen_id BIGINT NOT NULL,
    vaccine_id BIGINT NOT NULL,
    vaccination_date DATE NOT NULL,
    dose_number INT, -- Số thứ tự liều tiêm
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (citizen_id) REFERENCES citizen(id),
    FOREIGN KEY (vaccine_id) REFERENCES vaccine(id)
);
CREATE TABLE vaccination_schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    citizen_id BIGINT NOT NULL,
    scheduled_date DATE NOT NULL, -- Ngày dự kiến tiêm
    status VARCHAR(50), -- Trạng thái (Chờ tiêm, Đã tiêm)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (citizen_id) REFERENCES citizen(id)
);
-- Thêm tài khoản
INSERT INTO `vaccine_management`.`users` (`id`, `username`, `password`, `enabled`) 
VALUES ('1', 'admin', '$2a$10$PvJmK80Cn0qgmOIlKBPZJeXta61qAPyQV66qX7U/4PNkTrI4SvEka', true);

-- Thêm công dân
INSERT INTO citizen (full_name, phone_number, email, citizen_group, date_of_birth, gender, address)
VALUES ('Nguyen Van A', '0912345678', 'nguyenvana@example.com', 'Nguoi gia', '1950-01-15', 'Nam', '123 Nguyen Trai, Ha Noi');


INSERT INTO citizen (full_name, phone_number, email, citizen_group, date_of_birth, gender, address) 
VALUES ('Le Thi B', '0987654321', 'lethib@example.com', 'Tre em', '2015-07-20', 'Nu', '456 Hai Ba Trung, TP.HCM');

-- Thêm vắc xin
INSERT INTO vaccine (vaccine_name, manufacturer, doses_required) 
VALUES ('Vắc xin Covid-19', 'Pfizer', 2);

INSERT INTO vaccine (vaccine_name, manufacturer, doses_required) 
VALUES ('Vắc xin Cúm', 'Sanofi', 1);

-- Thêm lịch sử tiêm chủng
INSERT INTO vaccination_record (citizen_id, vaccine_id, vaccination_date, dose_number) 
VALUES (1, 1, '2022-01-10', 1); -- Công dân ID 1 tiêm vắc xin Covid-19 liều thứ 1

INSERT INTO vaccination_record (citizen_id, vaccine_id, vaccination_date, dose_number) 
VALUES (1, 1, '2022-02-10', 2); -- Công dân ID 1 tiêm vắc xin Covid-19 liều thứ 2

-- Thêm lịch tiêm
INSERT INTO vaccination_schedule (citizen_id, scheduled_date, status) 
VALUES (2, '2024-12-05', 'Chờ tiêm'); -- Công dân ID 2 dự kiến tiêm vào ngày 2024-12-05
