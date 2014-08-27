/*
   Copyright (c) 2014 Zachary Seguin

   Permission is hereby granted, free of charge, to any person obtaining a copy
   of this software and associated documentation files (the "Software"), to deal
   in the Software without restriction, including without limitation the rights
   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
   copies of the Software, and to permit persons to whom the Software is
   furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in
   all copies or substantial portions of the Software.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
   THE SOFTWARE.
*/

'use strict';

angular
   .module('Alerts')
   .controller('AlertController', ['$scope', '$routeParams', '$location', function($scope, $routeParams, $location) {
      $scope.alert = null;

      $scope.loadAlert = function(alertId) {
         for (var indx in $scope.alerts) {
            var alert = $scope.alerts[indx];

            // check for alert with id
            if (alert.identifier == $routeParams.identifier) {
               $scope.alert = alert;
               break;
            }

            // check for reference
            for (var rindx in alert.references) {
               if (alert.references[rindx].identifier == $routeParams.identifier) {
                  $location.path('/alert/' + alert.identifier).replace();
                  break;
               }
            }
         }
      }; // End of loadAlert

      $scope.loadAlert($routeParams.identifier);

      console.log($scope);
      $scope.$on('alertsloaded', function() {
         $scope.loadAlert($routeParams.identifier);
      });
   }]);
