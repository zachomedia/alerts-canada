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
   .controller('HomeController', ['$scope', '$window', '$location', function($scope, $window, $location) {
      $scope.locationsWithAlerts = [];

      $scope.map = {
         center: {
            latitude: 57,
            longitude:  -101
         },
         zoom: 3,

         colours: {
            warning: {
               fill: { color: '#aa0000', opacity: 0.5},
               stroke: { color: '#990000', opacity: 0.5}
            },
            watch: {
               fill: { color: '#aaaa00', opacity: 0.5},
               stroke: { color: '#999900', opacity: 0.5}
            },
            other: {
               fill: { color: '#aaa', opacity: 0.5},
               stroke: { color: '#999', opacity: 0.5}
            }
         },

         events: {
            click: function(polygon, eventName, polyMouseEvent) {
               var polygon = this.polygon;
               var scope = this.scope;
               scope.$apply(function() {
                  $location.path('/area/' + polygon.geocode).replace();
                  $window.scrollTo(0, 0);
               });
            }
         }
      };

      $scope.loadLocationsWithAlerts = function() {
         $scope.areasWithAlerts = [];

         for (var indx in $scope.alerts) {
            for (var iindx in $scope.alerts[indx].information) {
               if ($scope.alerts[indx].information[iindx].language != "en-CA") continue;

               for (var aindx in $scope.alerts[indx].information[iindx].areas) {
                  var found = null;

                  var area = $scope.alerts[indx].information[iindx].areas[aindx];
                  area.geocode = area.geocodes['layer:EC-MSC-SMC:1.0:CLC'];

                  var type = $scope.alerts[indx].information[iindx].parameters['layer:EC-MSC-SMC:1.0:Alert_Type'];

                  if (type == "warning") {
                     area.colours = $scope.map.colours.warning;
                  } else if (type == "watch") {
                     area.colours = $scope.map.colours.watch;
                  } else {
                     area.colours = $scope.map.colours.other;
                  }

                  area.events = {
                     click: _.bind($scope.map.events.click, { scope: $scope, polygon: area} )
                  };

                  for (var alindx in $scope.areasWithAlerts) {
                     if ($scope.areasWithAlerts[alindx].geocode == area.geocode) {
                        found = $scope.areasWithAlerts[alindx];
                        break;
                     }
                  }

                  if (found == null) {
                     $scope.areasWithAlerts.push(area);
                  } else {
                     var type = $scope.alerts[indx].information[iindx].parameters['layer:EC-MSC-SMC:1.0:Alert_Type'];

                     if (type == "warning") {
                        found.colours = $scope.map.colours.warning;
                     } else if (type == "watch" && found.colours != $scope.map.colours.warning) {
                        found.colours = $scope.map.colours.watch;
                     }
                  }
               }
            }
         }
      }

      $scope.loadLocationsWithAlerts();

      $scope.$on('alertsloaded', function() {
         $scope.loadLocationsWithAlerts();
      })
   }]);
