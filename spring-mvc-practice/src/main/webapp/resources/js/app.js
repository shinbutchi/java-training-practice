var userApp = angular.module("userApp", ['ngSanitize','ui.bootstrap']);

function resetError(scope) {
  scope.success = false;
  scope.error = false;
  scope.errorMessage = '';
  scope.errors = {};
  scope.successMessage = '';
}

function setSuccess(scope, data) {
  resetError(scope);
  scope.success = true;
  scope.successMessage = data.messageInfo;
}

function isErrorMessage(data) {
  return data.message == "error";
}

function setErrorMessage(scope, data) {
  var errorMessage = '';
  resetError(scope);
  errorMessage=data.messageInfo;
  scope.error = true;
  scope.errorMessage = errorMessage;
}

function setFieldErrorMessage(scope, data) {
  var errorMessage = '';
  resetError(scope);
  scope.error = true;
  data.fieldErrors.forEach(function(fieldError) {
    if(fieldError.field != null) {
      errorMessage+=fieldError.field;
      errorMessage+=': ';
    }
    errorMessage+=fieldError.message;
    errorMessage+="<br>";
  });
  scope.error = true;
  scope.errorMessage = errorMessage;
}