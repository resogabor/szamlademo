'use strict';

// Declare app level module which depends on views, and components
var myApp = angular.module('myApp', []);

myApp.service('SzamlaService', ['$http', '$q', function ($http, $q) {
    this.list = function () {
        var deferred = $q.defer();
        $http.get("/szamla/all").then(
            function succes(resp) {
                deferred.resolve(resp);
            },
            function error(err){
                deferred.reject(err);
        });
        return deferred.promise;
    };

    this.calculateSumm  = function(szamla){
        var sum = 0;
        for(var i in szamla.szamlaTetelek){
            if (!szamla.szamlaTetelek.hasOwnProperty(i)) continue;
            var obj = szamla.szamlaTetelek[i];
            sum = sum + obj.osszeg;
        }
        szamla.summ = sum;
        return szamla;
    };

    this.listTetelBySzamlaId = function(szlaID){
        var deferred = $q.defer();
        $http.get("/szamla_tetel/" + szlaID).then(
            function succes(resp) {
                deferred.resolve(resp);
            },
            function error(err){
                deferred.reject(err);
            });
        return deferred.promise;
    };



    this.szamlaMentes = function(szamla){
        var deferred = $q.defer();
        $http.post("/szamla/save/", szamla).then(
            function succes(resp) {
                deferred.resolve(resp);
            },
            function error(err){
                deferred.reject(err);
            });
        return deferred.promise;
    }

    this.szamlaTorles = function(szamlaId){
        var deferred = $q.defer();
        $http.delete("/szamla/delete/" + szamlaId).then(
            function succes(resp) {
                deferred.resolve(resp);
            },
            function error(err){
                deferred.reject(err);
            });
        return deferred.promise;

    }


}]);

myApp.service('szamlaTetelService', ['$http', '$q', function ($http, $q) {
    this.listSzamlaTetelAll = function () {
        var deferred = $q.defer();
        $http.get("/szamla/tetel/all").then(
            function succes(resp) {
                deferred.resolve(resp);
            },
            function error(err){
                deferred.reject(err);
            });
        return deferred.promise;
    };

    this.tetelMentes = function (tetel) {
        var deferred = $q.defer();
        $http.post("/szamla/tetel/save", tetel).then(
            function succes(resp) {
                deferred.resolve(resp);
            },
            function error(err){
                deferred.reject(err);
            });
        return deferred.promise;

    };

    this.tetelTorles = function (tetelId) {
        var deferred = $q.defer();
        $http.delete("/szamla/tetel/delete/" + tetelId).then(
            function succes(resp) {
                deferred.resolve(resp);
            },
            function error(err){
                deferred.reject(err);
            });
        return deferred.promise;

    }
}]);

myApp.filter('szamlaSumm', function () {
    return function (listOfTetel) {
        var summ = 0;
        angular.forEach(listOfTetel, function (tetel) {
            summ += tetel.osszeg;
        });
        return summ;
    }
})

myApp.controller('szamlaCtrl', ['$scope', function ($scope) {
    $scope.szamlaId;
}]);

myApp.controller('szamlaFejCtrl', ['$scope', 'SzamlaService' , function ($scope, SzamlaService) {
    $scope.load = function () {
        SzamlaService.list().then(
            function (data) {
                $scope.szamlak = data.data;
                if (typeof $scope.$parent.szamlaId == 'undefined'){
                    $scope.$parent.szamlaId = $scope.szamlak[0].id;
                }
            }
        )
    }
    $scope.szlaEdited = false;
    $scope.forEdit = function(){
        $scope.szlaEdited;
    }
    $scope.load();

    $scope.szamlaClick = function (szlaId) {
        $scope.$parent.szamlaId = szlaId;
        angular.forEach($scope.szamlak, function (sz) {
            if(sz.id == szlaId){
                $scope.szamla = sz;
            }
        });

    }
    $scope.newSzamla = function () {
        $scope.szamlak.push({id:-1});
        $scope.szamlaClick(-1);
        $scope.szlaEdited = true;
    }

    $scope.szamlaMentes = function (szamla) {
        SzamlaService.szamlaMentes(szamla).then(
            function (data) {
                $scope.$parent.szamlaId = data.data;
                $scope.load();
                $scope.szlaEdited = false;
            }
        );
    }
    $scope.szamlaTorles = function (szamlaId) {
        var deleteYes = $window.confirm('Biztos törli?');
        if(deleteYes) {
            SzamlaService.szamlaTorles(szamlaId).then(
                function (data) {
                    $scope.$parent.szamlaId = undefined;
                    $scope.load();
                }
            )
        }
    }

}]);

myApp.controller('szamlaEdit', ['$scope', 'SzamlaService', function ($scope, SzamlaService) {
    $scope.szamla = szamla;
}])

myApp.controller('szamlaTetelCtrl', ['$scope', '$window', 'szamlaTetelService', function ($scope, $window, szamlaTetelService) {

    $scope.szamlaTetelId;
    $scope.szlaTetelEdited = false;

    $scope.loadTetel = function() {
        szamlaTetelService.listSzamlaTetelAll().then(
            function (data) {
                $scope.tetelek = data.data;
                $scope.szlaTetelEdited = false;
                // angular.forEach($scope.tetelek, function (value, key) {
                //     if (key === $scope.$parent.$parent.szamlaId){
                //         $scope.szamlaTetelId = value[0].szamlaTetelId;
                //     }
                // })
            }
        )
    }
    $scope.loadTetel();
    $scope.tetelClick = function(tetelId){
        $scope.szamlaTetelId = tetelId;
        angular.forEach($scope.tetelek, function (t) {
            if(t.id == tetelId){
                $scope.tetel = t;
            }
        });
    }

    $scope.newTetel = function () {
        $scope.tetelek.push({id:-1, szamlaTetelId:$scope.$parent.szamlaId})
        $scope.tetelClick(-1);
        $scope.szlaTetelEdited = true;
    }

    $scope.tetelMentes = function(tetel){
        szamlaTetelService.tetelMentes(tetel).then(
            function (data) {
                $scope.loadTetel();
                $scope.szlaTetelEdited = false;
            }
        )
    }
    $scope.tetelTorles = function (tetelId) {
        var deleteYes = $window.confirm('Biztos törli?');
        if (deleteYes) {
            szamlaTetelService.tetelTorles(tetelId).then(
                function (data) {
                    $scope.szamlaTetelId = undefined;
                    $scope.loadTetel();
                }
            )
        }
    }
}]);

