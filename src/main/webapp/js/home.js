function openOverlay() {
    document.getElementById('overlayForm').style.display = 'flex';
}

function closeOverlay() {
    document.getElementById('overlayForm').style.display = 'none';
    // Clear form and preview
    const form = document.querySelector('.form-box form');
    form.reset();
    const preview = document.getElementById('imagePreview');
    preview.src = '#';
    preview.style.display = 'none';
}

function previewImage(event) {
    const input = event.target;
    const preview = document.getElementById('imagePreview');
    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function (e) {
            preview.src = e.target.result;
            preview.style.display = 'block';
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        preview.style.display = 'none';
    }
}
