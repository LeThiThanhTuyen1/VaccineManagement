package com.example.vaccineapp.dto;

import java.util.List;

public class VaccinationRateByRegionDTO {
    private String regionName;
    private long totalCitizens;  // Tổng số công dân trong khu vực
    private List<VaccinationRecord> vaccinatedCitizens;  // Danh sách các mũi tiêm đã thực hiện
    private double vaccinationRate;

    // Constructor
    public VaccinationRateByRegionDTO(String regionName, long totalCitizens, List<VaccinationRecord> vaccinatedCitizens) {
        this.regionName = regionName;
        this.totalCitizens = totalCitizens;
        this.vaccinatedCitizens = vaccinatedCitizens;
        this.vaccinationRate = calculateVaccinationRate();  // Tính tỷ lệ tiêm sau khi khởi tạo
    }

    // Getters and Setters
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public long getTotalCitizens() {
        return totalCitizens;
    }

    public void setTotalCitizens(long totalCitizens) {
        this.totalCitizens = totalCitizens;
    }

    public List<VaccinationRecord> getVaccinatedCitizens() {
        return vaccinatedCitizens;
    }

    public void setVaccinatedCitizens(List<VaccinationRecord> vaccinatedCitizens) {
        this.vaccinatedCitizens = vaccinatedCitizens;
    }

    public double getVaccinationRate() {
        return vaccinationRate;
    }

    public void setVaccinationRate(double vaccinationRate) {
        this.vaccinationRate = vaccinationRate;
    }

    // Phương thức tính tỷ lệ tiêm chủng
    public double calculateVaccinationRate() {
        if (totalCitizens == 0 || vaccinatedCitizens == null) {
            return 0;  // Tránh chia cho 0
        }

        // Đếm số mũi tiêm hoàn thành (status = "completed")
        long completedVaccinations = vaccinatedCitizens.stream()
            .filter(record -> "COMPLETED".equals(record.getStatus()))  // Xét mũi tiêm hoàn thành
            .count();

        return Math.min((double) completedVaccinations / totalCitizens * 100, 100);
    }

    // Lớp phụ để chứa thông tin về trạng thái tiêm chủng của từng công dân
    public static class VaccinationRecord {
        private String status;  

        // Constructor
        public VaccinationRecord(String status) {
            this.status = status;
        }

        // Getter
        public String getStatus() {
            return status;
        }
    }
}
