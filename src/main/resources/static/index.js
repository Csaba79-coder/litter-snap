document.addEventListener('DOMContentLoaded', function() {
    var inputForm = document.getElementById('inputFormCity');
    var cityInput = document.querySelector('input[name="city"]');

    cityInput.addEventListener('keypress', function(event) {
        if (event.key === 'Enter') {
            event.preventDefault(); // Prevent form submission

            // Submit the form programmatically
            inputForm.submit();
        }
    });
});