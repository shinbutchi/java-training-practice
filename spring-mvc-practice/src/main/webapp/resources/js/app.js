var userApp = angular.module("userApp", []);

function resetError(scope) {
  scope.success = false;
  scope.error = false;
  scope.errorMessage = '';
  scope.errors = {};
  scope.successMessag = '';
};

function setError(scope, data) {
  resetError(scope);
  scope.error = true;
  scope.errorMessage = data.messageInfo;
  scope.errors = data.detailErrors;

};

function setSuccess(scope, data) {
  resetError(scope);
  scope.success = true;
  scope.successMessage = data.messageInfo;
};