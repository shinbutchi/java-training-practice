var userApp = angular.module("userApp", ['ngSanitize']);

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
  scope.error = true;
  data.fieldErrors.forEach(function(fieldError) {
    errorMessage+=fieldError.field;
    errorMessage+=':';
    errorMessage+=fieldError.message;
    errorMessage+="\n";
  });
  scope.error = true;
  scope.errorMessage = errorMessage;
}