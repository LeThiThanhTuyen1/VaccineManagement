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
