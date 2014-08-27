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
   .controller('AreaController', ['$scope', '$window', '$routeParams', '$location', function($scope, $window, $routeParams, $location) {
      $scope.area = null;
      $scope.areaAlerts = [];

      $scope.loadAlerts = function(geocode) {
         $scope.area = null;
         $scope.areaAlerts = [];
         $window.scrollTo(0, 0);

         for (var indx in $scope.alerts) {
            for (var iindx in $scope.alerts[indx].information) {
               if ($scope.alerts[indx].information[iindx].language != "en-CA") continue;

               for (var aindx in $scope.alerts[indx].information[iindx].areas) {
                  if ($scope.alerts[indx].information[iindx].areas[aindx].geocodes['layer:EC-MSC-SMC:1.0:CLC'] == $routeParams.geocode) {
                     $scope.areaAlerts.push($scope.alerts[indx].information[iindx]);

                     if ($scope.area == null) {
                        $scope.area = $scope.alerts[indx].information[iindx].areas[aindx];
                        $scope.area.geocode = $scope.area.geocodes['layer:EC-MSC-SMC:1.0:CLC'];
                     }
                  }
               }
            }
         }
      }; // End of loadAlerts

      $scope.loadAlerts($routeParams.geocode);

      $scope.$on('alertsloaded', function() {
         $scope.loadAlerts($routeParams.geocode);
      });
   }]);
