$(function () {
  // Auto hidden class alert after 3 seconds
  setTimeout(function () {
    var alerts = document.getElementsByClassName("alert");
    for (var i = 0; i < alerts.length; i++) {
      alerts[i].style.display = "none";
    }
  }, 3000);
  // Auto hide invalid-feedback after 3 seconds
  setTimeout(function () {
    var feedbacks = document.getElementsByClassName("invalid-feedback");
    for (var i = 0; i < feedbacks.length; i++) {
      feedbacks[i].style.display = "none";
    }
  }, 3000);

  // Auto remove class is-invalid after 5 seconds
  setTimeout(function () {
    var inputs = document.getElementsByClassName("is-invalid");
    for (var i = 0; i < inputs.length; i++) {
      inputs[i].classList.remove("is-invalid");
    }
  }, 3000);
});
