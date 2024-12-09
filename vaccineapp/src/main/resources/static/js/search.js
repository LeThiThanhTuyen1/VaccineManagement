document.querySelector('form').addEventListener('submit', function (event) {
    const name = document.getElementById('name').value.trim();
    const phone = document.getElementById('phone').value.trim();
    if (!name && !phone) {
        event.preventDefault();
        const alertDiv = document.querySelector('.alert');
        alertDiv.textContent = "Vui lòng nhập thông tin tìm kiếm!";
        alertDiv.style.display = 'block';
    }
});