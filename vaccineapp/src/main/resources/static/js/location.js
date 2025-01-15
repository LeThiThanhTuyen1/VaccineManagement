document.getElementById('provinceSelect').addEventListener('change', function() {
    let provinceId = this.value;
    fetch('/locations/districts?provinceId=' + provinceId)
        .then(response => response.json())
        .then(data => {
            let districtSelect = document.getElementById('districtSelect');
            districtSelect.innerHTML = '<option value="">Chọn Huyện</option>';
            data.forEach(function(district) {
                let option = document.createElement('option');
                option.value = district.id;
                option.text = district.name;
                districtSelect.appendChild(option);
            });
            document.getElementById('wardSelect').innerHTML = '<option value="">Chọn Xã</option>'; // Clear ward options when province changes
        });
});

document.getElementById('districtSelect').addEventListener('change', function() {
    let districtId = this.value;
    fetch('/locations/wards?districtId=' + districtId)
        .then(response => response.json())
        .then(data => {
            let wardSelect = document.getElementById('wardSelect');
            wardSelect.innerHTML = '<option value="">Chọn Xã</option>';
            data.forEach(function(ward) {
                let option = document.createElement('option');
                option.value = ward.id;
                option.text = ward.name;
                wardSelect.appendChild(option);
            });
        });
});
