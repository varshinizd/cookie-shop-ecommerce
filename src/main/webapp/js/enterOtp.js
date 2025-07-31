window.onload = function () {
    document.getElementById('otp1').focus();
};

function moveToNext(current, nextFieldID) {
    if (current.value.length === 1) {
        const nextField = document.getElementById(nextFieldID);
        if (nextField) {
            nextField.focus();
        }
    }
}

function gatherOtpAndSubmit(event) {
    event.preventDefault();

    let otp = '';
    for (let i = 1; i <= 6; i++) {
        const value = document.getElementById(`otp${i}`).value;
        if (!value) {
            alert("Please enter all 6 digits of the OTP.");
            return;
        }
        otp += value;
    }

    // Create a hidden input to store combined OTP
    const hiddenInput = document.createElement('input');
    hiddenInput.type = 'hidden';
    hiddenInput.name = 'otp';
    hiddenInput.value = otp;

    const form = document.getElementById('otpForm');
    form.appendChild(hiddenInput);
    form.submit();
}

function handlePaste(event) {
    event.preventDefault();
    const pasteData = (event.clipboardData || window.clipboardData).getData('text');
    const digits = pasteData.replace(/\D/g, '').substring(0, 6); // Only digits

    for (let i = 0; i < digits.length; i++) {
        const input = document.getElementById(`otp${i + 1}`);
        if (input) {
            input.value = digits[i];
        }
    }

    // Move focus to the next empty box
    for (let i = 1; i <= 6; i++) {
        const input = document.getElementById(`otp${i}`);
        if (input.value === '') {
            input.focus();
            break;
        }
    }
}
