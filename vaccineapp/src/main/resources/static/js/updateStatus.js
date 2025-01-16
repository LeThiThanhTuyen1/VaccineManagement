function updateStatus(vaccinationId, status) {
    if (!status) {
        alert("Vui lòng chọn trạng thái!");
        return;
    }

    fetch(`/vaccinations/updateStatus?vaccinationId=${vaccinationId}&status=${status}`, {
        method: "POST"
    })
    .then(response => {
        if (response.ok) {
            alert("Cập nhật trạng thái thành công!");
            window.location.reload();
        } else {
            alert("Có lỗi xảy ra khi cập nhật trạng thái!");
        }
    });
}
// Thêm hiệu ứng thông báo khi trạng thái được cập nhật
document.addEventListener('DOMContentLoaded', function () {
    const alertBox = document.querySelector('.alert');
    if (alertBox) {
        setTimeout(() => {
            alertBox.style.display = 'none';
        }, 3000); // Ẩn thông báo sau 3 giây
    }
});

